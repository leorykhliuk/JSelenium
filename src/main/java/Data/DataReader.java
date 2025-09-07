package Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;

public class DataReader {

//    public static void main(String[] args) throws IOException {
//        System.out.println(new DataReader().getJsonData());
//    }
    public SequencedCollection<Map<String, String>> getJsonData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File("src/main/java/Data/testData1.json"),
                new TypeReference<List<Map<String, String>>>() {}
        );
    }
}
