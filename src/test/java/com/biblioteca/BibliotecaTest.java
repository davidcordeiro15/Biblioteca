package com.biblioteca;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.biblioteca.*;

import java.util.*;

public class BibliotecaTest {

    private Biblioteca biblioteca;
    private Livro livro;
    private Membro membro;

    @BeforeEach
    public void setUp() {
        biblioteca = new Biblioteca();
        livro = new Livro("123456789", "Livro Teste");
        membro = new Membro("Membro A", 1, "emailA@fiap.com.br");
    }

    @Test
    public void testAdicionarLivro() {
        biblioteca.adicionarLivro(livro);
        assertTrue(biblioteca.getLivros().contains(livro), "Livro não foi adicionado corretamente.");
    }

    @Test
    public void testRemoverLivro() {
        biblioteca.adicionarLivro(livro);
        biblioteca.removerLivro(livro);
        assertFalse(biblioteca.getLivros().contains(livro), "Livro não foi removido corretamente.");
    }

    @Test
    public void testRegistrarMembro() {
        biblioteca.registrarMembro(membro);
        assertTrue(biblioteca.getMembros().contains(membro), "Membro não foi registrado corretamente.");
    }

    @Test
    public void testRegistrarEmprestimo() {
        biblioteca.adicionarLivro(livro);
        biblioteca.registrarMembro(membro);
        
        Emprestimo emprestimoEsperado = new Emprestimo(livro, membro, new Date());
        biblioteca.registrarEmprestimo(livro, membro);

        boolean emprestimoRegistrado = false;
        for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
            if (emprestimo.getLivro().equals(emprestimoEsperado.getLivro()) &&
                emprestimo.getMembro().equals(emprestimoEsperado.getMembro())) {
                emprestimoRegistrado = true;
                break;
            }
        }

        assertTrue(emprestimoRegistrado, "Empréstimo não foi registrado corretamente.");
    }

    @Test
    public void testDevolverLivro() {
        biblioteca.adicionarLivro(livro);
        biblioteca.registrarMembro(membro);
        Emprestimo emprestimo = new Emprestimo(livro, membro, new Date());
        biblioteca.registrarEmprestimo(livro, membro);
        biblioteca.devolverLivro(emprestimo);
        assertFalse(biblioteca.getEmprestimos().contains(emprestimo), "Livro não foi devolvido corretamente.");
    }

    @Test
    public void testBuscarLivroPorIsbn() {
        biblioteca.adicionarLivro(livro);
        Livro livroEncontrado = biblioteca.buscarLivroPorIsbn("123456789");
        assertEquals(livro, livroEncontrado, "Livro não foi encontrado corretamente pelo ISBN.");
    }

    @Test
    public void testBuscarMembroPorId() {
        biblioteca.registrarMembro(membro);
        Membro membroEncontrado = biblioteca.buscarMembroPorId(1);
        assertEquals(membro, membroEncontrado, "Membro não foi encontrado corretamente pelo ID.");
    }
}
