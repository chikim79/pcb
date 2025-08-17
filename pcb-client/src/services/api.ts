import { SimulationReport, PCBType } from '../types';

const BASE_URL = 'http://localhost:8080/api/simulation';

class ApiService {
  private async fetchWithErrorHandling<T>(url: string, options?: RequestInit): Promise<T> {
    try {
      const response = await fetch(url, options);
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error('API call failed:', error);
      throw error;
    }
  }

  async runSimulation(pcbType: PCBType, quantity: number = 1000): Promise<string> {
    const response = await fetch(`${BASE_URL}/run/${pcbType}?quantity=${quantity}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    
    if (!response.ok) {
      throw new Error(`Failed to start simulation: ${response.status}`);
    }
    
    return await response.text();
  }

  async runAllSimulations(quantity: number = 1000): Promise<string> {
    const response = await fetch(`${BASE_URL}/run/all?quantity=${quantity}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    
    if (!response.ok) {
      throw new Error(`Failed to start simulations: ${response.status}`);
    }
    
    return await response.text();
  }

  async getSimulationResults(pcbType: PCBType): Promise<SimulationReport> {
    return this.fetchWithErrorHandling<SimulationReport>(`${BASE_URL}/results/${pcbType}`);
  }

  async getAllSimulationResults(): Promise<SimulationReport[]> {
    return this.fetchWithErrorHandling<SimulationReport[]>(`${BASE_URL}/results/all`);
  }

  async getPcbTypes(): Promise<string[]> {
    return this.fetchWithErrorHandling<string[]>(`${BASE_URL}/types`);
  }

  async clearAllResults(): Promise<string> {
    const response = await fetch(`${BASE_URL}/results`, {
      method: 'DELETE',
    });
    
    if (!response.ok) {
      throw new Error(`Failed to clear results: ${response.status}`);
    }
    
    return await response.text();
  }
}

export const apiService = new ApiService();