package com.gs.gestiontache;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.gs.gestiontache");

        noClasses()
            .that()
            .resideInAnyPackage("com.gs.gestiontache.service..")
            .or()
            .resideInAnyPackage("com.gs.gestiontache.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.gs.gestiontache.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
