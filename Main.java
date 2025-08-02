// src/main/Main.java

package main;

import view.MenuInicial;
import javax.swing.SwingUtilities;

public class Main {

    /**
     * O método main inicia a aplicação, chamando o método de inicialização do menu.
     */
    public static void main(String[] args) {
        // Usa uma lambda para garantir que o código da GUI seja executado na thread de despacho de eventos.
        SwingUtilities.invokeLater(Main::iniciarMenu);
    }
    
    /**
     * Inicia a janela do menu inicial do jogo.
     * Este método pode ser chamado para iniciar o jogo pela primeira vez ou para reiniciá-lo.
     */
    public static void iniciarMenu() {
        MenuInicial menu = new MenuInicial();
    }
}
