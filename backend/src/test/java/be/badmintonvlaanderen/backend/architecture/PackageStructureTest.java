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
public class PackageStructureTest {

    @ArchTest
    static final ArchRule all_classes_should_be_in_correct_packages =
        classes()
            .should().resideInAnyPackage(
                "be.badmintonvlaanderen.backend.domain.model..",
                "be.badmintonvlaanderen.backend.domain.service..",
                "be.badmintonvlaanderen.backend.application.port..",
                "be.badmintonvlaanderen.backend.application.service..",
                "be.badmintonvlaanderen.backend.infrastructure.adapter..",
                "be.badmintonvlaanderen.backend.infrastructure.config..",
                "be.badmintonvlaanderen.backend"
            );

    @ArchTest
    static final ArchRule domain_model_package_should_only_contain_domain_objects =
        classes()
            .that().resideInAPackage("..domain.model..")
            .should().notBeAnnotatedWith("org.springframework.stereotype.Component")
            .andShould().notBeAnnotatedWith("org.springframework.stereotype.Service")
            .andShould().notBeAnnotatedWith("org.springframework.stereotype.Repository")
            .andShould().notBeAnnotatedWith("jakarta.persistence.Entity");

    @ArchTest
    static final ArchRule application_port_package_should_only_contain_interfaces =
        classes()
            .that().resideInAPackage("..application.port..")
            .should().beInterfaces();

    @ArchTest
    static final ArchRule infrastructure_adapter_package_should_contain_implementations =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .should().notBeInterfaces()
            .orShould().beAnnotatedWith("org.springframework.data.jpa.repository.JpaRepository");

    @ArchTest
    static final ArchRule infrastructure_config_package_should_contain_configurations =
        classes()
            .that().resideInAPackage("..infrastructure.config..")
            .should().beAnnotatedWith("org.springframework.context.annotation.Configuration");

    @ArchTest
    static final ArchRule no_empty_packages_should_exist =
        classes()
            .that().resideInAPackage("be.badmintonvlaanderen.backend..")
            .should().resideInAnyPackage(
                "..domain.model..",
                "..domain.service..",
                "..application.port..",
                "..application.service..",
                "..infrastructure.adapter..",
                "..infrastructure.config..",
                "be.badmintonvlaanderen.backend"
            );

    @ArchTest
    static final ArchRule package_dependency_should_follow_hexagonal_rules =
        classes()
            .that().resideInAPackage("..domain..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                "..domain..",
                "java..",
                "javax..",
                "jakarta.validation..",
                "org.slf4j.."
            );

    @ArchTest
    static final ArchRule utilities_should_be_in_appropriate_packages =
        classes()
            .that().haveSimpleNameContaining("Util")
            .or().haveSimpleNameContaining("Helper")
            .or().haveSimpleNameContaining("Constants")
            .should().resideInAnyPackage(
                "..domain..",
                "..infrastructure.config.."
            );
}