package src.main.java.com.biblioteca;

import java.util.*;

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;
        
        System.out.println("\nBem-vindo ao Sistema de Biblioteca! ");

        while (escolha != 6) {
            System.out.println(
                """
            O que deseja fazer?
            1 - Registrar Membro
            2 - Registrar Empréstimo
            3 - Devolver Livro
            4 - Adicionar Livro
            5 - Remover Livro
            6 - Sair
            """
            );

            escolha = scanner.nextInt();
            scanner.nextLine(); 

            if (escolha == 1) {
                System.out.println("Digite o nome do membro:");
                String nome = scanner.nextLine();
                System.out.println("Digite o ID do membro:");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o e-mail do membro:");
                String email = scanner.nextLine();

                Membro novoMembro = new Membro(nome, id, email);
                biblioteca.registrarMembro(novoMembro);
                System.out.println("Membro registrado com sucesso!");

            } else if (escolha == 2) {
                System.out.println("Digite o ISBN do livro que deseja emprestar:");
                String isbn = scanner.nextLine();
                System.out.println("Digite o ID do membro que deseja registrar o empréstimo:");
                int membroId = scanner.nextInt();

                Livro livroEmprestado = biblioteca.buscarLivroPorIsbn(isbn);
                Membro membroEmprestado = biblioteca.buscarMembroPorId(membroId);

                if (livroEmprestado != null && membroEmprestado != null) {
                    biblioteca.registrarEmprestimo(livroEmprestado, membroEmprestado);
                    System.out.println("Empréstimo registrado com sucesso!");
                } else {
                    System.out.println("Livro ou Membro não encontrado.");
                }

            } else if (escolha == 3) {
                System.out.println("Digite o ISBN do livro que deseja devolver:");
                String isbn = scanner.nextLine();
                System.out.println("Digite o ID do membro que está devolvendo o livro:");
                int idMembro = scanner.nextInt();

                Livro livroDevolvido = biblioteca.buscarLivroPorIsbn(isbn);
                Membro membroDevolvendo = biblioteca.buscarMembroPorId(idMembro);

                if (livroDevolvido != null && membroDevolvendo != null) {
                    biblioteca.devolverLivro(new Emprestimo(livroDevolvido, membroDevolvendo, new Date()));
                    System.out.println("Livro devolvido com sucesso!");
                } else {
                    System.out.println("Livro ou Membro não encontrado.");
                }

            } else if (escolha == 4) {
                System.out.println("Digite o ISBN do livro:");
                String isbn = scanner.nextLine();
                System.out.println("Digite o título do livro:");
                String tituloLivro = scanner.nextLine();

                Livro novoLivro = new Livro(isbn, tituloLivro);
                biblioteca.adicionarLivro(novoLivro);
                System.out.println("Livro adicionado com sucesso!");

            } else if (escolha == 5) {
                System.out.println("Digite o ISBN do livro que deseja remover:");
                String isbn = scanner.nextLine();

                Livro livroRemover = biblioteca.buscarLivroPorIsbn(isbn);

                if (livroRemover != null) {
                    biblioteca.removerLivro(livroRemover);
                    System.out.println("Livro removido com sucesso!");
                } else {
                    System.out.println("Livro não encontrado.");
                }

            } else if (escolha == 6) {
                System.out.println("Saindo do sistema. Até logo!");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
