# Cotação Service

Serviço responsável por consultar a cotação atual das moedas utilizadas pela Casa de Câmbio.

## Tecnologias

- Java
- Spring Boot
- Spring Web
- RestClient
- Maven
- AwesomeAPI

## Responsabilidade

O `cotacao-service` consulta a cotação externa e realiza a conversão do valor informado para reais.

Moedas aceitas:

- USD - Dólar
- EUR - Euro

Qualquer outra moeda retorna HTTP 404.

## Fluxo

```text
Requisição
    ↓
CotacaoController
    ↓
CotacaoService
    ↓
AwesomeApiClient
    ↓
AwesomeAPI
```

## Configuração

A API Key da AwesomeAPI é fornecida através da variável de ambiente:

```text
AWESOME_API_KEY
```

No PowerShell:

```powershell
$env:AWESOME_API_KEY="SUA_CHAVE"
```

A chave não deve ser armazenada diretamente no código-fonte.

## Executando

```bash
mvn spring-boot:run
```

O serviço está configurado para executar na porta:

```text
8880
```

## Consultar cotação

```http
GET /cotacao/{moeda}/{valor}
```

Exemplo:

```bash
curl.exe "http://localhost:8880/cotacao/USD/100"
```

Exemplo de resposta:

```json
{
  "moeda": "USD",
  "valor": 100,
  "cotacao": 4.9921,
  "valorConvertido": 499.21
}
```

Nesse exemplo:

```text
100 USD × 4,9921 = R$ 499,21
```

## Tratamento de erros

Moeda não suportada:

```text
HTTP 404 - Not Found
```

Erro ao consultar o provedor externo:

```text
HTTP 500 - Internal Server Error
```

O `cotacao-service` é utilizado pelo `ordem-service` durante a criação de uma ordem de compra.
