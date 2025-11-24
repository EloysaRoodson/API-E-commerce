# 📌 API E-commerce – Backend Java

Esta API de E-commerce foi desenvolvida em **Java 21** utilizando **Spring Boot**, estruturada para simular o backend completo de uma loja virtual.  
O sistema fornece gerenciamento de **categorias, produtos, usuários e pedidos**, seguindo padrões **REST**, arquitetura em camadas e validações robustas.

---

## 🔎 Principais Recursos

- **Categorias**
  - Criar, listar, atualizar e remover categorias.
- **Produtos**
  - Cadastro com nome, descrição, preço, estoque e categoria.
  - Busca por ID, nome e categoria.
- **Usuários**
  - Registro com senha criptografada (BCrypt).
- **Pedidos**
  - Criação de pedidos com itens e quantidades.
  - Relacionamentos automáticos entre pedidos e produtos.

---

## ⚙️ Funcionamento Geral

1. O cliente faz requisições HTTP para:
   - `/api/categories`
   - `/api/products`
   - `/api/users`
   - `/api/orders`
2. Cada controlador aplica validações.
3. A camada **Service** processa regras de negócio.
4. O **Repository** persiste os dados via JPA.
5. O banco **H2** mantém os dados em memória durante a execução.
6. O **Swagger UI** exibe e testa todos os endpoints.

---

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Validation**
- **H2 Database**
- **Lombok**
- **ModelMapper**
- **Swagger/OpenAPI**

---

## 🎯 Objetivo do Projeto

Construir um backend de e-commerce funcional aplicando:

- Boas práticas REST
- Camadas bem definidas (Controller → Service → Repository)
- Validação e tratamento de erros
- Documentação automática
- Integração com Postman

Este projeto serve como base para evoluções futuras, como autenticação JWT, pagamentos e dashboards administrativos.

---
