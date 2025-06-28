package BDconnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:sqlite:lista-de-tarefas.db";
    private static Database INSTANCE = null;
    private Connection connection;

    private Database() {
        try {
            this.connection = DriverManager.getConnection(URL);
            System.out.println("Conexão com SQLite estabelecida.");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados:");
            e.printStackTrace();
            this.connection = null;
        }
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                this.connection = DriverManager.getConnection(URL);
                System.out.println("Conexão restaurada.");
            } catch (SQLException e) {
                System.err.println("Erro ao restaurar conexão:");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão:");
            e.printStackTrace();
        }
    }
}