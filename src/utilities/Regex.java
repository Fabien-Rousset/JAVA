package utilities;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Regex {

    // Déclaration de l'expression régulière pour valider un nom (commence par une Maj, ne contient que de lettres & min 2 caractères)
    public static final Pattern PATTERN_NOM = Pattern.compile("^[A-Z][a-zA-Z]{1,}$");

    // Déclaration de l'expression régulière pour valider une espece (commence par une Maj, ne contient que de lettres & min 3 caractères)
    public static final Pattern PATTERN_ESPECE = Pattern.compile("^[a-z][a-zA-Z]{2,}$");

    // Déclarartion de la regex pour le format date jj/mm/aaaa
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    // Déclarartion de la regex pour le format date jj/mm/aaaa
//    public static final Pattern PATTERN_DATE = Pattern.compile("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$");


}
