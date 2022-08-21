package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadInformationTest {
  private ReadInformation test;

  private String file;

  @BeforeEach
  void setUp() throws IOException {
    assertThrows(IOException.class, () -> new ReadInformation("supporters.csv"));
    file = "nonprofit-supporters-test.csv";
    test = new ReadInformation(file);

  }

  @Test
  void readTemplateFile() throws IOException {
    assertThrows(IOException.class, () -> ReadInformation.readTemplateFile("email-temp.txt"));
    List<String> listOfStrings = ReadInformation.readTemplateFile("email-template-test.txt");
  }

  @Test
  void getKeyWord2Index() {
    assertEquals(12, test.getKeyWord2Index().size());
  }

  @Test
  void getListOfString() {
    assertEquals(19, test.getListOfString().size());
  }

  @Test
  void testToString() {
    String str = "ReadSupportersInformation{" +
        "keyWord2Index=" + test.getKeyWord2Index() +
        ", listOfString=" + test.getListOfString() +
        '}';
    assertEquals(str, test.toString());
  }

  @Test
  void testEquals() throws IOException {
    assertEquals(test, test);
    assertNotEquals(test, null);
    ReadInformation test2 = new ReadInformation(file);
    assertEquals(test.getKeyWord2Index(), test2.getKeyWord2Index());
    test.getListOfString().equals(test2.getListOfString());
  }

  @Test
  void testHashCode() {
    int hash = Objects.hash(test.getKeyWord2Index(), test.getListOfString());
    assertEquals(hash, test.hashCode());
  }
}