package problem1;


import java.nio.file.Files;
import java.nio.file.*;

/**
 * Class of CommandLineParser to track the arguments are valid
 */
public class CommandLineParser {
    private String emailTemplate = null;
    private String letterTemplate = null;
    private String outputPath = null;
    private String csvFilePath = null;

    private Boolean outputExist = false;
    private Boolean csvOptionExist = false;
    private Boolean emailOptionExist = false;
    private Boolean emailTemplateOptionExist = false;
    private Boolean letterOptionExist = false;
    private Boolean letterTemplateOptionExist = false;
    private static final Integer NEXT_ARGUMENT = 1;

    /**
     * Method to check the path of the folder or the file is valid and
     * get the path if it is valid
     * @param args argument as String[]
     * @param index index as integer
     * @return Boolean true if the folder or the file exist in the path
     * @throws InvalidCombinationArgument if path to the folder or file doesn't exist
     */
    private Boolean checkAndGetPath(String[] args, int index) throws InvalidCombinationArgument {
        Path path = Paths.get(args[index+NEXT_ARGUMENT]);
        if ((index + NEXT_ARGUMENT) < args.length && Files.exists(path)) {
            return true;
        } else throw new InvalidCombinationArgument(args[index+1] + ": Path to the folder or file is invalid");
    }

    /**
     * Method to check which options appeared in the arguments
     * and depend on the condition, check from checkAndGetPath
     * @param args argument as String[]
     * @throws InvalidCombinationArgument if checkAndGetPath is invalid
     */
    private void checkOptions(String[] args) throws InvalidCombinationArgument {
        for(int i = 0; i < args.length; i++) {
            // Check if email option appeared
            if(args[i].equals(CmdLineArgs.EMAIL)) {
                this.emailOptionExist = true;
            }
            // Check if Letter option appeared
            if(args[i].equals(CmdLineArgs.LETTER)) {
                this.letterOptionExist = true;
            }
            // Check if Email template option appeared
            // Get the path of the email template
            if(args[i].equals(CmdLineArgs.EMAIL_TEMPLATE)) {
                this.emailTemplateOptionExist = true;
                this.checkAndGetPath(args, i);
                this.emailTemplate = args[i + NEXT_ARGUMENT];
            }
            // Check if Letter template option appeared
            // Get the path of the Letter template
            if(args[i].equals(CmdLineArgs.LETTER_TEMPLATE)) {
                this.letterTemplateOptionExist = true;
                this.checkAndGetPath(args, i);
                this.letterTemplate = args[i + NEXT_ARGUMENT];

            }
            // Check if Output option appeared
            // Get the path of the output directory
            if(args[i].equals(CmdLineArgs.OUTPUT_DIR)) {
                this.outputExist = true;
                this.checkAndGetPath(args, i);
                this.outputPath = args[i + NEXT_ARGUMENT];
            }
            // Check if CSV file option appeared
            // Get the path of the CSV file
            if(args[i].equals(CmdLineArgs.CSV_FILE)) {
                this.csvOptionExist = true;
                this.checkAndGetPath(args, i);
                this.csvFilePath = args[i + NEXT_ARGUMENT];
            }
        }
    }

    /**
     * Method to check argument is valid or not
     * @param args argument as String[]
     * @throws InvalidCombinationArgument if output option, csv option, doesn't exist
     * or if both, --email option and --email--template, options are not provided
     * or if both, --letter option and --letter--template, options are not provided
     */
    public CommandLineParser(String[] args) throws InvalidCombinationArgument {
        this.checkOptions(args);
        if(!this.outputExist) {
            throw new InvalidCombinationArgument("Output option are required");
        }
        if(!this.csvOptionExist) {
            throw new InvalidCombinationArgument("CSV file option are required");
        }
        if ((this.emailOptionExist && !this.emailTemplateOptionExist) ||
            (!this.emailOptionExist && this.emailTemplateOptionExist)) {
            throw new InvalidCombinationArgument("After the --email option, then --email-template option must be provided. Required both together");
        }
        if ((this.letterOptionExist && !this.letterTemplateOptionExist) ||
            (!this.letterOptionExist && this.letterTemplateOptionExist)) {
            throw new InvalidCombinationArgument("After the --letter, then --letter-template option must be provided. Required both together");
        }
    }

    /**
     * Getter for emailTemplate
     * @return emailTemplate as String
     */
    public String getEmailTemplate() {
        return emailTemplate;
    }

    /**
     * Getter for letterTemplate
     * @return letterTemplate as String
     */
    public String getLetterTemplate() {
        return letterTemplate;
    }

    /**
     * Getter for outputPath
     * @return outputPath as String
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * Getter for csvFilePath
     * @return csvFilePath as String
     */
    public String getCsvFilePath() {
        return csvFilePath;
    }

    @Override
    public String toString() {
        return "CommandLineParser{" +
            "emailPath='" + emailTemplate + '\'' +
            ", letterPath='" + letterTemplate + '\'' +
            ", outputPath='" + outputPath + '\'' +
            ", csvFilePath='" + csvFilePath + '\'' +
            '}';
    }
}
