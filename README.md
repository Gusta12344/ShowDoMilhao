# Show do Milhão

<div align="center">

![Java](https://img.shields.io/badge/Java-25-orange?style=flat-square&logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=flat-square&logo=apache-maven)
![GUI](https://img.shields.io/badge/GUI-Swing-blue?style=flat-square)
![Status](https://img.shields.io/badge/Status-Ativo-brightgreen?style=flat-square)

Um jogo educacional interativo baseado no famoso programa de TV brasileiro "Show do Milhão", desenvolvido com Java Swing.

</div>

---

## 📋 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Como Usar](#-como-usar)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Sistema de Ajudas](#-sistema-de-ajudas)
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

---

## 🛠️ Tecnologias

| Tecnologia | Descrição |
|-----------|-----------|
| **Java 25** | Linguagem de programação principal |
| **Swing** | Framework para interface gráfica |
| **Maven** | Gerenciador de dependências e build |
| **UTF-8** | Codificação de caracteres |

---

## 📋 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java Development Kit (JDK)** versão 25 ou superior
  - Download: [oracle.com/java](https://www.oracle.com/java/technologies/downloads/)
- **Maven** versão 3.6 ou superior
  - Download: [maven.apache.org](https://maven.apache.org/download.cgi)
  - Verificar instalação: `mvn --version`

### Verificar Instalações

```bash
# Verificar Java
java -version

# Verificar Maven
mvn --version
```

---

## 📦 Instalação

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/ShowDoMilhao.git
cd ShowDoMilhao
```

### 2. Navegue até a Pasta do Projeto

```bash
cd ShowDoMilhao
```

### 3. Compile o Projeto com Maven

```bash
mvn clean compile
```

### 4. Empacote a Aplicação

```bash
mvn package
```

---

## 🚀 Como Usar

### Executar pelo Maven

```bash
mvn exec:java@run
```

Ou use o comando direto:

```bash
mvn exec:java -Dexec.mainClass="com.showdomilhao.ShowDoMilhao"
```

### Executar pelo Executável

Após compilar, execute o JAR gerado:

```bash
java -jar target/ShowDoMilhao-1.0-SNAPSHOT.jar
```

### 📖 Instruções do Jogo

1. **Inicie a Aplicação**: A tela inicial do jogo aparecerá
2. **Leia a Pergunta**: Uma pergunta com 4 alternativas será exibida
3. **Escolha sua Resposta**: Clique em uma das opções (A, B, C ou D)
4. **Use as Ajudas** (opcional): 
   - Você tem direito a usar cada ajuda uma única vez
   - Escolha estrategicamente quando usá-las
5. **Acumule Prêmios**: Cada acerto aumenta seu prêmio
6. **Cuidado com os Erros**: Um erro pode custar seu prêmio acumulado (exceto após níveis seguros)
7. **Objetivo**: Alcançar o prêmio de R$ 1.000.000

---

## 📁 Estrutura do Projeto

```
ShowDoMilhao/
├── pom.xml                          # Arquivo de configuração Maven
├── src/
│   ├── main/
│   │   ├── java/com/showdomilhao/
│   │   │   ├── ShowDoMilhao.java           # Classe principal (entry point)
│   │   │   ├── TelaJogo.java               # Interface gráfica principal
│   │   │   ├── ControladorJogo.java        # Controlador de lógica do jogo
│   │   │   ├── BancoDePergunta.java        # Gerenciador de perguntas
│   │   │   ├── Pergunta.java               # Modelo de dados de pergunta
│   │   │   ├── GerenciadorAjudas.java      # Gerenciador do sistema de ajudas
│   │   │   └── TelaJogo.form               # Arquivo do designer de GUI
│   │   └── resources/
│   │       └── perguntas.txt               # Base de perguntas do jogo
│   └── test/
│       └── java/                           # Testes unitários (futuros)
└── target/                                  # Artefatos compilados (gerado automaticamente)
```

### Descrição das Classes Principais

| Classe | Responsabilidade |
|--------|------------------|
| `ShowDoMilhao` | Ponto de entrada da aplicação |
| `TelaJogo` | Interface gráfica do jogo (Swing) |
| `ControladorJogo` | Lógica do jogo e controle de fluxo |
| `BancoDePergunta` | Carregamento e gerenciamento de perguntas |
| `Pergunta` | Modelo representando uma pergunta |
| `GerenciadorAjudas` | Controle do sistema de 50/50, Plateia e Ligação |

---

## 🆘 Sistema de Ajudas

O jogo oferece 3 tipos de ajuda que podem ser usadas uma única vez cada:

### 1️⃣ **50/50**
- Elimina 2 alternativas incorretas
- Deixa apenas a resposta correta e uma incorreta

### 2️⃣ **Plateia**
- Simula a opinião agregada da plateia
- Exibe percentuais de votos para cada alternativa
- Não é 100% confiável

### 3️⃣ **Ligação**
- Conecta com um especialista no assunto
- Oferece orientação sobre a resposta correta
- Use como último recurso em dúvidas críticas

---

## 📊 Tabela de Prêmios

| Pergunta | Prêmio | Nível Seguro |
|----------|--------|--------------|
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

*Ao errar após um nível seguro, você garante o prêmio daquele nível.*

---

## 🐛 Resolução de Problemas

### Erro: "Comando Maven não encontrado"
- Verifique se Maven foi adicionado às variáveis de ambiente
- Execute `mvn --version` para confirmar instalação

### Erro: "Java versão incompatível"
- O projeto requer Java 25+
- Verifique a versão instalada: `java -version`
- Se necessário, faça download da versão correta

### Arquivo de Perguntas não encontrado
- Certifique-se de que `src/main/resources/perguntas.txt` existe
- O arquivo deve estar em UTF-8 com formatação correta

---

## 👨‍💻 Autor

**Gustavo Maciel Huçulak**
- 📚 Estudante - 3ª Fase em Análise e Desenvolvimento de Sistemas (ADS)
- 🏫 IFC Campus Fraiburgo
- 📧 Contato: [gustahuculak@gmail.com]

---

## 📝 Licença

Este projeto é fornecido como material educacional para fins de aprendizado e avaliação academecional.

---



## 📚 Referências

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Apache Maven Documentation](https://maven.apache.org/guides/)
- [Show do Milhão - Globo](https://globoplay.globo.com/)

---

<div align="center">


Desenvolvido com ❤️ para fins educacionais

</div>
