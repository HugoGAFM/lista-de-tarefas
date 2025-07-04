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
        CarregarTarefas();
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
        tarefaSelecionada.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Tasks item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(tarefaSelecionada.getPromptText());
                } else {
                    setText(item.toString());
                }
            }
        });
        statusSelecionado.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(statusSelecionado.getPromptText());
                } else {
                    setText(item.toString());
                }
            }
        });
    }

    @FXML
    public void CarregarTarefas() {
        tarefaSelecionada.getItems().clear();
        statusSelecionado.setValue(null);
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