// src/model/Tabuleiro.java

package model;

import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

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

        this.subGradeTamanho = (int) Math.sqrt(tamanho);

        if (subGradeTamanho * subGradeTamanho != tamanho) {
            throw new IllegalArgumentException("O tamanho do tabuleiro deve ser um quadrado perfeito (9, 16, 25, etc.).");
        }

        // Inicializa todas as células e depois gera o tabuleiro
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                this.grade[i][j] = new Celula();
            }
        }

        gerarTabuleiro();
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

    /**
     * Gera um tabuleiro de Sudoku completo e válido, e em seguida remove
     * números para criar o quebra-cabeça.
     */
    public void gerarTabuleiro() {
        preencherTabuleiro(0, 0);
        
        // Remove números para criar o desafio
        removerNumeros(tamanho * tamanho / 2);
    }

    /**
     * Algoritmo de backtracking para preencher o tabuleiro de forma recursiva.
     * @param linha A linha atual a ser preenchida.
     * @param coluna A coluna atual a ser preenchida.
     * @return true se a grade foi preenchida com sucesso, false caso contrário.
     */
    private boolean preencherTabuleiro(int linha, int coluna) {
        if (linha == tamanho) {
            return true;
        }

        int proximaLinha = (coluna == tamanho - 1) ? linha + 1 : linha;
        int proximaColuna = (coluna == tamanho - 1) ? 0 : coluna + 1;

        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= tamanho; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros);

        for (int numero : numeros) {
            if (isValorValido(linha, coluna, numero)) {
                grade[linha][coluna].setValor(numero);
                if (preencherTabuleiro(proximaLinha, proximaColuna)) {
                    return true;
                }
                grade[linha][coluna].setValor(0); // Backtrack
            }
        }
        return false;
    }

    /**
     * Remove um número específico de células para criar o desafio do Sudoku.
     * @param numRemocoes O número de células a serem esvaziadas.
     */
    private void removerNumeros(int numRemocoes) {
        Random random = new Random();
        int celulasRemovidas = 0;
        while (celulasRemovidas < numRemocoes) {
            int linha = random.nextInt(tamanho);
            int coluna = random.nextInt(tamanho);

            if (grade[linha][coluna].getValor() != 0) {
                grade[linha][coluna].setValor(0); // Remove o número
                grade[linha][coluna].setFixo(true); // Marca como célula fixa
                celulasRemovidas++;
            }
        }
    }

    /**
     * Verifica se o tabuleiro está completamente preenchido e se é válido.
     * @return true se o jogador venceu, false caso contrário.
     */
    public boolean isCompleto() {
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                if (grade[linha][coluna].getValor() == 0) {
                    return false;
                }
            }
        }
        return isTabuleiroValido();
    }
    
    /**
     * Verifica se o estado atual do tabuleiro é válido (sem repetições).
     * @return true se o tabuleiro é válido, false caso contrário.
     */
    public boolean isTabuleiroValido() {
        for (int i = 0; i < tamanho; i++) {
            if (!isLinhaValida(i) || !isColunaValida(i) || !isSubGradeValida(i)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checa se uma linha específica é válida.
     * @param linha A linha a ser verificada.
     * @return true se a linha for válida, false caso contrário.
     */
    private boolean isLinhaValida(int linha) {
        boolean[] numerosVistos = new boolean[tamanho + 1];
        for (int coluna = 0; coluna < tamanho; coluna++) {
            int valor = grade[linha][coluna].getValor();
            if (valor != 0) {
                if (numerosVistos[valor]) {
                    return false;
                }
                numerosVistos[valor] = true;
            }
        }
        return true;
    }
    
    /**
     * Checa se uma coluna específica é válida.
     * @param coluna A coluna a ser verificada.
     * @return true se a coluna for válida, false caso contrário.
     */
    private boolean isColunaValida(int coluna) {
        boolean[] numerosVistos = new boolean[tamanho + 1];
        for (int linha = 0; linha < tamanho; linha++) {
            int valor = grade[linha][coluna].getValor();
            if (valor != 0) {
                if (numerosVistos[valor]) {
                    return false;
                }
                numerosVistos[valor] = true;
            }
        }
        return true;
    }
    
    /**
     * Checa se uma sub-grade específica é válida.
     * @param indiceSubGrade O índice da sub-grade (0 a tamanho-1).
     * @return true se a sub-grade for válida, false caso contrário.
     */
    private boolean isSubGradeValida(int indiceSubGrade) {
        boolean[] numerosVistos = new boolean[tamanho + 1];
        int inicioLinha = (indiceSubGrade / subGradeTamanho) * subGradeTamanho;
        int inicioColuna = (indiceSubGrade % subGradeTamanho) * subGradeTamanho;

        for (int i = 0; i < subGradeTamanho; i++) {
            for (int j = 0; j < subGradeTamanho; j++) {
                int valor = grade[inicioLinha + i][inicioColuna + j].getValor();
                if (valor != 0) {
                    if (numerosVistos[valor]) {
                        return false;
                    }
                    numerosVistos[valor] = true;
                }
            }
        }
        return true;
    }
}
