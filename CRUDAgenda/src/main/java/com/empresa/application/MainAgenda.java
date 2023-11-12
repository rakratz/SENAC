package com.empresa.application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.empresa.dao.ContatoDAO;
import com.empresa.model.Contato;

public class MainAgenda {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		ContatoDAO contatoDAO = new ContatoDAO();

		int choice;

		do {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1. Adicionar Contato");
			System.out.println("2. Listar Contatos");
			System.out.println("3. Atualizar Contato");
			System.out.println("4. Excluir Contato");
			System.out.println("5. Sair");

			System.out.print("Opção: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (choice) {
			case 1:
				adicionarContato(scanner, contatoDAO);
				break;
			case 2:
				listarContatos(contatoDAO);
				break;
			case 3:
				atualizarContato(scanner, contatoDAO);
				break;
			case 4:
				excluirContato(scanner, contatoDAO);
				break;
			case 5:
				System.out.println("Saindo do programa. Até logo!");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (choice != 5);

		scanner.close();
	}

	private static void adicionarContato(Scanner scanner, ContatoDAO contatoDAO) {
		System.out.println("\nAdicionar Contato:");

		Contato novoContato = new Contato();

		System.out.print("Nome: ");
		novoContato.setNome(scanner.nextLine());

		System.out.print("Idade: ");
		novoContato.setIdade(scanner.nextInt());
		scanner.nextLine(); // Limpar o buffer

		novoContato.setDataCadastro(new Date());

		contatoDAO.saveAgenda(novoContato);
	}

	private static void listarContatos(ContatoDAO contatoDAO) {
		System.out.println("\nLista de Contatos:");

		List<Contato> contatos = contatoDAO.listaAgenda();

		for (Contato contato : contatos) {
			System.out.println("ID: " + contato.getId() + " | Nome: " + contato.getNome() + " | Idade: "
					+ contato.getIdade() + " | Criado em: " + contato.getDataCadastro());
		}
	}

	private static void atualizarContato(Scanner scanner, ContatoDAO contatoDAO) {
		System.out.println("\nAtualizar Contato:");

		System.out.print("Digite o ID do Contato a ser atualizado: ");
		int contatoId = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer

		Contato contatoExistente = encontrarContatoPorId(contatoId, contatoDAO);

		if (contatoExistente != null) {
			System.out.print("Novo Nome: ");
			contatoExistente.setNome(scanner.nextLine());

			System.out.print("Nova Idade: ");
			contatoExistente.setIdade(scanner.nextInt());
			scanner.nextLine(); // Limpar o buffer

			contatoExistente.setDataCadastro(new Date());

			contatoDAO.atualizaAgenda(contatoExistente);
		}
	}

	private static void excluirContato(Scanner scanner, ContatoDAO contatoDAO) {
		System.out.println("\nExcluir Contato:");

		System.out.print("Digite o ID do Contato a ser excluído: ");
		int contatoId = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer

		Contato contatoExistente = encontrarContatoPorId(contatoId, contatoDAO);

		if (contatoExistente != null) {
			contatoDAO.apagaAgenda(contatoId);
		}
	}

	private static Contato encontrarContatoPorId(int contatoId, ContatoDAO contatoDAO) {
		List<Contato> contatos = contatoDAO.listaAgenda();

		for (Contato contato : contatos) {
			if (contato.getId() == contatoId) {
				return contato;
			}
		}

		System.out.println("Contato não encontrado com o ID: " + contatoId);
		return null;
	}
}
