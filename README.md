<div align="center">
	
![GitHub top language"](https://img.shields.io/github/languages/top/renatonunes74/foodminder.svg?style=for-the-badge)
![Repository size"](https://img.shields.io/github/repo-size/renatonunes74/foodminder.svg?style=for-the-badge)
![GitHub last commit"](https://img.shields.io/github/last-commit/renatonunes74/foodminder.svg?style=for-the-badge)
![Repository issues"](https://img.shields.io/github/issues/rockofox/firefox-minima.svg?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/renatonunes74/foodminder?style=for-the-badge)
# FoodMinder
**Receitas preparadas com os ingredientes presentes<br>Esta em desenvolvimento!**

[Porque usar](#porque-usar) -
[Como usar](#como-usar) -
[Quando usar](#quando-usar) -
[Aonde usar](#aonde-usar) -
[Diagramas](#diagramas)
<!-- [Diagramas](#diagramas) - -->
<!-- [Alternativas](#alternativas) -->
<br>
</div>

## Pré-visualização
![](preview.gif)

## Porque usar
- Focado em auxiliar no controle mais amigável do estoque de comida, e a possibilidade de integração com outras ferramentas!

## Como usar
1. Clone o repositório
    - `git clone https://github.com/renatonunes74/foodminder`
1. Entre na pasta
    - `cd foodminder/FoodMinder`
1. Rode localmente
    - CLI / via Terminal:
        - `mvn spring-boot:run`
    - Via IDE (JetBrains, Eclipse...)
1. Agora é só fazer as requisições para a API

### Funcionalidades
- Listar, adicionar, deletar, atualizar novos produtos
    - Listar todos os produtos
        - `http http://localhost:8080/product`
    - Adicionar um novo produto
        - `http POST localhost:8080/product name="Nome do Produto" price_in_cents=2500`
    - Atualizar um produto
        - `http PUT localhost:8080/product id=5d5e5e33-a8a6-4b3e-bb7b-185d52b8671b name='Nome do Produto' price_in_cents=2600`
    - Deletar um produto
        - `http DELETE localhost:8080/product/5d5e5e33-a8a6-4b3e-bb7b-185d52b8671b`
- Listar, adicionar, deletar, atualizar novos ingredientes
    - Em breve...
- Listar, adicionar, deletar, atualizar receitas personalizadas
    - Em breve...
- Ver quais receitas podem ser feitas
    - Em breve...

### Dependências necessárias
- [Java](https://dev.java/)
- [MySQL](https://www.mysql.com/)

### Tecnologias usadas
- Linguagem: [Java](https://dev.java/)
    - Frameworks: [Spring Boot](https://spring.io/projects/spring-boot)
    - Bibliotecas:
        - [Lombok](https://projectlombok.org/) (Anotações para gerar automaticamente métodos getters, setters, construtores, entre outros, em tempo de compilação)
        - [FlyWay](https://documentation.red-gate.com/fd/flyway-documentation-138346877.html) (Versionamento do banco de dados)
- Banco de dados: [MySQL](https://www.mysql.com/)
- Complementares:
     - [Spring Initializer CLI](https://github.com/renatonunes74/spring_initializr_cli) (Para a criação do Spring Boot)
     - [httpie](https://httpie.io/) (Maior facilidade para requisições HTTP (alternativa ao `curl`))
     - [VHS](https://github.com/charmbracelet/vhs) (Criação de GIF do terminal via código)

## Quando usar
- Simples, quando tiver feito uma compra no mercado, liste os itens comprados, além de gerar uma ideia dos custos, também fará uma maior gestão daquilo que consome!
## Aonde usar
- A aplicação esta em desenvolvimento e por enquanto será possível por meio de requisições HTTP...

## Diagramas
### Diagrama de classes
```mermaid
classDiagram
class Product {
  - id : String
  - name : String
  - price_in_cents : List ~Postagem~
  + getAllProducts
  + registerProduct
  + updateProduct
  + deleteProduct
}
```
# Diagrama de relacionamento
```mermaid
erDiagram
   	product {
		VARCHAR id
		TEXT nome
		INT price_in_cents 
    }
```

<!-- ## Alternativas -->
<!-- - [FoodMinder CLI]() -->

## Contribuição
Estou aberto a contribuições, feedback e opiniões da comunidade! Se você tiver alguma ideia para melhorar o projeto, correções de bugs ou sugestões de novos recursos, ficarei feliz em receber sua contribuição! Basta enviar um Pull Request no repositório do projeto.
