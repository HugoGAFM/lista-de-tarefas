package BDconnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection = null;

    private static Database INSTANCE = null;

    private Database(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:lista-de-tarefas.db");
        } catch (SQLException e){
            System.err.println("Puts :/ deu erro ao criar o arquivo !");

        }
    }

    public Connection getConnection(){
        return this.connection;

    }

    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("Deu erro ao fechar a conexão !");
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Database();
        }
        return INSTANCE;
    }
}
