import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;

/*
 * This is a rough demo. You must adjust it to fit the project needs.
 * This class doesn't have our standards and style guides applied yet.
 * We'll do that and some refactoring together. 
 */
public class CsvDataReader {

    // doesn't follow enum naming convention for convenience when using commons-csv
    private enum RideDataHeaders {
        rideId, rideName, rideType, dispatchTimeInSeconds, ridersPerDispatch
    };

    private enum LineDataHeaders {
        rideId, peopleInLineCurrently
    };

    public static void main(String[] args) {
        CsvDataReader loader = new CsvDataReader();
        loader.readRideData();
    }

    public HashMap<String,Ride> readRideData() {
        BOMInputStream bomInputStream = null;
        Reader reader = null;
        CSVParser csvParser = null;
        HashMap<String,Ride> result = new HashMap<String, Ride>();
        try {

            bomInputStream = BOMInputStream.builder().setPath(Paths.get("src/resources/ride-data.csv"))
                    .setByteOrderMarks(ByteOrderMark.UTF_8).setInclude(false) // ignore BOM from Excel-generated CSV
                    .get();

            reader = new InputStreamReader(bomInputStream);

            CSVFormat csvFormat = CSVFormat.Builder.create(CSVFormat.EXCEL).setSkipHeaderRecord(true)
                    .setHeader(RideDataHeaders.class).build();
            csvParser = new CSVParser(reader, csvFormat);

            for (CSVRecord record : csvParser) {
                Ride ride = new Ride();
                ride.setId(record.get(RideDataHeaders.rideId));
                String id = ride.getId();
                ride.setName(record.get(RideDataHeaders.rideName));
                ride.setRideType(RideType.valueOf(record.get(RideDataHeaders.rideType)));
                // tip: you'll need to format the time to appear in rounded minutes in your project
                ride.setDispatchTime(Long.valueOf(record.get(RideDataHeaders.dispatchTimeInSeconds)));
                ride.setRidersPerDispatch(Long.valueOf(record.get(RideDataHeaders.ridersPerDispatch)));
                System.out.println(ride);
                if(!result.containsKey(id)) {
                	result.put(id, ride);
                }

            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (csvParser != null) {
                    csvParser.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return result;
    }

    public HashMap<String, Integer> readLineData() {

        BOMInputStream bomInputStream = null;
        Reader reader = null;
        CSVParser csvParser = null;
        HashMap<String, Integer> map = new HashMap<>();
        try {

            bomInputStream = BOMInputStream.builder().setPath(Paths.get("src/resources/line-data.csv"))
                    .setByteOrderMarks(ByteOrderMark.UTF_8).setInclude(false) // ignore BOM from Excel-generated CSV
                    .get();

            reader = new InputStreamReader(bomInputStream);

            CSVFormat csvFormat = CSVFormat.Builder.create(CSVFormat.EXCEL).setSkipHeaderRecord(true)
                    .setHeader(LineDataHeaders.class).build();
            csvParser = new CSVParser(reader, csvFormat);

            for (CSVRecord record : csvParser) {
                System.out.println(record.get(LineDataHeaders.rideId));
                System.out.println(record.get(LineDataHeaders.peopleInLineCurrently));
                // Line line = new Line(record.get(LineDataHeaders.rideId),
                // Integer.parseInt(record.get(LineDataHeaders.peopleInLineCurrently)));
                if (!map.containsKey(record.get(LineDataHeaders.rideId))) {
                    map.put(record.get(LineDataHeaders.rideId),
                            Integer.parseInt(record.get(LineDataHeaders.peopleInLineCurrently)));
                }

            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (csvParser != null) {
                    csvParser.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return map;
    }
}
