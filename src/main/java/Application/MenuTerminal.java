package Application;

import DAO.TasksDAO;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MenuTerminal {
    private final TasksDAO dao;
    private final Scanner scanner;

    public MenuTerminal(Connection conn) {
        this.dao = new TasksDAO(conn);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("Bem-vindo à Lista de Tarefas!");

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInt(">> ");

            switch (opcao) {
                case 1 -> cadastrarTarefa();
                case 2 -> alterarStatus();
                case 3 -> listarTarefas("todas");
                case 4 -> listarTarefas("concluidas");
                case 5 -> listarTarefas("pendentes");
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void mostrarMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Cadastrar nova tarefa");
        System.out.println("2 - Alterar status de uma tarefa");
        System.out.println("3 - Ver todas as tarefas");
        System.out.println("4 - Ver tarefas concluídas");
        System.out.println("5 - Ver tarefas pendentes");
        System.out.println("0 - Sair");
    }

    private void cadastrarTarefa() {
        String texto = lerString("Digite a descrição da tarefa: ");
        try {
            dao.adicionarTarefa(texto);
            System.out.println("Tarefa adicionada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
    }

    private void alterarStatus() {
        int id = lerInt("Digite o ID da tarefa: ");
        boolean status = lerBoolean("Digite o novo status (true para concluída, false para pendente): ");
        try {
            dao.alterarStatus(id, status);
            System.out.println("Status atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
        }
    }

    private void listarTarefas(String filtro) {
        try {
            List<Tasks> tarefas = dao.listarTarefas(filtro);
            if (tarefas.isEmpty()) {
                System.out.println("Nenhuma tarefa encontrada.");
                return;
            }

            System.out.println("\nTarefas (" + filtro + "):");
            for (Tasks t : tarefas) {
                System.out.printf("ID: %d | [%s] %s | Última alteração: %s\n",
                        t.getId(), t.isDone() ? "X" : " ", t.getText(), t.getDataAlteracao());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }

    // Métodos utilitários de leitura
    private int lerInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Digite um número inteiro: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private boolean lerBoolean(String prompt) {
        System.out.print(prompt);
        return Boolean.parseBoolean(scanner.nextLine());
    }
}