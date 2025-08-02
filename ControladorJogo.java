package controller;

import model.Tabuleiro;
import view.PainelJogo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A classe ControladorJogo gerencia a interação do jogador com o tabuleiro.
 * Ela é responsável por tratar os eventos do teclado para preencher as células.
 */
public class ControladorJogo implements KeyListener {

    private Tabuleiro tabuleiro;
    private PainelJogo painelJogo;

    /**
     * Construtor para o ControladorJogo.
     * @param tabuleiro A instância do Tabuleiro.
     * @param painelJogo A instância do PainelJogo (a interface gráfica).
     */
    public ControladorJogo(Tabuleiro tabuleiro, PainelJogo painelJogo) {
        this.tabuleiro = tabuleiro;
        this.painelJogo = painelJogo;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: Implementar a lógica para navegação com a tecla TAB.
        // A lógica de navegação será mais complexa, focaremos no preenchimento primeiro.

        // Trata a entrada de números
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar)) {
            int valor = Character.getNumericValue(keyChar);
            // Verifica se uma célula está selecionada e se o valor é válido
            if (painelJogo.getCelulaSelecionadaLinha() != -1 && painelJogo.getCelulaSelecionadaColuna() != -1) {
                int linha = painelJogo.getCelulaSelecionadaLinha();
                int coluna = painelJogo.getCelulaSelecionadaColuna();

                // TODO: Adicionar a verificação para o tamanho máximo permitido (ex: 9 para 9x9).
                if (tabuleiro.setValor(linha, coluna, valor)) {
                    // Valor válido, atualiza o tabuleiro e a interface
                    painelJogo.repaint();
                } else {
                    // Valor inválido, pode exibir um feedback visual ou uma mensagem
                    System.out.println("Valor inválido para esta célula.");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
