# PCB Assembly Line Simulation Client

A React TypeScript client application for testing the PCB Assembly Line Simulation REST API.

## Features

- **Run Simulations**: Start simulations for individual PCB types or all types at once
- **View Results**: Retrieve and display simulation results with detailed statistics
- **Real-time UI**: Loading states, success/error messages, and responsive design
- **Result Management**: Clear stored results from the server

## Prerequisites

1. **Java Server**: The PCB simulation server must be running on `http://localhost:8080`
2. **Node.js**: Version 14 or higher

## Getting Started

1. **Start the Java server** (from the parent directory):
   ```bash
   ./gradlew bootRun
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Start the React client**:
   ```bash
   npm start
   ```

4. **Open browser**: Navigate to `http://localhost:3000`

## Usage

### Running Simulations
1. Select PCB type (Test Board, Sensor Board, or Gateway Board)
2. Set quantity (default: 1000)
3. Click "Run Single Simulation" or "Run All Simulations"
4. Wait for confirmation message

### Viewing Results
1. After running simulations, click "Get Single Results" or "Get All Results"
2. Results will display showing:
   - PCB type and quantity processed
   - Success rate and failure statistics
   - Station-specific failure counts
   - Defect-specific failure counts
   - Detailed formatted report

### Managing Results
- Click "Clear All Results" to remove stored data from the server

## API Endpoints Used

- `POST /api/simulation/run/{pcbType}` - Run simulation for specific PCB type
- `POST /api/simulation/run/all` - Run simulations for all PCB types
- `GET /api/simulation/results/{pcbType}` - Get results for specific PCB type
- `GET /api/simulation/results/all` - Get all simulation results
- `DELETE /api/simulation/results` - Clear all stored results

## Technologies

- **React 18** with TypeScript
- **Modern CSS** with responsive design
- **Fetch API** for HTTP requests
- **Error handling** and loading states

## Available Scripts (Create React App)

### `npm start`
Runs the app in development mode at [http://localhost:3000](http://localhost:3000)

### `npm test`
Launches the test runner in interactive watch mode

### `npm run build`
Builds the app for production to the `build` folder
