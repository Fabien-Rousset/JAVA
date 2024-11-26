package test;

import entities.ExoException;
import entities.Oiseau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Classe de test JUnit pour valider le comportement de la classe {@link Oiseau}.
 * Cette classe vérifie les règles de validation de l'attribut "nombre de plumes" de l'entité Oiseau.
 */
class OiseauTest {

    /**
     * Instance de la classe {@link Oiseau} qui sera utilisée pour les tests.
     */
    private Oiseau oiseau;

    /**
     * Méthode d'initialisation qui est exécutée avant chaque test.
     * Crée un objet {@link Oiseau} en utilisant un constructeur avec des valeurs par défaut valides.
     */
    @BeforeEach
    void setUp() {
        // Utilisation du constructeur avec des valeurs par défaut valides
        oiseau = new Oiseau();
    }

    /**
     * Test paramétré pour vérifier les valeurs invalides de "nombreDePlumes".
     * Ce test vérifie que lorsqu'on essaie de définir un nombre de plumes négatif,
     * une exception {@link ExoException} est correctement levée.
     *
     * @param invalidValue La valeur invalide du nombre de plumes (doit être négative).
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -5}) // Cas invalides pour "nombreDePlumes" : négatif
    void testNombreDePlumesInvalide(int invalidValue) {
        assertThrows(ExoException.class, () -> {
            oiseau.setNombreDePlumes(invalidValue);
        }, "Une ExoException aurait dû être lancée pour la valeur : " + invalidValue);
    }

    /**
     * Test paramétré pour vérifier les valeurs valides de "nombreDePlumes".
     * Ce test vérifie que lorsqu'on essaie de définir un nombre de plumes non négatif,
     * aucune exception n'est levée.
     *
     * @param validValue La valeur valide du nombre de plumes (doit être non négative).
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 50, 1000}) // Cas valides pour "nombreDePlumes"
    void testNombreDePlumesValide(int validValue) {
        assertDoesNotThrow(() -> {
            oiseau.setNombreDePlumes(validValue);
        }, "Aucune exception ne devrait être lancée pour la valeur : " + validValue);
    }
}

