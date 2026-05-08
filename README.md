# Show do Milhão

<div align="center">

![Java](https://img.shields.io/badge/Java-25-orange?style=flat-square&logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=flat-square&logo=apache-maven)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![GUI](https://img.shields.io/badge/GUI-Swing-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Ativo-brightgreen?style=flat-square)

Um jogo educacional interativo baseado no famoso programa de TV brasileiro "Show do Milhão", desenvolvido com Java Swing e MySQL.

</div>

---

## 📋 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Arquitetura do Banco de Dados](#-arquitetura-do-banco-de-dados)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Configuração](#-instalação-e-configuração)
- [Como Usar](#-como-usar)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Sistema de Ajudas](#-sistema-de-ajudas)
- [Tabela de Prêmios](#-tabela-de-prêmios)
- [Resolução de Problemas](#-resolução-de-problemas)
- [Autor](#-autor)
- [Licença](#-licença)

---

## 🎮 Sobre o Projeto

O **Show do Milhão** é uma aplicação Java que simula o famoso programa de TV, onde o jogador responde a uma série de perguntas de múltipla escolha em dificuldade crescente, com o objetivo de acumular prêmios em dinheiro até atingir o máximo de 1 milhão.

Desenvolvido como projeto avaliativo da disciplina de **Programação II** do curso de **Análise e Desenvolvimento de Sistemas (ADS)** - 3ª Fase, no **IFC Campus Fraiburgo**.

### Objetivo Educacional

Este projeto foi desenvolvido para consolidar conhecimentos em:
- Programação Orientada a Objetos (POO)
- Desenvolvimento de interfaces gráficas com Java Swing
- Banco de Dados Relacional e JDBC
- Padrão Data Access Object (DAO)
- Manipulação de arquivos e estruturas de dados
- Lógica de programação aplicada

---

## ✨ Funcionalidades

- **🎯 Jogo Interativo**: 10 perguntas progressivas com prêmios aumentando a cada acerto
- **💰 Sistema de Prêmios**: Tabela de prêmios em reais, variando de R$ 1.000 até R$ 1.000.000
- **🛡️ Níveis Seguros**: Proteção de ganhos em determinados pontos do jogo (após 5ª e 10ª perguntas)
- **🆘 Sistema de Ajudas**: 
  - 50/50: Elimina duas alternativas incorretas
  - Plateia: Mostra a opinião agregada da plateia
  - Ligação: Permite contatar um especialista
- **📂 Banco de Perguntas**: Carregamento de perguntas de arquivo externo
- **🎨 Interface Gráfica**: Interface amigável e intuitiva desenvolvida com Swing
- **⚙️ Gerenciamento de Estado**: Controle completo do fluxo do jogo
- **📊 Sistema de Ranking**: Ranking de jogadores ordenado por melhor prêmio conquistado
- **📈 Histórico de Partidas**: Registro persistente de todas as partidas com prêmios e datas
- **🔄 Modo Chaveamento**: Competição entre jogadores com registro de resultados (em desenvolvimento)
- **💾 Persistência de Dados**: Integração com banco de dados MySQL para armazenamento seguro

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 25 | Linguagem de programação principal |
| **Swing** | - | Framework para interface gráfica desktop |
| **Maven** | 3.6+ | Gerenciador de dependências e build |
| **MySQL** | 8.0+ | Sistema gerenciador de banco de dados relacional |
| **JDBC** | - | API para conectividade com banco de dados |
| **UTF-8** | - | Codificação de caracteres com suporte Unicode |

---

## �️ Arquitetura do Banco de Dados

### Visão Geral

O sistema utiliza **MySQL** para persistência de dados, implementando o padrão **Data Access Object (DAO)** para abstração das operações com o banco de dados. Todas as operações são executadas através dos DAOs, garantindo separação de responsabilidades e facilidade de manutenção.

### Estrutura de Tabelas

#### **Tabela: `jogador`**
Armazena informações dos jogadores e seu melhor prêmio conquistado.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | INT (PK, AI) | Identificador único do jogador |
| `nome` | VARCHAR(100) | Nome único do jogador (constraint UNIQUE) |
| `pontuacao` | INT | Melhor prêmio conquistado (padrão: 0) |

---

#### **Tabela: `historico_jogo`**
Mantém o registro histórico de todas as partidas individuais realizadas, permitindo rastreabilidade completa.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | INT (PK, AI) | Identificador único do registro |
| `jogador_id` | INT (FK) | Referência ao jogador (chave estrangeira) |
| `premio` | INT | Prêmio conquistado naquela partida |
| `data_jogo` | DATETIME | Data e hora da partida (padrão: NOW()) |

---

#### **Tabela: `partida`**
Registra resultados de chaveamentos (competições entre dois jogadores), com suporte a empates.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | INT (PK, AI) | Identificador único da partida |
| `jogador1_id` | INT (FK) | Referência ao primeiro jogador |
| `jogador2_id` | INT (FK) | Referência ao segundo jogador |
| `vencedor_id` | INT (FK, NULL) | Referência ao vencedor (NULL se empate) |
| `data_partida` | DATETIME | Data e hora da partida (padrão: NOW()) |

### Padrão DAO (Data Access Object)

O projeto implementa o padrão **Data Access Object** com as seguintes classes:

#### **ConexaoBD.java** - Gerenciador de Conexão
- Implementa padrão **Singleton** para conexão única com banco de dados
- Pool de conexão simples com reutilização de conexão
- Tratamento centralizado de exceções SQL
- Trata conexões fechadas automaticamente

**Configuração:**
```java
private static final String URL     = "jdbc:mysql://localhost:3306/show_do_milhao?useSSL=false&serverTimezone=UTC";
private static final String USUARIO = "root"; 
private static final String SENHA   = "root";
```

#### **JogadorDAO.java** - Operações com Jogadores
Responsável por todas as operações CRUD relacionadas a jogadores:

- **`inserir(Jogador)`**: Insere novo jogador no banco
- **`buscarPorNome(String)`**: Busca jogador específico pelo nome (retorna null se não existir)
- **`buscarOuCriar(String)`**: Busca jogador ou cria automaticamente se não existir
- **`listarRanking()`**: Lista jogadores ordenados por melhor prêmio (DESC)
- **`listarTodos()`**: Lista todos os jogadores ordenados por nome (para JComboBox)
- **`atualizarMelhorPremio(int, int)`**: Atualiza melhor prêmio apenas se novo valor for maior
- **`adicionarPontos(int, int)`**: Adiciona pontos extras (para chaveamento)
- **`deletar(int)`**: Remove jogador do banco
- **`resetarRanking()`**: Reseta pontuação de todos os jogadores para 0

#### **PartidaDAO.java** - Operações com Partidas e Histórico
Gerencia registros de partidas individuais e chaveamentos:

- **`registrarPartida(int, int, int)`**: Registra resultado de chaveamento entre dois jogadores
- **`registrarJogo(int, int)`**: Registra uma partida individual do Show
- **`listarHistoricoJogos()`**: Retorna histórico formatado [Nome, Prêmio, Data]
- **`listarHistoricoChaveamento()`**: Retorna histórico de chaveamentos [J1, J2, Vencedor, Data]
- **`limparHistorico()`**: Remove todos os registros de histórico

---

## �📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

### Software Obrigatório

- **Java Development Kit (JDK)** versão 25 ou superior
  - Download: [oracle.com/java/technologies/downloads](https://www.oracle.com/java/technologies/downloads/)
  - ⚠️ Importante: Java 25 ou posterior é obrigatório

- **Maven** versão 3.6 ou superior
  - Download: [maven.apache.org/download](https://maven.apache.org/download.cgi)
  - Verificação: `mvn --version`

- **MySQL Server** versão 8.0 ou superior
  - Download: [mysql.com/downloads](https://www.mysql.com/downloads/mysql/)
  - ⚠️ Importante: Crie usuário com credenciais padrão ou configure conforme especificado

### Verificar Instalações

Execute os seguintes comandos no terminal/CMD:

```bash
# Verificar Java (deve retornar versão 25+)
java -version

# Verificar Maven (deve retornar versão 3.6+)
mvn --version

# Verificar MySQL (deve retornar versão 8.0+)
mysql --version
```

---

## 📦 Instalação e Configuração

### Passo 1: Clone o Repositório

```bash
git clone https://github.com/Gusta12344/ShowDoMilhao.git
cd ShowDoMilhao
```

### Passo 2: Configure o Banco de Dados

#### Opção A: Executar Script SQL (Recomendado)

Execute o script SQL para criar o banco de dados e tabelas automaticamente:

```bash
# No Windows (CMD)
mysql -u root -p < Database\Database.sql

# No macOS/Linux (Terminal)
mysql -u root -p < Database/Database.sql
```

Será solicitada a senha do usuário `root` (padrão: `root`).

O script cria automaticamente:
- ✅ Banco de dados: `show_do_milhao`
- ✅ Tabelas: `jogador`, `historico_jogo`, `partida`
- ✅ Charset: `utf8mb4` (suporte completo a Unicode)
- ✅ Chaves estrangeiras com cascata de deleção

#### Opção B: Executar Manualmente

Abra o MySQL e execute:

```sql
CREATE DATABASE IF NOT EXISTS show_do_milhao
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE show_do_milhao;

CREATE TABLE jogador (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL UNIQUE,
    pontuacao INT DEFAULT 0
);

CREATE TABLE historico_jogo (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    jogador_id INT NOT NULL,
    premio     INT NOT NULL,
    data_jogo  DATETIME DEFAULT NOW(),
    FOREIGN KEY (jogador_id) REFERENCES jogador(id) ON DELETE CASCADE
);

CREATE TABLE partida (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    jogador1_id  INT NOT NULL,
    jogador2_id  INT NOT NULL,
    vencedor_id  INT,
    data_partida DATETIME DEFAULT NOW(),
    FOREIGN KEY (jogador1_id) REFERENCES jogador(id) ON DELETE CASCADE,
    FOREIGN KEY (jogador2_id) REFERENCES jogador(id) ON DELETE CASCADE,
    FOREIGN KEY (vencedor_id) REFERENCES jogador(id) ON DELETE SET NULL
);
```

### Passo 3: Configurar Credenciais (Se Necessário)

Se usar usuário/senha diferentes de `root`/`root`, edite o arquivo:

**Arquivo**: `src/main/java/com/showdomilhao/db/ConexaoBD.java`

```java
private static final String URL     = "jdbc:mysql://localhost:3306/show_do_milhao?useSSL=false&serverTimezone=UTC";
private static final String USUARIO = "seu_usuario";  // Altere aqui
private static final String SENHA   = "sua_senha";    // Altere aqui
```

### Passo 4: Compile o Projeto

```bash
# Limpa compilações anteriores e compila
mvn clean compile
```

### Passo 5: Empacote a Aplicação

```bash
# Gera o JAR executável
mvn package
```

Será gerado o arquivo: `target/ShowDoMilhao-1.0-SNAPSHOT.jar`

---

## 🚀 Como Usar

### Executar via Maven (Desenvolvimento)

```bash
mvn exec:java -Dexec.mainClass="com.showdomilhao.ShowDoMilhao"
```

### Executar via JAR (Produção)

```bash
java -jar target/ShowDoMilhao-1.0-SNAPSHOT.jar
```

### Fluxo Principal do Jogo

1. **Tela Inicial**: Insira seu nome para começar
2. **Seleção de Modo**: Escolha entre Jogo Individual ou Modo Chaveamento
3. **Gameplay**: Responda as 10 perguntas progressivas
   - Leia a pergunta com atenção
   - Escolha uma das 4 alternativas (A, B, C, D)
   - Use as ajudas estrategicamente
4. **Resultado**: Veja seu prêmio final
5. **Ranking**: Visualize seu desempenho e compare com outros jogadores
6. **Histórico**: Acompanhe todas as partidas já realizadas

### Instruções de Jogo

- **Responda corretamente**: Avance para a próxima pergunta e aumente seu prêmio
- **Erre uma pergunta**: Perde o prêmio acumulado (exceto após níveis seguros - 5ª e 10ª)
- **Use as ajudas com sabedoria**: Cada ajuda pode ser usada apenas uma vez por partida
- **Atinja R$ 1.000.000**: Ganhe o prêmio máximo!

---

## 📁 Estrutura do Projeto

```
ShowDoMilhao/
│
├── Database/
│   └── Database.sql                          # Script SQL para criar BD e tabelas
│
├── src/
│   ├── main/
│   │   ├── java/com/showdomilhao/
│   │   │   ├── ShowDoMilhao.java             # 🎯 Classe principal (entry point)
│   │   │   ├── ControladorJogo.java          # 🎮 Lógica principal do jogo
│   │   │   ├── GerenciadorAjudas.java        # 🆘 Gerencia ajudas (50/50, etc)
│   │   │   ├── BancoDePergunta.java          # 📂 Carrega perguntas do arquivo
│   │   │   ├── Pergunta.java                 # ❓ Modelo de pergunta
│   │   │   │
│   │   │   ├── db/
│   │   │   │   └── ConexaoBD.java            # 🔌 Gerenciador de conexão com BD (Singleton)
│   │   │   │
│   │   │   ├── dao/
│   │   │   │   ├── JogadorDAO.java           # 👤 DAO para operações com jogadores
│   │   │   │   └── PartidaDAO.java           # 🎯 DAO para operações com partidas
│   │   │   │
│   │   │   ├── model/
│   │   │   │   └── Jogador.java              # 📊 Modelo de dados: Jogador
│   │   │   │
│   │   │   ├── TelaInicio.java               # 🎬 Tela de boas-vindas
│   │   │   ├── TelaInicio.form               # 🎨 Descrição GUI (NetBeans)
│   │   │   ├── TelaJogo.java                 # 🎮 Tela principal do jogo
│   │   │   ├── TelaJogo.form                 # 🎨 Descrição GUI (NetBeans)
│   │   │   ├── TelaRanking.java              # 🏆 Tela com ranking e histórico
│   │   │   └── TelaRanking.form              # 🎨 Descrição GUI (NetBeans)
│   │   │
│   │   └── Resources/
│   │       └── perguntas.txt                 # ❓ Banco de perguntas em TXT
│   │
│   └── test/java/                            # 🧪 Testes unitários
│
├── target/                                   # 📦 Artefatos compilados (gerado pelo Maven)
│   ├── classes/                              # Classes compiladas
│   ├── generated-sources/                    # Fontes geradas
│   └── ShowDoMilhao-1.0-SNAPSHOT.jar        # JAR executável
│
├── pom.xml                                   # ⚙️ Configuração Maven (dependências, build)
├── README.md                                 # 📖 Este arquivo
└── .gitignore                                # 🔒 Arquivos ignorados pelo Git
```

### Descrição das Classes Principais

| Classe | Responsabilidade |
|--------|------------------|
| `ShowDoMilhao` | Ponto de entrada da aplicação - inicializa as telas |
| `TelaJogo` | Interface gráfica do jogo (Swing) - exibe perguntas e alternativas |
| `ControladorJogo` | Lógica do jogo e controle de fluxo - gerencia perguntas e prêmios |
| `BancoDePergunta` | Carregamento e gerenciamento de perguntas do arquivo |
| `Pergunta` | Modelo de dados representando uma pergunta com alternativas |
| `GerenciadorAjudas` | Controle do sistema de 50/50, Plateia e Ligação |
| `ConexaoBD` | Gerenciador de conexão com MySQL (Singleton) |
| `JogadorDAO` | Operações CRUD de jogadores no banco de dados |
| `PartidaDAO` | Operações de partidas e histórico no banco de dados |
| `Jogador` | Modelo de dados representando um jogador |

---

## 🆘 Sistema de Ajudas

O jogo oferece **3 tipos de ajuda** que podem ser usados **uma única vez cada** durante uma partida:

### 1️⃣ **50/50**
- **Efeito**: Elimina 2 alternativas incorretas automaticamente
- **Resultado**: Deixa apenas a resposta correta e uma incorreta
- **Estratégia**: Use quando estiver muito em dúvida

### 2️⃣ **Plateia**
- **Efeito**: Simula a opinião agregada da plateia
- **Resultado**: Exibe percentuais de votos para cada alternativa
- **Confiabilidade**: Não é 100% confiável - a plateia também pode errar
- **Estratégia**: Use quando a percentagem for significativamente diferente

### 3️⃣ **Ligação**
- **Efeito**: Conecta com um especialista no assunto da pergunta
- **Resultado**: Oferece orientação sobre a resposta correta
- **Confiabilidade**: Geralmente mais confiável que as outras ajudas
- **Estratégia**: Use como último recurso em dúvidas críticas

---

## 📊 Tabela de Prêmios

| Pergunta | Prêmio | Nível Seguro |
|----------|--------|:----------:|
| 1ª | R$ 1.000 | ❌ |
| 2ª | R$ 5.000 | ❌ |
| 3ª | R$ 10.000 | ❌ |
| 4ª | R$ 20.000 | ❌ |
| 5ª | R$ 40.000 | ✅ |
| 6ª | R$ 80.000 | ❌ |
| 7ª | R$ 150.000 | ❌ |
| 8ª | R$ 300.000 | ❌ |
| 9ª | R$ 500.000 | ❌ |
| 10ª | R$ 1.000.000 | ✅ |

**Níveis Seguros**: Ao errar após um nível seguro, você garante o prêmio daquele nível.

---

## 🐛 Resolução de Problemas

### ❌ Erro: "Comando Maven não encontrado"
**Solução**: 
- Verifique se Maven foi adicionado às variáveis de ambiente do sistema
- Reinicie o terminal/CMD após instalar Maven
- Execute `mvn --version` para confirmar instalação

### ❌ Erro: "Java versão incompatível"
**Solução**:
- O projeto requer **Java 25 ou superior**
- Verifique a versão instalada: `java -version`
- Faça download da versão correta em oracle.com/java

### ❌ Erro: "Não foi possível conectar ao banco de dados"
**Solução**:
- Verifique se MySQL Server está rodando
  - Windows: Verifique Services ou execute `net start MySQL80`
  - macOS: `brew services start mysql`
  - Linux: `sudo systemctl start mysql`
- Confirme as credenciais em `ConexaoBD.java` (usuário: `root`, senha: `root`)
- Verifique se o banco `show_do_milhao` foi criado: `mysql -u root -p -e "SHOW DATABASES;"`

### ❌ Erro: "Arquivo de Perguntas não encontrado"
**Solução**:
- Certifique-se de que `src/main/resources/perguntas.txt` existe
- O arquivo deve estar em UTF-8 com formatação correta
- Após compilar, o arquivo é copiado para `target/classes/perguntas.txt`

### ❌ Erro: "Port 3306 already in use"
**Solução**:
- Outra instância MySQL está rodando
- Encontre e feche a aplicação, ou altere a porta em `ConexaoBD.java`

---

## 👨‍💻 Autor

**Gustavo Maciel Huçulak**
- 📚 Estudante de Análise e Desenvolvimento de Sistemas (ADS) - 3ª Fase
- 🏫 **IFC Campus Fraiburgo** - Instituto Federal Catarinense
- 📧 **Email**: gustahuculak@gmail.com
- 🔗 **GitHub**: [github.com/Gusta12344](https://github.com/Gusta12344)

---

## 📝 Licença

Este projeto é fornecido como material educacional para fins de aprendizado e avaliação acadêmica na disciplina de **Programação II**.

---

## 📚 Referências e Recursos

- [Java 25 Documentation](https://docs.oracle.com/en/java/javase/25/)
- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Apache Maven Documentation](https://maven.apache.org/guides/)
- [MySQL 8.0 Documentation](https://dev.mysql.com/doc/mysql-en/8.0/en/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [Design Patterns - DAO](https://www.oracle.com/java/technologies/dataaccessobject.html)
- [Show do Milhão - Globo](https://globoplay.globo.com/)

---

**Última atualização**: Maio 2026  
**Versão**: 1.5
