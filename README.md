# API REST produto-seguros

## Indice

1. [Tecnologias](#tecnologias)
2. [Execução local](#execucaolocal)
3. [Observações](#observacoes)
4. [Acessando o Grafana](#grafana)

## <a name="tecnologias">Tecnologias</a>

### Algumas das tecnologias e frameworks utilizados na API

* Java 17
* Spring Boot 3
* Spring Data
* Banco de dados H2
* Junit
* Lombok
* Mapstruct

### A API também está integrada com o Grafana, para que seja possível realizar o monitoramento da mesma, para isso foi utilizado

* Docker
* Prometheus
* Grafana

## <a name="execucaolocal">Execução local</a>

* API
  * Para executar a API, ao usar uma IDE, como por exemplo InteliJ, é necessário executar o maven para baixar as dependências e depois é só executar a classe ProdutoSegurosApplication.java (A API está configurada para usar a porta 8080)
* Grafana
  * Para executar o Grafana, é necessário executar o comando docker-compose up, dentro da pasta docker, este comando vai executar uma instância do Prometheus e do Grafana


### Pré requisitos para execução local

* Para subir a API localmente é necessário ter o Java 17 e maven instalados
* Para conseguir vizualizar o Grafana é necessário ter o Docker instalado. Obs. A API funciona idependente do Docker, ou seja, se a máquina não possuir ele instalado, apenas não será possível ver o Grafana


### Chamar a API

A API possui dois serviços, um que inseri um novo produto e outro que atualiza um produto

### Validações

Para facilitar o consumo, pode-se usar a collection do postman que está na pasta postman

A API tem algumas validações, o campo categoria aceita os seguintes valores: 

* VIDA
* AUTO
* VIAGEM
* RESIDENCIAL
* PATRIMONIAL

Os seguintes campos são obrigatórios tanto ao um inserir um produto, como ao atualizar o mesmo

* nome
* categoria
* preco_base


### Inserir produto

POST http://localhost:8080/v1/produtos

```json
{
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": 100.00
}
```

Com retorno de sucesso, HTTP 201 Created

```json
{
  "preco_base": 100.00,
  "preco_tarifado": 106.00,
  "id": 1,
  "nome": "Seguro de Vida Individual",
  "categoria": "VIDA"
}
```

### Atualizar produto

PUT http://localhost:8080/v1/produtos/{id}

```json
{
  "preco_base": 200.00,
  "nome": "Seguro de Vida Individual Alterado",
  "categoria": "VIDA"
}
```

Como retorno de sucesso, HTTP 200 OK

```json
{
  "preco_base": 200.00,
  "preco_tarifado": 212.00,
  "id": 1,
  "nome": "Seguro de Vida Individual Alterado",
  "categoria": "VIDA"
}
```

## <a name="observacoes">Observações</a>

O projeto conta com testes unitários e teste de integração, o teste de integração tem o nome Integracao na composição do nome da classe

## <a name="grafana">Acessando o Grafana</a>

Com a API em funcionamento e o docker executado, acesse o Grafana em

http://localhost:3000/login

Para conseguir logar, use o usuário admin e a senha admin

### Configurando o Dashborad

Depois de acessar o Grafana, você verá a tela a seguir

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/7725ff21-fab0-4af3-8aec-ad353ca66447">

Agora clique em DATA SOURCES

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/f5e38901-7c95-4bac-8fc4-ec60976ee6f6">

Selecione o Prometeus e configure conforme abaixo

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/c97f973f-bf1a-4d00-a016-5b5c7280d398">

Depois é só clicar em Save & Test

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/be553ca4-b670-4006-9c40-c78ff08ec4ea" >

Após isso volte para a Home e clique em DASHBOARDS

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/f54c25a8-0529-4b34-a031-048d63397ab4" >

Importe um dashboard

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/5e92de97-ddef-4ac6-ba88-1491dc374671" >

Preencha com o número 14430 e depois clique em load

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/6f3e4044-4906-4f6c-9382-7109a8ea19eb" >

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/b7f2e591-30f8-443d-806c-4b1c75509fd8" >

Selecione o Prometheus e depois clique para importar

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/1a26347c-8a18-4f4c-ad52-b651b19dd380" >

Agora com o Dashboard importado, selecione a aplicação no campo Application

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/59c5bf76-a64e-4025-93bb-2ce0b32311b8" >

O nome da aplicação está configurada como api-produto-seguros, escreva este nome no campo e depois aperte Enter

<img src="https://github.com/DouglasTrigo/produto-seguros/assets/11529081/9a0cf4f9-71c9-4ab3-8073-413ab2b771ed" >

