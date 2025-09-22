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
public class NamingConventionTest {

    @ArchTest
    static final ArchRule domain_services_should_be_suffixed =
        classes()
            .that().resideInAPackage("..domain.service..")
            .should().haveSimpleNameEndingWith("DomainService")
            .orShould().haveSimpleNameEndingWith("Service");

    @ArchTest
    static final ArchRule application_services_should_be_suffixed =
        classes()
            .that().resideInAPackage("..application.service..")
            .should().haveSimpleNameEndingWith("ApplicationService")
            .orShould().haveSimpleNameEndingWith("Service")
            .orShould().haveSimpleNameEndingWith("UseCase");

    @ArchTest
    static final ArchRule ports_should_be_suffixed =
        classes()
            .that().resideInAPackage("..application.port..")
            .should().haveSimpleNameEndingWith("Port")
            .orShould().haveSimpleNameEndingWith("Repository")
            .orShould().haveSimpleNameEndingWith("Gateway")
            .orShould().haveSimpleNameEndingWith("Service");

    @ArchTest
    static final ArchRule controllers_should_be_suffixed =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
            .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static final ArchRule repositories_should_be_suffixed =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().areAnnotatedWith("org.springframework.stereotype.Repository")
            .should().haveSimpleNameEndingWith("Repository")
            .orShould().haveSimpleNameEndingWith("Adapter");

    @ArchTest
    static final ArchRule domain_models_should_not_be_suffixed_with_entity =
        classes()
            .that().resideInAPackage("..domain.model..")
            .should().notHaveSimpleNameEndingWith("Entity");

    @ArchTest
    static final ArchRule infrastructure_entities_should_be_suffixed =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().areAnnotatedWith("jakarta.persistence.Entity")
            .should().haveSimpleNameEndingWith("Entity")
            .orShould().haveSimpleNameEndingWith("JpaEntity");

    @ArchTest
    static final ArchRule configuration_classes_should_be_suffixed =
        classes()
            .that().resideInAPackage("..infrastructure.config..")
            .should().haveSimpleNameEndingWith("Configuration")
            .orShould().haveSimpleNameEndingWith("Config");

    @ArchTest
    static final ArchRule dto_classes_should_be_suffixed =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().haveSimpleNameContaining("Request")
            .or().haveSimpleNameContaining("Response")
            .or().haveSimpleNameContaining("Dto")
            .should().haveSimpleNameEndingWith("Request")
            .orShould().haveSimpleNameEndingWith("Response")
            .orShould().haveSimpleNameEndingWith("Dto");
}