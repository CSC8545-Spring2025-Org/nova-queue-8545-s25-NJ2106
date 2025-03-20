
import java.util.*;

public class BatchMode extends Mode{

	private CsvDataReader csvRead;
	private CsvDataWriter csvWrite;
	private HashMap<String, Integer> lineData;
	private List<WaitTimeOutput> outputs;
	
	public BatchMode() {
		csvRead = new CsvDataReader();
		csvWrite = new CsvDataWriter();
		
		lineData = new HashMap<String, Integer>();
		outputs = new ArrayList<>();
		
		loadData();
	}
	@Override
	void loadData() {
		// TODO Auto-generated method stub
		
		map = csvRead.readRideData();
		lineData = csvRead.readLineData();
	}
	
	public void ProcessOutPut() {
		for(Map.Entry<String,Integer> entry : lineData.entrySet()) {
			ProcessOutPut(entry.getKey(), entry.getValue());
		}
		csvWrite.writePredictionData(outputs);
	}
	

	@Override
	void ProcessOutPut(String id, int peopleInLine) {
		// TODO Auto-generated method stub
		double wt = TabulatePredictedWaitTime(id, peopleInLine);
		int tolerance = GetTolerance(id);
		String status = GetStatusString(tolerance, wt);
		outputs.add(new WaitTimeOutput(id, peopleInLine, wt, status));
	}
	
	public List<WaitTimeOutput> GetResult(){
		return this.outputs;
	}

	
}
