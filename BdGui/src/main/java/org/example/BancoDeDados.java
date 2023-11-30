package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {

    // URL de conexão com o banco de dados PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/cardapio";

    // Nome de usuário do banco de dados
    private static final String USUARIO = "postgres";

    // Senha de acesso ao banco de dados
    private static final String SENHA = "merg01";

    /**
     * Estabelece a conexão com o banco de dados.
     *
     * @return Objeto Connection representando a conexão estabelecida.
     * @throws SQLException Se houver um erro durante a conexão.
     */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @param conexao Objeto Connection representando a conexão a ser fechada.
     */
    public static void fecharConexao(Connection conexao) {
        try {
            // Verifica se a conexão não é nula e não está fechada
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();  // Fecha a conexão
            }
        } catch (SQLException e) {
            // Imprime mensagem de erro em caso de falha no fechamento da conexão
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
