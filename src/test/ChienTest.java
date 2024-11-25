package test;

import entities.Chien;
import entities.ExoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChienTest {

    private Chien chien;

    @BeforeEach
    void setUp()  {
        // Utilisation du constructeur avec des valeurs par défaut valides
        chien = new Chien();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "AB"}) // Cas invalides pour "race" : vide, 1 ou 2 caractères
    void testRaceChienInvalide(String invalidValues) {
        assertThrows(ExoException.class, () -> {
            chien.setRace(invalidValues);
        }, "Une ExoException aurait dû être lancée pour la valeur : " + invalidValues);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Berger", "Caniche", "Husky"}) // Cas valides pour "race"
    void testRaceChienValide(String validValues) {
        assertDoesNotThrow(() -> {
            chien.setRace(validValues);
        }, "Aucune exception ne devrait être lancée pour la valeur : " + validValues);
    }
}
