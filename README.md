
# Microservice Email Send - Spring

Microserviço de envio de email usando Spring Framework

Siga as instruções do link:
https://support.google.com/accounts/answer/185833

Crie uma nova senha e no arquivo application.properties cole essa nova senha e o email vinculado a ela.
Execute o projeto.


## Documentação da API

#### Envia o email

```http
  POST /email/send
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `subject` | `string` | **Obrigatório**. Titulo do email |
| `content` | `string` | **Obrigatório**. Corpo do email |
| `owner` | `string` | **Obrigatório**. Email raiz, o mesmo do application |
| `addressee` | `string` | **Obrigatório**. Email do destinatario |

O corpo da requisição deve seguir o seguinte padrão:
```json
{
    "subject": "Titulo do email",
    "content": "Corpo do email",
    "owner": "emaildoemissor@gmail.com",
    "addressee": "destinatario@gmail.com"
}
```

#### Retorna todos os emails

```http
  GET /email
```
#### Retorna email por id

```http
  GET /email/:id
```
