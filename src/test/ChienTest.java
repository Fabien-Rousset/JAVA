package test;

import entities.Chien;
import entities.ExoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe de test JUnit pour valider le comportement de la classe {@link Chien}.
 * Cette classe vérifie les règles de validation des attributs de l'entité Chien, en particulier la validation de la race.
 */
class ChienTest {

    /**
     * Instance de la classe {@link Chien} qui sera utilisée pour les tests.
     */
    private Chien chien;

    /**
     * Méthode d'initialisation qui est exécutée avant chaque test.
     * Crée un objet {@link Chien} en utilisant un constructeur avec des valeurs par défaut valides.
     */
    @BeforeEach
    void setUp()  {
        // Utilisation du constructeur avec des valeurs par défaut valides
        chien = new Chien();
    }

    /**
     * Test paramétré pour vérifier les valeurs invalides de "race".
     * Ce test vérifie que lorsqu'on essaie de définir une race vide ou avec trop peu de caractères (moins de 3),
     * une exception {@link ExoException} est correctement levée.
     *
     * @param invalidValues La valeur invalide de la race (vide, ou moins de 3 caractères).
     */
    @ParameterizedTest
    @ValueSource(strings = {"", "A", "AB"}) // Cas invalides pour "race" : vide, 1 ou 2 caractères
    void testRaceChienInvalide(String invalidValues) {
        assertThrows(ExoException.class, () -> {
            chien.setRace(invalidValues);
        }, "Une ExoException aurait dû être lancée pour la valeur : " + invalidValues);
    }

    /**
     * Test paramétré pour vérifier les valeurs valides de "race".
     * Ce test vérifie que lorsqu'on essaie de définir une race avec des noms valides (minimum 3 caractères),
     * aucune exception n'est levée.
     *
     * @param validValues La valeur valide de la race (doit avoir au moins 3 caractères).
     */
    @ParameterizedTest
    @ValueSource(strings = {"Berger", "Caniche", "Husky"}) // Cas valides pour "race"
    void testRaceChienValide(String validValues) {
        assertDoesNotThrow(() -> {
            chien.setRace(validValues);
        }, "Aucune exception ne devrait être lancée pour la valeur : " + validValues);
    }
}
