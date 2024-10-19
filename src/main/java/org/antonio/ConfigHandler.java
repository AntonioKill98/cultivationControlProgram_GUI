package org.antonio;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigHandler {
    private static final String CONFIG_FILE = "config.json";

    public static Config loadConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        File configFile = new File(CONFIG_FILE);

        if (configFile.exists()) {
            try {
                // Carica la configurazione dal file JSON
                return objectMapper.readValue(configFile, Config.class);
            } catch (IOException e) {
                showErrorDialog("Errore di lettura del file di configurazione", "Errore durante la lettura di " + CONFIG_FILE + ": " + e.getMessage());
                System.exit(1);
            }
        } else {
            // Crea una configurazione di default e avvisa l'utente
            Config defaultConfig = createDefaultConfig();
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(configFile, defaultConfig);
                showErrorDialog("File di configurazione mancante", "Il file di configurazione non esisteva. È stato creato un file config.json con valori di default. Modificalo e riavvia l'applicazione.");
            } catch (IOException e) {
                showErrorDialog("Errore di creazione del file di configurazione", "Non è stato possibile creare il file di configurazione: " + e.getMessage());
            }
            System.exit(0); // Chiude l'applicazione in attesa che l'utente modifichi il file di configurazione
        }
        return null;
    }

    private static Config createDefaultConfig() {
        return new Config(
                "test", // aws_access_key_id
                "test", // aws_secret_access_key
                "us-east-2", // regione
                "http://localhost:4566", // endpoint
                "Campi", // nome della tabella
                List.of("cultivationName", "device_ids", "measure_date", "temperature", "humidity") // colonne
        );
    }

    private static void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
