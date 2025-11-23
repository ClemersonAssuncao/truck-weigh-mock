# DUO WEIGH - Simulador de Balança

Aplicação desktop JavaFX que simula uma balança de caminhão e envia os dados para o servidor Weigh Hub.

## 🚀 Funcionalidades

- ✅ **Interface Gráfica Intuitiva** - Interface desktop com JavaFX
- ⚖️ **Simulação Realista** - Simula variações de peso até estabilização
- 🌐 **Integração com Servidor** - Envia dados automaticamente ao Weigh Hub
- 💾 **Fallback Local** - Salva dados em arquivo quando o servidor está offline
- 📊 **Log de Atividades** - Acompanhe todas as operações em tempo real
- 🔄 **Validação de Placas** - Suporte para placas brasileiras (antigo e Mercosul)

## 📋 Pré-requisitos

- Java 17 ou superior
- JavaFX 21
- Servidor Weigh Hub rodando (opcional - com fallback)

## 🎯 Como Executar

```bash
cd duo-weigh
./gradlew run
```

Ou no Windows:
```cmd
cd duo-weigh
gradlew.bat run
```

## 🖥️ Como Usar

1. **Configure os campos:**
   - **ID da Balança**: Identificador único da balança (ex: SCALE-001)
   - **Placa do Caminhão**: Placa no formato brasileiro (ex: ABC1D34)
   - **Peso Alvo (kg)**: Peso final esperado (ex: 15000)
   - **URL do Servidor**: Endereço do Weigh Hub (padrão: http://localhost:8080)

2. **Clique em "Iniciar Pesagem"**
   - O sistema simulará variações de peso
   - Após estabilização, enviará automaticamente ao servidor

3. **Acompanhe o Status:**
   - ⚖ ESTABILIZANDO... - Peso ainda variando
   - ✓ PESO ESTABILIZADO - Peso estável
   - ✓ ENVIADO AO SERVIDOR - Dados enviados com sucesso
   - ✗ ERRO - SALVO LOCALMENTE - Falha no envio, dados salvos localmente

## 💾 Sistema de Fallback

Quando o servidor está indisponível, os dados são salvos automaticamente em:
```
duo-weigh/failed_weighings/
```

Cada registro falho é salvo como um arquivo JSON com:
- ID da balança
- Placa do caminhão
- Peso registrado
- Timestamp
- Mensagem de erro

## 🏗️ Arquitetura

```
duo-weigh/
├── model/
│   └── WeightSimulator.java       # Simulador de peso
├── service/
│   ├── WeighHubClient.java        # Cliente HTTP
│   └── FallbackStorage.java       # Armazenamento local
└── controller/
    └── MainController.java        # Controller JavaFX
```

## 🔧 Tecnologias

- JavaFX 21 (Interface Gráfica)
- OkHttp 4.12.0 (Cliente HTTP)
- Gson 2.10.1 (Serialização JSON)
- Logback (Logging)
- Java 17

## 📦 Build

### JAR Executável:

```bash
./gradlew fatJar
```

O JAR será criado em: `build/libs/duo-weigh-1.0.0-all.jar`

Executar:
```bash
java -jar build/libs/duo-weigh-1.0.0-all.jar
```

## 🎨 Interface

A aplicação possui:
- **Painel de Configuração**: Campos para configurar a pesagem
- **Display de Peso**: Mostra o peso atual em tempo real
- **Status Visual**: Indicadores coloridos do estado da pesagem
- **Log de Atividades**: Histórico de todas as operações
- **Contador de Falhas**: Número de registros não enviados

## 🧪 Simulação

O simulador:
1. Inicia com peso variando entre 70% e 130% do peso alvo
2. Gradualmente se aproxima do peso alvo com variações realistas
3. Estabiliza quando fica dentro de 1% do peso alvo por 3 leituras consecutivas
4. Leituras acontecem a cada 500ms

## 📝 Logs

Logs são salvos em:
- Console (STDOUT)
- Arquivo `duo-weigh.log`

## 🔒 Validações

- Placa deve estar no formato brasileiro (ABC1234 ou ABC1D34)
- Peso deve ser numérico e positivo
- Todos os campos são obrigatórios
