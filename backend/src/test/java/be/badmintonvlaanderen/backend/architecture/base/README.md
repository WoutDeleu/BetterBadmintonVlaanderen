# Reusable Architecture Tests

This package contains reusable ArchUnit test classes that can be used in any Spring Boot project following hexagonal architecture principles.

## Overview

The base test classes provide comprehensive architecture validation for:
- Hexagonal architecture compliance
- Layer dependency rules
- Naming conventions
- Spring annotation usage
- Security best practices
- Package structure validation

## Usage

### Quick Start

1. Copy the `base` package to your project's test directory
2. Add ArchUnit dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>1.2.1</version>
    <scope>test</scope>
</dependency>
```

3. Create test classes that extend the base classes:

```java
@AnalyzeClasses(
    packages = "com.yourcompany.yourproject",
    importOptions = ImportOption.DoNotIncludeTests.class
)
public class YourHexagonalArchitectureTest extends BaseHexagonalArchitectureTest {

    @Override
    protected String getApplicationRootPackage() {
        return "com.yourcompany.yourproject..";
    }

    @ArchTest
    static final ArchRule hexagonal_architecture_is_respected =
        new YourHexagonalArchitectureTest().hexagonal_architecture_is_respected();

    // Add more @ArchTest rules as needed
}
```

## Base Test Classes

### BaseHexagonalArchitectureTest
Validates hexagonal architecture principles:
- Domain independence from application and infrastructure layers
- Proper port definition (interfaces only)
- Immutable domain models
- Dependency flow compliance

**Customizable methods:**
- `getApplicationRootPackage()`: Your project's base package
- `getAllowedDomainDependencies()`: Additional allowed dependencies for domain layer
- `getAllowedInfrastructureDependencies()`: Additional allowed dependencies for infrastructure layer

### BaseLayerDependencyTest
Validates layer access rules:
- Domain layer isolation
- Application layer boundaries
- Infrastructure layer restrictions

**Customizable methods:**
- `getApplicationRootPackage()`: Your project's base package
- `getMainApplicationClass()`: Pattern for your main application class

### BaseNamingConventionTest
Validates naming conventions:
- Service suffixes
- Repository suffixes
- Controller suffixes
- Entity naming patterns

**Customizable methods:**
- `getDomainServiceSuffixes()`: Allowed domain service suffixes
- `getApplicationServiceSuffixes()`: Allowed application service suffixes
- `getPortSuffixes()`: Allowed port interface suffixes
- `getEntitySuffixes()`: Allowed entity suffixes

### BaseSpringAnnotationTest
Validates Spring annotation usage:
- Domain layer Spring annotation restrictions
- Proper service annotations
- Controller annotations
- Repository annotations
- Injection patterns

**Customizable methods:**
- `getSpringStereotypeAnnotations()`: Spring annotations forbidden in domain
- `getSpringWebAnnotations()`: Web-related annotations
- `getHttpAnnotations()`: HTTP-related annotations

### BaseSecurityTest
Validates security best practices:
- No direct System.out usage
- No generic exception throwing
- Proper logging framework usage
- Security configuration placement

**Customizable methods:**
- `getForbiddenLoggingPackages()`: Forbidden logging frameworks
- `getForbiddenExceptionTypes()`: Generic exception types to avoid
- `getDeprecatedPackages()`: Deprecated packages to avoid

### BasePackageStructureTest
Validates package organization:
- Proper package hierarchy
- Domain model restrictions
- Configuration placement
- Utility class organization

**Customizable methods:**
- `getAllowedPackages()`: Valid package patterns for your project
- `getForbiddenAnnotationsForDomainModel()`: Annotations not allowed in domain models
- `getAllowedDomainDependencies()`: Allowed dependencies for domain layer

## Example Implementation

See the concrete test classes in the parent package for complete examples:
- `HexagonalArchitectureTest`
- `LayerDependencyTest`
- `NamingConventionTest`
- `SpringAnnotationTest`
- `SecurityTest`
- `PackageStructureTest`

## Customization

Each base test class provides protected methods that can be overridden to customize the rules for your specific project needs. Common customizations include:

1. **Different package structure**: Override `getApplicationRootPackage()`
2. **Additional allowed dependencies**: Override dependency array methods
3. **Different naming conventions**: Override suffix array methods
4. **Custom annotations**: Override annotation array methods

## Benefits

- **Consistency**: Enforce architectural patterns across all your projects
- **Quality**: Catch architectural violations early in development
- **Documentation**: Tests serve as living documentation of your architecture
- **Maintainability**: Centralized rules that can be updated and reused
- **Team Alignment**: Shared understanding of architectural principles

## Integration with CI/CD

These tests integrate seamlessly with your CI/CD pipeline and will fail the build if architectural violations are detected, ensuring your codebase maintains its architectural integrity over time.