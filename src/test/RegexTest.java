package test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test JUnit pour valider les fonctionnalités du DateTimeFormatter.
 * Cette classe vérifie la conversion des chaînes de caractères en LocalDate
 * et la validation des dates en utilisant un format strict "dd/MM/uuuu".
 */
class RegexTest {

    /**
     * Formatteur de date avec un pattern "dd/MM/uuuu".
     * Utilise {@link ResolverStyle#STRICT} pour garantir une validation stricte des dates.
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Teste la conversion d'une chaîne de date valide en objet {@link LocalDate}.
     * Vérifie que la chaîne "26/11/2024" est correctement analysée en une date LocalDate correspondante.
     */
    @Test
    void testValidDateParsing() {
        String validDate = "26/11/2024";
        LocalDate expectedDate = LocalDate.of(2024, 11, 26);

        // Test parsing a valid date
        LocalDate parsedDate = LocalDate.parse(validDate, DATE_FORMATTER);
        assertEquals(expectedDate, parsedDate);
    }

    /**
     * Teste la conversion d'un objet {@link LocalDate} en chaîne de caractères au format "dd/MM/uuuu".
     * Vérifie que la date {@link LocalDate} (26 novembre 2024) est correctement formatée en "26/11/2024".
     */
    @Test
    void testValidDateFormatting() {
        LocalDate date = LocalDate.of(2024, 11, 26);
        String expectedFormattedDate = "26/11/2024";

        // Test formatting a LocalDate
        String formattedDate = date.format(DATE_FORMATTER);
        assertEquals(expectedFormattedDate, formattedDate);
    }

    /**
     * Teste la tentative d'analyse d'une chaîne au format invalide.
     * Vérifie que l'analyse de la chaîne "2024-11-26" (format incorrect) lève une {@link DateTimeParseException}.
     */
    @Test
    void testInvalidDateParsing() {
        String invalidDate = "2024-11-26"; // Format incorrect

        // Test parsing an invalid date format
        assertThrows(DateTimeParseException.class, () -> {
            LocalDate.parse(invalidDate, DATE_FORMATTER);
        });
    }

    /**
     * Teste la tentative d'analyse d'une chaîne représentant une date invalide (par exemple, le 31 février).
     * Vérifie que l'analyse de la chaîne "31/02/2024" lève une {@link DateTimeParseException},
     * car février n'a jamais 31 jours.
     */
    @Test
    void testInvalidDateValue() {
        String invalidDate = "31/02/2024"; // Jour invalide pour le mois de février

        // Test parsing an invalid date value
        assertThrows(DateTimeParseException.class, () -> {
            LocalDate.parse(invalidDate, DATE_FORMATTER);
        });
    }
}
