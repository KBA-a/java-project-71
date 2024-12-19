package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
    description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylist",
            description = "output format [default: stylish]")
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private static String readFilePathFirst;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private static String readFilePathSecond;

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        //System.out.println(readFilePathFirst);
        var text = Differ.generate(readFilePathFirst, readFilePathSecond);
        System.out.println(toString(text));
        return 0;
    }

    public String toString (List<Map<String, Object>> compareResult) {
        var stringdiffFsonFiles = new StringBuilder("{\n");
        for (var map: compareResult) {
            switch ((String) map.get("Status")) {
                case "Add" -> stringdiffFsonFiles.append(getAddString(map));
                case "Delete" -> stringdiffFsonFiles.append(getDeleteString(map));
                case "Update" -> stringdiffFsonFiles.append(getUpdateString(map));
                case "Same" -> stringdiffFsonFiles.append(getSameString(map));
                default -> throw new RuntimeException();
            }
        }
        stringdiffFsonFiles.append("}");
        return stringdiffFsonFiles.toString();
    }
    public static StringBuilder getAddString (Map<String, Object> diffFile) {
        var stringBuild = new StringBuilder(" +");
        stringBuild.append(diffFile.get("Field")).append(": ")
                .append(diffFile.get("New_value"))
                .append("\n");
      return stringBuild;
    }
    public static StringBuilder getDeleteString(Map<String, Object> diffFile) {
        var stringBuild = new StringBuilder(" -");
        stringBuild.append(diffFile.get("Field")).append(": ")
                .append(diffFile.get("Old_value"))
                .append("\n");
        return stringBuild;
    }
    public static StringBuilder getUpdateString(Map<String, Object> diffFile) {
        var stringBuild = new StringBuilder();
        stringBuild.append(getDeleteString(diffFile));
        stringBuild.append(getAddString(diffFile));
        return stringBuild;
    }
    public static StringBuilder getSameString(Map<String, Object> diffFile) {
        var stringBuild = new StringBuilder("  ");
        stringBuild.append(diffFile.get("Field")).append(": ")
                .append(diffFile.get("Value"))
                .append("\n");
        return stringBuild;
    }
}