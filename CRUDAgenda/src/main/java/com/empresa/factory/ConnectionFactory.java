package com.empresa.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    // Nome do usuário do MySql
    private static final String USERNAME = "root";

    // Senha do usuário do MySql
    private static final String PASSWORD = "Aluno@123";

    // Caminho (path) do MySQL, porta e banco (Esquema)
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    // Conexão com o MySQL
    public static Connection createConnectionToMySql() throws Exception {
        // Faz com que a Classe seja carregada pelo JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Cria a conexão com o Banco de Dados MySql
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        // Retorna a conexão
        return connection;
    }

    public static void main(String[] args) throws Exception {

        // Recuperar conexão se existir
        // Conecta no MySQL
        Connection con = createConnectionToMySql();

        // Verificar se já tem uma conexão ativa - Design Patterns Singleton (apenas uma
        // conexão por aplicação)
        // Testar se a conexão é nula
        if (con != null) {
            System.out.println("Havia uma conexão Banco de Dados estava aberta! Foi fechada");
            con.close();
        }

        System.out.println("Conexão com Banco de Dados aberta");
    }
}

