# Image Service

Microserviço Spring Boot para gerenciamento de imagens com arquitetura baseada em microsserviços.

## Sobre o Projeto

Este é um microsserviço construído com Spring Boot 3.5.5 e Java 21 para gerenciamento de imagens. O serviço fornece endpoints RESTful para operações CRUD de imagens, incluindo armazenamento de URL, nome do arquivo e tamanho.

## Tecnologias Utilizadas

- **Java 21** - Linguagem principal
- **Spring Boot 3.5.5** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Segurança da aplicação
- **Spring Web** - API REST
- **Spring Cloud** - Configuração e descoberta de serviços
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências

## Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

- **Controllers**: `ImageController` - Endpoints RESTful
- **Service**: `ImageService` - Lógica de negócio
- **Repository**: `ImageRepository` - Acesso a dados
- **Entities**: `Image` - Entidade JPA
- **DTOs**: `ImageRequestDTO`, `ImageResponseDTO` - Transferência de dados

## Funcionalidades

- ✅ Listar todas as imagens
- ✅ Buscar imagem por ID
- ✅ Salvar nova imagem
- ✅ Atualizar imagem (PATCH)
- ✅ Excluir imagem
- ✅ Console H2 para visualização dos dados

## Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/images` | Listar todas as imagens |
| GET | `/images/{id}` | Buscar imagem por ID |
| POST | `/images` | Criar nova imagem |
| PATCH | `/images/{id}` | Atualizar imagem existente |
| DELETE | `/images/{id}` | Excluir imagem |

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/guialves/image_service/
│   │       ├── ImageServiceApplication.java
│   │       ├── config/
│   │       │   └── SecurityConfig.java
│   │       ├── controllers/
│   │       │   └── ImageController.java
│   │       ├── dtos/
│   │       │   ├── ImageRequestDTO.java
│   │       │   └── ImageResponseDTO.java
│   │       ├── entities/
│   │       │   └── Image.java
│   │       ├── exceptions/
│   │       │   └── GlobalExceptionHandler.java
│   │       │   └── ImageNotFoundException.java
│   │       ├── repository/
│   │       │   └── ImageRepository.java
│   │       └── service/
│   │           └── ImageService.java
│   └── resources/
│       ├── application.properties
│       ├── static/
│       └── templates/
└── test/
    └── java/
        └── com/guialves/image_service/
            └── ImageServiceApplicationTests.java
```

## Modelo de Dados

### Image Entity
- `id`: Long (auto-increment)
- `url`: String (URL da imagem)
- `fileName`: String (nome do arquivo)
- `size`: String (tamanho do arquivo)

### DTOs
- `ImageRequestDTO`: Usado para criar/atualizar imagens
- `ImageResponseDTO`: Usado para retornar dados de imagens

## Configuração

### Banco de Dados
- **Tipo**: H2 (em memória)
- **Console**: Disponível em `/h2-console`
- **DDL**: Auto-update habilitado
- **SQL Logging**: Habilitado

### Spring Cloud
- **Eureka Client**: Desabilitado por padrão
- **Config Server**: Desabilitado por padrão

## Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior

### Execução
```bash
# Clonar o repositório
git clone <repository-url>
cd image-service

# Executar com Maven
./mvnw spring-boot:run

# Ou compilar e executar
./mvnw clean package
java -jar target/image-service-0.0.1-SNAPSHOT.jar
```

### Acesso à Aplicação
- **API REST**: `http://localhost:8080/images`
- **H2 Console**: `http://localhost:8080/h2-console`
- **Health Check**: `http://localhost:8080/actuator/health`

## Exemplos de Uso

### Criar Imagem
```bash
curl -X POST http://localhost:8080/images \
  -H "Content-Type: application/json" \
  -d '{
    "url": "https://example.com/image.jpg",
    "fileName": "image.jpg",
    "size": "1024KB"
  }'
```

### Listar Imagens
```bash
curl -X GET http://localhost:8080/images
```

### Buscar Imagem por ID
```bash
curl -X GET http://localhost:8080/images/1
```

### Atualizar Imagem
```bash
curl -X PATCH http://localhost:8080/images/1 \
  -H "Content-Type: application/json" \
  -d '{
    "fileName": "new-name.jpg"
  }'
```

### Excluir Imagem
```bash
curl -X DELETE http://localhost:8080/images/1
```

## Desenvolvimento

### Testes
```bash
# Executar testes
./mvnw test

# Executar testes com coverage
./mvnw clean test jacoco:report
```

### Build
```bash
# Compilar projeto
./mvnw clean compile

# Gerar JAR
./mvnw clean package

# Limpar build
./mvnw clean
```

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Licença

Este projeto está sob licença MIT - veja o arquivo LICENSE para detalhes.

## Autor

**Guilherme Alves**
- GitHub: [guialves](https://github.com/guialves)
- Email: guialves@email.com

## Versão

0.0.1-SNAPSHOT