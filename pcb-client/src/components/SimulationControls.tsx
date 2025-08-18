import React, { useState } from 'react';
import { PCBType } from '../types';

interface SimulationControlsProps {
  onRunSimulation: (pcbType: PCBType, quantity: number) => void;
  onRunAllSimulations: (quantity: number) => void;
  onGetResults: (pcbType?: PCBType) => void;
  onClearResults: () => void;
  isLoading: boolean;
}

const SimulationControls: React.FC<SimulationControlsProps> = ({
  onRunSimulation,
  onRunAllSimulations,
  onGetResults,
  onClearResults,
  isLoading,
}) => {
  const [selectedPcbType, setSelectedPcbType] = useState<PCBType>('test');
  const [quantity, setQuantity] = useState<number>(1000);

  const pcbTypes: { value: PCBType; label: string }[] = [
    { value: 'test', label: 'Test Board' },
    { value: 'sensor', label: 'Sensor Board' },
    { value: 'gateway', label: 'Gateway Board' },
  ];

  return (
    <div className="simulation-controls">
      <h2>PCB Simulation Controls</h2>
      
      <div className="control-group">
        <label>
          PCB Type:
          <select
            value={selectedPcbType}
            onChange={(e) => setSelectedPcbType(e.target.value as PCBType)}
            disabled={isLoading}
          >
            {pcbTypes.map((type) => (
              <option key={type.value} value={type.value}>
                {type.label}
              </option>
            ))}
          </select>
        </label>
      </div>

      <div className="control-group">
        <label>
          Quantity:
          <input
            type="number"
            value={quantity}
            onChange={(e) => setQuantity(parseInt(e.target.value))}
            min="1"
            max="10000"
            disabled={isLoading}
          />
        </label>
      </div>

      <div className="button-group">
        <button
          onClick={() => onRunSimulation(selectedPcbType, quantity)}
          disabled={isLoading}
          className="btn-primary"
        >
          Run Single Simulation
        </button>
        
        <button
          onClick={() => onRunAllSimulations(quantity)}
          disabled={isLoading}
          className="btn-primary"
        >
          Run All Simulations
        </button>
      </div>

      <div className="button-group">
        <button
          onClick={() => onGetResults(selectedPcbType)}
          disabled={isLoading}
          className="btn-secondary"
        >
          Get Single Results
        </button>
        
        <button
          onClick={() => onGetResults()}
          disabled={isLoading}
          className="btn-secondary"
        >
          Get All Results
        </button>
      </div>

      <div className="button-group">
        <button
          onClick={onClearResults}
          disabled={isLoading}
          className="btn-danger"
        >
          Clear All Results
        </button>
      </div>
    </div>
  );
};

export default SimulationControls;