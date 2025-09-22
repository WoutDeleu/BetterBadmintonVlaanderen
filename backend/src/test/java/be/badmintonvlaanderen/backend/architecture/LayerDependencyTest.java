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
public class LayerDependencyTest {

    @ArchTest
    static final ArchRule domain_layer_should_not_access_application_layer =
        noClasses()
            .that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("..application..");

    @ArchTest
    static final ArchRule domain_layer_should_not_access_infrastructure_layer =
        noClasses()
            .that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("..infrastructure..");

    @ArchTest
    static final ArchRule application_layer_should_not_access_infrastructure_layer =
        noClasses()
            .that().resideInAPackage("..application..")
            .should().accessClassesThat().resideInAPackage("..infrastructure..");

    @ArchTest
    static final ArchRule domain_layer_should_only_be_accessed_by_application_and_infrastructure =
        classes()
            .that().resideInAPackage("..domain..")
            .should().onlyBeAccessed().byClassesThat().resideInAnyPackage(
                "..domain..",
                "..application..",
                "..infrastructure.."
            );

    @ArchTest
    static final ArchRule application_ports_should_only_be_accessed_by_infrastructure_and_application =
        classes()
            .that().resideInAPackage("..application.port..")
            .should().onlyBeAccessed().byClassesThat().resideInAnyPackage(
                "..application..",
                "..infrastructure.."
            );

    @ArchTest
    static final ArchRule infrastructure_adapters_should_not_be_accessed_by_domain_or_application =
        classes()
            .that().resideInAPackage("..infrastructure.adapter..")
            .should().onlyBeAccessed().byClassesThat().resideInAnyPackage(
                "..infrastructure..",
                "..BetterBadmintonApplication"
            );
}