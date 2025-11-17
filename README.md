# Sistema de Materiais (Sys-Materiais)

Um sistema web completo para gerenciamento de materiais e solicitações desenvolvido em Spring Boot com banco de dados MariaDB.

## 📋 Descrição

O Sys-Materiais é um sistema robusto de gestão de materiais que permite:
- Cadastro e controle de itens/materiais com rastreamento de alterações
- Gerenciamento completo de usuários com controle de status
- Sistema de solicitação de materiais
- Autenticação e autorização de usuários
- Sistema de logs integrado (Frontend e Backend)
- Timestamps automáticos de cadastro e alteração

## 🚀 Tecnologias Utilizadas

- **Backend:**
  - Java 21
  - Spring Boot 3.4.0
  - Spring Web
  - Spring Data JPA
  - Spring Transaction Management

- **Banco de Dados:**
  - MariaDB 10.x
  - JPA/Hibernate para ORM
  - Triggers automáticos para timestamps

- **Frontend:**
  - HTML5
  - CSS3
  - JavaScript (Vanilla)
  - Fetch API para requisições assíncronas

- **Build Tool:**
  - Apache Maven
  - Maven Wrapper incluído

## 📁 Estrutura do Projeto

```
sys-materiais/
├── src/
│   ├── main/
│   │   ├── java/com/keeper/sys_materiais/
│   │   │   ├── config/          # Configurações (JPA, Web, CORS)
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── model/          # Entidades do domínio
│   │   │   ├── repository/     # Repositórios de dados
│   │   │   ├── service/        # Serviços de negócio (Auth, Log)
│   │   │   └── exception/      # Tratamento de exceções
│   │   └── resources/
│   │       ├── static/         # Arquivos estáticos
│   │       │   ├── *.html      # Páginas da aplicação
│   │       │   └── css/        # Estilos CSS
│   │       └── application.properties
│   └── test/                   # Testes unitários
├── logs/                       # Logs da aplicação
├── pom.xml                     # Configuração Maven
└── mvnw / mvnw.cmd            # Maven Wrapper
```

## 🎯 Funcionalidades

### Gestão de Itens
- ✅ Cadastro de novos itens/materiais com validação
- ✅ Edição de itens existentes via PUT
- ✅ Listagem completa de todos os itens
- ✅ Controle de status (ativo/inativo)
- ✅ Campos automáticos: dataCadastro e dataAlteracao
- ✅ Visualização detalhada com badge de status
- ✅ Rastreamento de quem cadastrou o item

### Gestão de Usuários
- ✅ Cadastro de usuários com matrícula única (4 dígitos)
- ✅ Atualização de usuários com @Transactional
- ✅ Controle de usuários ativos/inativos
- ✅ Sistema de autenticação com senha criptografada
- ✅ Página de detalhes com badge de status
- ✅ Registro automático de última alteração
- ✅ Validação de matrícula duplicada

### Solicitação de Materiais
- ✅ Criação de solicitações de materiais
- ✅ Vinculação de múltiplos itens por solicitação
- ✅ Controle temporal das solicitações
- ✅ Associação com usuário solicitante

## 🛠️ Configuração e Instalação

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior
- MariaDB 10.x ou MySQL 8.x
- Git (opcional)

### Configuração do Banco de Dados

1. **Crie o banco de dados**
   ```sql
   CREATE DATABASE sys_materiais CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. **Crie o usuário (opcional)**
   ```sql
   CREATE USER 'sys_materiais'@'localhost' IDENTIFIED BY 'sua_senha';
   GRANT ALL PRIVILEGES ON sys_materiais.* TO 'sys_materiais'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Configure as tabelas** (o JPA criará automaticamente se `ddl-auto=update`)

### Passos para execução

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd sys-materiais
   ```

2. **Configure o application.properties**
   ```properties
   # Atualize com suas credenciais
   spring.datasource.url=jdbc:mariadb://localhost:3306/sys_materiais?useSSL=false
   spring.datasource.username=sys_materiais
   spring.datasource.password=sua_senha
   ```

3. **Compile o projeto**
   ```bash
   ./mvnw clean compile
   # Windows: .\mvnw.cmd clean compile
   ```

4. **Execute a aplicação**
   ```bash
   ./mvnw spring-boot:run
   # Windows: .\mvnw.cmd spring-boot:run
   ```

5. **Acesse a aplicação**
   - URL: `http://localhost:9090`
   - Home: `http://localhost:9090/home.html`

