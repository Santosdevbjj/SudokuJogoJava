
Aqui está um README.md completo em Markdown para o seu projeto Sudoku‑Java-GUI:


---

Sudoku‑Java‑GUI

Um jogo de Sudoku com interface gráfica (Java Swing), desenvolvido com foco em programação orientada a objetos, com suporte a tabuleiros de múltiplos tamanhos (9×9, 12×12, 16×16, 25×25, 30×30, 32×32), geração automática de puzzles com solução única, sistema de dicas (“hint”), exportação para CSV e área para rascunhos/candidatos.

Projetado para ser um portfólio completo: com classes bem definidas, JavaDoc, estrutura clara e fácil rodar tanto por IDE quanto pela linha de comando.


---

🎯 Funcionalidades principais

Geração automática de tabuleiros com solução única, usando backtracking.

Tamanhos suportados: 9×9, 12×12, 16×16, 25×25, 30×30, 32×32.

Interface gráfica responsiva usando Java Swing:

Menu inicial para digitar o nome do jogador e escolher o tamanho do tabuleiro.

Grade navegável com teclas Tab / Shift+Tab para mover-se entre células vazias.

Suporte à entrada por mouse ou teclado.

Validação em tempo real da jogada para evitar violar regras: linha, coluna ou bloco.

Painel lateral para rascunho de candidatos (digits de 1 a 9).


Funcionalidade de dica (“hint”): preenche corretamente uma célula vazia ou mostra candidatos possíveis.

Exportação da situação atual do tabuleiro para arquivo CSV (com células vazias como “0”).

Verificação final do tabuleiro e mensagem de vitória.

Código organizado em pacotes com documentação JavaDoc (fácil para avaliadores).



---

🏗️ Estrutura do projeto

sudoku-java-gui/
├── README.md
├── src/
│   ├── sudoku/
│   │   ├── Main.java
│   │   └── ui/
│   │       ├── MainMenuFrame.java
│   │       ├── GameFrame.java
│   │       ├── [outras classes de UI…]
│   └── sudoku/
│       └── core/
│           ├── Cell.java
│           ├── Board.java
│           ├── SudokuSolver.java
│           ├── PuzzleGenerator.java
│           ├── HintService.java
│           └── CsvExporter.java
└── pom.xml ou build.gradle (opcional)


---

📦 Descrição das classes (pacote sudoku.core e sudoku.ui)

Classe	Pacote	Função principal

Main	sudoku	Inicializa aplicação; mostra menu inicial (nome + tamanho), abre janela do jogo.
MainMenuFrame	sudoku.ui	Tela Swing para entrada do nome do jogador, seleção do tamanho e botão “Começar”.
GameFrame	sudoku.ui	Janela principal do jogo: grade de células, painel de rascunho, menu de exportação e dica.
Cell	sudoku.core	Representa cada célula do tabuleiro: valor, se é fixa (dica), candidatos.
Board	sudoku.core	Representação abstrata do tabuleiro: valor das células, validações de jogadas.
SudokuSolver	sudoku.core	Resolve tabuleiros por backtracking, contando até 2 soluções (para gerar puzzles únicos).
PuzzleGenerator	sudoku.core	Gera solução completa e “cava” pistas removendo números de modo que permaneça única solução.
HintService	sudoku.core	Gera dicas: preenche aleatoriamente uma célula vazia correta ou exibe candidatos.
CsvExporter	sudoku.core ou sudoku.ui	Exporta o estado atual do Board para arquivo CSV legível (valores separados por vírgula).


Todas as classes contêm JavaDoc explicando parâmetros, lógica interna e comportamento, facilitando a leitura automática por avaliadores (IDE ou geradores de documentação).


---

🚀 Instruções para compilar e rodar

Requisitos

Java 11+ (funciona com Java 17 ou acima).

(Opcional) Maven ou Gradle para compilação automatizada.

IDE recomendada: IntelliJ IDEA, Eclipse ou VS Code com extensão Java.


A) Usando IDE

1. Clone o repositório:

git clone https://github.com/seu-usuario/sudoku-java-gui.git


2. Abra o projeto pela estrutura de diretórios (sem necessariamente ter pom.xml).


3. Configure o classpath para incluir a pasta src/.


4. Execute a classe sudoku.Main como aplicação Java.



B) Linha de comando (sem Maven)

# Dentro da raiz do projeto
javac -d out $(find src -name "*.java")
java -cp out sudoku.Main

C) Com Maven (se incluído pom.xml)

# Para compilar e empacotar
mvn clean package

# Executar Jar
java -jar target/sudoku‑gui‑1.0.jar


---

🕹️ Como jogar

1. Ao abrir o app, insira o nome do jogador e selecione o tamanho do tabuleiro no combo (ex: “9×9”, “16×16” etc.).


2. Clique em Começar → será gerado um tabuleiro válido com pistas iniciais.


3. Preencha células vazias:

Clique com mouse e digite ou

Pressione Tab / Shift+Tab para navegar células vazias.



4. Se digitar um valor inválido (que violaria as regras), o jogável é rejeitado e destacado.


5. Use o painel de rascunho para digitar ou marcar possíveis candidatos em uma célula.


6. Pressione o botão Dica (“Hint”) para que o jogo preencha uma célula correta automaticamente.


7. Para exportar o estado em CSV, vá no menu ou botão Exportar como CSV; escolha local e salve.


8. Quando terminar, clique em Verificar solução e ganhe uma mensagem de vitória se estiver correto.




---

✅ Check‑list de funcionalidades

[x] POO com Cell, Board, PuzzleGenerator, SudokuSolver, CsvExporter, HintService

[x] Geração de puzzle único (backtracking + unhas pistas removidas cuidadosamente)

[x] Interface gráfica Swing com menu, grade e painel lateral

[x] Entrada por teclado e mouse; navegação com Tab

[x] Validação de jogadas instantânea

[x] Painel de rascunho para candidatos

[x] Modalidade Hint para ajudar sem resolver todo o tabuleiro

[x] Exportação CSV legível

[x] Mensagem de conclusão ao completar corretamente



---

🛠️ Melhorias futuras (sugestões)

Níveis de dificuldade customizados: remover mais pistas para dificuldade difícil, menos para easy.

Undo / Redo: permite desfazer jogadas usando padrão Memento ou Command.

Dicas mais sofisticadas: naked single, hidden pair, X‑Wing (em HintService com explicação).

Tema visual: cores, fontes, modo escuro/claro; usar FlatLaf no Swing.

Versão JavaFX ou Web para rodar em browser ou como app moderno.

Gravação de progresso ou scores, usando arquivos JSON ou SQLite.



---

📜 Licença

Este código está licenciado sob a MIT License (ou escolha outra compatível). Você pode usar, modificar e compartilhar livremente—certifique-se apenas de manter os créditos no repositório.


---

Em resumo...

O Sudoku‑Java‑GUI é um projeto robusto, ideal para demonstrar seus conhecimentos em Java OOP, algoritmos de geração e resolução, interface gráfica responsiva e boas práticas de documentação. Com este README, o avaliador tem tudo que precisa para entender, compilar e executar o código sem dificuldades.


