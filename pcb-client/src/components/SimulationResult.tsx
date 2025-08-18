import React from 'react';
import { SimulationReport } from '../types';

interface SimulationResultProps {
  report: SimulationReport;
}

const SimulationResult: React.FC<SimulationResultProps> = ({ report }) => {
  return (
    <div className="simulation-result">
      <h3>{report.pcbType} Results</h3>
      
      <div className="summary">
        <p><strong>PCBs Run:</strong> {report.pcbsRun}</p>
        <p><strong>Total Produced:</strong> {report.totalPcbsProduced}</p>
        <p><strong>Total Failed:</strong> {report.totalFailedPcbs}</p>
        <p><strong>Success Rate:</strong> {((report.totalPcbsProduced / report.pcbsRun) * 100).toFixed(2)}%</p>
      </div>

      <div className="failures">
        <h4>Station Failures</h4>
        <table>
          <thead>
            <tr>
              <th>Station</th>
              <th>Failures</th>
            </tr>
          </thead>
          <tbody>
            {Object.entries(report.stationFailures).map(([station, failures]) => (
              <tr key={station}>
                <td>{station}</td>
                <td>{failures}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="defects">
        <h4>Defect Failures</h4>
        <table>
          <thead>
            <tr>
              <th>Defect Type</th>
              <th>Failures</th>
            </tr>
          </thead>
          <tbody>
            {Object.entries(report.defectFailures).map(([defect, failures]) => (
              <tr key={defect}>
                <td>{defect}</td>
                <td>{failures}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="formatted-report">
        <h4>Detailed Report</h4>
        <pre>{report.formattedReport}</pre>
      </div>
    </div>
  );
};

export default SimulationResult;