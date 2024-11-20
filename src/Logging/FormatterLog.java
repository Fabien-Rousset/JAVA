package Logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * FormatterLog est un formateur personnalisé pour les messages de log.
 * Il formate les messages de log avec un horodatage, le niveau de log, et le message.
 * La date est formatée selon "dd/MM/yyyy HH:mm:ss".
 */
public class FormatterLog extends Formatter {
    /**
     * Formatte l'enregistrement de log donné en une chaîne lisible.
     *
     * @param record L'enregistrement de log à formatter.
     * @return Une chaîne formatée contenant la date, le niveau, et le message de l'enregistrement de log.
     */
    @Override
    public String format(LogRecord record) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        StringBuilder formattedMessage = new StringBuilder();
        formattedMessage.append(dateFormat.format(new Date(record.getMillis())));
        formattedMessage.append(" Niveau: ");
        formattedMessage.append(record.getLevel());
        formattedMessage.append(" - Message: ");
        formattedMessage.append(record.getMessage());
        formattedMessage.append("\n");

        return formattedMessage.toString();
    }
}
