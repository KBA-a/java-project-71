package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.*;
import java.util.Map;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
    description = "Compares two configuration files and shows a difference.")

public class App {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;

    public static void main(String[] args) throws Exception {

        String readFilePathFirst = "src/main/java/hexlet/code/fixtures/file1.json";
        String readFilePathSecond = "src/main/java/hexlet/code/fixtures/file2.json";
        ObjectMapper mapper = new ObjectMapper();

        Path filePathFirst = Paths.get(readFilePathFirst).toAbsolutePath().normalize();
        Path filePathSecond = Paths.get(readFilePathSecond).toAbsolutePath().normalize();

        if (!Files.exists(filePathFirst)) {
            throw new Exception("File:" + filePathFirst + " - does not exist!");
        }
        if (!Files.exists(filePathSecond)) {
            throw new Exception("File:" + filePathSecond + " - does not exist!");
        }

        String textInFirstFile = Files.readString(filePathFirst);
        String textInFirstSecond = Files.readString(filePathSecond);

        Map<String, Object> parsFirstFile = mapper.readValue(textInFirstFile, Map.class);
        Map<String, Object> parsSecondFile = mapper.readValue(textInFirstSecond, Map.class);


        System.out.println("Hello world!");
        System.out.println(parsFirstFile);
        System.out.println(parsSecondFile);
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}