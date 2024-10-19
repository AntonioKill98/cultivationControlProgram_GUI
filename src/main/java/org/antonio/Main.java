package org.antonio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import org.antonio.DynamoDBItem;
import org.antonio.DynamoDBHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Carica la configurazione
        Config config = ConfigHandler.loadConfig();

        // Crea il frame principale
        JFrame frame = new JFrame("DynamoDB Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Crea il modello della tabella dinamicamente in base alle colonne configurate
        DefaultTableModel model = new DefaultTableModel(config.getColumns().toArray(), 0);
        JTable table = new JTable(model);

        // Aggiungi un pulsante per caricare i dati
        JButton loadButton = new JButton("Load Data");
        loadButton.addActionListener(e -> {
            // Carica i dati da DynamoDB
            List<Map<String, String>> data = DynamoDBHandler.loadData();
            model.setRowCount(0); // Resetta la tabella

            for (Map<String, String> row : data) {
                // Aggiungi i dati dinamicamente alla tabella
                Object[] rowData = config.getColumns().stream()
                        .map(row::get) // Ottieni i dati per ogni colonna dalla mappa
                        .toArray();
                model.addRow(rowData);
            }
        });

        // Layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(loadButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }
}
