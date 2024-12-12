package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
    description = "Compares two configuration files and shows a difference.")

public class App {
    /*@Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;*/

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}