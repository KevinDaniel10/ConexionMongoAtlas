package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VerDatos {
    protected JPanel panel1;
    private JTextField nombreTF;
    private JButton buscarButton;
    private JTable table1;
    private JTextField calificacionTF;
    private JButton actualizarButton;
    private JLabel busquedaestudiantesL;

    public VerDatos() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

                MongoDatabase database = mongoClient.getDatabase("mydb2");
                MongoCollection<Document> collection = database.getCollection("mydb2");

                Document filtro = new Document("Nombre",nombreTF.getText());
                Document actualizacion = new Document("$set", new Document("Calificacion", calificacionTF.getText()));

                if (Objects.equals(nombreTF.getText(), filtro)){
                    busquedaestudiantesL.setText("Estudiante encontrado");
                }


            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

                MongoDatabase database = mongoClient.getDatabase("mydb2");
                MongoCollection<Document> collection = database.getCollection("mydb2");

                Document filtro = new Document("Nombre",nombreTF.getText());
                Document actualizacion = new Document("$set", new Document("Calificacion", calificacionTF.getText()));

                collection.updateOne(filtro, actualizacion);

                mongoClient.close();
            }
        });
    }
}
