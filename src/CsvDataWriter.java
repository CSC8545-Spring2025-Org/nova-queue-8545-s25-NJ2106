import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public class CsvDataWriter {
    
    private enum PredictionHeaders {
        rideId, peopleInLineCurrently, predictedWaitTimeInMinutes, statusLabel
    }

    public static void main(String[] args) {
        CsvDataWriter writer = new CsvDataWriter();
       //writer.writePredictionData();
    }
    
    protected void writePredictionData(List<WaitTimeOutput> output) {
        CSVPrinter csvPrinter = null;
        try {
            // note that there's no need to write a BOM
            CSVFormat csvFormat = CSVFormat.Builder.create(CSVFormat.EXCEL)
                    .setHeader(PredictionHeaders.class)
                    .build();
            
            csvPrinter = new CSVPrinter(
                    new FileWriter("src/resources/wait-time-output-actual.csv", 
                    StandardCharsets.UTF_8),
                    csvFormat);
            
            
            int i =0 ;
            while(i < output.size()) {
                csvPrinter.printRecord(output.get(i).getrideId(),output.get(i).getPeopleInLineCurrently() , output.get(i).getpredictedWaitTime(), output.get(i).getStatusString());
                i++;
            }
               // csvPrinter.printRecord(rideId, peopleInLineCurrently, predictedWaitTimeInMinutes, statusLabel);

            csvPrinter.flush();
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally {
            try {
                if (csvPrinter != null) {
                    csvPrinter.close();
                }
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }        
    }

}
