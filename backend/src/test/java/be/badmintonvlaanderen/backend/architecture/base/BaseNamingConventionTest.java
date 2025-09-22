package be.badmintonvlaanderen.backend.architecture.base;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Base class for naming convention tests.
 * Can be extended by any project to test naming conventions.
 */
public abstract class BaseNamingConventionTest {

    protected String getApplicationRootPackage() {
        return "..";
    }

    protected String[] getDomainServiceSuffixes() {
        return new String[]{"DomainService", "Service"};
    }

    protected String[] getApplicationServiceSuffixes() {
        return new String[]{"ApplicationService", "Service", "UseCase"};
    }

    protected String[] getPortSuffixes() {
        return new String[]{"Port", "Repository", "Gateway", "Service"};
    }

    protected String[] getRepositorySuffixes() {
        return new String[]{"Repository", "Adapter"};
    }

    protected String[] getEntitySuffixes() {
        return new String[]{"Entity", "JpaEntity"};
    }

    protected String[] getConfigurationSuffixes() {
        return new String[]{"Configuration", "Config"};
    }

    protected String[] getDtoSuffixes() {
        return new String[]{"Request", "Response", "Dto"};
    }

    public ArchRule domain_services_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        String[] suffixes = getDomainServiceSuffixes();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "domain.service..")
            .should().haveSimpleNameEndingWith(suffixes[0]);

        for (int i = 1; i < suffixes.length; i++) {
            rule = rule.orShould().haveSimpleNameEndingWith(suffixes[i]);
        }

        return rule;
    }

    public ArchRule application_services_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        String[] suffixes = getApplicationServiceSuffixes();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "application.service..")
            .should().haveSimpleNameEndingWith(suffixes[0]);

        for (int i = 1; i < suffixes.length; i++) {
            rule = rule.orShould().haveSimpleNameEndingWith(suffixes[i]);
        }

        return rule;
    }

    public ArchRule ports_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        String[] suffixes = getPortSuffixes();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "application.port..")
            .should().haveSimpleNameEndingWith(suffixes[0]);

        for (int i = 1; i < suffixes.length; i++) {
            rule = rule.orShould().haveSimpleNameEndingWith(suffixes[i]);
        }

        return rule;
    }

    public ArchRule controllers_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
            .should().haveSimpleNameEndingWith("Controller");
    }

    public ArchRule repositories_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        String[] suffixes = getRepositorySuffixes();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().areAnnotatedWith("org.springframework.stereotype.Repository")
            .should().haveSimpleNameEndingWith(suffixes[0]);

        for (int i = 1; i < suffixes.length; i++) {
            rule = rule.orShould().haveSimpleNameEndingWith(suffixes[i]);
        }

        return rule;
    }

    public ArchRule domain_models_should_not_be_suffixed_with_entity() {
        String rootPackage = getApplicationRootPackage();
        return classes()
            .that().resideInAPackage(rootPackage + "domain.model..")
            .should().notHaveSimpleNameEndingWith("Entity");
    }

    public ArchRule infrastructure_entities_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        String[] suffixes = getEntitySuffixes();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().areAnnotatedWith("jakarta.persistence.Entity")
            .should().haveSimpleNameEndingWith(suffixes[0]);

        for (int i = 1; i < suffixes.length; i++) {
            rule = rule.orShould().haveSimpleNameEndingWith(suffixes[i]);
        }

        return rule;
    }

    public ArchRule configuration_classes_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        String[] suffixes = getConfigurationSuffixes();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "infrastructure.config..")
            .should().haveSimpleNameEndingWith(suffixes[0]);

        for (int i = 1; i < suffixes.length; i++) {
            rule = rule.orShould().haveSimpleNameEndingWith(suffixes[i]);
        }

        return rule;
    }

    public ArchRule dto_classes_should_be_suffixed() {
        String rootPackage = getApplicationRootPackage();
        String[] suffixes = getDtoSuffixes();

        ArchRule rule = classes()
            .that().resideInAPackage(rootPackage + "infrastructure.adapter..")
            .and().haveSimpleNameContaining("Request")
            .or().haveSimpleNameContaining("Response")
            .or().haveSimpleNameContaining("Dto")
            .should().haveSimpleNameEndingWith(suffixes[0]);

        for (int i = 1; i < suffixes.length; i++) {
            rule = rule.orShould().haveSimpleNameEndingWith(suffixes[i]);
        }

        return rule;
    }
}