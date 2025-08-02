// src/view/MenuInicial.java

package view;

import model.Tabuleiro;
import controller.ControladorJogo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





/**
 * A classe MenuInicial é responsável por criar a interface gráfica do menu inicial do jogo Sudoku.
 * Nela, o jogador pode inserir seu nome e escolher o tamanho do tabuleiro antes de iniciar o jogo.
 */
public class MenuInicial extends JFrame {

    private JTextField nomeJogadorField;
    private JComboBox<String> tamanhoTabuleiroComboBox;
    private JButton iniciarButton;

    public MenuInicial() {
        // Configurações básicas da janela
        setTitle("Sudoku - Menu Inicial");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criação dos componentes
        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 linhas, 1 coluna, com espaçamento
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens internas

        JLabel tituloLabel = new JLabel("Bem-vindo ao Sudoku!", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel nomeLabel = new JLabel("Seu Nome:");
        nomeJogadorField = new JTextField();

        JLabel tamanhoLabel = new JLabel("Escolha o Tamanho do Tabuleiro:");
        String[] tamanhos = {"9x9", "12x12", "16x16", "25x25", "30x30", "32x32"};
        tamanhoTabuleiroComboBox = new JComboBox<>(tamanhos);

        iniciarButton = new JButton("Iniciar Jogo");

        // Adiciona os componentes ao painel
        painel.add(tituloLabel);
        painel.add(nomeLabel);
        painel.add(nomeJogadorField);
        painel.add(tamanhoLabel);
        painel.add(tamanhoTabuleiroComboBox);
        painel.add(iniciarButton);

        // Adiciona o painel à janela
        add(painel);

        // Adiciona um listener ao botão de iniciar
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });
    }

    /**
     * Este método é chamado quando o botão "Iniciar Jogo" é clicado.
     * Ele obtém o nome do jogador e o tamanho do tabuleiro selecionado
     * e, em seguida, inicia a próxima etapa do jogo.
     */
    private void iniciarJogo() {
        String nome = nomeJogadorField.getText().trim();
        String tamanhoStr = (String) tamanhoTabuleiroComboBox.getSelectedItem();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira seu nome.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int tamanho = Integer.parseInt(tamanhoStr.split("x")[0]);
        JOptionPane.showMessageDialog(this,
            "Olá, " + nome + "! Você escolheu um tabuleiro " + tamanhoStr + ". O jogo vai começar!",
            "Jogo Iniciado", JOptionPane.INFORMATION_MESSAGE);

        // TODO: Aqui vamos instanciar a classe principal do jogo, passando o nome e o tamanho
        // Ex: new PainelJogo(nome, tamanho);
        this.dispose(); // Fecha a janela do menu inicial
    }
} 




// FIZ ALTERAÇÃO AQUI



public class MenuInicial extends JFrame {

    // ... (atributos) ...
    
    public MenuInicial() {
        // ... (código existente) ...

        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });
        
        // Finaliza o setup da janela
        pack();
        setVisible(true);
    }
    
    private void iniciarJogo() {
        String nome = nomeJogadorField.getText().trim();
        String tamanhoStr = (String) tamanhoTabuleiroComboBox.getSelectedItem();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira seu nome.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int tamanho = Integer.parseInt(tamanhoStr.split("x")[0]);
        this.dispose(); // Fecha a janela do menu inicial
        
        // Instancia o tabuleiro, o painel do jogo e o controlador
        Tabuleiro tabuleiro = new Tabuleiro(tamanho);
        PainelJogo painelJogo = new PainelJogo(tabuleiro);
        
        // Cria a nova janela do jogo
        JFrame jogoFrame = new JFrame("Sudoku - " + nome);
        ControladorJogo controlador = new ControladorJogo(tabuleiro, painelJogo, jogoFrame);
        
        jogoFrame.add(painelJogo);
        jogoFrame.pack();
        jogoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jogoFrame.setLocationRelativeTo(null);
        jogoFrame.addKeyListener(controlador);
        jogoFrame.setVisible(true);
        
        painelJogo.setFocusable(true);
        painelJogo.requestFocusInWindow();
    }
}








