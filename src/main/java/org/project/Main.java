package org.project;

import Application.MenuTerminal;
import Application.Tasks;
import BDconnection.BDconnection;
import BDconnection.Database;
import Controllers.Navegador;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.sql.Connection;

import static javafx.application.Application.launch;
import javafx.application.Application;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        BDconnection.iniciar();
        Connection conn = Database.getInstance().getConnection();
        TabPane painel = new Navegador().montarPainelPrincipal(conn);
        stage.setScene(new Scene(painel, 800, 600));
        stage.setTitle("Lista de Tarefas");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}