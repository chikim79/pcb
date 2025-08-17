import React, { useState } from 'react';
import './App.css';
import SimulationControls from './components/SimulationControls';
import SimulationResult from './components/SimulationResult';
import { apiService } from './services/api';
import { SimulationReport, PCBType } from './types';

function App() {
  const [results, setResults] = useState<SimulationReport[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState<string>('');
  const [error, setError] = useState<string>('');

  const showMessage = (msg: string) => {
    setMessage(msg);
    setError('');
    setTimeout(() => setMessage(''), 3000);
  };

  const showError = (err: string) => {
    setError(err);
    setMessage('');
    setTimeout(() => setError(''), 5000);
  };

  const handleRunSimulation = async (pcbType: PCBType, quantity: number) => {
    setIsLoading(true);
    try {
      const response = await apiService.runSimulation(pcbType, quantity);
      showMessage(response);
    } catch (error) {
      showError(`Failed to start simulation: ${error}`);
    } finally {
      setIsLoading(false);
    }
  };

  const handleRunAllSimulations = async (quantity: number) => {
    setIsLoading(true);
    try {
      const response = await apiService.runAllSimulations(quantity);
      showMessage(response);
    } catch (error) {
      showError(`Failed to start simulations: ${error}`);
    } finally {
      setIsLoading(false);
    }
  };

  const handleGetResults = async (pcbType?: PCBType) => {
    setIsLoading(true);
    try {
      if (pcbType) {
        const result = await apiService.getSimulationResults(pcbType);
        setResults([result]);
        showMessage(`Results retrieved for ${pcbType}`);
      } else {
        const allResults = await apiService.getAllSimulationResults();
        setResults(allResults);
        showMessage(`Retrieved ${allResults.length} simulation results`);
      }
    } catch (error) {
      showError(`Failed to get results: ${error}`);
    } finally {
      setIsLoading(false);
    }
  };

  const handleClearResults = async () => {
    setIsLoading(true);
    try {
      const response = await apiService.clearAllResults();
      setResults([]);
      showMessage(response);
    } catch (error) {
      showError(`Failed to clear results: ${error}`);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>PCB Assembly Line Simulation Client</h1>
      </header>

      <main>
        <SimulationControls
          onRunSimulation={handleRunSimulation}
          onRunAllSimulations={handleRunAllSimulations}
          onGetResults={handleGetResults}
          onClearResults={handleClearResults}
          isLoading={isLoading}
        />

        {message && <div className="message success">{message}</div>}
        {error && <div className="message error">{error}</div>}
        {isLoading && <div className="loading">Processing...</div>}

        <div className="results-container">
          {results.length > 0 && (
            <div className="results">
              <h2>Simulation Results</h2>
              {results.map((result, index) => (
                <SimulationResult key={index} report={result} />
              ))}
            </div>
          )}
        </div>
      </main>
    </div>
  );
}

export default App;
