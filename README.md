![TonnieJava](https://github.com/user-attachments/assets/7c213a67-451f-4fde-88ba-a48f690e2452)

# 🔢 Jogo de Sudoku em Java — MVC + Backtracking + Swing

> **Bootcamp TONNIE — Java and AI in Europe | DIO**

---

## 1. 🧩 Problema de Negócio

Implementar um jogo de Sudoku funcional parece simples — até você tentar gerar um tabuleiro válido automaticamente.

Qualquer desenvolvedor consegue criar uma grade 9x9 e validar se um número é repetido na linha ou coluna. O verdadeiro desafio começa quando as perguntas ficam mais difíceis:

- Como **gerar automaticamente** um tabuleiro com solução única e garantidamente válida, para qualquer tamanho de grade — de 9x9 a 32x32?
- Como **remover células** do tabuleiro completo para criar o desafio sem torná-lo insolúvel ou trivial?
- Como organizar o código para que a **lógica do jogo, a interface gráfica e o controle de entrada** do jogador não se misturem — e qualquer um dos três possa ser modificado sem quebrar os outros dois?

> **O que este projeto resolve:** um jogo de Sudoku completo com interface gráfica Swing, geração automática de tabuleiros via algoritmo de backtracking, suporte a 6 tamanhos de grade diferentes, modo rascunho, validação em tempo real e arquitetura MVC aplicada de forma funcional e consciente.

---

## 2. 📌 Contexto

O projeto foi desenvolvido como parte do Bootcamp TONNIE — Java and AI in Europe, como desafio de consolidação de POO, algoritmos e desenvolvimento de interfaces gráficas com Java Swing.

O Sudoku foi escolhido como domínio porque ele combina três desafios técnicos que raramente aparecem juntos em projetos de portfólio:

- **Algoritmo não trivial** — gerar um tabuleiro válido exige backtracking recursivo, não apenas aleatoriedade;
- **Interface gráfica reativa** — o tabuleiro precisa responder ao mouse e ao teclado, destacar a célula selecionada, diferenciar células fixas de editáveis e exibir rascunhos em tamanho menor dentro da célula;
- **Arquitetura que suporte múltiplos tamanhos** — o mesmo código que funciona para 9x9 precisa funcionar para 16x16 e 32x32 sem duplicação de lógica.

---

## 3. 📐 Premissas da Solução

As seguintes premissas guiaram as decisões de design e implementação:

- **O tabuleiro é sempre gerado, nunca hard-coded** — não existem tabuleiros pré-montados no código. Cada novo jogo é gerado dinamicamente pelo algoritmo de backtracking, garantindo variedade infinita;
- **`Celula.fixo` é imutável após a geração** — células que fazem parte do tabuleiro inicial não podem ser alteradas pelo jogador. Essa restrição é aplicada pela própria estrutura da classe, não por validação condicional no controller;
- **Arquitetura MVC com separação real de responsabilidades** — `Tabuleiro` (Model) não sabe que existe uma interface gráfica. `PainelJogo` (View) não contém lógica de jogo. `ControladorJogo` (Controller) coordena os dois sem misturar suas responsabilidades;
- **O modo rascunho não afeta o valor da célula** — `Celula.rascunhos` é uma lista separada de `Celula.valor`. O jogador pode anotar candidatos sem comprometer o estado do jogo;
- **Suporte a múltiplos tamanhos é paramétrico** — o tamanho do tabuleiro é passado como parâmetro na criação do `Tabuleiro`. Nenhuma lógica é duplicada para cada tamanho suportado.

---

## 4. ⚙️ Estratégia da Solução

A construção seguiu uma progressão da camada mais interna para a mais externa — do algoritmo ao menu:

**Passo 1 — Algoritmo de geração do tabuleiro (`Tabuleiro.java`)**

O coração do projeto. O backtracking funciona em duas fases:

1. **Preencher o tabuleiro completo:** começa pela primeira célula vazia, tenta inserir um número aleatório (1 a N) que não viole as regras da linha, coluna e sub-grade. Se nenhum número é válido, retrocede para a célula anterior e tenta o próximo número disponível. Repete até preencher toda a grade.
2. **Criar o desafio:** remove células aleatórias do tabuleiro completo, marcando-as como editáveis. A quantidade removida define a dificuldade.

**Passo 2 — Modelagem da célula (`Celula.java`)**

Cada célula é um objeto com três estados independentes: `valor` (o número atual ou zero se vazia), `fixo` (boolean — imutável após geração) e `rascunhos` (lista de candidatos anotados pelo jogador). Separar esses três estados em atributos distintos evitou lógica condicional espalhada pela View.

**Passo 3 — Interface gráfica (`PainelJogo.java`)**

Extende `JPanel` e sobrescreve `paintComponent()` para desenhar a grade dinamicamente. O método de renderização distingue células fixas (fonte normal), células editadas corretamente (fonte azul), células com erro (fundo vermelho) e rascunhos (fonte menor, em cinza). Cliques do mouse mapeiam as coordenadas do pixel para o índice da célula na grade.

**Passo 4 — Controle de entrada (`ControladorJogo.java`)**

Implementa `KeyListener` e atua como intermediário entre o jogador e o modelo. Recebe teclas numéricas, delega a validação ao `Tabuleiro`, instrui o `PainelJogo` a atualizar a exibição e verifica vitória após cada jogada. A navegação por TAB percorre apenas as células editáveis, ignorando as células fixas.

**Passo 5 — Menu inicial (`MenuInicial.java`)**

Coleta nome do jogador e tamanho do tabuleiro antes de instanciar `Tabuleiro` e `PainelJogo`. Garante que `PainelJogo` só é criado na EDT (Event Dispatch Thread) via `SwingUtilities.invokeLater()` — requisito do Swing para thread safety.

---

## 5. 💡 Insights Técnicos

Os aprendizados mais reveladores vieram dos problemas que o algoritmo e o Swing trouxeram:

- **Backtracking sem embaralhamento gera sempre o mesmo tabuleiro.** A primeira implementação do backtracking preenchia a grade de forma determinística — o número 1 sempre ia para a primeira célula disponível. O resultado era que jogos diferentes tinham tabuleiros estruturalmente idênticos. A solução foi embaralhar a lista de candidatos antes de cada tentativa, usando `Collections.shuffle()`. Uma linha de código que transformou um algoritmo correto em um gerador verdadeiramente aleatório.

- **Remover muitas células torna o tabuleiro insolúvel para humanos.** A primeira versão removia 60-70% das células e os tabuleiros gerados eram matematicamente válidos mas humanamente impossíveis. O balanceamento da dificuldade — quantas células remover por tamanho de grade — foi a parte mais iterativa do projeto, resolvida empiricamente testando com jogadores reais.

- **`paintComponent()` é chamado automaticamente pelo Swing a qualquer momento.** A primeira versão mantinha estado de renderização em variáveis locais dentro do método. Quando o Swing decidia repintar a janela (ao minimizar e maximizar, por exemplo), o estado era perdido. A solução foi mover todo o estado de exibição para os objetos `Celula` — o método só lê o estado, não o mantém.

- **A separação MVC tornou trivial adicionar novos tamanhos de tabuleiro.** Quando foi necessário adicionar suporte a 25x25 e 32x32, a única mudança foi no `MenuInicial` — adicionar as novas opções ao combobox. `Tabuleiro`, `Celula`, `PainelJogo` e `ControladorJogo` não precisaram de nenhuma modificação. Isso não foi sorte — foi consequência direta de ter parametrizado o tamanho desde o início.

- **`SwingUtilities.invokeLater()` é obrigatório, não opcional.** Criar componentes Swing fora da EDT causa bugs de renderização intermitentes e impossíveis de reproduzir de forma consistente. Aprender isso da forma difícil — debugando uma janela que aparecia em branco em 1 de cada 10 execuções — foi um aprendizado que não aparece em nenhum tutorial básico de Swing.

---

## 6. 📊 Resultados

| Funcionalidade | Status |
|---|---|
| Geração automática de tabuleiro via backtracking | ✅ |
| 6 tamanhos de grade: 9x9, 12x12, 16x16, 25x25, 30x30, 32x32 | ✅ |
| Interface gráfica Swing com mouse e teclado | ✅ |
| Navegação por TAB apenas entre células editáveis | ✅ |
| Modo rascunho (anotação de candidatos por célula) | ✅ |
| Validação de jogadas em tempo real | ✅ |
| Verificação de vitória com opção de reiniciar | ✅ |
| Arquitetura MVC com separação real de camadas | ✅ |

**Arquitetura MVC — responsabilidades de cada classe:**

| Camada | Classe | Responsabilidade |
|---|---|---|
| **Model** | `Tabuleiro.java` | Geração via backtracking, validação de jogadas, verificação de vitória |
| **Model** | `Celula.java` | Estado individual: valor, fixo, rascunhos |
| **View** | `PainelJogo.java` | Renderização da grade, destaque de seleção, exibição de rascunhos |
| **Controller** | `ControladorJogo.java` | Captura de teclado, delegação para Model, atualização da View |
| **Entry Point** | `MenuInicial.java` | Coleta de dados do jogador e inicialização da stack MVC |
| **Entry Point** | `Main.java` | Inicialização da aplicação na EDT |

---

## 7. 🚀 Próximos Passos

A arquitetura MVC torna cada evolução isolada e sem risco de regressão:

- [ ] **Sistema de dificuldade parametrizável** — expor no `MenuInicial` a escolha entre Fácil, Médio e Difícil, que controla quantas células são removidas pelo gerador;
- [ ] **Persistência de partidas** — salvar o estado do tabuleiro em arquivo JSON e recarregar ao reabrir o jogo, permitindo retomar uma partida inacabada;
- [ ] **Cronômetro e ranking** — medir o tempo de conclusão por jogador e tamanho de tabuleiro, persistindo os melhores tempos em arquivo CSV;
- [ ] **Resolução automática** — implementar um botão "Resolver" que aplica o backtracking sobre o tabuleiro atual do jogador, demonstrando a solução passo a passo;
- [ ] **Geração de tabuleiro em thread separada** — para tabuleiros grandes (25x25, 32x32), o backtracking pode levar alguns segundos. Mover a geração para uma `SwingWorker` exibe uma barra de progresso e evita congelar a UI;
- [ ] **Testes unitários para `Tabuleiro`** — validar que o backtracking gera tabuleiros corretos, que `isValido()` rejeita corretamente duplicatas e que a remoção de células não viola a unicidade da solução.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Função no projeto |
|---|---|
| Java 17+ | Linguagem principal |
| Java Swing (`JPanel`, `JFrame`) | Interface gráfica da grade e do menu |
| `paintComponent()` | Renderização customizada do tabuleiro |
| `KeyListener` + `MouseListener` | Captura de entrada do jogador |
| `SwingUtilities.invokeLater()` | Thread safety na criação de componentes Swing |
| Algoritmo de Backtracking | Geração de tabuleiros válidos e únicos |
| `Collections.shuffle()` | Aleatorização dos candidatos no backtracking |
| Arquitetura MVC | Separação de Model, View e Controller |

---

## 🔧 Como Executar

**Pré-requisito:** Java 17+ instalado.

**1. Clone o repositório**
```bash
git clone https://github.com/Santosdevbjj/SudokuJogoJava.git
cd SudokuJogoJava
```

**2. Compile o projeto**
```bash
javac -d out src/**/*.java
```

**3. Execute o jogo**
```bash
java -cp out Main
```

**4. No menu inicial:**
- Digite seu nome
- Escolha o tamanho do tabuleiro (9x9, 12x12, 16x16, 25x25, 30x30 ou 32x32)
- Clique em **Iniciar Jogo**

**Controles durante o jogo:**

| Ação | Controle |
|---|---|
| Selecionar célula | Clique do mouse |
| Inserir número | Teclas numéricas |
| Navegar entre células editáveis | TAB |
| Alternar modo rascunho | Tecla R |
| Apagar número | DELETE ou BACKSPACE |

---

## 📚 Aprendizados

Este foi o projeto que mais me ensinou sobre a diferença entre um algoritmo que **funciona** e um algoritmo que **funciona bem**.

O backtracking da primeira versão era correto — gerava tabuleiros válidos — mas sempre produzia o mesmo padrão estrutural. Adicionar o `Collections.shuffle()` antes de cada tentativa foi uma mudança de uma linha que tornou o gerador genuinamente aleatório. Esse momento deixou claro que algoritmos corretos e algoritmos úteis não são a mesma coisa.

O segundo grande aprendizado foi entender o Swing como um framework orientado a eventos e thread-safe. Antes deste projeto, eu criava componentes onde fosse conveniente. Depois de depurar a janela que aparecia em branco intermitentemente, nunca mais criei um componente Swing fora da EDT. Esse é o tipo de bug que derruba aplicações em produção e que só se encontra com experiência — ou com um projeto que força você a encontrá-lo.

Se fosse recomeçar, desenharia a separação MVC no papel antes de abrir o IDE. A arquitetura final ficou boa, mas duas refatorações no meio do desenvolvimento foram necessárias para mover lógica de validação que havia "escapado" para dentro do `PainelJogo`. Definir as fronteiras de cada camada antes de começar teria poupado esse retrabalho.

---

## 👤 Autor

**Sérgio Santos**



[![Portfólio](https://img.shields.io/badge/Portfólio-Sérgio_Santos-111827?style=for-the-badge&logo=githubpages&logoColor=00eaff)](https://portfoliosantossergio.vercel.app)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Sérgio_Santos-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/santossergioluiz)

---

 
