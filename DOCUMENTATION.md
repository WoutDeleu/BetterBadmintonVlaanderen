# Better Badminton Vlaanderen - Project Documentation

## Table of Contents

1. [Project Overview](#project-overview)
2. [Architecture Design](#architecture-design)
3. [Technology Stack](#technology-stack)
4. [Project Structure](#project-structure)
5. [Backend Architecture](#backend-architecture)
6. [Frontend Architecture](#frontend-architecture)
7. [Development Setup](#development-setup)
8. [Testing Strategy](#testing-strategy)
9. [Deployment](#deployment)
10. [Integration with Badminton Vlaanderen](#integration-with-badminton-vlaanderen)
11. [Feature Specifications](#feature-specifications)
12. [API Documentation](#api-documentation)
13. [Security Considerations](#security-considerations)
14. [Performance Considerations](#performance-considerations)
15. [Monitoring and Logging](#monitoring-and-logging)
16. [Contributing Guidelines](#contributing-guidelines)
17. [Troubleshooting](#troubleshooting)

---

## Project Overview

### Vision
Better Badminton Vlaanderen is an enhanced application designed to extend and improve the functionality of the existing badminton portal system used in Flanders (www.badmintonvlaanderen.be). The application serves as a comprehensive extension platform providing advanced analytical capabilities, ranking simulations, and strategic planning tools for badminton players, clubs, and administrators.

### Mission Statement
To provide badminton enthusiasts in Flanders with powerful tools for:
- Strategic competition planning
- Future ranking simulations
- Tournament outcome predictions
- League scenario analysis
- Enhanced data visualization and insights

### Key Stakeholders
- **Players**: Individual badminton players seeking ranking insights and tournament strategies
- **Clubs**: Badminton clubs planning competitive strategies and member development
- **Administrators**: Tournament and league organizers requiring analytical tools
- **Coaches**: Training professionals analyzing player progression and competition scenarios

### Business Value
- **Enhanced Decision Making**: Data-driven insights for competitive planning
- **Strategic Planning**: "What-if" scenario analysis for tournaments and leagues
- **Improved User Experience**: Modern, intuitive interface extending the official portal
- **Community Growth**: Tools to engage and develop the badminton community in Flanders

---

## Architecture Design

### Architectural Principles

#### 1. Hexagonal Architecture (Ports and Adapters)
The backend follows strict hexagonal architecture principles:
- **Domain Layer**: Core business logic independent of external concerns
- **Application Layer**: Use cases and port definitions (interfaces)
- **Infrastructure Layer**: Adapters for external systems and frameworks

#### 2. Clean Architecture Benefits
- **Independence**: Business rules don't depend on frameworks, UI, or databases
- **Testability**: Business rules can be tested without external elements
- **Framework Independence**: Not bound to specific frameworks or libraries
- **Database Independence**: Business rules don't know about the database
- **External Agency Independence**: Business rules don't know about external interfaces

#### 3. SOLID Principles
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Open for extension, closed for modification
- **Liskov Substitution**: Objects should be replaceable with instances of their subtypes
- **Interface Segregation**: Many client-specific interfaces are better than one general-purpose interface
- **Dependency Inversion**: Depend on abstractions, not concretions

### System Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                        Frontend                             │
│              React + TypeScript                            │
│                                                            │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐       │
│  │   Ranking   │  │ Tournament  │  │ Competition │       │
│  │ Simulation  │  │ Prediction  │  │  Scenarios  │       │
│  │     UI      │  │     UI      │  │     UI      │       │
│  └─────────────┘  └─────────────┘  └─────────────┘       │
└─────────────────────────────────────────────────────────────┘
                               │
                               │ HTTP/REST API
                               │
┌─────────────────────────────────────────────────────────────┐
│                       Backend                               │
│                Java Spring Boot                            │
│                                                            │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              Infrastructure Layer                   │   │
│  │                                                     │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐ │   │
│  │  │    REST     │  │ Badminton   │  │  Database   │ │   │
│  │  │ Controllers │  │ Vlaanderen  │  │   Adapter   │ │   │
│  │  │             │  │   Adapter   │  │             │ │   │
│  │  └─────────────┘  └─────────────┘  └─────────────┘ │   │
│  └─────────────────────────────────────────────────────┘   │
│                               │                             │
│  ┌─────────────────────────────────────────────────────┐   │
│  │               Application Layer                     │   │
│  │                                                     │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐ │   │
│  │  │   Ranking   │  │ Tournament  │  │ Competition │ │   │
│  │  │  Services   │  │  Services   │  │  Services   │ │   │
│  │  └─────────────┘  └─────────────┘  └─────────────┘ │   │
│  │                                                     │   │
│  │  ┌─────────────────────────────────────────────────┐ │   │
│  │  │                  Ports                          │ │   │
│  │  │         (Interfaces/Contracts)                  │ │   │
│  │  └─────────────────────────────────────────────────┘ │   │
│  └─────────────────────────────────────────────────────┘   │
│                               │                             │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                Domain Layer                         │   │
│  │                                                     │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐ │   │
│  │  │   Player    │  │ Tournament  │  │   Ranking   │ │   │
│  │  │   Model     │  │    Model    │  │    Model    │ │   │
│  │  └─────────────┘  └─────────────┘  └─────────────┘ │   │
│  │                                                     │   │
│  │  ┌─────────────────────────────────────────────────┐ │   │
│  │  │              Business Logic                     │ │   │
│  │  │         (Domain Services)                       │ │   │
│  │  └─────────────────────────────────────────────────┘ │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                               │
                               │
┌─────────────────────────────────────────────────────────────┐
│                  External Systems                          │
│                                                            │
│  ┌─────────────────┐              ┌─────────────────┐     │
│  │   Badminton     │              │    Database     │     │
│  │  Vlaanderen     │              │   PostgreSQL    │     │
│  │   Portal API    │              │                 │     │
│  └─────────────────┘              └─────────────────┘     │
└─────────────────────────────────────────────────────────────┘
```

---

## Technology Stack

### Backend Technologies

#### Core Framework
- **Java 17**: Latest LTS version providing modern language features
- **Spring Boot 3.2.0**: Enterprise-grade framework for building production-ready applications
- **Maven**: Build automation and dependency management

#### Spring Ecosystem
- **Spring Web**: RESTful web services and MVC architecture
- **Spring Data JPA**: Data access layer with object-relational mapping
- **Spring Security**: Authentication and authorization framework
- **Spring Boot Actuator**: Production-ready monitoring and management

#### Database Technologies
- **H2 Database**: In-memory database for development and testing
- **PostgreSQL**: Production database for data persistence
- **Hibernate**: Object-relational mapping implementation

#### Testing Frameworks
- **JUnit 5**: Unit testing framework
- **ArchUnit**: Architecture testing to enforce design principles
- **Spring Boot Test**: Integration testing support
- **Testcontainers**: Integration testing with real database instances

### Frontend Technologies

#### Core Framework
- **React 18**: Modern JavaScript library for building user interfaces
- **TypeScript**: Statically typed superset of JavaScript
- **Create React App**: Zero-configuration build setup

#### UI and Styling
- **CSS Modules**: Scoped CSS styling
- **Material-UI** (planned): React components implementing Google's Material Design
- **Responsive Design**: Mobile-first approach for cross-device compatibility

#### State Management
- **React Context** (planned): Built-in state management for simple scenarios
- **Redux Toolkit** (planned): Predictable state container for complex state management

#### HTTP Client
- **Axios** (planned): Promise-based HTTP client for API communication

### Infrastructure and DevOps

#### Containerization
- **Docker**: Containerization platform for consistent deployment environments
- **Docker Compose**: Multi-container application orchestration

#### Web Server
- **Nginx**: High-performance web server and reverse proxy

#### Development Tools
- **Git**: Version control system
- **Maven Wrapper**: Ensures consistent Maven version across environments

---

## Project Structure

### Root Directory Structure

```
BetterBadmintonVlaanderen/
├── README.md                          # Project overview and quick start
├── DOCUMENTATION.md                   # Comprehensive project documentation
├── .gitignore                         # Git ignore rules
├── docker-compose.yml                 # Multi-container deployment configuration
│
├── backend/                           # Spring Boot backend application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/be/badmintonvlaanderen/backend/
│   │   │   │   ├── BetterBadmintonApplication.java
│   │   │   │   ├── domain/            # Domain layer
│   │   │   │   │   ├── model/         # Domain entities
│   │   │   │   │   └── service/       # Domain services
│   │   │   │   ├── application/       # Application layer
│   │   │   │   │   ├── port/          # Ports (interfaces)
│   │   │   │   │   └── service/       # Application services
│   │   │   │   └── infrastructure/    # Infrastructure layer
│   │   │   │       ├── adapter/       # External adapters
│   │   │   │       └── config/        # Configuration classes
│   │   │   └── resources/
│   │   │       └── application.yml    # Application configuration
│   │   └── test/
│   │       └── java/be/badmintonvlaanderen/backend/
│   │           └── architecture/      # Architecture tests
│   │               ├── base/          # Reusable base test classes
│   │               └── *.java         # Concrete architecture tests
│   ├── pom.xml                        # Maven configuration
│   └── Dockerfile                     # Backend container configuration
│
└── frontend/                          # React frontend application
    ├── public/                        # Public assets
    ├── src/                          # Source code
    │   ├── components/               # React components
    │   ├── pages/                    # Page components
    │   ├── services/                 # API service layer
    │   ├── hooks/                    # Custom React hooks
    │   ├── types/                    # TypeScript type definitions
    │   └── utils/                    # Utility functions
    ├── package.json                  # Node.js dependencies
    ├── tsconfig.json                 # TypeScript configuration
    ├── Dockerfile                    # Frontend container configuration
    └── nginx.conf                    # Nginx configuration for production
```

### Backend Package Structure Details

#### Domain Layer (`domain/`)
```
domain/
├── model/                            # Domain entities (immutable)
│   ├── Player.java                   # Player domain model
│   ├── Tournament.java               # Tournament domain model
│   ├── Ranking.java                  # Ranking domain model
│   └── Competition.java              # Competition domain model
└── service/                          # Domain business logic
    ├── RankingCalculationService.java
    ├── TournamentSimulationService.java
    └── CompetitionAnalysisService.java
```

#### Application Layer (`application/`)
```
application/
├── port/                             # Interfaces (contracts)
│   ├── PlayerRepository.java
│   ├── TournamentRepository.java
│   ├── BadmintonVlaanderenGateway.java
│   └── RankingCalculationPort.java
└── service/                          # Application services (use cases)
    ├── PlayerService.java
    ├── TournamentService.java
    ├── RankingSimulationService.java
    └── CompetitionScenarioService.java
```

#### Infrastructure Layer (`infrastructure/`)
```
infrastructure/
├── adapter/                          # External system adapters
│   ├── web/                         # REST controllers
│   │   ├── PlayerController.java
│   │   ├── TournamentController.java
│   │   └── RankingController.java
│   ├── persistence/                 # Database adapters
│   │   ├── jpa/                    # JPA entities and repositories
│   │   └── PlayerRepositoryImpl.java
│   ├── external/                    # External service adapters
│   │   └── BadmintonVlaanderenAdapter.java
│   └── dto/                         # Data transfer objects
│       ├── PlayerRequest.java
│       ├── PlayerResponse.java
│       └── RankingSimulationRequest.java
└── config/                          # Configuration classes
    ├── DatabaseConfig.java
    ├── SecurityConfig.java
    └── ExternalServiceConfig.java
```

---

## Backend Architecture

### Hexagonal Architecture Implementation

#### Domain Layer Principles

The domain layer represents the core business logic and is completely independent of external concerns:

**Characteristics:**
- No dependencies on frameworks or external libraries
- Immutable domain models with business logic
- Domain services containing complex business rules
- Pure functions where possible

**Domain Models Example:**
```java
public class Player {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Integer currentRanking;
    private final String clubName;

    // Constructor, getters, business methods
    public RankingProjection simulateRankingAfter(List<Match> matches) {
        // Business logic for ranking simulation
    }
}
```

#### Application Layer Design

The application layer orchestrates the domain and defines the contracts for external systems:

**Application Services:**
- Coordinate between domain objects
- Handle transaction boundaries
- Implement use cases
- Validate input and transform data

**Ports (Interfaces):**
- Define contracts for external dependencies
- Repository interfaces for data access
- Gateway interfaces for external services
- Event publishing interfaces

#### Infrastructure Layer Implementation

The infrastructure layer implements the ports and handles external concerns:

**Web Adapters:**
- REST controllers handling HTTP requests
- Request/response DTOs
- Input validation and transformation
- Error handling and HTTP status codes

**Persistence Adapters:**
- JPA entity mappings
- Repository implementations
- Database-specific optimizations
- Transaction management

**External Service Adapters:**
- HTTP clients for external APIs
- Data transformation and mapping
- Circuit breakers and retry logic
- Caching strategies

### Data Flow Architecture

#### Request Processing Flow
1. **HTTP Request** → REST Controller (Infrastructure)
2. **DTO Validation** → Request validation and transformation
3. **Application Service** → Use case coordination
4. **Domain Service** → Business logic execution
5. **Repository** → Data persistence/retrieval
6. **External Gateway** → External service integration
7. **Response Transformation** → DTO creation and HTTP response

#### Dependency Injection Strategy
- Constructor injection for mandatory dependencies
- No field injection to maintain immutability
- Interface-based dependency inversion
- Configuration through Spring Boot auto-configuration

---

## Frontend Architecture

### Component Architecture

#### Component Hierarchy
```
App
├── Layout
│   ├── Header
│   ├── Navigation
│   └── Footer
├── Pages
│   ├── Dashboard
│   ├── RankingSimulation
│   ├── TournamentPrediction
│   └── CompetitionScenarios
└── Shared Components
    ├── PlayerSelector
    ├── RankingDisplay
    ├── TournamentBracket
    └── ScenarioBuilder
```

#### State Management Strategy

**Local State:**
- Component-specific state using React hooks
- Form state management
- UI interaction state

**Global State:**
- User authentication state
- Application-wide settings
- Cached data from API calls

**Server State:**
- API response caching
- Optimistic updates
- Real-time data synchronization

#### Routing Architecture
```
/                          # Dashboard
/ranking/simulation        # Ranking simulation tool
/tournament/prediction     # Tournament prediction
/competition/scenarios     # Competition scenario analysis
/player/profile/:id        # Player profile
/club/overview/:clubName   # Club overview
```

### TypeScript Integration

#### Type Safety Strategy
- Strict TypeScript configuration
- API response type definitions
- Props interface definitions
- Utility type usage for transformations

#### Type Definitions Structure
```typescript
// Domain types
interface Player {
  id: number;
  firstName: string;
  lastName: string;
  currentRanking: number;
  clubName: string;
}

// API types
interface RankingSimulationRequest {
  playerId: number;
  matches: MatchScenario[];
  timeframe: TimeframePeriod;
}

// Component props
interface PlayerSelectorProps {
  onPlayerSelect: (player: Player) => void;
  selectedPlayer?: Player;
  disabled?: boolean;
}
```

---

## Development Setup

### Prerequisites

#### System Requirements
- **Java 17 or higher**: OpenJDK or Oracle JDK
- **Node.js 16+ and npm**: For frontend development
- **Docker and Docker Compose**: For containerized development
- **Git**: Version control
- **IDE**: IntelliJ IDEA, VS Code, or similar

#### Environment Setup
1. **Java Development Kit**
   ```bash
   # Verify Java installation
   java -version
   javac -version
   ```

2. **Node.js and npm**
   ```bash
   # Verify Node.js installation
   node --version
   npm --version
   ```

3. **Docker**
   ```bash
   # Verify Docker installation
   docker --version
   docker-compose --version
   ```

### Local Development Environment

#### Clone and Setup
```bash
# Clone the repository
git clone <repository-url>
cd BetterBadmintonVlaanderen

# Backend setup
cd backend
./mvnw clean install

# Frontend setup
cd ../frontend
npm install
```

#### Running the Application

**Option 1: Docker Compose (Recommended)**
```bash
# From project root
docker-compose up --build
```

**Option 2: Manual Startup**
```bash
# Terminal 1: Backend
cd backend
./mvnw spring-boot:run

# Terminal 2: Frontend
cd frontend
npm start

# Terminal 3: Database (optional)
docker run --name postgres-dev -e POSTGRES_DB=badminton -e POSTGRES_USER=badminton -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:15-alpine
```

#### Development URLs
- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console (development only)
- **API Documentation**: http://localhost:8080/swagger-ui.html (when implemented)

### IDE Configuration

#### IntelliJ IDEA Setup
1. **Import Project**: Open the root directory as a Maven project
2. **Java SDK**: Configure Project SDK to Java 17
3. **Code Style**: Import and configure code formatting rules
4. **Plugins**: Install Spring Boot, Docker, and ArchUnit plugins

#### VS Code Setup
1. **Extensions**: Install Java Extension Pack, Spring Boot Extension Pack
2. **Settings**: Configure Java home and Maven settings
3. **Debugger**: Configure launch configurations for Spring Boot

---

## Testing Strategy

### Testing Pyramid

#### Unit Tests (Base Layer)
**Domain Layer Testing:**
```java
@Test
void shouldCalculateCorrectRankingProjection() {
    // Given
    Player player = new Player(1L, "John", "Doe", 100, "Club A");
    List<Match> matches = List.of(
        new Match(opponent, MatchResult.WIN),
        new Match(opponent2, MatchResult.LOSS)
    );

    // When
    RankingProjection projection = player.simulateRankingAfter(matches);

    // Then
    assertThat(projection.getProjectedRanking()).isEqualTo(95);
}
```

**Application Layer Testing:**
```java
@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void shouldReturnPlayerWhenExists() {
        // Test application service logic
    }
}
```

#### Integration Tests (Middle Layer)
**Repository Testing:**
```java
@DataJpaTest
class PlayerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerJpaRepository playerRepository;

    @Test
    void shouldFindPlayersByClub() {
        // Test database integration
    }
}
```

**API Testing:**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnPlayerWhenValidId() {
        // Test full API flow
    }
}
```

#### Architecture Tests (Foundation)

**ArchUnit Testing:**
```java
@AnalyzeClasses(packages = "be.badmintonvlaanderen.backend")
public class HexagonalArchitectureTest extends BaseHexagonalArchitectureTest {

    @ArchTest
    static final ArchRule hexagonal_architecture_is_respected =
        new HexagonalArchitectureTest().hexagonal_architecture_is_respected();
}
```

### Test Configuration

#### Test Profiles
```yaml
# application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
```

#### Test Data Management
- **@Sql**: Database initialization scripts
- **TestEntityManager**: JPA test data setup
- **@MockBean**: Spring context mocking
- **Testcontainers**: Real database testing

### Frontend Testing

#### Unit Testing with Jest
```typescript
// PlayerSelector.test.tsx
import { render, screen, fireEvent } from '@testing-library/react';
import { PlayerSelector } from './PlayerSelector';

test('should call onPlayerSelect when player is selected', () => {
  const mockOnSelect = jest.fn();
  render(<PlayerSelector onPlayerSelect={mockOnSelect} />);

  fireEvent.click(screen.getByText('John Doe'));

  expect(mockOnSelect).toHaveBeenCalledWith(expect.objectContaining({
    firstName: 'John',
    lastName: 'Doe'
  }));
});
```

#### Integration Testing with React Testing Library
```typescript
// RankingSimulation.integration.test.tsx
test('should display ranking projection after simulation', async () => {
  render(<RankingSimulation />);

  // Simulate user interactions
  fireEvent.change(screen.getByLabelText('Player'), { target: { value: 'John Doe' } });
  fireEvent.click(screen.getByText('Run Simulation'));

  // Assert results
  await waitFor(() => {
    expect(screen.getByText('Projected Ranking: 95')).toBeInTheDocument();
  });
});
```

---

## Deployment

### Container Strategy

#### Multi-Stage Docker Builds

**Backend Dockerfile:**
```dockerfile
# Build stage
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

**Frontend Dockerfile:**
```dockerfile
# Build stage
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci --only=production
COPY . .
RUN npm run build

# Runtime stage
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
```

#### Docker Compose Configuration

**Development Environment:**
```yaml
version: '3.8'
services:
  backend:
    build: ./backend
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    depends_on:
      - db

  frontend:
    build: ./frontend
    ports:
      - "3000:3000"
    environment:
      - REACT_APP_API_URL=http://localhost:8080
    depends_on:
      - backend

  db:
    image: postgres:15-alpine
    environment:
      - POSTGRES_DB=badminton
      - POSTGRES_USER=badminton
      - POSTGRES_PASSWORD=password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
```

### Production Deployment

#### Environment Configuration
```yaml
# application-prod.yml
spring:
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

logging:
  level:
    be.badmintonvlaanderen: INFO
    org.springframework.web: INFO
```

#### Health Checks and Monitoring
```yaml
# docker-compose.prod.yml
services:
  backend:
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:8080/actuator/health"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 60s
```

#### Load Balancing and Scaling
- **Horizontal Scaling**: Multiple backend instances behind load balancer
- **Database Connection Pooling**: Optimized connection management
- **Caching**: Redis for session and data caching
- **CDN**: Static asset delivery optimization

---

## Integration with Badminton Vlaanderen

### Data Source Integration

#### Official Portal Integration
The application integrates with the official Badminton Vlaanderen portal (www.badmintonvlaanderen.be) to fetch:

**Player Data:**
- Current rankings and historical data
- Player profiles and statistics
- Club affiliations and transfers
- Tournament participation history

**Tournament Data:**
- Tournament schedules and results
- Match outcomes and statistics
- Bracket structures and progressions
- Prize money and ranking points

**Competition Data:**
- League standings and schedules
- Team compositions and lineups
- Season statistics and trends
- Championship records

#### API Integration Strategy

**HTTP Client Configuration:**
```java
@Component
public class BadmintonVlaanderenAdapter implements BadmintonVlaanderenGateway {

    private final RestTemplate restTemplate;
    private final BadmintonVlaanderenConfig config;

    @Override
    public Optional<PlayerData> fetchPlayerData(String playerId) {
        try {
            ResponseEntity<PlayerDataResponse> response = restTemplate.getForEntity(
                config.getPlayerEndpoint() + "/" + playerId,
                PlayerDataResponse.class
            );

            return Optional.of(mapToPlayerData(response.getBody()));
        } catch (Exception e) {
            log.warn("Failed to fetch player data for ID: {}", playerId, e);
            return Optional.empty();
        }
    }
}
```

**Circuit Breaker Pattern:**
```java
@Component
public class ResilientBadmintonVlaanderenAdapter {

    @CircuitBreaker(name = "badminton-vlaanderen-api")
    @Retry(name = "badminton-vlaanderen-api")
    @TimeLimiter(name = "badminton-vlaanderen-api")
    public CompletableFuture<PlayerData> fetchPlayerDataAsync(String playerId) {
        return CompletableFuture.supplyAsync(() -> fetchPlayerData(playerId));
    }
}
```

#### Data Synchronization Strategy

**Incremental Updates:**
- Poll for changes every 15 minutes during active hours
- Full synchronization daily during off-peak hours
- Event-driven updates for critical changes

**Data Consistency:**
- Local caching with TTL for frequently accessed data
- Optimistic locking for concurrent updates
- Conflict resolution strategies for data discrepancies

### Authentication and Authorization

#### Portal Integration Authentication
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .oauth2Login(oauth2 -> oauth2
                .authorizationEndpoint(authorization -> authorization
                    .baseUri("/oauth2/authorization"))
                .redirectionEndpoint(redirection -> redirection
                    .baseUri("/oauth2/callback/*"))
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(badmintonVlaanderenUserService)))
            .build();
    }
}
```

---

## Feature Specifications

### Ranking Simulation

#### Functional Requirements

**Core Functionality:**
- Input current player ranking and upcoming matches
- Calculate projected ranking based on match outcomes
- Display ranking changes over time
- Support multiple scenario comparisons

**User Stories:**
1. **As a player**, I want to see how my ranking will change if I win/lose specific matches
2. **As a coach**, I want to plan which tournaments will most benefit my player's ranking
3. **As a club**, I want to understand ranking implications for strategic planning

#### Technical Implementation

**Domain Model:**
```java
public class RankingSimulation {
    private final Player player;
    private final List<MatchScenario> scenarios;
    private final TimeframePeriod timeframe;

    public RankingProjection calculateProjection() {
        return rankingCalculationService.projectRanking(
            player.getCurrentRanking(),
            scenarios,
            timeframe
        );
    }
}

public class MatchScenario {
    private final Player opponent;
    private final MatchResult expectedResult;
    private final TournamentLevel level;
    private final LocalDate matchDate;
}
```

**API Endpoints:**
```java
@RestController
@RequestMapping("/api/ranking")
public class RankingSimulationController {

    @PostMapping("/simulate")
    public ResponseEntity<RankingProjectionResponse> simulateRanking(
            @Valid @RequestBody RankingSimulationRequest request) {

        RankingProjection projection = rankingSimulationService
            .simulateRanking(request);

        return ResponseEntity.ok(
            RankingProjectionResponse.from(projection)
        );
    }
}
```

**Frontend Interface:**
```typescript
interface RankingSimulationPage {
  playerSelector: PlayerSelector;
  matchScenarioBuilder: MatchScenarioBuilder;
  projectionDisplay: RankingProjectionChart;
  comparisonTable: ScenarioComparisonTable;
}
```

### Tournament Prediction

#### Functional Requirements

**Prediction Capabilities:**
- Analyze tournament brackets and predict outcomes
- Calculate win probabilities based on current rankings
- Identify optimal tournament strategies
- Suggest draw analysis and preparation focus

**Advanced Features:**
- Head-to-head historical analysis
- Playing style compatibility assessment
- Form analysis and recent performance trends
- Weather and venue impact considerations

#### Technical Implementation

**Prediction Algorithm:**
```java
@Service
public class TournamentPredictionService {

    public TournamentPrediction predictTournament(Tournament tournament) {
        TournamentBracket bracket = tournament.getBracket();

        return bracket.getRounds().stream()
            .reduce(new TournamentPrediction(),
                (prediction, round) -> prediction.addRound(
                    predictRound(round, prediction.getCurrentProbabilities())
                ),
                TournamentPrediction::merge);
    }

    private RoundPrediction predictRound(Round round,
                                       Map<Player, Double> probabilities) {
        return round.getMatches().stream()
            .map(match -> predictMatch(match, probabilities))
            .collect(toRoundPrediction());
    }
}
```

### Competition Scenarios

#### Functional Requirements

**Scenario Analysis:**
- "What if" analysis for league competitions
- Team composition optimization
- Strategic match planning
- Championship probability calculations

**Example Scenarios:**
- "What do we need to win the league if Club X wins all remaining matches?"
- "How does player A's injury affect our championship chances?"
- "What's the optimal lineup for maximizing points in remaining matches?"

#### Technical Implementation

**Scenario Engine:**
```java
@Service
public class CompetitionScenarioService {

    public ScenarioAnalysis analyzeScenario(CompetitionScenario scenario) {
        Competition competition = scenario.getCompetition();
        List<ScenarioRule> rules = scenario.getRules();

        return rules.stream()
            .map(rule -> applyRule(competition, rule))
            .reduce(new ScenarioAnalysis(), ScenarioAnalysis::combine);
    }

    public List<StrategicRecommendation> generateRecommendations(
            ScenarioAnalysis analysis) {
        return strategicAnalysisEngine.analyze(analysis);
    }
}
```

---

## API Documentation

### RESTful API Design

#### API Versioning Strategy
- URL path versioning: `/api/v1/`
- Backward compatibility maintenance
- Deprecation notices for breaking changes

#### Standard Response Format
```json
{
  "data": {
    // Response payload
  },
  "meta": {
    "timestamp": "2024-01-01T10:00:00Z",
    "version": "1.0.0",
    "requestId": "req-123456"
  },
  "links": {
    "self": "/api/v1/players/123",
    "related": ["/api/v1/players/123/rankings"]
  }
}
```

#### Error Response Format
```json
{
  "error": {
    "code": "PLAYER_NOT_FOUND",
    "message": "Player with ID 123 not found",
    "details": {
      "field": "playerId",
      "value": "123"
    }
  },
  "meta": {
    "timestamp": "2024-01-01T10:00:00Z",
    "requestId": "req-123456"
  }
}
```

### Core API Endpoints

#### Player Management
```
GET    /api/v1/players                    # List all players
GET    /api/v1/players/{id}               # Get player by ID
GET    /api/v1/players/search             # Search players
GET    /api/v1/players/{id}/rankings      # Get player ranking history
GET    /api/v1/players/{id}/matches       # Get player match history
```

#### Ranking Simulation
```
POST   /api/v1/ranking/simulate           # Simulate ranking changes
GET    /api/v1/ranking/factors            # Get ranking calculation factors
POST   /api/v1/ranking/compare            # Compare multiple scenarios
```

#### Tournament Operations
```
GET    /api/v1/tournaments                # List tournaments
GET    /api/v1/tournaments/{id}           # Get tournament details
POST   /api/v1/tournaments/{id}/predict   # Predict tournament outcomes
GET    /api/v1/tournaments/{id}/bracket   # Get tournament bracket
```

#### Competition Analysis
```
GET    /api/v1/competitions               # List competitions
POST   /api/v1/competitions/{id}/analyze  # Analyze competition scenarios
GET    /api/v1/competitions/{id}/standings # Get current standings
```

### Request/Response Examples

#### Ranking Simulation Request
```json
POST /api/v1/ranking/simulate
{
  "playerId": 123,
  "scenarios": [
    {
      "opponentId": 456,
      "expectedResult": "WIN",
      "tournamentLevel": "INTERNATIONAL",
      "matchDate": "2024-02-15"
    }
  ],
  "timeframe": "NEXT_3_MONTHS"
}
```

#### Ranking Simulation Response
```json
{
  "data": {
    "currentRanking": 100,
    "projectedRanking": 95,
    "rankingChange": -5,
    "confidence": 0.85,
    "timeline": [
      {
        "date": "2024-02-15",
        "ranking": 95,
        "change": -5,
        "reason": "Win vs Player #50"
      }
    ]
  }
}
```

---

## Security Considerations

### Application Security

#### Authentication and Authorization
```java
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtDecoder(jwtDecoder())))
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .build();
    }
}
```

#### Input Validation
```java
public class RankingSimulationRequest {
    @NotNull(message = "Player ID is required")
    @Positive(message = "Player ID must be positive")
    private Long playerId;

    @Valid
    @NotEmpty(message = "At least one scenario is required")
    @Size(max = 50, message = "Maximum 50 scenarios allowed")
    private List<MatchScenario> scenarios;

    @NotNull(message = "Timeframe is required")
    private TimeframePeriod timeframe;
}
```

#### Data Protection
- **Encryption at Rest**: Database column encryption for sensitive data
- **Encryption in Transit**: TLS 1.3 for all communications
- **API Rate Limiting**: Prevent abuse and ensure fair usage
- **CORS Configuration**: Restrict cross-origin requests appropriately

#### Security Headers
```java
@Configuration
public class SecurityHeadersConfig {

    @Bean
    public SecurityFilterChain securityHeaders(HttpSecurity http) {
        return http
            .headers(headers -> headers
                .contentTypeOptions(withDefaults())
                .frameOptions(FrameOptionsConfig::deny)
                .httpStrictTransportSecurity(hstsConfig -> hstsConfig
                    .includeSubdomains(true)
                    .maxAgeInSeconds(31536000)))
            .build();
    }
}
```

### Infrastructure Security

#### Container Security
- **Base Image Security**: Use official, minimal base images
- **Vulnerability Scanning**: Regular image scanning with tools like Trivy
- **Non-Root User**: Run containers with non-privileged users
- **Resource Limits**: CPU and memory constraints to prevent resource exhaustion

#### Network Security
- **Service Mesh**: Istio for service-to-service encryption
- **Network Policies**: Kubernetes network policies for traffic isolation
- **Firewall Rules**: Restrict unnecessary network access
- **VPN Access**: Secure administrative access

#### Secrets Management
```yaml
# Kubernetes secrets example
apiVersion: v1
kind: Secret
metadata:
  name: app-secrets
type: Opaque
data:
  database-password: <base64-encoded-password>
  jwt-secret: <base64-encoded-secret>
```

---

## Performance Considerations

### Backend Performance

#### Database Optimization
```java
@Entity
@Table(name = "players")
@NamedEntityGraph(
    name = "Player.withRankings",
    attributeNodes = @NamedAttributeNode("rankings")
)
public class PlayerJpaEntity {
    // Optimized entity mappings
}

@Repository
public class PlayerRepositoryImpl {

    @EntityGraph("Player.withRankings")
    public List<Player> findPlayersWithRankings(String clubName) {
        // Optimized query with entity graph
    }
}
```

#### Caching Strategy
```java
@Service
public class PlayerService {

    @Cacheable(value = "players", key = "#id")
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @CacheEvict(value = "players", key = "#player.id")
    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }
}
```

#### Async Processing
```java
@Service
public class RankingSimulationService {

    @Async("simulationExecutor")
    public CompletableFuture<RankingProjection> simulateRankingAsync(
            RankingSimulationRequest request) {

        return CompletableFuture.supplyAsync(() -> {
            return performComplexSimulation(request);
        });
    }
}
```

### Frontend Performance

#### Code Splitting
```typescript
// Lazy loading of components
const RankingSimulation = lazy(() => import('./RankingSimulation'));
const TournamentPrediction = lazy(() => import('./TournamentPrediction'));

function App() {
  return (
    <Suspense fallback={<Loading />}>
      <Routes>
        <Route path="/ranking" element={<RankingSimulation />} />
        <Route path="/tournament" element={<TournamentPrediction />} />
      </Routes>
    </Suspense>
  );
}
```

#### Memoization and Optimization
```typescript
const PlayerList = memo(({ players, onPlayerSelect }: PlayerListProps) => {
  const sortedPlayers = useMemo(() =>
    players.sort((a, b) => a.ranking - b.ranking),
    [players]
  );

  const handlePlayerSelect = useCallback((player: Player) => {
    onPlayerSelect(player);
  }, [onPlayerSelect]);

  return (
    <VirtualizedList
      items={sortedPlayers}
      renderItem={({ item }) => (
        <PlayerItem
          player={item}
          onSelect={handlePlayerSelect}
        />
      )}
    />
  );
});
```

#### Data Fetching Optimization
```typescript
// React Query for efficient data fetching
function usePlayerData(playerId: string) {
  return useQuery({
    queryKey: ['player', playerId],
    queryFn: () => fetchPlayer(playerId),
    staleTime: 5 * 60 * 1000, // 5 minutes
    cacheTime: 10 * 60 * 1000, // 10 minutes
  });
}
```

### Performance Monitoring

#### Application Metrics
```java
@Component
public class PerformanceMetrics {

    private final MeterRegistry meterRegistry;
    private final Timer simulationTimer;

    @EventListener
    public void handleSimulationEvent(RankingSimulationEvent event) {
        Timer.Sample sample = Timer.start(meterRegistry);
        sample.stop(simulationTimer);

        meterRegistry.counter("simulation.count",
            "type", event.getType()).increment();
    }
}
```

#### Database Performance
```yaml
# Application configuration for performance
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 20000
      idle-timeout: 300000
      max-lifetime: 1200000

  jpa:
    properties:
      hibernate:
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true
        jdbc:
          batch_versioned_data: true
```

---

## Monitoring and Logging

### Application Monitoring

#### Health Checks
```java
@Component
public class CustomHealthIndicator implements HealthIndicator {

    private final BadmintonVlaanderenGateway gateway;

    @Override
    public Health health() {
        try {
            gateway.healthCheck();
            return Health.up()
                .withDetail("external-service", "available")
                .build();
        } catch (Exception e) {
            return Health.down()
                .withDetail("external-service", "unavailable")
                .withException(e)
                .build();
        }
    }
}
```

#### Metrics Collection
```java
@RestController
public class MetricsController {

    private final MeterRegistry meterRegistry;

    @GetMapping("/api/metrics/simulation")
    public ResponseEntity<Map<String, Double>> getSimulationMetrics() {
        Map<String, Double> metrics = Map.of(
            "simulation.count", meterRegistry.get("simulation.count").counter().count(),
            "simulation.avg.duration", meterRegistry.get("simulation.timer").timer().mean(TimeUnit.MILLISECONDS)
        );

        return ResponseEntity.ok(metrics);
    }
}
```

### Logging Strategy

#### Structured Logging
```java
@Service
public class RankingSimulationService {

    private static final Logger log = LoggerFactory.getLogger(RankingSimulationService.class);

    public RankingProjection simulateRanking(RankingSimulationRequest request) {
        MDC.put("playerId", request.getPlayerId().toString());
        MDC.put("scenarioCount", String.valueOf(request.getScenarios().size()));

        try {
            log.info("Starting ranking simulation for player {}", request.getPlayerId());

            RankingProjection result = performSimulation(request);

            log.info("Ranking simulation completed. Projected ranking: {}",
                result.getProjectedRanking());

            return result;
        } catch (Exception e) {
            log.error("Ranking simulation failed", e);
            throw e;
        } finally {
            MDC.clear();
        }
    }
}
```

#### Log Configuration
```yaml
# logback-spring.xml configuration
logging:
  level:
    be.badmintonvlaanderen: DEBUG
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

  pattern:
    console: "%d{HH:mm:ss.SSS} [%thread] %-5level [%X{playerId}] %logger{36} - %msg%n"
    file: "%d{ISO8601} [%thread] %-5level [%X{playerId},%X{traceId}] %logger{36} - %msg%n"

  file:
    name: logs/application.log
    max-size: 100MB
    max-history: 30
```

### Error Tracking and Alerting

#### Global Exception Handling
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFound(PlayerNotFoundException e) {
        log.warn("Player not found: {}", e.getMessage());

        ErrorResponse error = ErrorResponse.builder()
            .code("PLAYER_NOT_FOUND")
            .message(e.getMessage())
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception e) {
        String errorId = UUID.randomUUID().toString();
        log.error("Unexpected error [{}]", errorId, e);

        ErrorResponse error = ErrorResponse.builder()
            .code("INTERNAL_ERROR")
            .message("An unexpected error occurred")
            .errorId(errorId)
            .timestamp(Instant.now())
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

---

## Contributing Guidelines

### Development Workflow

#### Branch Strategy
```
main
├── develop
│   ├── feature/ranking-simulation
│   ├── feature/tournament-prediction
│   └── feature/competition-scenarios
├── release/v1.0.0
└── hotfix/critical-bug-fix
```

#### Commit Convention
```
feat: add ranking simulation endpoint
fix: resolve tournament bracket display issue
docs: update API documentation
test: add integration tests for player service
refactor: extract ranking calculation logic
style: format code according to standards
perf: optimize database queries for player search
```

#### Pull Request Process
1. **Feature Branch**: Create feature branch from `develop`
2. **Development**: Implement feature with tests
3. **Code Quality**: Ensure all tests pass and code coverage meets requirements
4. **Pull Request**: Create PR with detailed description
5. **Code Review**: Address review feedback
6. **Integration**: Merge to `develop` after approval

### Code Quality Standards

#### Backend Code Standards
```java
// Example of well-documented service method
/**
 * Simulates ranking changes for a player based on match scenarios.
 *
 * @param request the simulation request containing player ID and scenarios
 * @return projected ranking information including timeline and confidence
 * @throws PlayerNotFoundException if the player ID is not found
 * @throws InvalidScenarioException if any scenario is invalid
 */
@Transactional(readOnly = true)
public RankingProjection simulateRanking(RankingSimulationRequest request) {
    validateRequest(request);

    Player player = playerRepository.findById(request.getPlayerId())
        .orElseThrow(() -> new PlayerNotFoundException(request.getPlayerId()));

    return rankingCalculationService.calculateProjection(player, request.getScenarios());
}
```

#### Frontend Code Standards
```typescript
/**
 * Hook for managing ranking simulation state and operations.
 *
 * @param playerId - The ID of the player to simulate rankings for
 * @returns Simulation state and control functions
 */
export const useRankingSimulation = (playerId: string) => {
  const [scenarios, setScenarios] = useState<MatchScenario[]>([]);
  const [projection, setProjection] = useState<RankingProjection | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  const runSimulation = useCallback(async () => {
    setIsLoading(true);
    try {
      const result = await simulateRanking({ playerId, scenarios });
      setProjection(result);
    } catch (error) {
      console.error('Simulation failed:', error);
    } finally {
      setIsLoading(false);
    }
  }, [playerId, scenarios]);

  return {
    scenarios,
    setScenarios,
    projection,
    isLoading,
    runSimulation,
  };
};
```

### Testing Requirements

#### Test Coverage Targets
- **Unit Tests**: Minimum 80% line coverage
- **Integration Tests**: All API endpoints covered
- **Architecture Tests**: All architecture rules enforced
- **Frontend Tests**: All components and hooks tested

#### Test Naming Conventions
```java
// Backend test naming
@Test
void shouldReturnProjectedRankingWhenValidScenariosProvided() {
    // Test implementation
}

@Test
void shouldThrowPlayerNotFoundExceptionWhenPlayerDoesNotExist() {
    // Test implementation
}
```

```typescript
// Frontend test naming
describe('RankingSimulation', () => {
  it('should display loading state while simulation is running', () => {
    // Test implementation
  });

  it('should show projected ranking after successful simulation', () => {
    // Test implementation
  });
});
```

---

## Troubleshooting

### Common Issues and Solutions

#### Backend Issues

**Issue: Application fails to start with database connection error**
```
Solution:
1. Verify database is running: docker ps
2. Check connection string in application.yml
3. Ensure database credentials are correct
4. Verify network connectivity: telnet localhost 5432
```

**Issue: ArchUnit tests failing**
```
Solution:
1. Check package structure matches expected patterns
2. Verify class naming conventions
3. Ensure proper annotations are used
4. Review dependency violations in test output
```

**Issue: External API integration failing**
```
Solution:
1. Check network connectivity to external service
2. Verify API credentials and endpoints
3. Review circuit breaker status
4. Check rate limiting status
```

#### Frontend Issues

**Issue: Build fails with TypeScript errors**
```
Solution:
1. Update type definitions: npm install @types/node
2. Check tsconfig.json configuration
3. Verify all imports have proper types
4. Run type checking: npm run type-check
```

**Issue: API calls failing with CORS errors**
```
Solution:
1. Verify backend CORS configuration
2. Check API URL configuration in .env
3. Ensure proper headers are set
4. Verify network connectivity to backend
```

#### Development Environment Issues

**Issue: Docker containers not communicating**
```
Solution:
1. Check docker-compose network configuration
2. Verify service names in docker-compose.yml
3. Restart docker-compose: docker-compose down && docker-compose up
4. Check container logs: docker-compose logs [service-name]
```

### Debug Configuration

#### Backend Debugging
```yaml
# application-debug.yml
logging:
  level:
    be.badmintonvlaanderen: TRACE
    org.springframework: DEBUG
    org.hibernate.SQL: DEBUG

spring:
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        type:
          descriptor:
            sql:
              BasicBinder: TRACE
```

#### Frontend Debugging
```typescript
// Debug configuration
const DEBUG = process.env.NODE_ENV === 'development';

export const apiClient = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  timeout: 10000,
});

if (DEBUG) {
  apiClient.interceptors.request.use(request => {
    console.log('API Request:', request);
    return request;
  });

  apiClient.interceptors.response.use(
    response => {
      console.log('API Response:', response);
      return response;
    },
    error => {
      console.error('API Error:', error);
      return Promise.reject(error);
    }
  );
}
```

### Performance Troubleshooting

#### Slow Database Queries
```sql
-- Enable query logging
SET log_statement = 'all';
SET log_min_duration_statement = 1000;

-- Analyze slow queries
SELECT query, mean_time, calls
FROM pg_stat_statements
ORDER BY mean_time DESC
LIMIT 10;
```

#### Memory Issues
```bash
# Check Java memory usage
jcmd <pid> VM.info
jcmd <pid> GC.run

# Check container memory
docker stats

# Monitor heap usage
java -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -jar app.jar
```

#### Frontend Performance
```typescript
// Performance monitoring
if (process.env.NODE_ENV === 'development') {
  import('react-dom/profiler').then(({ Profiler }) => {
    // Enable React profiling
  });
}

// Monitor component renders
const useRenderCount = (componentName: string) => {
  const renderCount = useRef(0);

  useEffect(() => {
    renderCount.current += 1;
    console.log(`${componentName} rendered ${renderCount.current} times`);
  });
};
```

---

This comprehensive documentation provides a complete reference for the Better Badminton Vlaanderen project. It covers all aspects from high-level architecture to specific implementation details, making it suitable for developers, stakeholders, and future maintainers of the system.

The documentation emphasizes the clean architecture principles, comprehensive testing strategy, and integration capabilities that make this project a robust extension to the official Badminton Vlaanderen portal.