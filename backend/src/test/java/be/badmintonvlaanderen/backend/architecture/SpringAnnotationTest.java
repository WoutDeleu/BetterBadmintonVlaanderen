package be.badmintonvlaanderen.backend.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(
    packages = "be.badmintonvlaanderen.backend",
    importOptions = ImportOption.DoNotIncludeTests.class
)
public class SpringAnnotationTest {

    @ArchTest
    static final ArchRule domain_layer_should_not_use_spring_annotations =
        noClasses()
            .that().resideInAPackage("..domain..")
            .should().beAnnotatedWith("org.springframework.stereotype.Component")
            .orShould().beAnnotatedWith("org.springframework.stereotype.Service")
            .orShould().beAnnotatedWith("org.springframework.stereotype.Repository")
            .orShould().beAnnotatedWith("org.springframework.web.bind.annotation.RestController")
            .orShould().beAnnotatedWith("org.springframework.beans.factory.annotation.Autowired")
            .orShould().beAnnotatedWith("org.springframework.context.annotation.Configuration");

    @ArchTest
    static final ArchRule application_services_should_be_annotated_with_service =
        classes()
            .that().resideInAPackage("..application.service..")
            .should().beAnnotatedWith("org.springframework.stereotype.Service");

    @ArchTest
    static final ArchRule controllers_should_be_annotated_with_rest_controller =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("Controller")
            .should().beAnnotatedWith("org.springframework.web.bind.annotation.RestController");

    @ArchTest
    static final ArchRule repository_implementations_should_be_annotated_with_component =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("RepositoryImpl")
            .should().beAnnotatedWith("org.springframework.stereotype.Component")
            .orShould().beAnnotatedWith("org.springframework.stereotype.Repository");

    @ArchTest
    static final ArchRule configuration_classes_should_be_annotated_with_configuration =
        classes()
            .that().resideInAPackage("..infrastructure.config..")
            .should().beAnnotatedWith("org.springframework.context.annotation.Configuration");

    @ArchTest
    static final ArchRule jpa_entities_should_be_annotated_with_entity =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("Entity")
            .should().beAnnotatedWith("jakarta.persistence.Entity");

    @ArchTest
    static final ArchRule jpa_repositories_should_extend_jpa_repository =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("JpaRepository")
            .should().beAssignableTo("org.springframework.data.jpa.repository.JpaRepository");

    @ArchTest
    static final ArchRule no_field_injection_should_be_used =
        noFields()
            .should().beAnnotatedWith("org.springframework.beans.factory.annotation.Autowired");

    @ArchTest
    static final ArchRule constructor_injection_should_be_preferred =
        classes()
            .that().resideInAPackage("..application.service..")
            .or().resideInAPackage("..infrastructure.adapter..")
            .should().haveOnlyFinalFields()
            .orShould().haveOnlyStaticFields();
}