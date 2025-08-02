// src/model/Celula.java

package model;

import java.util.ArrayList;


/**
 * A classe Celula representa uma única célula no tabuleiro de Sudoku.
 * Cada célula armazena um valor numérico e um status que indica se o valor é fixo (original do jogo)
 * ou se foi inserido pelo jogador. Também permite armazenar rascunhos.
 */
public class Celula {

    private int valor;
    private boolean fixo;
    private int[] rascunhos;

    /**
     * Construtor para a classe Celula.
     */
    public Celula() {
        this.valor = 0; // 0 significa célula vazia
        this.fixo = false;
        // O array de rascunhos pode ter um tamanho fixo, por exemplo, 9 para o Sudoku 9x9.
        // Adaptaremos isso dinamicamente dependendo do tamanho do tabuleiro.
        // Por enquanto, usaremos um tamanho padrão para demonstrar a ideia.
        this.rascunhos = new int[9];
    }

    /**
     * Obtém o valor atual da célula.
     * @return O valor da célula.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Define um novo valor para a célula.
     * @param valor O novo valor a ser definido.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Verifica se a célula tem um valor fixo (pré-definido pelo jogo).
     * @return true se o valor for fixo, false caso contrário.
     */
    public boolean isFixo() {
        return fixo;
    }

    /**
     * Define se a célula tem um valor fixo.
     * @param fixo true para fixar o valor, false para torná-lo editável.
     */
    public void setFixo(boolean fixo) {
        this.fixo = fixo;
    }
}




// ALTEREI AQUI

/**
 * A classe Celula representa uma única célula no tabuleiro de Sudoku.
 * Cada célula armazena um valor numérico, um status fixo e uma lista de rascunhos.
 */
public class Celula {

    private int valor;
    private boolean fixo;
    private ArrayList<Integer> rascunhos;

    public Celula() {
        this.valor = 0;
        this.fixo = false;
        this.rascunhos = new ArrayList<>();
    }

    // ... (getters e setters existentes) ...

    /**
     * Adiciona um número aos rascunhos da célula.
     * @param numero O número a ser adicionado como rascunho.
     */
    public void adicionarRascunho(int numero) {
        if (!rascunhos.contains(numero)) {
            rascunhos.add(numero);
        }
    }

    /**
     * Remove um número dos rascunhos da célula.
     * @param numero O número a ser removido.
     */
    public void removerRascunho(int numero) {
        rascunhos.remove(Integer.valueOf(numero));
    }
    
    /**
     * Limpa todos os rascunhos da célula.
     */
    public void limparRascunhos() {
        rascunhos.clear();
    }
    
    /**
     * Retorna a lista de rascunhos.
     * @return A lista de rascunhos da célula.
     */
    public ArrayList<Integer> getRascunhos() {
        return rascunhos;
    }
}

 





