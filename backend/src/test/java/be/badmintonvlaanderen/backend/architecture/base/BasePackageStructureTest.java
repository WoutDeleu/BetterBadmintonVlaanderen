package be.badmintonvlaanderen.backend.architecture.base;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Base class for package structure tests.
 * Can be extended by any project to test package structure compliance.
 */
public abstract class BasePackageStructureTest {

    protected String getApplicationRootPackage() {
        return "..";
    }

    protected String[] getAllowedPackages() {
        String rootPackage = getApplicationRootPackage();
        return new String[]{
            rootPackage + "domain.model..",
            rootPackage + "domain.service..",
            rootPackage + "application.port..",
            rootPackage + "application.service..",
            rootPackage + "infrastructure.adapter..",
            rootPackage + "infrastructure.config..",
            getMainApplicationPackage()
        };
    }

    protected String getMainApplicationPackage() {
        String rootPackage = getApplicationRootPackage();
        return rootPackage.replace("..", "").isEmpty() ?
            "be.badmintonvlaanderen.backend" :
            rootPackage.replace("..", "");
    }

    protected String[] getForbiddenAnnotationsForDomainModel() {
        return new String[]{
            "org.springframework.stereotype.Component",
            "org.springframework.stereotype.Service",
            "org.springframework.stereotype.Repository",
            "jakarta.persistence.Entity"
        };
    }

    protected String[] getAllowedDomainDependencies() {
        String rootPackage = getApplicationRootPackage();
        return new String[]{
            rootPackage + "domain..",
            "java..",
            "javax..",
            "jakarta.validation..",
            "org.slf4j.."
        };
    }

    protected String[] getAllowedUtilityPackages() {
        String rootPackage = getApplicationRootPackage();
        return new String[]{
            rootPackage + "domain..",
            rootPackage + "infrastructure.config.."
        };
    }

    public ArchRule all_classes_should_be_in_correct_packages() {
        return classes()
            .should().resideInAnyPackage(getAllowedPackages());
    }

    public ArchRule domain_model_package_should_only_contain_domain_objects() {
        String rootPackage = getApplicationRootPackage();
        String[] forbiddenAnnotations = getForbiddenAnnotationsForDomainModel();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "domain.model..")
            .should().notBeAnnotatedWith(forbiddenAnnotations[0]);

        for (int i = 1; i < forbiddenAnnotations.length; i++) {
            rule = rule.andShould().notBeAnnotatedWith(forbiddenAnnotations[i]);
        }

        return rule;
    }

    public ArchRule application_port_package_should_only_contain_interfaces() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "application.port..")
            .should().beInterfaces();
    }

    public ArchRule infrastructure_adapter_package_should_contain_implementations() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .should().notBeInterfaces()
            .orShould().beAnnotatedWith("org.springframework.data.jpa.repository.JpaRepository");
    }

    public ArchRule infrastructure_config_package_should_contain_configurations() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.config..")
            .should().beAnnotatedWith("org.springframework.context.annotation.Configuration");
    }

    public ArchRule no_empty_packages_should_exist() {
        return classes()
            .that().resideInAPackage(getApplicationRootPackage())
            .should().resideInAnyPackage(getAllowedPackages());
    }

    public ArchRule package_dependency_should_follow_hexagonal_rules() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(getAllowedDomainDependencies());
    }

    public ArchRule utilities_should_be_in_appropriate_packages() {
        return classes()
            .that().haveSimpleNameContaining("Util")
            .or().haveSimpleNameContaining("Helper")
            .or().haveSimpleNameContaining("Constants")
            .should().resideInAnyPackage(getAllowedUtilityPackages());
    }

    public ArchRule test_classes_should_be_in_test_packages() {
        return classes()
            .that().haveSimpleNameEndingWith("Test")
            .or().haveSimpleNameEndingWith("Tests")
            .or().haveSimpleNameContaining("Test")
            .should().resideInAPackage("..test..");
    }

    public ArchRule integration_tests_should_be_separated() {
        return classes()
            .that().haveSimpleNameContaining("Integration")
            .or().haveSimpleNameEndingWith("IT")
            .should().resideInAPackage("..integration..");
    }
}