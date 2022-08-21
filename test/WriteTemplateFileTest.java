package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WriteTemplateFileTest {
  private WriteTemplateFile write;

  @BeforeEach
  void setUp() throws IOException {
    String nameStartsWith = "email_";
    ReadInformation csv = new ReadInformation("nonprofit-supporters-test.csv");
    List<String> template = ReadInformation.readTemplateFile("email-template-test.txt");
    Path path = Paths.get("out-directory-test");
    write = new WriteTemplateFile(csv, path, template, nameStartsWith);
  }

  @Test
  void testToString() {
    assertEquals("WriteTemplateFile{}", write.toString());
  }
}