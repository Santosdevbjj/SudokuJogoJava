package model;

import java.util.Random;

/**
 * A classe Tabuleiro gerencia a matriz de células do jogo Sudoku.
 * É responsável por gerar um tabuleiro válido, remover números para criar o desafio
 * e validar as regras do jogo (linhas, colunas e sub-grades).
 */
public class Tabuleiro {

    private int tamanho;
    private int subGradeTamanho;
    private Celula[][] grade;

    /**
     * Construtor para a classe Tabuleiro.
     * @param tamanho O tamanho da grade (ex: 9 para 9x9).
     */
    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.grade = new Celula[tamanho][tamanho];

        // Calcula o tamanho da sub-grade (ex: 3 para 9x9, 4 para 16x16, etc.)
        this.subGradeTamanho = (int) Math.sqrt(tamanho);

        // Inicializa todas as células
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                this.grade[i][j] = new Celula();
            }
        }

        // TODO: Implementar a lógica de geração de um tabuleiro completo e válido.
        // Por enquanto, faremos uma implementação simples para demonstração.
        // A lógica de geração de Sudoku é complexa, envolvendo backtracking.
    }

    /**
     * Retorna a célula em uma posição específica do tabuleiro.
     * @param linha A linha da célula.
     * @param coluna A coluna da célula.
     * @return A célula na posição (linha, coluna).
     */
    public Celula getCelula(int linha, int coluna) {
        return this.grade[linha][coluna];
    }

    /**
     * Define um valor para uma célula, validando as regras do Sudoku.
     * @param linha A linha da célula.
     * @param coluna A coluna da célula.
     * @param valor O valor a ser inserido.
     * @return true se o valor for válido e inserido, false caso contrário.
     */
    public boolean setValor(int linha, int coluna, int valor) {
        if (!isValorValido(linha, coluna, valor)) {
            return false;
        }
        this.grade[linha][coluna].setValor(valor);
        return true;
    }

    /**
     * Verifica se um valor é válido para ser colocado em uma célula,
     * seguindo as regras do Sudoku (linha, coluna e sub-grade).
     * @param linha A linha da célula.
     * @param coluna A coluna da célula.
     * @param valor O valor a ser verificado.
     * @return true se o valor for válido, false caso contrário.
     */
    public boolean isValorValido(int linha, int coluna, int valor) {
        // Verifica a linha
        for (int i = 0; i < tamanho; i++) {
            if (i != coluna && this.grade[linha][i].getValor() == valor) {
                return false;
            }
        }

        // Verifica a coluna
        for (int i = 0; i < tamanho; i++) {
            if (i != linha && this.grade[i][coluna].getValor() == valor) {
                return false;
            }
        }

        // Verifica a sub-grade
        int inicioLinhaSubGrade = linha - linha % subGradeTamanho;
        int inicioColunaSubGrade = coluna - coluna % subGradeTamanho;
        for (int i = 0; i < subGradeTamanho; i++) {
            for (int j = 0; j < subGradeTamanho; j++) {
                int l = inicioLinhaSubGrade + i;
                int c = inicioColunaSubGrade + j;
                if (l != linha || c != coluna) {
                    if (this.grade[l][c].getValor() == valor) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
