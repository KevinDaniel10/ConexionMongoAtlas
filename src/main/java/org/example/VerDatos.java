package org.example;

import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VerDatos {
    public JPanel panel1;
    private JButton buscarButton;
    private DefaultTableModel tableModel;
    private JTable table1;
    private JTextField calificacionTF;
    private JButton actualizarButton;
    private JLabel busquedaestudiantesL;

    public VerDatos() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cadena de conexión a MongoDB Atlas
                String connectionString = "mongodb+srv://esfot:esfot2024@cluster0.xzffuex.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
                MongoClient mongoClient = MongoClients.create(connectionString);
                MongoDatabase database = mongoClient.getDatabase("DeberPoo");
                MongoCollection<Document> collection = database.getCollection("Pasatiempos");

                // tabla tabler
                String[] columnNames = {"Nombre","Pasatiempo","Descripcion"};
                tableModel = new DefaultTableModel(columnNames, 0);
                table1.setModel(tableModel);

                // Agregar una fila por defecto a la tabla tabler
                Object[] defaultRow = {"Nombre","Pasatiempo","Descripcion"};
                tableModel.addRow(defaultRow);

                Document projection = new Document()
                        .append("Nombre", 1)
                        .append("Pasatiempo:", 1)
                        .append("Descripcion:", 1)
                        .append("_id", 0); // Excluye el campo "_id"

                FindIterable<Document> documents = collection.find().projection(projection);

                for (Document document : documents) {
                        // Imprimir el documento solo si contiene dato
                        Object[] fila = {
                                document.getString("Nombre"),
                                document.getString("Pasatiempo:"),
                                document.getString("Descripcion:")
                        };
                        tableModel.addRow(fila);
                }

                mongoClient.close();
            }
        });

    }
}
