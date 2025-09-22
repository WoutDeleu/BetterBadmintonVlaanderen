package be.badmintonvlaanderen.backend.architecture.base;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Base class for layer dependency tests.
 * Can be extended by any project to test layer dependency rules.
 */
public abstract class BaseLayerDependencyTest {

    protected String getApplicationRootPackage() {
        return "..";
    }

    protected String[] getApplicationMainPackages() {
        String rootPackage = getApplicationRootPackage();
        return new String[]{
            rootPackage + "domain..",
            rootPackage + "application..",
            rootPackage + "infrastructure.."
        };
    }

    public ArchRule domain_layer_should_not_access_application_layer() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().accessClassesThat().resideInAPackage(rootPackage + "application..");
    }

    public ArchRule domain_layer_should_not_access_infrastructure_layer() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().accessClassesThat().resideInAPackage(rootPackage + "infrastructure..");
    }

    public ArchRule application_layer_should_not_access_infrastructure_layer() {
        String rootPackage = getApplicationRootPackage();
        return noClasses()
            .that().resideInAPackage(rootPackage + "application..")
            .should().accessClassesThat().resideInAPackage(rootPackage + "infrastructure..");
    }

    public ArchRule domain_layer_should_only_be_accessed_by_application_and_infrastructure() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "domain..")
            .should().onlyBeAccessed().byClassesThat().resideInAnyPackage(getApplicationMainPackages());
    }

    public ArchRule application_ports_should_only_be_accessed_by_infrastructure_and_application() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "application.port..")
            .should().onlyBeAccessed().byClassesThat().resideInAnyPackage(
                rootPackage + "application..",
                rootPackage + "infrastructure.."
            );
    }

    public ArchRule infrastructure_adapters_should_not_be_accessed_by_domain_or_application() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .should().onlyBeAccessed().byClassesThat().resideInAnyPackage(
                rootPackage + "infrastructure..",
                getMainApplicationClass()
            );
    }

    /**
     * Override this method to specify your main application class package.
     * Default assumes a class ending with "Application" in the root package.
     */
    protected String getMainApplicationClass() {
        return "..Application";
    }
}