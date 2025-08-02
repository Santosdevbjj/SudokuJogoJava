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



// xxxxxxxxxxxxxxxxxxxx

    /**
     * Gera um tabuleiro de Sudoku completo e válido, e em seguida remove
     * números para criar o quebra-cabeça.
     */
    public void gerarTabuleiro() {
        preencherTabuleiro(0, 0); // Inicia o preenchimento a partir da primeira célula
        
        // Remove números para criar o desafio
        removerNumeros(tamanho * tamanho / 2); // Remove metade das células
    }

    /**
     * Algoritmo de backtracking para preencher o tabuleiro de forma recursiva.
     * @param linha A linha atual a ser preenchida.
     * @param coluna A coluna atual a ser preenchida.
     * @return true se a grade foi preenchida com sucesso, false caso contrário.
     */
    private boolean preencherTabuleiro(int linha, int coluna) {
        if (linha == tamanho) {
            return true; // Tabuleiro preenchido
        }

        int proximaLinha = (coluna == tamanho - 1) ? linha + 1 : linha;
        int proximaColuna = (coluna == tamanho - 1) ? 0 : coluna + 1;

        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= tamanho; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros); // Embaralha os números para gerar tabuleiros diferentes

        for (int numero : numeros) {
            if (isValorValido(linha, coluna, numero)) {
                grade[linha][coluna].setValor(numero);
                if (preencherTabuleiro(proximaLinha, proximaColuna)) {
                    return true;
                }
                grade[linha][coluna].setValor(0); // Backtrack: se não deu certo, limpa a célula
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
                int valorTemporario = grade[linha][coluna].getValor();
                grade[linha][coluna].setValor(0); // Remove o número
                
                // (Opcional) Verificação de unicidade da solução, mas para este projeto,
                // uma única solução é suficiente.

                grade[linha][coluna].setFixo(true); // Marca como célula fixa
                celulasRemovidas++;
            }
        } 





// Alterei aqui. 


        
    
    // (atributos e construtor anterior)


    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        this.grade = new Celula[tamanho][tamanho];
        this.subGradeTamanho = (int) Math.sqrt(tamanho);

        if (subGradeTamanho * subGradeTamanho != tamanho) {
            // Adicionando uma verificação para garantir que o tamanho do tabuleiro
            // seja um quadrado perfeito, o que é essencial para o Sudoku.
            throw new IllegalArgumentException("O tamanho do tabuleiro deve ser um quadrado perfeito (9, 16, 25, etc.).");
        }

        // Inicializa todas as células e depois gera o tabuleiro
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                this.grade[i][j] = new Celula();
            }
        }

        // Chama o método para gerar um tabuleiro completo e válido
        gerarTabuleiro();
    }
    
    // (restante do código da classe Tabuleiro)
}


















    }

