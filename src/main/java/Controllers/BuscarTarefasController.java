package Controllers;

import Application.Tasks;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import DAO.TasksDAO;

import java.util.List;

public class BuscarTarefasController {
    @FXML private ComboBox filtros = new ComboBox<>();
    @FXML private TextArea resultadoArea;

    private TasksDAO tasksDAO;

    public void setTasksDAO(TasksDAO dao) {
        this.tasksDAO = dao;
    }

    @FXML
    public void BuscarTarefas() {
        filtros.getItems().addAll("Todas", "Pendentes", "Concluídas");
        try {
            String filtro = String.valueOf(filtros.getValue());
            List<Tasks> resultado = tasksDAO.listarTarefas(filtro);

            for (Tasks task : resultado) {
                resultadoArea.setText(task.toString());
            }

        } catch (Exception e) {
            resultadoArea.setText("Erro na consulta.");
        }
    }
}

