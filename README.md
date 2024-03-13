# API Spring Boot para Validação de Token JWT

Esta é uma API Spring Boot criada para validar tokens JWT. A API possui dois endpoints principais:

## Endpoints

- **/api/generate/token**
  - Método: POST
  - Descrição: Gera um token JWT com base no payload fornecido.
  - Exemplo de Requisição:
    ```json
    POST /api/generate/token
    {
        "Role": "Admin",
        "Seed": "7841",
        "Name": "Toninho Araujo"
    }
    ```
  - Exemplo de Resposta (200 OK):
    ```
    eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.GDT7UYeUx5c1-n3w3qr3SyAjsan5AANuHqJvq_lW1-Y
    ```

- **/api/validation/token/{token}**
  - Método: GET
  - Descrição: Valida o token JWT fornecido e verifica se as claims estão de acordo com as regras especificadas.
  - Parâmetros da URL: `{token}` - Token JWT a ser validado.
  - Exemplo de Requisição:
    ```url
    GET /api/validation/token/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.GDT7UYeUx5c1-n3w3qr3SyAjsan5AANuHqJvq_lW1-Y
    ```
  - Exemplo de Resposta (200 OK):
    ```
    true
    ```
  - Exemplo de Resposta (400 Bad Request):
    ```
    false
    ```

## Etapas de Validação das Claims

1. **Validar se o token é válido**: Verifica se o token JWT fornecido é válido.

2. **Validar as Claims**:
   - **Name**: Não pode conter caracteres numéricos e deve ter no máximo 256 caracteres.
   - **Role**: Deve conter apenas um dos seguintes valores: Admin, Member, External.
   - **Seed**: Deve ser um número primo.

## Como Contribuir

Caso deseje contribuir para este projeto, sinta-se à vontade para abrir issues ou enviar pull requests para melhorias.

## Contato

Para mais informações, entre em contato com [gustavo.melo_10@hotmail.com](mailto:gustavo.melo_10@hotmail.com).
