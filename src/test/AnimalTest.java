package test;

import entities.Animal;
import entities.Chien;
import entities.ExoException;
import entities.Oiseau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import utilities.Regex;

import static org.junit.jupiter.api.Assertions.*;
class AnimalTest {
    private Chien chien;
    private Oiseau oiseau;

    @BeforeEach
    void setUp() {
        chien = new Chien();
        oiseau = new Oiseau();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testSetNomInvalide(String nom) {
        assertThrows(ExoException.class, () -> chien.setNom(nom));
        assertThrows(ExoException.class, () -> oiseau.setNom(nom));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Albert", "Chien", "Labrador"})
    void testPatternNomValide(String nom) {
        assertTrue(Regex.PATTERN_NOM.matcher(nom).matches(), "Le nom '" + nom + "' devrait être valide mais ne l'est pas.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"albert", "chien123", "Chi@n", "A", "B3au", "123"})
    void testPatternNomInvalide(String nom) {
        assertFalse(Regex.PATTERN_NOM.matcher(nom).matches(), "Le nom '" + nom + "' devrait être invalide mais est considéré comme valide.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chien", "chat", "caniche", "oiseau"})
    void testPatternEspeceValide(String espece) {
        assertTrue(Regex.PATTERN_ESPECE.matcher(espece).matches(), "L'espèce '" + espece + "' devrait être valide mais ne l'est pas.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Chien", "ch", "C123", "chi@", "", "a", "Baleine"})
    void testPatternEspeceInvalide(String espece) {
        assertFalse(Regex.PATTERN_ESPECE.matcher(espece).matches(), "L'espèce '" + espece + "' devrait être invalide mais est considérée comme valide.");
    }
}




