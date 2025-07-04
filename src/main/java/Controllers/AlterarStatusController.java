package Controllers;

import Application.Tasks;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import DAO.TasksDAO;

import java.util.List;

public class AlterarStatusController {
    @FXML ComboBox tarefaSelecionada;
    @FXML ComboBox statusSelecionado;

    private TasksDAO tasksDAO;

    public void setTasksDAO(TasksDAO dao) {
        this.tasksDAO = dao;
    }

    @FXML
    public void handleSalvar() {
        statusSelecionado.getItems().addAll("false", "true");
        try {
            int id = Integer.parseInt((String) tarefaSelecionada.getValue());
            boolean status = (boolean) statusSelecionado.getValue();
            tasksDAO.alterarStatus(id, status);
            showMessage("Status alterado com sucesso!");
        } catch (Exception e) {
            showMessage("Erro: " + e.getMessage());
        }
    }

    @FXML
    public void CarregarTarefas() {
        try {
            String a = "";
            List<Tasks> resultado = tasksDAO.listarTarefas(a);

            for (Tasks task : resultado) {
                tarefaSelecionada.getItems().addAll(task);
            }

        } catch (Exception e) {
            showMessage("Erro: " + e.getMessage());
        }
    }

    private void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}