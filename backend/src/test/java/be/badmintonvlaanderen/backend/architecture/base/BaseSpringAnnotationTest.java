package be.badmintonvlaanderen.backend.architecture.base;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Base class for Spring annotation tests.
 * Can be extended by any project to test Spring annotation compliance.
 */
public abstract class BaseSpringAnnotationTest {

    protected String getApplicationRootPackage() {
        return "..";
    }

    protected String[] getSpringStereotypeAnnotations() {
        return new String[]{
            "org.springframework.stereotype.Component",
            "org.springframework.stereotype.Service",
            "org.springframework.stereotype.Repository",
            "org.springframework.web.bind.annotation.RestController",
            "org.springframework.beans.factory.annotation.Autowired",
            "org.springframework.context.annotation.Configuration"
        };
    }

    protected String[] getSpringWebAnnotations() {
        return new String[]{
            "org.springframework.web.."
        };
    }

    protected String[] getHttpAnnotations() {
        return new String[]{
            "javax.servlet..",
            "jakarta.servlet..",
            "org.springframework.http.."
        };
    }

    public ArchRule domain_layer_should_not_use_spring_annotations() {
        String rootPackage = getApplicationRootPackage();
        String[] annotations = getSpringStereotypeAnnotations();

        ArchRule rule = noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().beAnnotatedWith(annotations[0]);

        for (int i = 1; i < annotations.length; i++) {
            rule = rule.orShould().beAnnotatedWith(annotations[i]);
        }

        return rule;
    }

    public ArchRule application_services_should_be_annotated_with_service() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "application.service..")
            .should().beAnnotatedWith("org.springframework.stereotype.Service");
    }

    public ArchRule controllers_should_be_annotated_with_rest_controller() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("Controller")
            .should().beAnnotatedWith("org.springframework.web.bind.annotation.RestController");
    }

    public ArchRule repository_implementations_should_be_annotated_with_component() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("RepositoryImpl")
            .should().beAnnotatedWith("org.springframework.stereotype.Component")
            .orShould().beAnnotatedWith("org.springframework.stereotype.Repository");
    }

    public ArchRule configuration_classes_should_be_annotated_with_configuration() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.config..")
            .should().beAnnotatedWith("org.springframework.context.annotation.Configuration");
    }

    public ArchRule jpa_entities_should_be_annotated_with_entity() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("Entity")
            .should().beAnnotatedWith("jakarta.persistence.Entity");
    }

    public ArchRule jpa_repositories_should_extend_jpa_repository() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("JpaRepository")
            .should().beAssignableTo("org.springframework.data.jpa.repository.JpaRepository");
    }

    public ArchRule no_field_injection_should_be_used() {
        return noFields()
            .should().beAnnotatedWith("org.springframework.beans.factory.annotation.Autowired");
    }

    public ArchRule constructor_injection_should_be_preferred() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "application.service..")
            .or().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .should().haveOnlyFinalFields()
            .orShould().haveOnlyStaticFields();
    }

    public ArchRule domain_should_not_access_spring_web() {
        String rootPackage = getApplicationRootPackage();
        String[] webAnnotations = getSpringWebAnnotations();

        ArchRule rule = noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().accessClassesThat().resideInAPackage(webAnnotations[0]);

        for (int i = 1; i < webAnnotations.length; i++) {
            rule = rule.and().should().accessClassesThat().resideInAPackage(webAnnotations[i]);
        }

        return rule;
    }

    public ArchRule application_should_not_access_spring_web() {
        String rootPackage = getApplicationRootPackage();
        String[] webAnnotations = getSpringWebAnnotations();

        ArchRule rule = noClasses()
            .that().resideInAPackage(rootPackage + "application..")
            .should().accessClassesThat().resideInAPackage(webAnnotations[0]);

        for (int i = 1; i < webAnnotations.length; i++) {
            rule = rule.and().should().accessClassesThat().resideInAPackage(webAnnotations[i]);
        }

        return rule;
    }

    public ArchRule domain_should_not_access_http_classes() {
        String rootPackage = getApplicationRootPackage();
        String[] httpAnnotations = getHttpAnnotations();

        return noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().accessClassesThat().resideInAnyPackage(httpAnnotations);
    }

    public ArchRule application_should_not_access_http_classes() {
        String rootPackage = getApplicationRootPackage();
        String[] httpAnnotations = getHttpAnnotations();

        return noClasses()
            .that().resideInAPackage(rootPackage + "application..")
            .should().accessClassesThat().resideInAnyPackage(httpAnnotations);
    }
}