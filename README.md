# Sistema de Materiais (Sys-Materiais)

Um sistema web para gerenciamento de materiais e solicitações desenvolvido em Spring Boot.

## 📋 Descrição

O Sys-Materiais é um sistema completo de gestão de materiais que permite:
- Cadastro e controle de itens/materiais
- Gerenciamento de usuários
- Sistema de solicitação de materiais
- Autenticação e autorização de usuários

## 🚀 Tecnologias Utilizadas

- **Backend:**
  - Java 21
  - Spring Boot 3.5.7
  - Spring Web
  - Spring Data JPA
  - BCrypt para criptografia de senhas

- **Banco de Dados:**
  - H2 Database (em memória)
  - JPA/Hibernate para ORM

- **Frontend:**
  - HTML5
  - CSS3
  - JavaScript

- **Build Tool:**
  - Apache Maven

## 📁 Estrutura do Projeto

```
src/main/java/
├── config/          # Configurações (JPA, Web)
├── controller/      # Controladores REST
├── model/          # Entidades do domínio
├── repository/     # Repositórios de dados
└── service/        # Serviços de negócio

src/main/resources/
├── static/         # Arquivos estáticos (HTML, CSS)
└── application.properties
```

## 🎯 Funcionalidades

### Gestão de Itens
- Cadastro de novos itens/materiais
- Edição de itens existentes
- Listagem de todos os itens
- Controle de status (ativo/inativo)

### Gestão de Usuários
- Cadastro de usuários com matrícula
- Controle de usuários ativos
- Sistema de autenticação

### Solicitação de Materiais
- Criação de solicitações de materiais
- Vinculação de múltiplos itens por solicitação
- Controle temporal das solicitações

### Segurança
- Autenticação de usuários
- Criptografia de senhas com BCrypt
- Controle de sessão

## 🛠️ Configuração e Instalação

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior

### Passos para execução

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd sys-materiais
   ```

2. **Compile o projeto**
   ```bash
   mvnw clean compile
   ```

3. **Execute a aplicação**
   ```bash
   mvnw spring-boot:run
   ```

4. **Acesse a aplicação**
   - URL: `http://localhost:9090`
   - Console H2: `http://localhost:9090/h2-console`

### Configuração do Banco de Dados H2

- **URL JDBC:** `jdbc:h2:mem:testdb`
- **Usuário:** `sa`
- **Senha:** (em branco)

## 📊 Endpoints da API

### Itens
- `GET /itens/list` - Lista todos os itens
- `POST /itens/add` - Adiciona novo item
- `PUT /itens/{id}` - Atualiza item existente

### Usuários
- `GET /usuarios/list` - Lista todos os usuários
- `POST /usuarios/add` - Adiciona novo usuário
- `PUT /usuarios/{matricula}` - Atualiza usuário

### Solicitações
- `GET /solicitacoes/list` - Lista todas as solicitações
- `POST /solicitacoes/add` - Cria nova solicitação

### Autenticação
- `POST /auth/login` - Realiza login
- `POST /auth/logout` - Realiza logout

## 🖥️ Páginas Web

- **Home** (`/home.html`) - Página principal do sistema
- **Login** (`/login.html`) - Página de autenticação
- **Cadastrar Item** (`/cadastrarItem.html`) - Formulário para novos itens
- **Solicitar Material** (`/solicitarMaterial.html`) - Formulário de solicitação
- **Usuários** (`/usuarios.html`) - Gestão de usuários
- **Detalhes** - Páginas de visualização detalhada

## 🔧 Configurações

### application.properties
```properties
# Servidor
server.port=9090

# Banco de dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Console H2
spring.h2.console.enabled=true
```

## 📝 Modelos de Dados

### Item
- `id` (Long) - Identificador único
- `codigo` (String) - Código do item
- `nome` (String) - Nome do item
- `cadastradoPor` (String) - Usuário que cadastrou
- `ativo` (boolean) - Status do item

### Usuario
- `matricula` (String) - Matrícula do usuário
- `nome` (String) - Nome completo
- `ativo` (boolean) - Status do usuário

### Solicitacao
- `id` (Long) - Identificador único
- `solicitante` (String) - Usuário solicitante
- `data` (LocalDateTime) - Data da solicitação
- `itensIds` (List<Long>) - IDs dos itens solicitados

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## ✨ Autor

Desenvolvido por **Saulo Christian** - Sistema de Gestão de Materiais

---

**Versão:** 1.0  
**Data:** 2025
