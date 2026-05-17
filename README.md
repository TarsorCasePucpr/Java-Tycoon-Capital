# Java-Tycoon-Capital

**Disciplina:** Programação Orientada a Objetos
**Professor:** Pedro Rocha Horchulhack
**Curso:** Bacharelado em Cibersegurança — PUCPR · 2026/1
**Equipe:** Gerard Gonzalez · Bismark Otto · Kauã Garcia Reschetti Rubbo
**Apresentação:** 15/06 ou 19/06 (horário da aula)

---

Jogo *tycoon* em Java, inspirado em *Adventure Capitalist*. O jogador acumula dinheiro vendendo produtos, reinveste em melhorias e desbloqueia novos produtos de forma progressiva. Toda a mecânica é modelada com orientação a objetos para atender à rubrica do PjBL.

---

## Estrutura do Projeto (planejada)

```
src/
  main/
    java/
      tycoon/
        model/         classes de domínio (Produto, Negocio, Jogador, ...)
        gui/           interface gráfica
        persistence/   leitura CSV/TXT, save/load de objetos
        exceptions/    exceções customizadas
        Main.java
    resources/
      produtos.csv     dados iniciais dos produtos
saves/                 arquivos de save do jogador (gerados em runtime)
```

---

## Requisitos do Professor — Checklist

### Parte A — Fundamentos de POO (50%)

| # | Requisito | Status |
|---|-----------|--------|
| A1 | Classes estruturadas segundo o princípio de encapsulamento | ⏳ |
| A2 | No mínimo 5 classes | ⏳ |
| A3 | Pelo menos uma classe abstrata | ⏳ |
| A4 | Pelo menos um método abstrato | ⏳ |
| A5 | Pelo menos 10 atributos e 10 métodos no total | ⏳ |
| A6 | Pelo menos duas relações de herança entre classes | ⏳ |
| A7 | Pelo menos um método sobrescrito por subclasse (não conta implementação de abstrato) | ⏳ |
| A8 | Pelo menos uma chamada polimórfica de método | ⏳ |
| A9 | Pelo menos uma relação de associação entre duas classes | ⏳ |
| A10 | Pelo menos uma coleção de objetos (ex.: `ArrayList`) | ⏳ |
| A11 | Pelo menos uma interface | ⏳ |

### Parte B — Recursos Complementares (50%)

| # | Requisito | Status |
|---|-----------|--------|
| B1 | Pelo menos uma classe derivada de `Exception` | ⏳ |
| B2 | Interface gráfica | ⏳ |
| B3 | Leitura de dados de arquivo CSV ou TXT | ⏳ |
| B4 | Recupera e salva objetos persistentes | ⏳ |

### Orientações Gerais

| # | Item | Status |
|---|------|--------|
| G1 | Equipe de três estudantes | ✅ |
| G2 | Todos os integrantes defendem o projeto | ⏳ |
| G3 | Repositório GitHub entregue até a defesa | ⏳ |
| G4 | Apresentação em 15/06 ou 19/06 | ⏳ |

> **Fórmula da nota:** `((Parte A + Parte B) / 2) * Apresentação`
> A apresentação determina a nota individual de cada integrante.

---

## Mapeamento Rubrica → Implementação

> Tabela viva: cada vez que um requisito for implementado, preencher a coluna *Onde* com o caminho do arquivo/classe e marcar o status como ✅.

| Req. | Onde está implementado | Status |
|------|------------------------|--------|
| A1 Encapsulamento | _todas as classes em `model/`_ | ⏳ |
| A2 ≥5 classes | _a listar_ | ⏳ |
| A3 Classe abstrata | _a definir, ex.: `Produto` abstrato_ | ⏳ |
| A4 Método abstrato | _a definir, ex.: `Produto.calcularReceita()`_ | ⏳ |
| A5 ≥10 atributos / ≥10 métodos | _contagem ao final_ | ⏳ |
| A6 ≥2 heranças | _ex.: `ProdutoBasico extends Produto`, `ProdutoPremium extends Produto`_ | ⏳ |
| A7 Override | _ex.: `toString()` em subclasses_ | ⏳ |
| A8 Polimorfismo | _ex.: `List<Produto>` iterando `calcularReceita()`_ | ⏳ |
| A9 Associação | _ex.: `Jogador` ↔ `Negocio`_ | ⏳ |
| A10 Coleção | _`ArrayList<Produto>` no `Jogador`_ | ⏳ |
| A11 Interface | _ex.: `Vendavel`, `Persistivel`_ | ⏳ |
| B1 Exception customizada | _ex.: `SaldoInsuficienteException`_ | ⏳ |
| B2 GUI | _`gui/MainWindow`_ | ⏳ |
| B3 CSV/TXT | _`persistence/ProdutoLoader` → `resources/produtos.csv`_ | ⏳ |
| B4 Persistência | _`persistence/SaveManager` → `saves/jogador.dat`_ | ⏳ |

---

## Divisão de Responsabilidades

| Integrante | Frente | Entregas principais |
|------------|--------|---------------------|
| Gerard | _a definir_ | _a definir_ |
| Bismark | _a definir_ | _a definir_ |
| Kauã | _a definir_ | _a definir_ |

---

## Como rodar (futuro)

```bash
# clonar o repositório
git clone https://github.com/<org>/Java-Tycoon-Capital.git
cd Java-Tycoon-Capital

# compilar e executar (placeholder — atualizar quando definir build tool)
javac -d out $(find src/main/java -name "*.java")
java -cp out tycoon.Main
```

---

## Convenções

- **Branch:** `main` apenas para versões estáveis; trabalho em `develop`.
- **Commits:** mensagens curtas e descritivas em português.
- **Code style:** padrão Java (camelCase, PascalCase para classes, UPPER_SNAKE para constantes).
- **Sem secrets no repositório.**
