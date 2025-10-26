package MST.io;

import MST.Model.Graph;
import MST.Model.Input.InputWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Graph> readGraphs(String inputFilePath) throws IOException {
        InputWrapper wrapper = mapper.readValue(new File(inputFilePath), InputWrapper.class);
        return wrapper.getGraphs();
    }
}