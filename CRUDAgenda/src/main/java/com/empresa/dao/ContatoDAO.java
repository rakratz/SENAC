package com.empresa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.empresa.factory.ConnectionFactory;
import com.empresa.model.Contato;

public class ContatoDAO {

	public void saveAgenda(Contato contato) {
		// 1 2 3
		String sqlQuery = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?, ?, ?);";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o MySQL
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = conn.prepareStatement(sqlQuery);

			// Adcionar os valores na Query SQL
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new java.sql.Date(contato.getDataCadastro().getTime()));

			// Executar a Query SQL
			pstm.execute();
			System.out.println("Novo Contato Criado com Sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao tentar criar o Contato na Agenda! ");
			System.out.println(e);
		} finally {
			// Fecha conexão
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("Erro ao fechar a Conexão com o Banco de Dados! ");
				System.out.println(e);
			}
		}
	}

	public List<Contato> listaAgenda() {
		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection conn = null;

		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;

		// ResultSet é uma interface na linguagem de programação Java que fornece
		// métodos para acessar e manipular os resultados de consultas SQL executadas
		// em um banco de dados. Um objeto ResultSet mantém um ponteiro que aponta
		// para a linha de dados atual no conjunto de resultados.

		try {
			// Conecta no Banco de Dados
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			// Buscar todos os dados da Agenda
			while (rset.next()) {

				Contato contato = new Contato();

				// Recuperar o id
				contato.setId(rset.getInt("id"));
				// Recuperar o nome
				contato.setNome(rset.getString("nome"));
				// Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				// Recuperar a data de cadastrado
				contato.setDataCadastro(rset.getDate("dataCadastro"));

				contatos.add(contato);

			}
		} catch (Exception e) {
			System.out.println("Erro ao tentar listar os Contatos na Agenda! ");
			System.out.println(e);
		} finally {
			// Fecha conexões
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("Erro ao fechar a Conexão com o Banco de Dados! ");
				System.out.println(e);
			}
		}

		return contatos;
	}

	public void atualizaAgenda(Contato contato) {
		String sqlQuery = "UPDATE contatos SET nome=?, idade=?, dataCadastro=? WHERE id=?";

		try (Connection conn = ConnectionFactory.createConnectionToMySql();
				PreparedStatement pstm = conn.prepareStatement(sqlQuery)) {

			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new java.sql.Date(contato.getDataCadastro().getTime()));
			pstm.setInt(4, contato.getId()); // Defina o ID do contato a ser atualizado

			int rowsAffected = pstm.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Contato atualizado com sucesso!");
			} else {
				System.out.println("Nenhum contato foi atualizado. Verifique o ID.");
			}

		} catch (Exception e) {
			System.out.println("Erro ao tentar atualizar o Contato na Agenda! ");
			System.out.println(e);
		}
	}
	
    public void apagaAgenda(int contatoId) {
        String sqlQuery = "DELETE FROM contatos WHERE id=?";

        try (Connection conn = ConnectionFactory.createConnectionToMySql();
             PreparedStatement pstm = conn.prepareStatement(sqlQuery)) {

            pstm.setInt(1, contatoId); // Defina o ID do contato a ser excluído

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Contato excluído com sucesso!");
            } else {
                System.out.println("Nenhum contato foi excluído. Verifique o ID.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao tentar excluir o Contato na Agenda! ");
            System.out.println(e);
        }
    }
}