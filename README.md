# Better Badminton Vlaanderen

An enhanced application for the badminton portal used in Flanders, designed to extend and improve the functionality of the existing badminton portal system.

## Overview

This application serves as an extension to the official Badminton Vlaanderen portal, providing additional features and capabilities for badminton players, clubs, and administrators in Flanders.

### Key Features

- **Ranking Simulations**: Simulate future rankings based on potential match outcomes
- **Tournament Predictions**: Predict tournament results and scenarios
- **Competition Scenarios**: Analyze "what-if" scenarios for leagues and competitions
  - "What do we need to win the league if this club wins everything and we draw?"
  - Strategic planning for competitive outcomes

## Architecture

### Backend
- **Technology**: Java Spring Boot with hexagonal architecture
- **Data Source**: Integration with www.badmintonvlaanderen.be
- **Architecture Pattern**: Clean architecture with clear separation of concerns
  - **Domain Layer**: Core business logic and entities
  - **Application Layer**: Use cases and application services
  - **Infrastructure Layer**: External integrations and data persistence

### Frontend
- **Technology**: React with TypeScript
- **Purpose**: User-friendly interface for interacting with enhanced portal features

## Project Structure

```
.
├── backend/                 # Spring Boot backend application
│   ├── src/main/java/
│   │   └── be/badmintonvlaanderen/backend/
│   │       ├── application/ # Application layer (services, ports)
│   │       ├── domain/      # Domain layer (entities, business logic)
│   │       └── infrastructure/ # Infrastructure layer (adapters, config)
│   └── pom.xml
├── frontend/               # React TypeScript frontend
└── README.md
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Node.js 16+ and npm
- Git

### Running the Backend
```bash
cd backend
mvn spring-boot:run
```

### Running the Frontend
```bash
cd frontend
npm start
```

## Use Cases

### Ranking Simulations
- Input potential match results
- Calculate projected ranking changes
- Visualize ranking progression over time

### Tournament Predictions
- Analyze tournament brackets
- Predict likely outcomes based on current rankings
- Suggest optimal strategies for players/clubs

### Competition Scenarios
- Model different league scenarios
- Calculate required results to achieve specific goals
- Strategic planning for club competitions

## Data Integration

The application integrates with the official Badminton Vlaanderen portal (www.badmintonvlaanderen.be) to:
- Fetch current player rankings
- Retrieve tournament results
- Access club and player information
- Synchronize competition data

## Contributing

This project follows clean architecture principles and hexagonal architecture patterns. When contributing:

1. Maintain clear separation between layers
2. Follow domain-driven design principles
3. Ensure comprehensive test coverage
4. Document any new integrations or features

## License

This project is intended to enhance the official Badminton Vlaanderen portal and should be used in accordance with their terms and conditions.