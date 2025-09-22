package be.badmintonvlaanderen.backend.architecture.base;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Base class for security-related architecture tests.
 * Can be extended by any project to test security compliance.
 */
public abstract class BaseSecurityTest {

    protected String getApplicationRootPackage() {
        return "..";
    }

    protected String[] getForbiddenLoggingPackages() {
        return new String[]{"java.util.logging.."};
    }

    protected String[] getForbiddenExceptionTypes() {
        return new String[]{
            Exception.class.getName(),
            RuntimeException.class.getName(),
            Throwable.class.getName()
        };
    }

    protected String[] getDeprecatedPackages() {
        return new String[]{};
    }

    public ArchRule no_classes_should_use_java_util_logging() {
        String[] forbidden = getForbiddenLoggingPackages();
        return noClasses()
            .should().accessClassesThat().resideInAnyPackage(forbidden);
    }

    public ArchRule no_classes_should_use_system_out_or_err() {
        return noClasses()
            .should().accessClassesThat().belongToAnyOf(System.class)
            .andShould().callMethodWhere(target ->
                target.getName().equals("out") ||
                target.getName().equals("err"));
    }

    public ArchRule no_classes_should_throw_generic_exceptions() {
        String[] forbidden = getForbiddenExceptionTypes();

        ArchRule rule = noClasses()
            .should().throwExceptionOfType(Exception.class);

        for (String exceptionType : forbidden) {
            try {
                Class<?> clazz = Class.forName(exceptionType);
                rule = rule.orShould().throwExceptionOfType(clazz);
            } catch (ClassNotFoundException e) {
                // Skip if class not found
            }
        }

        return rule;
    }

    public ArchRule controllers_should_not_directly_access_repositories() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().haveSimpleNameEndingWith("Controller")
            .should().dependOnClassesThat().haveSimpleNameEndingWith("Repository")
            .andShould().dependOnClassesThat().haveSimpleNameEndingWith("JpaRepository");
    }

    public ArchRule domain_should_not_access_spring_web() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().accessClassesThat().resideInAPackage("org.springframework.web..");
    }

    public ArchRule application_should_not_access_spring_web() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "application..")
            .should().accessClassesThat().resideInAPackage("org.springframework.web..");
    }

    public ArchRule domain_should_not_access_http_classes() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().accessClassesThat().resideInAnyPackage(
                "javax.servlet..",
                "jakarta.servlet..",
                "org.springframework.http.."
            );
    }

    public ArchRule application_should_not_access_http_classes() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "application..")
            .should().accessClassesThat().resideInAnyPackage(
                "javax.servlet..",
                "jakarta.servlet..",
                "org.springframework.http.."
            );
    }

    public ArchRule no_classes_should_access_deprecated_apis() {
        return noClasses()
            .should().accessClassesThat().areAnnotatedWith(Deprecated.class);
    }

    public ArchRule security_configurations_should_be_in_config_package() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().haveSimpleNameContaining("Security")
            .should().resideInAPackage(rootPackage + "infrastructure.config..");
    }

    public ArchRule no_hardcoded_passwords_in_source() {
        return noClasses()
            .should().containCodeUnitWithParameterOfType(String.class)
            .andShould().containCodeUnitWithFullNameMatching(".*[Pp]assword.*");
    }

    public ArchRule sensitive_data_should_not_be_logged() {
        return noMethods()
            .that().haveNameMatching(".*[Ll]og.*")
            .should().haveParameterWithNameMatching(".*[Pp]assword.*")
            .orShould().haveParameterWithNameMatching(".*[Tt]oken.*")
            .orShould().haveParameterWithNameMatching(".*[Kk]ey.*");
    }
}