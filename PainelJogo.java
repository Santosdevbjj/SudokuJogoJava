package view;

import model.Tabuleiro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A classe PainelJogo é o componente visual do tabuleiro de Sudoku.
 * Ela é responsável por desenhar a grade, as células, os números e gerenciar a interação visual com o mouse.
 */
public class PainelJogo extends JPanel {

    private Tabuleiro tabuleiro;
    private int tamanhoCelula;
    private int celulaSelecionadaLinha = -1;
    private int celulaSelecionadaColuna = -1;

    /**
     * Construtor para o PainelJogo.
     * @param tabuleiro A instância do Tabuleiro que este painel irá exibir.
     */
    public PainelJogo(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.tamanhoCelula = 500 / tabuleiro.getTamanho(); // Tamanho dinâmico da célula para caber na janela
        
        setPreferredSize(new Dimension(500, 500));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int coluna = e.getX() / tamanhoCelula;
                int linha = e.getY() / tamanhoCelula;
                
                if (linha < tabuleiro.getTamanho() && coluna < tabuleiro.getTamanho()) {
                    celulaSelecionadaLinha = linha;
                    celulaSelecionadaColuna = coluna;
                    repaint(); // Redesenha o painel para destacar a célula selecionada
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharTabuleiro(g);
    }

    /**
     * Desenha a grade do tabuleiro, os números e destaca a célula selecionada.
     * @param g O contexto gráfico.
     */
    private void desenharTabuleiro(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int tamanho = tabuleiro.getTamanho();
        int subGradeTamanho = (int) Math.sqrt(tamanho);

        // Desenha as linhas da grade
        for (int i = 0; i <= tamanho; i++) {
            int espessura = (i % subGradeTamanho == 0) ? 3 : 1;
            g2d.setStroke(new BasicStroke(espessura));
            g2d.drawLine(i * tamanhoCelula, 0, i * tamanhoCelula, tamanho * tamanhoCelula); // Linhas verticais
            g2d.drawLine(0, i * tamanhoCelula, tamanho * tamanhoCelula, i * tamanhoCelula); // Linhas horizontais
        }

        // Preenche as células e desenha os números
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                int valor = tabuleiro.getCelula(linha, coluna).getValor();
                
                // Destaca a célula selecionada
                if (linha == celulaSelecionadaLinha && coluna == celulaSelecionadaColuna) {
                    g2d.setColor(new Color(200, 220, 255)); // Azul claro
                    g2d.fillRect(coluna * tamanhoCelula + 1, linha * tamanhoCelula + 1, tamanhoCelula - 1, tamanhoCelula - 1);
                }

                if (valor != 0) {
                    g2d.setColor(Color.BLACK);
                    String valorStr = String.valueOf(valor);
                    FontMetrics fm = g2d.getFontMetrics();
                    int x = coluna * tamanhoCelula + (tamanhoCelula - fm.stringWidth(valorStr)) / 2;
                    int y = linha * tamanhoCelula + (tamanhoCelula - fm.getHeight()) / 2 + fm.getAscent();
                    g2d.drawString(valorStr, x, y);
                }
            }
        }
    }
}
