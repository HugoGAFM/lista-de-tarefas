package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import DAO.TasksDAO;

public class CadastroTarefasController {
    @FXML private TextField textoField;

    private TasksDAO tasksDAO;

    public void setTasksDAO(TasksDAO dao) {
        this.tasksDAO = dao;
    }

    @FXML
    public void handleSalvar() {
        try {
            tasksDAO.adicionarTarefa(textoField.getText());
            showMessage("Livro cadastrado com sucesso!");
            limparCampos();
        } catch (Exception e) {
            showMessage("Erro: " + e.getMessage());
        }
    }

    private void limparCampos() {
        textoField.clear();
    }

    private void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}