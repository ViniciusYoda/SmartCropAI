# SmartCropAI

Api para controle de usuário, solo, clima do app SmartCropAI

## Endpoints

- Clima
    - [Cadastrar](#cadastrar-clima)
    - [Mostrar detalhes](#detalhar-clima)
    - [Apagar](#apagar-clima)
    - [Atualizar](#detalhar-atualização-clima)
    - [Listar todos clima](#listar-climas)
- Solo
    - [Cadastrar](#cadstrar-solo)
    - [Mostrar detalhes](#dedtalhar-solo)
    - [Apagar](#apagar-solo)
    - [Atualizar](#detalhar-atualização-solo)
    - [Listar todos solo](#listar-solo)
 - Usuario
    - [Cadastrar](#cadastrar-usuario)
    - [Login](#login-usuario)

---

### Cadastrar clima

`POST` /api/clima

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | O id precisa ser diferente para cada produto
| Cep | string | sim | O cep precisa seguir esse padrão: 00000-000
| Precipitação | String | sim | O campo pode ser alta ou baixa
| Umidade |String | sim | O campo pode ser alta ou baixa
| Vel. Do Vento | String | Campo para colocar velocidade do vento
| Dir. Do Vento | String | Campo para colocar direção do vento
| Pressão Atmosférica | String | Campo para colocar pressão atmosférica

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    cep: "02945-100",
    precipitacao: "Baixa",
    umidade: "Baixa",
    velDoVento: "Ventos alisios",
    dirDoVento: "Moderados a fortes",
    pressaoAtmosferica: "Alta
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | clima cadastrado com sucesso
| 400 | os campos enviados são inválidos

---

### Detalhar clima

`GET` /api/clima/{id}

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    cep: "02945-100",
    precipitacao: "Baixa",
    umidade: "Baixa",
    velDoVento: "Ventos alisios",
    dirDoVento: "Moderados a fortes",
    pressaoAtmosferica: "Alta"
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 400 | não existe clima com o id informado

---

### Apagar clima

`DELETE`/api/clima/{id}

| código | descrição
|-|-
| 200 | clima apagado com sucesso
| 400 | não existe clima com o id informado

---

### Atualizar clima

`PUT` /api/clima/{id}

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    cep: "02945-100",
    precipitacao: "Baixa",
    umidade: "Baixa",
    velDoVento: "Ventos alisios",
    dirDoVento: "Moderados a fortes",
    pressaoAtmosferica: "Alta"
}
```

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    cep: "79073-245",
    precipitacao: "Baixa",
    umidade: "Baixa",
    velDoVento: "Ventos alisios",
    dirDoVento: "Moderados a fortes",
    pressaoAtmosferica: "Alta"
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | clima atualizado com sucesso
| 400 | clima com id não encontrado

--- 

### Listar clima

`GET` /api/clima

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    cep: "79073-245",
    precipitacao: "Baixa",
    umidade: "Baixa",
    velDoVento: "Ventos alisios",
    dirDoVento: "Moderados a fortes",
    pressaoAtmosferica: "Alta"
},
{
    id: 2,
    cep: "69907-290",
    precipitacao: "Alta",
    umidade: "Baixa",
    velDoVento: "Ventos alisios",
    dirDoVento: "Moderados a baixo",
    pressaoAtmosferica: "Baixo"
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | climas listado com sucesso
| 400 | climas não encontrado

---

### Cadastrar solo

`POST` /api/solo

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | O id precisa ser diferente para cada produto
| Textura do Solo | string | sim | tipo de textura do solo
| PH do solo | Double | sim | PH do solo
| Retenção da água |String | sim | Se a retenção é alta ou baixa
| Condutor Eletrica | String | Qual tipo de condutor eletrico

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    texturaDoSolo: "Argiloso",
    phDoSolo: 6.5,
    retencaoDeAgua: "Alta",
    condutEletrica: "Cobre"
    
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | solo cadastrado com sucesso
| 400 | os campos enviados são inválidos

---

### Detalhar solo

`GET` /api/solo/{id}

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    texturaDoSolo: "Argiloso",
    phDoSolo: 6.5,
    retencaoDeAgua: "Alta",
    condutEletrica: "Cobre"
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 400 | não existe solo com o id informado

---

### Apagar solo

`DELETE`/api/solo/{id}

| código | descrição
|-|-
| 200 | solo apagado com sucesso
| 400 | não existe solo com o id informado

---

### Atualizar solo

`PUT` /api/solo/{id}

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    texturaDoSolo: "Argiloso",
    phDoSolo: 6.5,
    retencaoDeAgua: "Alta",
    condutEletrica: "Cobre"
}
```

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    texturaDoSolo: "Argiloso",
    phDoSolo: 8.1,
    retencaoDeAgua: "Alta",
    condutEletrica: "Cobre"
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | solo atualizado com sucesso
| 400 | solo com id não encontrado

--- 

### Listar solo

`GET` /api/solo

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    texturaDoSolo: "Argiloso",
    phDoSolo: 6.5,
    retencaoDeAgua: "Alta",
    condutEletrica: "Cobre"
},
{
    id: 2,
    texturaDoSolo: "Arenoso",
    phDoSolo: 4.5,
    retencaoDeAgua: "Alta",
    condutEletrica: "Cobre"
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | solos listado com sucesso
| 400 | solos não encontrado

---

### Cadastrar usuario

`POST` /api/cadastrar

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | O id precisa ser diferente para cada produto
| Nome | string | sim | Campo para colocar o nome do usuário
| CPF | String | sim | CPF precisa seguir esse padrão: 000.000.000-00
| Email |String | sim | Campo para colocar email
| Senha | String | A senha precisa ter mais de 8 caracteres

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    nome: "José Maria",
    cpf: "618.360.121-23",
    email: "vinicius@gmail.com",
    senha: "12345678"
    
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | usuario cadastrado com sucesso
| 400 | os campos enviados são inválidos

---


### Login usuario

`POST` /api/login

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | O id precisa ser diferente para cada produto
| Email |String | sim | Campo para colocar email
| Senha | String | A senha precisa ser igual ao do cadastrado

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    token: "$2a$12$pMH3uGhwRXAaEq21jmmqn.PzxykI/HJyVAXM6sIQlcQ/2emqevaWC"
    
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | token criado com sucesso
| 400 | os campos enviados são inválidos

---
