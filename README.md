# Truck Weigh Mock

Projeto multi-módulo com dois serviços Java independentes para gestão de pesagens.

> Se trata de um simulador apenas.

## Pesagem

![Janela de pesagem](/docs/assets/images/pesagem.png)

## Estrutura do Projeto

```
duo-weight/
├── docker-compose.yml      # PostgreSQL (alternativa ao Testcontainers)
├── weigh-hub/              # Servidor de Pesagens (Spring Boot + PostgreSQL)
│   ├── build.gradle
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/duoweight/weighhub/
│           │       ├── domain/          # Camada de Domínio
│           │       ├── application/     # Casos de Uso
│           │       └── infrastructure/  # Infraestrutura (DB, Web)
│           └── resources/
│               └── application.properties
│
├── duo-weigh/              # Aplicação Desktop - Simulador de Balança
│   ├── build.gradle
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/duoweight/duoweigh/
│           │       ├── model/           # Simulador de peso
│           │       ├── service/         # HTTP client e fallback
│           │       └── controller/      # JavaFX controller
│           └── resources/
│               ├── com/duoweight/duoweigh/
│               │   └── main-view.fxml   # Interface FXML
│               └── logback.xml
│
└── settings.gradle         # Configuração multi-módulo
```

## 🏗️ Weigh Hub - Sistema de Pesagens

Servidor REST com Spring Boot usando arquitetura em camadas (Domain, Application, Infrastructure).

### 🐳 Executar o servidor:

**Pré-requisito:** Docker Desktop rodando

```bash
cd weigh-hub
./gradlew bootRun
```

Ou no Windows:
```cmd
cd weigh-hub
gradlew.bat bootRun
```

O Testcontainers irá iniciar automaticamente um container PostgreSQL (similar ao Quarkus Dev Services).

### 📡 Endpoints disponíveis:

**Registrar Pesagem:**
```http
POST http://localhost:8080/api/v1/weighing/records
Content-Type: application/json

{
    "scaleId": "Balance_1",
    "plate": "ABC1D34",
    "weight": 15000.50
}
```

**Listar todas as pesagens:**
```http
GET http://localhost:8080/api/v1/weighing/records
```

**Buscar pesagem por ID:**
```http
GET http://localhost:8080/api/v1/weighing/records/{id}
```

**Filtrar por balança:**
```http
GET http://localhost:8080/api/v1/weighing/records?scaleId=Balance_1
```

**Filtrar por placa:**
```http
GET http://localhost:8080/api/v1/weighing/records?plate=ABC1D34
```

### ✅ Validações:

- **Placas brasileiras:** Suporta formato antigo (ABC1234) e Mercosul (ABC1D34)
- **Peso:** Deve ser positivo
- **Campos obrigatórios:** scaleId, plate, weight

### 🏗️ Arquitetura:

- **Domain:** Entidades, Value Objects e interfaces de repositório
- **Application:** Casos de uso e DTOs
- **Infrastructure:** Implementação JPA, Controllers REST

### Build:

```bash
cd weigh-hub
./gradlew build
```

## 🖥️ DUO WEIGH - Simulador de Balança Desktop

Aplicação desktop JavaFX que simula uma balança de caminhão e envia dados automaticamente para o Weigh Hub.

### 🚀 Executar a aplicação:

```bash
cd duo-weigh
./gradlew run
```

Ou no Windows:
```cmd
cd duo-weigh
gradlew.bat run
```

### ⚖️ Funcionalidades:

- **Interface Gráfica Intuitiva** - Interface desktop com JavaFX
- **Simulação Realista** - Simula variações de peso até estabilização
- **Integração Automática** - Envia dados ao Weigh Hub quando estabiliza
- **Fallback Local** - Salva em arquivo JSON quando o servidor está offline
- **Log em Tempo Real** - Acompanhe todas as operações
- **Validação de Placas** - Suporte para placas brasileiras (antigo e Mercosul)

### 📋 Como Usar:

1. Configure os campos:
   - **ID da Balança**: Ex: SCALE-001
   - **Placa do Caminhão**: Ex: ABC1D34
   - **Peso Alvo (kg)**: Ex: 15000
   - **URL do Servidor**: http://localhost:8080

2. Clique em "Iniciar Pesagem"

3. Aguarde a estabilização do peso (variações simuladas)

4. Dados são enviados automaticamente ao servidor

### 💾 Sistema de Fallback:

Registros não enviados são salvos em `duo-weigh/failed_weighings/` como arquivos JSON.

### 📦 Criar JAR executável:

```bash
cd duo-weigh
./gradlew fatJar
```

JAR criado em: `build/libs/duo-weigh-1.0.0-all.jar`

Executar:
```bash
java -jar build/libs/duo-weigh-1.0.0-all.jar
```

## Build de todos os projetos

Da raiz do projeto:

```bash
./gradlew build
```

Ou no Windows:
```cmd
gradlew.bat build
```

## Requisitos

- Java 17 ou superior
- Docker Desktop (para PostgreSQL via Testcontainers)
- Gradle 8.5 (incluído via Gradle Wrapper)

## Tecnologias

### Weigh Hub (Sistema de Pesagens - Backend)
- Spring Boot 3.2.0
- Spring Web
- Spring Data JPA
- PostgreSQL 16
- Spring Boot Testcontainers (gerenciamento automático do PostgreSQL)
- Jakarta Validation
- Arquitetura em Camadas (Clean Architecture)
- Java 17

### DUO WEIGH (Simulador de Balança - Desktop)
- JavaFX 21 (Interface Gráfica)
- OkHttp 4.12.0 (Cliente HTTP)
- Gson 2.10.1 (Serialização JSON)
- Logback (Logging)
- Simulador de peso com estabilização realista
- Sistema de fallback para falhas de rede
- Java 17

## 🚀 Quick Start

### Opção 1: Teste completo com Interface Gráfica

```bash
# 1. Certifique-se que o Docker está rodando

# 2. Terminal 1: Execute o Weigh Hub (servidor)
cd weigh-hub
./gradlew bootRun

# 3. Terminal 2: Execute o DUO WEIGH (simulador desktop)
cd duo-weigh
./gradlew run
```

Preencha os campos na interface e clique em "Iniciar Pesagem"!

### Opção 2: Teste via API (curl)

```bash
# 1. Execute o Weigh Hub
cd weigh-hub
./gradlew bootRun

# 2. Teste a API
curl -X POST http://localhost:8080/api/v1/weighing/records \
  -H "Content-Type: application/json" \
  -d '{"scaleId":"Balance_1","plate":"ABC1D34","weight":15000}'
```

## 📊 Fluxo de Trabalho

1. **DUO WEIGH** simula o peso do caminhão na balança
2. Peso varia até **estabilizar** (±1% do alvo)
3. Dados são **enviados automaticamente** ao Weigh Hub via POST
4. **Weigh Hub** valida e armazena no PostgreSQL
5. Se falhar, **DUO WEIGH** salva localmente em JSON
# truck-weigh-mock
