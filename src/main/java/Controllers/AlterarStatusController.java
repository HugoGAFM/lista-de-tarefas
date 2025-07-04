package Controllers;

import Application.Tasks;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import DAO.TasksDAO;

import java.util.List;

public class AlterarStatusController {
    @FXML ComboBox<Tasks> tarefaSelecionada;
    @FXML ComboBox<String> statusSelecionado;

    private TasksDAO tasksDAO;

    public void setTasksDAO(TasksDAO dao) {
        this.tasksDAO = dao;
        statusSelecionado.getItems().addAll("Concluída", "Pendente");

    }

    @FXML
    public void handleSalvar() {
        try {
            Tasks task = tarefaSelecionada.getValue();
            boolean status = statusSelecionado.getSelectionModel().getSelectedItem().equals("Concluída");

            tasksDAO.alterarStatus(task.getId(), status);
            showMessage("Status alterado com sucesso!");
            CarregarTarefas();
        } catch (Exception e) {
            showMessage("Erro: " + e.getMessage());
        }
    }

    @FXML
    public void CarregarTarefas() {
        tarefaSelecionada.getItems().clear();
        try {
            String a = "";
            List<Tasks> resultado = tasksDAO.listarTarefas(a);

            for (Tasks task : resultado) {
                tarefaSelecionada.getItems().add(task);
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