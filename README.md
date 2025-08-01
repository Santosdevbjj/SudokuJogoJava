



🏗️ Pacotes e descrição das classes

Toda a lógica do jogo está separada em dois pacotes principais:

Pacote sudoku.core: “o motor do jogo”

Cell
Representa cada célula do tabuleiro. Guarda a posição (linha, coluna), o valor atual (0 para vazio ou de 1 a maxDigit) e uma flag indicando se é uma dica fixa (pista inicial). A célula permite leitura e escrita do valor (exceto se fixa), e também manipula candidatos (rascunho), caso o jogador queira marcar possibilidades.

Board
Encarrega-se da estrutura do tabuleiro no formato size × size, além das regras de Sudoku: não permitir repetição de valores em linhas, colunas ou blocos. Oferece métodos para acessar células, verificar se uma jogada é válida, listar candidatos para uma posição vazia (eliminação lógica), e verificar se o tabuleiro está completo. Também possui método para criar cópias profundas da instância—fundamental para solver, dicas ou exportação.

SudokuSolver
Implementa o algoritmo de backtracking que resolve o tabuleiro. Aceita um parâmetro maxSolutions, que costuma ser 2 ao gerar puzzles, para interromper a busca assim que um segundo caminho válido é encontrado. Assim, serve para verificar unicidade da solução e completar o tabuleiro quando necessário.

PuzzleGenerator
Responsável por gerar o Sudoku “do zero”: primeiro cria uma solução completa via solvers embaralhados, depois remove pistas gradualmente, cada remoção validada para manter única solução. O resultado é um tabuleiro lógico, com retórica humana, pronto para jogar.

HintService
Controla a lógica de dica (“hint”) dentro do jogo. Ao ser acionado, escolhe rigorosamente uma célula vazia, obtém o valor correto da solução completa (armazenada no PuzzleGenerator) e preenche a célula ou, se assistido no modo “mostrar candidatos”, retorna os valores possíveis conforme lógica de eliminação.

CsvExporter
Classe utilitária que permite exportar o estado atual do tabuleiro em formato CSV (arquivo de texto com vírgulas), com células vazias representadas por “0”. Ideal para registro, benchmarking ou visualização fora do sistema.


Pacote sudoku.ui: “a interface com o jogador”

MainMenuFrame
Uma janela Swing inicial onde o jogador insere seu nome e seleciona o tamanho do tabuleiro (9×9, 12×12, etc.) por meio de um JComboBox. Ao clicar em “Começar”, essa classe chama o controlador principal que instancia o GameFrame.

GameFrame
Interface principal do jogo. Exibe o tabuleiro de Sudoku como uma grade de campos editáveis, ativando navegação por Tab ou clique do mouse. O jogador pode digitar um número, ver validações imediatas, adicionar ou remover candidatos via painel lateral ou atalho. Contém também botões para “Verificar Solução”, “Dica” (hint), e “Exportar CSV”.

**Main** (package raiz sudoku)
Ponto de entrada da aplicação: chama o MainMenuFrame dentro do Event Dispatch Thread do Swing. Essa classe inicializa todo o fluxo de interação de maneira simples — apenas o main() que roda a GUI.



---

📌 Interação entre as classes

Quando o jogo é iniciado, MainMenuFrame cria uma instância de PuzzleGenerator, que gera o tabuleiro (Board) e armazena internamente a solução completa. Em seguida, GameFrame monta a interface visual com base neste Board. À medida que o usuário digita valores ou faz marcações, o GameFrame utiliza os métodos de Board (isValidPlace, getCandidates, etc.) para validar e manter o estado do jogo. Se o usuário solicitar uma dica, HintService é instanciado com o Board atual e a solução pré‑armazenada para preencher uma célula, mantendo o restante intacto. No fim, quando o jogador quiser exportar o progresso, CsvExporter gera um arquivo .csv com o estado atual do tabuleiro. Finalmente, ao clicar em “Verificar solução”, SudokuSolver resolve o tabuleiro para checar se há exatamente uma solução válida — mostrando uma mensagem de vitória caso o tabuleiro esteja completo e correto.

Essa arquitetura modular faz com que cada classe tenha uma responsabilidade bem definida, facilitando testes unitários, manutenção ou extensão futura—como inclusão de modo multiplayer, níveis de dificuldade ou exportação/importação em JSON. A abordagem também reflete boas práticas de projeto recomendadas em guias como o artigo do DEV Community, que destaca clara separação entre lógica de negócio e interface para tornar o README mais legível e funcional mesmo quando exibido como arquivo de texto puro  .


---

🔧 Dica de estilo para o README

Evite tabelas muito densas que dificultem leitura no GitHub ou em visualizadores sombreados no terminal. Em vez disso, use parágrafos corridos com subtítulos para cada grupo de classes.

Mantenha as explicações diretas: o nome da classe, pacote, papel principal, e breve fluxo de interação — isso facilita pra quem está avaliando o código sem precisar compilar.

Siga uma largura de linha de cerca de 80‑100 caracteres no README.md, para que seja legível até em terminals simples, conforme recomendado na documentação de estilo  .



---

Com essa apresentação da arquitetura do projeto e explicação contínua das classes, seu README terá uma clareza elevada e uma aparência profissional, além de facilitar que qualquer avaliador compreenda rapidamente a estrutura do jogo e seus principais componentes.

