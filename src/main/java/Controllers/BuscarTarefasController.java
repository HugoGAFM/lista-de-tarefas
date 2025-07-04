package Controllers;

import Application.Tasks;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import DAO.TasksDAO;

import java.util.List;

public class BuscarTarefasController {
    @FXML private ComboBox<String> filtros;
    @FXML private TextArea resultadoArea;

    private TasksDAO tasksDAO;

    public void setTasksDAO(TasksDAO dao) {
        this.tasksDAO = dao;
        filtros.getItems().addAll("Todas", "Pendentes", "Concluidas");
    }

    @FXML
    public void BuscarTarefas() {
        resultadoArea.setText("");
        try {
            String filtro = filtros.getValue().toLowerCase();
            List<Tasks> resultado = tasksDAO.listarTarefas(filtro);
            StringBuilder sb = new StringBuilder();
            for (Tasks tarefa : resultado) {
                sb.append("Título: ").append(tarefa.getText()).append("\n");
                sb.append("Status: ").append(tarefa.isDone() ? "Concluida" : "Pendente").append("\n");
                sb.append("------------------------\n");
            }
            resultadoArea.setText(sb.toString());

        } catch (Exception e) {
            resultadoArea.setText("Erro na consulta.");
        }
    }
}

