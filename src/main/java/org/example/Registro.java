package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro {
    protected JPanel registro;
    private JTextField nombreF;
    private JTextField pasatiempoF;
    private JTextField descripcionF;
    private JButton agregarButton;
    private JButton borrarButton;
    private JButton buscarButton;

    public Registro() {
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Cadena de conexión a MongoDB Atlas
                String connectionString = "mongodb+srv://esfot:esfot2024@cluster0.xzffuex.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

                // Crear una conexión al cliente de MongoDB
                MongoClient mongoClient = MongoClients.create(connectionString);

                // Seleccionar la base de datos
                MongoDatabase database = mongoClient.getDatabase("DeberPoo");

                // Seleccionar la colección
                MongoCollection<Document> collection = database.getCollection("Pasatiempos");

                // Crear un documento
                Document document = new Document("Nombre", nombreF.getText())
                        .append("Pasatiempo:", pasatiempoF.getText())
                        .append("Descripcion:", descripcionF.getText());

                // Insertar el documento en la colección
                collection.insertOne(document);
                System.out.println("Documento insertado");
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreF.setText("");
                pasatiempoF.setText("");
                descripcionF.setText("");
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame tranFrame = (JFrame) SwingUtilities.getWindowAncestor(buscarButton);
                tranFrame.dispose();
                JFrame frame = new JFrame("Registro");
                frame.setContentPane(new VerDatos().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(450,300);
                //frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
