package be.badmintonvlaanderen.backend.architecture;

import be.badmintonvlaanderen.backend.architecture.base.BaseHexagonalArchitectureTest;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
    packages = "be.badmintonvlaanderen.backend",
    importOptions = ImportOption.DoNotIncludeTests.class
)
public class HexagonalArchitectureTest extends BaseHexagonalArchitectureTest {

    @Override
    protected String getApplicationRootPackage() {
        return "be.badmintonvlaanderen.backend..";
    }

    @ArchTest
    static final ArchRule hexagonal_architecture_is_respected = new HexagonalArchitectureTest().hexagonal_architecture_is_respected();

    @ArchTest
    static final ArchRule domain_should_not_depend_on_application = new HexagonalArchitectureTest().domain_should_not_depend_on_application();

    @ArchTest
    static final ArchRule domain_should_not_depend_on_infrastructure = new HexagonalArchitectureTest().domain_should_not_depend_on_infrastructure();

    @ArchTest
    static final ArchRule application_should_not_depend_on_infrastructure = new HexagonalArchitectureTest().application_should_not_depend_on_infrastructure();

    @ArchTest
    static final ArchRule infrastructure_can_depend_on_application_and_domain = new HexagonalArchitectureTest().infrastructure_can_depend_on_application_and_domain();

    @ArchTest
    static final ArchRule ports_should_be_interfaces = new HexagonalArchitectureTest().ports_should_be_interfaces();

    @ArchTest
    static final ArchRule ports_should_not_depend_on_infrastructure = new HexagonalArchitectureTest().ports_should_not_depend_on_infrastructure();

    @ArchTest
    static final ArchRule domain_models_should_not_have_setters = new HexagonalArchitectureTest().domain_models_should_not_have_setters();

    @ArchTest
    static final ArchRule domain_models_should_be_immutable = new HexagonalArchitectureTest().domain_models_should_be_immutable();

    @ArchTest
    static final ArchRule domain_should_only_depend_on_allowed_packages = new HexagonalArchitectureTest().domain_should_only_depend_on_allowed_packages();
}