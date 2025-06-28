package BDconnection;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static BDconnection.FileUtils.loadTextFile;

public class BDconnection {
    public static void iniciar() {
        Connection con = Database.getInstance().getConnection(); // usa a conexão única
        try {
            Statement statement = con.createStatement();
            statement.setQueryTimeout(30);

            String sql = loadTextFile("lista_de_tarefas.sql");
            statement.execute(sql);


            ResultSet rs = statement.executeQuery("SELECT * FROM tarefa");
            while (rs.next()) {
                System.out.println("Tarefa: " + rs.getString("texto"));
            }

        } catch (SQLException | IOException e) {
            System.err.println("Erro ao inicializar banco:");
            e.printStackTrace();
        }
    }
}

