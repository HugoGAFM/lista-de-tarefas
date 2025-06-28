package DAO;

import Application.Tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TasksDAO {

    private Connection conn;

    public TasksDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarTarefa(String texto) throws SQLException {
        String sql = "INSERT INTO tarefa (texto, concluido, data_alteracao) VALUES (?, 0, datetime('now'))";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, texto);
            stmt.executeUpdate();
        }
    }

    public void alterarStatus(int id, boolean concluido) throws SQLException {
        String sql = "UPDATE tarefa SET concluido = ?, data_alteracao = datetime('now') WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, concluido ? 1 : 0);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Tasks> listarTarefas(String filtro) throws SQLException {
        String sql = switch (filtro.toLowerCase()) {
            case "pendentes" -> "SELECT * FROM tarefa WHERE concluido = 0";
            case "concluidas" -> "SELECT * FROM tarefa WHERE concluido = 1";
            default -> "SELECT * FROM tarefa";
        };

        List<Tasks> tarefas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tasks tarefa = new Tasks(
                        rs.getInt("id"),
                        rs.getString("texto"),
                        rs.getInt("concluido") == 1,
                        rs.getString("data_alteracao")
                );
                tarefas.add(tarefa);
            }
        }
        return tarefas;
    }
}
