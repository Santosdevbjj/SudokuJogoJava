import model.Tabuleiro;
import view.MenuInicial;
import view.PainelJogo;
import controller.ControladorJogo;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuInicial menu = new MenuInicial();
            menu.setVisible(true);

            // TODO: Ajustar o MenuInicial para chamar a lógica abaixo quando o botão for clicado
            // Exemplo de como a lógica de inicialização do jogo funcionará:
            
            // int tamanhoTabuleiro = 9; // Tamanho escolhido pelo jogador
            // Tabuleiro tabuleiro = new Tabuleiro(tamanhoTabuleiro);
            // PainelJogo painelJogo = new PainelJogo(tabuleiro);
            // ControladorJogo controlador = new ControladorJogo(tabuleiro, painelJogo);
            
            // JFrame jogoFrame = new JFrame("Sudoku - Jogo");
            // jogoFrame.add(painelJogo);
            // jogoFrame.pack(); // Ajusta o tamanho da janela ao conteúdo
            // jogoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // jogoFrame.setLocationRelativeTo(null);
            // jogoFrame.addKeyListener(controlador);
            // jogoFrame.setVisible(true);
            
            // Para que o KeyListener funcione, o componente deve ter foco.
            // painelJogo.requestFocusInWindow();
        });
    }
}
