package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Differ {
    public static List<Map<String, Object>> generate(String pathToString1, String pathToString2) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String textInFirstFile = readFile(pathToString1);
        String textInFirstSecond = readFile(pathToString2);

        Map<String, Object> parsFirstFile = mapper.readValue(textInFirstFile, new TypeReference<>() { });
        Map<String, Object> parsSecondFile = mapper.readValue(textInFirstSecond, new TypeReference<>() { });

        var result = Comparison.compareJsonFile(parsFirstFile, parsSecondFile);

        //System.out.println(result);
        return result;
    }
    private static String readFile(String path) throws Exception {
        Path absolutePath = Paths.get(path).toAbsolutePath().normalize();
        if (!Files.exists(absolutePath)) {
            throw new Exception("File:" + absolutePath + " - does not exist!");
        }
        return Files.readString(absolutePath);
    }
}
