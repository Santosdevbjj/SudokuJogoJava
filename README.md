## Bootcamp TONNIE - Java and AI in Europe.

![TonnieJava](https://github.com/user-attachments/assets/7c213a67-451f-4fde-88ba-a48f690e2452)


**Criando um Jogo do Sudoku em Java.**


**DESCRIÇÃO:**

Neste desafio, você será responsável por criar um jogo de Sudoku em Java, implementando funcionalidades essenciais para um jogo interativo e funcional no terminal.

O objetivo é consolidar seus conhecimentos em programação orientada a objetos, manipulação de estruturas de dados, uso de métodos e classes, além de lidar com entradas e saídas no terminal.



**O que é Sudoku?**

Sudoku é um famoso desafio de lógica em que a pessoa jogadora deve distribuir números, letras ou figuras nos espaços em branco do tabuleiro. “Não repita números nas linhas, colunas e blocos.” 

Para quem adora uma brincadeira matemática, a regra única do sudoku é mais do que conhecida.

Ao longo do tempo, o passatempo, já popular no Japão, foi conquistando os países ocidentais. 


**História e Evolução do Jogo de Sudoku:**

Embora o sucesso do puzzle por aqui seja relativamente recente, o Sudoku foi criado em 1979. 

O responsável pelo feito foi um construtor de quebra-cabeças, o arquiteto Howard Garns.

Inicialmente chamado de number place (que pode ser traduzido como algo no sentido de lugar numérico), o nome mudou anos depois para sudoku, acrônimo em japonês para “os dígitos devem ser únicos” (Suuji wa dokushin ni kagiru).


As regras do jogo são simples: as linhas horizontais e verticais da grade quadriculada devem ser preenchidas com números de 1 a 9, assim como cada um dos nove blocos quadrados.

O desafio é completar todo o sistema sem repetir algarismos nas linhas, colunas ou quadrados.



Se o jogo traz números de 1 a 9, o resultado da soma de um mesmo bloco, coluna ou linha será sempre 45 (1+2+3+4+5+6+7+8+9). 


Assim já é possível ir dando os primeiros passos.


Sudoku é um jogo baseado na colocação lógica de números. O objetivo do jogo é a colocação de números de 1 a 9 em cada uma das células vazias numa grade de 9×9, constituída por 3×3 subgrades chamadas regiões.

O quebra-cabeça contém algumas pistas iniciais, que são números inseridos em algumas células, de maneira a permitir uma indução ou dedução dos números em células que estejam vazias. 

Cada coluna, linha e região só pode ter um número de cada um dos 1 a 9. Resolver o problema requer apenas raciocínio lógico e algum tempo. 

Os problemas são normalmente classificados em relação à sua realização.


**Quais são as regras básicas do Sudoku?**

A grade do Sudoku consiste em espaços 9x9. Ou 12x12 ou 16x16 ou 25x25 ou 30x30 ou 32x32 ... Etc...

Somente números de 1 a 9 podem ser usados.
Cada bloco 3x3 pode conter apenas números de 1 a 9.

Cada coluna vertical pode conter apenas números de 1 a 9.

Cada linha horizontal pode conter apenas números de 1 a 9.

Cada número em um bloco 3x3, uma coluna vertical ou uma linha horizontal pode ser usado apenas uma vez.

O jogo termina quando toda a grade do Sudoku está corretamente preenchida com números.





**O jogo:**

O jogo tem um menu inicial para o jogador dar o seu nome e escolher o tamanho do tabuleiro a ser utilizado no jogo.
As opções de tamanho do tabuleiro são:
9x9 - 12x12 - 16x16 - 25x25 - 30x30 - 32x32


Após o jogador digitar o seu nome e escolher o tamanho do tabuleiro, é apresentado o tabuleiro iniciando o jogo, seguindo as regras do Sudoku.

O jogador pode preencher o tabuleiro usando o mouse, ou usando só  o teclado,
apertando a tecla TAB, indo para as células que estão vazias no tabuleiro.



**Como o desafio de projeto foi desenvolvido:**!

O projeto do jogo Sudoku em Java foi desenvolvido com foco em uma arquitetura robusta e em uma experiência de usuário agradável. 

As classes foram criadas seguindo os princípios de Programação Orientada a Objetos (POO) para garantir a modularidade e a facilidade de manutenção.


A estrutura do projeto, com as classes model, view e controller, separa a lógica de negócio da interface gráfica, o que é uma boa prática de desenvolvimento.


**O jogo tem as seguintes funcionalidades:**


 - **Menu inicial** para o jogador inserir seu nome e escolher o tamanho do tabuleiro.

 - **Geração de tabuleiro** com um algoritmo de backtracking, garantindo uma solução válida para cada novo jogo.

 -  **Interface gráfica** interativa que suporta múltiplos tamanhos de tabuleiro.

 -  **Interação com mouse e teclado**, incluindo navegação com a tecla TAB em células editáveis.

 -  **Funcionalidade de rascunho** para ajudar o jogador a resolver o quebra-cabeça.

 -  **Validação de jogadas** em tempo real.

 -  **Verificação de vitória** com a opção de reiniciar o jogo, retornando ao menu inicial.



**Descrição das classes do jogo Sudoku:**


**1. Main.java**
 - **Função:** É o ponto de entrada da aplicação.
 - **Descrição:** Contém o método main, que inicia a aplicação. Ele cria uma instância da classe MenuInicial na thread de eventos do Swing, garantindo que a interface gráfica seja renderizada corretamente.


**2. MenuInicial.java**
  - **Função:** Interface do menu inicial.
  - **Descrição:** Esta classe cria a primeira janela do jogo, onde o jogador pode inserir seu nome e escolher o tamanho do tabuleiro. Após o clique no botão "Iniciar Jogo", ela fecha o menu e inicia a janela principal do jogo (PainelJogo).


**3. Tabuleiro.java**
 - **Função:** O modelo e a lógica principal do jogo.
 - **Descrição:** Gerencia a matriz de células do Sudoku. É responsável por:
   - Gerar um tabuleiro completo e válido usando um algoritmo de backtracking.
   - Remover números do tabuleiro para criar o desafio.
   - Validar jogadas do usuário (checando se o número é válido para a linha, coluna e sub-grade).
   -  Verificar se o tabuleiro está completo e correto, determinando a vitória do jogador.


**4. Celula.java**
 - **Função:** Representa cada quadrado do tabuleiro.
 - **Descrição:** É uma classe simples que armazena o estado de uma célula individual. Seus atributos incluem:
   - valor: O número atual da célula.
   - fixo: Um booleano que indica se a célula é parte do tabuleiro inicial e não pode ser alterada.
   -  rascunhos: Uma lista de números que o jogador anotou como possíveis candidatos para a célula.


**5. PainelJogo.java**
 - **Função:** A interface gráfica do tabuleiro.
 - **Descrição:** Estende JPanel e é a representação visual do jogo. Ela é responsável por:
   -  Desenhar a grade do tabuleiro, os números e os rascunhos.
   -  Destacar a célula que está selecionada no momento.
   - Capturar os cliques do mouse para selecionar uma célula.


**6. ControladorJogo.java**
 - Função: Gerencia a interação entre o jogador e a lógica do jogo.
 -  Descrição: Atua como o "cérebro" do jogo, respondendo aos eventos do teclado. É responsável por:
   -  Receber a entrada de números do jogador.
   -  Alternar entre o modo de preenchimento e o modo de rascunho.
   -  Validar a jogada com a classe Tabuleiro.
   -  Navegar entre as células editáveis usando a tecla TAB.
   -  Verificar a vitória após cada jogada e, se o jogo terminar, perguntar ao jogador se ele deseja jogar novamente.


Essa documentação cobre de forma clara e direta a responsabilidade de cada classe, seguindo a arquitetura MVC (Model-View-Controller) que utilizamos. Com isso, o projeto está completo e bem documentado.