## 📊 Endpoints da API

### Itens
- `GET /itens/list` - Lista todos os itens
- `POST /itens/add` - Adiciona novo item
- `PUT /itens/{id}` - Atualiza item existente (com @Transactional)
- `GET /itens/{id}` - Busca item por ID

### Usuários
- `GET /usuarios/list` - Lista todos os usuários
- `POST /usuarios/add` - Adiciona novo usuário (valida matrícula única)
- `PUT /usuarios/{id}` - Atualiza usuário existente (com @Transactional)
- `GET /usuarios/{matricula}` - Busca usuário por matrícula

### Solicitações
- `GET /solicitacoes/list` - Lista todas as solicitações
- `POST /solicitacoes/add` - Cria nova solicitação
- `GET /solicitacoes/{id}` - Busca solicitação por ID

### Autenticação
- `POST /auth/login` - Realiza login (retorna usuário autenticado)
- `POST /auth/logout` - Realiza logout
- `GET /auth/check` - Verifica status da sessão

## 🖥️ Páginas Web

- **Login** (`/login.html`) - Página de autenticação (Sem funcionalidade no momento)
- **Home** (`/home.html`) - Página principal do sistema
- **Cadastrar Item** (`/cadastrarItem.html`) - Formulário para novos itens
- **Detalhes do Item** (`/detalhesItem.html`) - Visualização detalhada do item com badge de status
- **Solicitar Material** (`/solicitarMaterial.html`) - Formulário de solicitação
- **Usuários** (`/usuarios.html`) - Listagem de usuários
- **Detalhes do Usuário** (`/detalhesUsuario.html`) - Visualização e edição de usuário com badge de status
- **Debug Item** (`/debugItem.html`) - Página de debug (desenvolvimento)

## 🔧 Configurações

### application.properties
```properties
# Servidor
server.port=9090

# Banco de dados MariaDB
spring.datasource.url=jdbc:mariadb://localhost:3306/sys_materiais?useSSL=false
spring.datasource.username=sys_materiais
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# JPA e Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

## 📝 Modelos de Dados

### Item
- `id` (Long) - Identificador único
- `codigo` (String) - Código do item
- `nome` (String) - Nome do item
- `cadastradoPor` (String) - Usuário que cadastrou
- `ativo` (Boolean) - Status do item
- `dataCadastro` (LocalDateTime) - Data de cadastro (automático)
- `dataAlteracao` (LocalDateTime) - Data da última alteração (automático)

### Usuario
- `id` (Long) - Identificador único
- `matricula` (String) - Matrícula do usuário (4 dígitos, única)
- `nome` (String) - Nome completo (max 100 caracteres)
- `senha` (String) - Senha criptografada com BCrypt
- `ativo` (Boolean) - Status do usuário
- `dataCadastro` (LocalDateTime) - Data de cadastro (automático)
- `dataAlteracao` (LocalDateTime) - Data da última alteração (automático)

### Solicitacao
- `id` (Long) - Identificador único
- `solicitante` (String) - Usuário solicitante
- `data` (LocalDateTime) - Data da solicitação
- `itensIds` (List<Long>) - IDs dos itens solicitados

## 🔍 Observações Importantes

### Timestamps Automáticos
- Os campos `dataCadastro` e `dataAlteracao` são gerenciados automaticamente pelo banco de dados
- Configurados com `updatable=false` e `insertable=false` no JPA
- Utilizam triggers do MariaDB: `DEFAULT current_timestamp()` e `ON UPDATE current_timestamp()`

### Transações
- Métodos de atualização (PUT) utilizam `@Transactional`
- `EntityManager.flush()` e `refresh()` garantem que as datas atualizadas sejam retornadas
- Validações de unicidade (matrícula de usuário) antes de salvar

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## ✨ Autor

Desenvolvido por **Saulo Christian** - Sistema de Gestão de Materiais

---

**Versão:** 1.0.0  
**Data:** Novembro 2025  
**Última Atualização:** 16/11/2025
