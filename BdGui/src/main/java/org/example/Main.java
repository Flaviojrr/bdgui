package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection conexao = null;
        conexao = BancoDeDados.conectar();  // Estabelece a conexão com o banco de dados
        BancoDeDados banco = new BancoDeDados();  // Instância a classe responsável pelo banco de dados
        int op = 0;
        do {
            // Menu principal
            System.out.println("\nCLIENTE!!!"
                    + "\n(1)-Listar"
                    + "\n(2)-Deletar"
                    + "\n(3)-Pesquisar"
                    + "\n\nCARDAPIO!!!"
                    + "\n(4)-Adicionar"
                    + "\n(5)-Pesquisar"
                    + "\n(6)-Deletar"
                    + "\n(7)-Listar"
                    + "\n\nPedidos!!!"
                    + "\n(8)-Listar"
                    + "\n(9)-Deletar"
                    + "\n(10)-Sair "
                    + "\nSua escolha: ");
            op = sc.nextInt();

            // Switch para lidar com as opções do menu
            switch (op) {
                case 1:
                    listarClientes(conexao);
                    break;
                case 2:
                    System.out.println("CPF do cliente que deseja deletar: ");
                    sc.nextLine();
                    String cpf = sc.nextLine();
                    deletarClientePorId(conexao, cpf);
                    break;
                case 3:
                    System.out.println("CPF do cliente que deseja pesquisar: ");
                    sc.nextLine();
                    String cpf2 = sc.nextLine();
                    System.out.println("Idade do cliente que deseja pesquisar: ");
                    int idade = sc.nextInt();
                    pesquisarCliente(conexao, cpf2, idade);
                    break;
                case 4:
                    System.out.println("Nome do prato que deseja adicionar: ");
                    sc.nextLine();
                    String nomePrato = sc.nextLine();
                    System.out.println("Tipo do prato que deseja adicionar: ");
                    String tipoPrato = sc.nextLine();
                    System.out.println("Valor do prato que deseja adicionar: ");
                    double preco = sc.nextDouble();
                    adicionarPrato(conexao, nomePrato, tipoPrato, preco);
                    break;
                case 5:
                    System.out.println("Nome do prato que deseja pesquisar: ");
                    sc.nextLine();
                    String nomePrato2 = sc.nextLine();
                    pesquisarPratoPorNome(conexao, nomePrato2);
                    break;
                case 6:
                    System.out.println("ID do prato que deseja deletar: ");
                    int idPrato = sc.nextInt();
                    deletarPratoPorId(conexao, idPrato);
                    break;
                case 7:
                    listarTodosPratos(conexao);
                    break;
                case 8:
                    listarTodosPedidos(conexao);
                    break;
                case 9:
                    System.out.println("ID do pedido:");
                    int idPedido = sc.nextInt();
                    deletarPedidoPorId(conexao, idPedido);
                    break;
                case 11:
                    System.out.println("CPF para adicionar novo cliente: ");
                    sc.nextLine();
                    String cpf3 = sc.nextLine();
                    System.out.println("Nome para adicionar novo cliente: ");
                    String nome = sc.nextLine();
                    System.out.println("Idade para adicionar novo cliente: ");
                    int idade3 = sc.nextInt();
                    adicionarCliente(conexao, cpf3, nome, idade3);
                    break;
            }
        } while (op != 10);
    }

    // Adiciona um novo cliente ao banco de dados
    public static void adicionarCliente(Connection connection, String cpf, String nome, int idade) {
        try {
            // SQL para inserir um novo cliente
            String sql = "INSERT INTO Cliente (Cpf, nome, idade) VALUES (?, ?, ?)";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, cpf);
                statement.setString(2, nome);
                statement.setInt(3, idade);

                // Executar a inserção
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Cliente adicionado com sucesso!");
                } else {
                    System.out.println("Falha ao adicionar o cliente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os clientes no banco de dados
    public static void listarClientes(Connection connection) {
        try {
            // SQL para selecionar todos os clientes
            String sql = "SELECT Cpf, nome, idade FROM Cliente";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                // Executar a consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterar pelos resultados e imprimir os clientes
                    while (resultSet.next()) {
                        String cpf = resultSet.getString("Cpf");
                        String nome = resultSet.getString("nome");
                        int idade = resultSet.getInt("idade");

                        System.out.println("CPF: " + cpf + ", Nome: " + nome + ", Idade: " + idade);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deleta um cliente do banco de dados pelo CPF
    public static void deletarClientePorId(Connection connection, String cpf) {
        try {
            // SQL para deletar cliente pelo CPF
            String sql = "DELETE FROM Cliente WHERE Cpf = ?";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, cpf);

                // Executar a deleção
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Cliente deletado com sucesso!");
                } else {
                    System.out.println("Cliente não encontrado para deletar.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Pesquisa um cliente no banco de dados pelo CPF e/ou idade
    public static void pesquisarCliente(Connection connection, String cpf, int idade) {
        try {
            // SQL para selecionar cliente pelo CPF e/ou idade
            String sql = "SELECT Cpf, nome, idade FROM Cliente WHERE Cpf = ? AND idade = ?";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, cpf);
                statement.setInt(2, idade);

                // Executar a consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterar pelos resultados e imprimir os clientes
                    while (resultSet.next()) {
                        String clienteCpf = resultSet.getString("Cpf");
                        String nome = resultSet.getString("nome");
                        int clienteIdade = resultSet.getInt("idade");

                        System.out.println("CPF: " + clienteCpf + ", Nome: " + nome + ", Idade: " + clienteIdade);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void adicionarPrato(Connection connection, String prato, String tipoPrato, double preco) {
        try {
            // SQL para inserir um novo prato no cardápio
            String sql = "INSERT INTO Cardapio (prato, tipo_prato, preço) VALUES (?, ?, ?)";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, prato);
                statement.setString(2, tipoPrato);
                statement.setDouble(3, preco);

                // Executar a inserção
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Prato adicionado ao cardápio com sucesso!");
                } else {
                    System.out.println("Falha ao adicionar o prato ao cardápio.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para pesquisar prato pelo nome
    public static void pesquisarPratoPorNome(Connection connection, String nomePrato) {
        try {
            // SQL para selecionar prato pelo nome
            String sql = "SELECT id_prato, prato, tipo_prato, preço FROM Cardapio WHERE prato = ?";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nomePrato);

                // Executar a consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterar pelos resultados e imprimir os pratos
                    while (resultSet.next()) {
                        int idPrato = resultSet.getInt("id_prato");
                        String pratoNome = resultSet.getString("prato");
                        String tipoPrato = resultSet.getString("tipo_prato");
                        double preco = resultSet.getDouble("preço");

                        System.out.println("ID: " + idPrato + ", Prato: " + pratoNome + ", Tipo: " + tipoPrato + ", Preço: " + preco);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar prato pelo ID
    public static void deletarPratoPorId(Connection connection, int idPrato) {
        try {
            // SQL para deletar prato pelo ID
            String sql = "DELETE FROM Cardapio WHERE id_prato = ?";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idPrato);

                // Executar a deleção
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Prato deletado do cardápio com sucesso!");
                } else {
                    System.out.println("Prato não encontrado para deletar.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os pratos no cardápio
    public static void listarTodosPratos(Connection connection) {
        try {
            // SQL para selecionar todos os pratos
            String sql = "SELECT id_prato, prato, tipo_prato, preço FROM Cardapio";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                // Executar a consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterar pelos resultados e imprimir os pratos
                    while (resultSet.next()) {
                        int idPrato = resultSet.getInt("id_prato");
                        String pratoNome = resultSet.getString("prato");
                        String tipoPrato = resultSet.getString("tipo_prato");
                        double preco = resultSet.getDouble("preço");

                        System.out.println("ID: " + idPrato + ", Prato: " + pratoNome + ", Tipo: " + tipoPrato + ", Preço: " + preco);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void listarTodosPedidos(Connection connection) {
        try {
            // SQL para selecionar todos os pedidos
            String sql = "SELECT id_pedido, id_cliente, nome_cliente, preço_total FROM Pedido";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                // Executar a consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterar pelos resultados e imprimir os pedidos
                    while (resultSet.next()) {
                        int idPedido = resultSet.getInt("id_pedido");
                        int idCliente = resultSet.getInt("id_cliente");
                        String nomeCliente = resultSet.getString("nome_cliente");
                        double precoTotal = resultSet.getDouble("preço_total");

                        System.out.println("ID Pedido: " + idPedido + ", ID Cliente: " + idCliente + ", Nome Cliente: " + nomeCliente + ", Preço Total: " + precoTotal);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar pedido pelo ID
    public static void deletarPedidoPorId(Connection connection, int idPedido) {
        try {
            // SQL para deletar pedido pelo ID
            String sql = "DELETE FROM Pedido WHERE id_pedido = ?";

            // Preparar a declaração SQL
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idPedido);

                // Executar a deleção
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Pedido deletado com sucesso!");
                } else {
                    System.out.println("Pedido não encontrado para deletar.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
