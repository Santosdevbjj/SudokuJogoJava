// src/controller/ControladorJogo.java

package controller;

import model.Tabuleiro;
import view.PainelJogo;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Main; // Importa a classe principal para reiniciar o jogo

/**
 * A classe ControladorJogo gerencia a interação do jogador com o tabuleiro.
 * Ela é responsável por tratar os eventos do teclado para preencher as células.
 */
public class ControladorJogo implements KeyListener {

    private Tabuleiro tabuleiro;
    private PainelJogo painelJogo;
    private boolean modoRascunho = false;
    private JFrame framePrincipal; // Adiciona uma referência à janela principal

    /**
     * Construtor para o ControladorJogo.
     * @param tabuleiro A instância do Tabuleiro.
     * @param painelJogo A instância do PainelJogo (a interface gráfica).
     * @param framePrincipal A janela principal do jogo.
     */
    public ControladorJogo(Tabuleiro tabuleiro, PainelJogo painelJogo, JFrame framePrincipal) {
        this.tabuleiro = tabuleiro;
        this.painelJogo = painelJogo;
        this.framePrincipal = framePrincipal;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Método não utilizado, mas obrigatório pela interface KeyListener
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int linha = painelJogo.getCelulaSelecionadaLinha();
        int coluna = painelJogo.getCelulaSelecionadaColuna();

        // Lógica de navegação com a tecla TAB
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            navegarProximaCelula();
            return;
        }

        // Alterna entre modo de rascunho e preenchimento com Ctrl
        if (e.isControlDown()) {
            modoRascunho = !modoRascunho;
            System.out.println("Modo Rascunho: " + (modoRascunho ? "Ativado" : "Desativado"));
            return;
        }

        // Se uma célula não estiver selecionada, não faz nada
        if (linha == -1 || coluna == -1) {
            return;
        }

        // Lógica de preenchimento ou rascunho
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar)) {
            int valor = Character.getNumericValue(keyChar);

            // Se a célula é fixa, não faz nada
            if (tabuleiro.getCelula(linha, coluna).isFixo()) {
                System.out.println("Não é possível alterar uma célula fixa.");
                return;
            }

            // Lógica de rascunho
            if (modoRascunho) {
                if (tabuleiro.getCelula(linha, coluna).getValor() == 0) {
                    // Adiciona ou remove o rascunho
                    if (tabuleiro.getCelula(linha, coluna).getRascunhos().contains(valor)) {
                        tabuleiro.getCelula(linha, coluna).removerRascunho(valor);
                    } else {
                        tabuleiro.getCelula(linha, coluna).adicionarRascunho(valor);
                    }
                }
            } else {
                // Lógica de preenchimento
                if (valor >= 1 && valor <= tabuleiro.getTamanho()) {
                    if (tabuleiro.setValor(linha, coluna, valor)) {
                        // Limpa os rascunhos quando um valor é preenchido
                        tabuleiro.getCelula(linha, coluna).limparRascunhos();
                    } else {
                        // feedback de valor inválido
                        System.out.println("Valor inválido para esta célula.");
                    }
                }
            }
            painelJogo.repaint();
        }

        // Depois de qualquer jogada válida, verificamos se o jogo terminou
        if (!modoRascunho && tabuleiro.isCompleto()) {
            int opcao = JOptionPane.showConfirmDialog(
                null,
                "Parabéns, você completou o Sudoku! Deseja jogar novamente?",
                "Vitória!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
            );

            if (opcao == JOptionPane.YES_OPTION) {
                framePrincipal.dispose(); // Fecha a janela atual do jogo
                Main.iniciarMenu(); // Chama o método estático para reiniciar o menu
            } else {
                System.exit(0); // Sai da aplicação
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Método não utilizado, mas obrigatório pela interface KeyListener
    }

    /**
     * Navega para a próxima célula editável (não fixa).
     */
    private void navegarProximaCelula() {
        int tamanho = tabuleiro.getTamanho();
        int linhaAtual = painelJogo.getCelulaSelecionadaLinha();
        int colunaAtual = painelJogo.getCelulaSelecionadaColuna();

        // Se nenhuma célula estiver selecionada, seleciona a primeira editável
        if (linhaAtual == -1 || colunaAtual == -1) {
            linhaAtual = 0;
            colunaAtual = -1;
        }

        for (int i = 0; i < tamanho * tamanho; i++) {
            colunaAtual++;
            if (colunaAtual >= tamanho) {
                colunaAtual = 0;
                linhaAtual++;
            }
            if (linhaAtual >= tamanho) {
                linhaAtual = 0;
            }

            if (!tabuleiro.getCelula(linhaAtual, colunaAtual).isFixo()) {
                painelJogo.setCelulaSelecionada(linhaAtual, colunaAtual);
                painelJogo.repaint();
                return;
            }
        }
    }
}
