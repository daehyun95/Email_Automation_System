package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {
  private CommandLineParser parser;
  private CommandLineParser parser2;


  @BeforeEach
  void setUp() throws InvalidCombinationArgument {
    String[] args1 = new String[] { "--email", "--email-template",
        "email-template-test.txt","--csv-file", "nonprofit-supporters-test.csv"};
    String[] args2 = new String[] { "--email", "--output-dir", "out-directory-test","--csv-file", "nonprofit-supporters-test.csv"};
    String[] args3 = new String[] { "--letter", "--output-dir", "out-directory-test","--csv-file", "nonprofit-supporters-test.csv"};
    String[] args4 = new String[] { "--email", "--email-template", "--output-dir", "out-directory-test"};
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args1));
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args2));
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args3));
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args4));
    String[] args5 = new String[] {CmdLineArgs.EMAIL, CmdLineArgs.EMAIL_TEMPLATE,
        "email-template-test.txt",CmdLineArgs.CSV_FILE, "nonprofit-supporters-test.csv", CmdLineArgs.OUTPUT_DIR, "out-directory-test"};
    parser = new CommandLineParser(args5);
    String[] args7 = new String[] {CmdLineArgs.EMAIL, CmdLineArgs.EMAIL_TEMPLATE,
        "email-template-test.txt",CmdLineArgs.CSV_FILE, "nonprofit-supporters-test.csv"};
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args7));
    String[] args8 = new String[] {CmdLineArgs.LETTER_TEMPLATE,
        "letter-template-test.txt",CmdLineArgs.CSV_FILE, "nonprofit-supporters-test.csv", CmdLineArgs.OUTPUT_DIR, "out-directory-test"};
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args8));
    String[] args9 = new String[] {CmdLineArgs.LETTER_TEMPLATE,
        "letter-template-test.txt",CmdLineArgs.CSV_FILE, "nonprofit-supporters-test.csv", CmdLineArgs.OUTPUT_DIR, "out-directory-test"};
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args9));
    String[] args10 = new String[] { "--email-template", "--output-dir", "out-directory-test","--csv-file", "nonprofit-supporters-test.csv"};
    String[] args11 = new String[] { "--letter-template", "--output-dir", "out-directory-test","--csv-file", "nonprofit-supporters-test.csv"};
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args10));
    assertThrows(InvalidCombinationArgument.class, () -> new CommandLineParser(args11));
  }

  @Test
  void getEmailTemplate() {
    assertEquals("email-template-test.txt", parser.getEmailTemplate());
  }

  @Test
  void getLetterTemplate() {
    assertEquals(null, parser.getLetterTemplate());
  }

  @Test
  void getOutputPath() {
    assertEquals("out-directory-test", parser.getOutputPath());
  }

  @Test
  void getCsvFilePath() {
    assertEquals("nonprofit-supporters-test.csv", parser.getCsvFilePath());
  }

  @Test
  void testToString() {
    String str = "CommandLineParser{" +
        "emailPath='" + parser.getEmailTemplate() + '\'' +
        ", letterPath='" + parser.getLetterTemplate() + '\'' +
        ", outputPath='" + parser.getOutputPath() + '\'' +
        ", csvFilePath='" + parser.getCsvFilePath() + '\'' +
        '}';
    assertEquals(str, parser.toString());
  }
}