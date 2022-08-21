package problem1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Main class that checks the command arguments, read and write files with given information
 */
public class Main {

    /**
     * Main to check the command argument and read the given csv file
     * then write files to the given output path
     * @param args arguments represented as String[]
     */
    public static void main(String[] args) {
        try {
            // Check the command argument
            CommandLineParser command = new CommandLineParser(args);
            Path path = Paths.get(command.getOutputPath());
            // Read the given csv file
            ReadInformation readFile = new ReadInformation(command.getCsvFilePath());

            String nameStartsWith = null;
            List<String> tempLine = null;

            // If --email-template is not null create email files in given output path
            if (command.getEmailTemplate() != null) {
                nameStartsWith = "email_";
                tempLine = readFile.readTemplateFile(command.getEmailTemplate());
                new WriteTemplateFile(readFile, path, tempLine, nameStartsWith);
            }

            // If --letter-template is not null create email files in given output path
            if (command.getLetterTemplate() != null) {
                nameStartsWith = "letter_";
                tempLine = readFile.readTemplateFile(command.getLetterTemplate());
                new WriteTemplateFile(readFile, path, tempLine, nameStartsWith);
            }
        } catch (InvalidCombinationArgument e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}