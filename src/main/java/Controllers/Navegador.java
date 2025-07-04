package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.Parent;
import DAO.TasksDAO;
import BDconnection.Database;

import java.sql.Connection;

public class Navegador {
    public TabPane montarPainelPrincipal(Connection con) {
        TabPane tabPane = new TabPane();
        try {
            var tasksDAO = new TasksDAO(con);

            FXMLLoader loaderTarefas = new FXMLLoader(getClass().getResource("/CadastroTarefas.fxml"));
            Parent tarefasPane = loaderTarefas.load();
            CadastroTarefasController tarefaCtrl = loaderTarefas.getController();
            tarefaCtrl.setTasksDAO(tasksDAO);
            Tab tabTarefa = new Tab("tarefas", tarefasPane);

            FXMLLoader loaderAlterar = new FXMLLoader(getClass().getResource("/AlterarStatus.fxml"));
            Parent alterarPane = loaderAlterar.load();
            AlterarStatusController alterarCtrl = loaderAlterar.getController();
            alterarCtrl.setTasksDAO(tasksDAO);
            Tab tabAlterar = new Tab("alterar", alterarPane);

            FXMLLoader loaderBusca = new FXMLLoader(getClass().getResource("/BuscaTarefas.fxml"));
            Parent buscaPane = loaderBusca.load();
            BuscarTarefasController buscaCtrl = loaderBusca.getController();
            buscaCtrl.setTasksDAO(tasksDAO);
            Tab tabBusca = new Tab("Empréstimo", buscaPane);

            tabPane.getTabs().addAll(tabTarefa, tabAlterar, tabBusca);
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao montar painel: " + e.getMessage());
        }

        return tabPane;
    }
}
