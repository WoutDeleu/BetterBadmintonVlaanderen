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
public class SecurityTest {

    @ArchTest
    static final ArchRule no_classes_should_use_java_util_logging =
        noClasses()
            .should().accessClassesThat().resideInAPackage("java.util.logging..");

    @ArchTest
    static final ArchRule no_classes_should_use_system_out_or_err =
        noClasses()
            .should().accessClassesThat().belongToAnyOf(System.class)
            .andShould().callMethodWhere(target ->
                target.getName().equals("out") ||
                target.getName().equals("err"));

    @ArchTest
    static final ArchRule no_classes_should_throw_generic_exceptions =
        noClasses()
            .should().throwExceptionOfType(Exception.class)
            .orShould().throwExceptionOfType(RuntimeException.class)
            .orShould().throwExceptionOfType(Throwable.class);

    @ArchTest
    static final ArchRule controllers_should_not_directly_access_repositories =
        noClasses()
            .that().resideInAPackage("..infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("Controller")
            .should().dependOnClassesThat().haveSimpleNameEndingWith("Repository")
            .andShould().dependOnClassesThat().haveSimpleNameEndingWith("JpaRepository");

    @ArchTest
    static final ArchRule domain_should_not_access_spring_web =
        noClasses()
            .that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("org.springframework.web..");

    @ArchTest
    static final ArchRule application_should_not_access_spring_web =
        noClasses()
            .that().resideInAPackage("..application..")
            .should().accessClassesThat().resideInAPackage("org.springframework.web..");

    @ArchTest
    static final ArchRule domain_should_not_access_http_classes =
        noClasses()
            .that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAnyPackage(
                "javax.servlet..",
                "jakarta.servlet..",
                "org.springframework.http.."
            );

    @ArchTest
    static final ArchRule application_should_not_access_http_classes =
        noClasses()
            .that().resideInAPackage("..application..")
            .should().accessClassesThat().resideInAnyPackage(
                "javax.servlet..",
                "jakarta.servlet..",
                "org.springframework.http.."
            );

    @ArchTest
    static final ArchRule no_classes_should_access_deprecated_apis =
        noClasses()
            .should().accessClassesThat().areAnnotatedWith(Deprecated.class);

    @ArchTest
    static final ArchRule security_configurations_should_be_in_config_package =
        classes()
            .that().haveSimpleNameContaining("Security")
            .should().resideInAPackage("..infrastructure.config..");
}