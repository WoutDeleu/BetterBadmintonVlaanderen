# Better Badminton Vlaanderen

An enhanced application for the badminton portal used in Flanders, designed to extend and improve the functionality of the existing badminton portal system.

## Overview

This application serves as an extension to the official Badminton Vlaanderen portal, providing additional features and capabilities for badminton players, clubs, and administrators in Flanders.

### Key Features

- **Ranking Simulations**: Simulate future rankings based on potential match outcomes
- **Tournament Predictions**: Predict tournament results and scenarios
- **Tournament Draws**: Efficient overview of tournament draws (per discipline and round)
- **Links to toernooi.nl**: Buttons to open a player or tournament on toernooi.nl / the official portal
- **User Accounts & Login** (future): Log in to save favorites and manage your clubs, on web and mobile
- **Favorites Dashboard**: Personal dashboard with graphs, statistics and history of your favorite players and your teams' rankings
- **Club Management**: Manage clubs, starting with interclub team composition (versioned, solver-generated, exportable)
- **Mobile App**: iOS and Android app (React Native) offering the same features on the go
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

### Mobile App
- **Technology**: React Native (Expo) with TypeScript
- **Platforms**: iOS and Android
- **Purpose**: Native mobile access to rankings, simulations, tournament draws and predictions
- **Shared code**: TypeScript types and API client are shared with the web frontend

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
├── mobile/                 # React Native (Expo) mobile app
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

### Running the Mobile App
```bash
cd mobile
npm install
npx expo start
```
Scan the QR code with the Expo Go app, or press `i` / `a` for the iOS simulator / Android emulator.

## Use Cases

### Ranking Simulations
- Input potential match results
- Calculate projected ranking changes
- Visualize ranking progression over time

### Tournament Predictions
- Analyze tournament brackets
- Predict likely outcomes based on current rankings
- Suggest optimal strategies for players/clubs

### Tournament Draws
- Import the draw of a tournament from the portal
- Compact overview per discipline (MS, WS, MD, WD, XD) and round
- Highlight a player's or club's path through the draw
- Filter and search by player, club or ranking level
- "View on toernooi.nl" button on the tournament and on every player

### User Accounts & Login (future)
- Log in on web and mobile; browsing public data (rankings, draws, predictions) stays possible without an account
- Accounts store favorites, dashboard layout and simulation scenarios
- Users can link their own player profile and manage the clubs they are authorized for
- Roles per club (e.g. club admin, team captain, member) control who may change team compositions
- Planned in a later phase; features that need it are marked in this document

### Favorites Dashboard
- Mark players (and optionally clubs and teams) as favorites
- One overview with a card per favorite: current ranking, index, recent form
- Graphs: ranking/index progression over time, win/loss trend, results per discipline
- Statistics: win percentage, head-to-head record, results per tournament level
- History: past matches and tournament results, filterable by period and discipline
- Standings of the interclub teams you play in, with your team highlighted
- Works on web and mobile; favorites sync between both

### Club Management
- **Interclub team composition**: divide the club's players over its interclub teams
- **Version control**: every change is saved as a version; compare, restore or publish versions
- **Generated base teams**: a constraint solver proposes a valid base team per team, based on player indexes and the interclub rules
- **Exports**: per team, all teams, or the full club overview (PDF, Excel, CSV)
- Designed to grow with further club management aspects later on

### Links to toernooi.nl
- A "View on toernooi.nl" button on every player profile and player card, opening that player on toernooi.nl
- A "View tournament" button on every tournament and draw, opening the tournament on toernooi.nl
- Optional link to the matching page on the official Badminton Vlaanderen portal
- Opens in a new tab on web and in the system or in-app browser on mobile

### Competition Scenarios
- Model different league scenarios
- Calculate required results to achieve specific goals
- Strategic planning for club competitions

## Data Integration

The application integrates with the official Badminton Vlaanderen portal (www.badmintonvlaanderen.be) to:
- Fetch current player rankings
- Retrieve tournament results
- Retrieve tournament draws
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