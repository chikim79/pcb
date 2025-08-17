export interface SimulationReport {
  pcbType: string;
  pcbsRun: number;
  stationFailures: Record<string, number>;
  defectFailures: Record<string, number>;
  totalFailedPcbs: number;
  totalPcbsProduced: number;
  formattedReport: string;
}

export type PCBType = 'test' | 'sensor' | 'gateway';