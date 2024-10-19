package org.antonio;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import javax.swing.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DynamoDBHandler {

    public static List<Map<String, String>> loadData() {
        Config config = ConfigHandler.loadConfig(); // Carica la configurazione dal file JSON
        List<Map<String, String>> data = new ArrayList<>();

        try {
            // Configura le credenziali in base alla configurazione
            AwsBasicCredentials awsCreds = AwsBasicCredentials.create(config.getAwsAccessKeyId(), config.getAwsSecretAccessKey());

            // Configura il client DynamoDB con i dati presi dal file di configurazione
            DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                    .endpointOverride(URI.create(config.getEndpoint()))
                    .region(Region.of(config.getRegion()))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                    .build();

            // Esegui lo scan della tabella DynamoDB e gestisci i dati
            ScanRequest scanRequest = ScanRequest.builder().tableName(config.getTableName()).build();
            ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);

            // Leggi i dati dalle colonne specificate nella configurazione
            for (Map<String, AttributeValue> item : scanResponse.items()) {
                Map<String, String> row = new java.util.HashMap<>();
                for (String column : config.getColumns()) {
                    row.put(column, item.containsKey(column) ? item.get(column).s() : "");
                }
                data.add(row);
            }

        } catch (SdkClientException e) {
            // Gestisce gli errori relativi a problemi con il client AWS o la connessione
            showErrorDialog("Errore di connessione", "Non è possibile connettersi a DynamoDB.\n" + e.getMessage());
        } catch (Exception e) {
            // Gestisce qualsiasi altro errore imprevisto
            showErrorDialog("Errore sconosciuto", "Si è verificato un errore imprevisto.\n" + e.getMessage());
        }

        return data;
    }

    // Funzione per mostrare un messaggio di errore in una message box
    private static void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
