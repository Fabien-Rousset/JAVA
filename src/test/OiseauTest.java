package test;

import entities.ExoException;
import entities.Oiseau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OiseauTest {

    private Oiseau oiseau;

    @BeforeEach
    void setUp() {
        // Utilisation du constructeur avec des valeurs par défaut valides
        oiseau = new Oiseau();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100, -5}) // Cas invalides pour "nombreDePlumes" : négatif
    void testNombreDePlumesInvalide(int invalidValue) {
        assertThrows(ExoException.class, () -> {
            oiseau.setNombreDePlumes(invalidValue);
        }, "Une ExoException aurait dû être lancée pour la valeur : " + invalidValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 50, 1000}) // Cas valides pour "nombreDePlumes"
    void testNombreDePlumesValide(int validValue) {
        assertDoesNotThrow(() -> {
            oiseau.setNombreDePlumes(validValue);
        }, "Aucune exception ne devrait être lancée pour la valeur : " + validValue);
    }
}
