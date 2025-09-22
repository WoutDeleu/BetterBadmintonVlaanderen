package be.badmintonvlaanderen.backend.architecture.base;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

/**
 * Base class for hexagonal architecture tests.
 * Can be extended by any project to test hexagonal architecture compliance.
 *
 * Usage:
 * 1. Extend this class in your project
 * 2. Add @AnalyzeClasses annotation with your base package
 * 3. Optionally override getApplicationRootPackage() to specify your base package
 */
public abstract class BaseHexagonalArchitectureTest {

    /**
     * Override this method to specify the root package of your application.
     * Default implementation returns "..".
     */
    protected String getApplicationRootPackage() {
        return "..";
    }

    /**
     * Override this method to specify additional allowed packages for domain layer.
     * Default includes standard Java and validation packages.
     */
    protected String[] getAllowedDomainDependencies() {
        return new String[]{
            "java..",
            "javax.validation..",
            "jakarta.validation..",
            "org.slf4j.."
        };
    }

    /**
     * Override this method to specify additional allowed packages for infrastructure layer.
     */
    protected String[] getAllowedInfrastructureDependencies() {
        return new String[]{
            "java..",
            "javax..",
            "jakarta..",
            "org.springframework..",
            "org.slf4j..",
            "com.fasterxml.jackson..",
            "org.hibernate..",
            "org.mapstruct.."
        };
    }

    public ArchRule hexagonal_architecture_is_respected() {
        String rootPackage = getApplicationRootPackage();
        return onionArchitecture()
            .domainModels(rootPackage + "domain.model..")
            .domainServices(rootPackage + "domain.service..")
            .applicationServices(rootPackage + "application.service..")
            .adapter("infrastructure", rootPackage + "infrastructure..");
    }

    public ArchRule domain_should_not_depend_on_application() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().dependOnClassesThat().resideInAPackage(rootPackage + "application..");
    }

    public ArchRule domain_should_not_depend_on_infrastructure() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().dependOnClassesThat().resideInAPackage(rootPackage + "infrastructure..");
    }

    public ArchRule application_should_not_depend_on_infrastructure() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "application..")
            .should().dependOnClassesThat().resideInAPackage(rootPackage + "infrastructure..");
    }

    public ArchRule infrastructure_can_depend_on_application_and_domain() {
        String rootPackage = getApplicationRootPackage();
        String[] allowedDependencies = getAllowedInfrastructureDependencies();
        String[] allAllowed = new String[allowedDependencies.length + 3];
        allAllowed[0] = rootPackage + "infrastructure..";
        allAllowed[1] = rootPackage + "application..";
        allAllowed[2] = rootPackage + "domain..";
        System.arraycopy(allowedDependencies, 0, allAllowed, 3, allowedDependencies.length);

        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(allAllowed);
    }

    public ArchRule ports_should_be_interfaces() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "application.port..")
            .should().beInterfaces()
            .andShould().notBeEnums();
    }

    public ArchRule ports_should_not_depend_on_infrastructure() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "application.port..")
            .should().dependOnClassesThat().resideInAPackage(rootPackage + "infrastructure..");
    }

    public ArchRule domain_models_should_not_have_setters() {
        String rootPackage = getApplicationRootPackage();
        return noMethods()
            .that().areDeclaredInClassesThat().resideInAPackage(rootPackage + "domain.model..")
            .and().areNotConstructors()
            .should().haveNameMatching("set.*");
    }

    public ArchRule domain_models_should_be_immutable() {
        String rootPackage = getApplicationRootPackage();
        return fields()
            .that().areDeclaredInClassesThat().resideInAPackage(rootPackage + "domain.model..")
            .should().beFinal()
            .orShould().beStatic();
    }

    public ArchRule domain_should_only_depend_on_allowed_packages() {
        String rootPackage = getApplicationRootPackage();
        String[] allowedDependencies = getAllowedDomainDependencies();
        String[] allAllowed = new String[allowedDependencies.length + 1];
        allAllowed[0] = rootPackage + "domain..";
        System.arraycopy(allowedDependencies, 0, allAllowed, 1, allowedDependencies.length);

        return classes()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(allAllowed);
    }
}