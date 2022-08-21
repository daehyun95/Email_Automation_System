package problem1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class dealing with reading supporters' information and reading the templates
 */
public class ReadInformation {
  private Map<String, Integer> keyWord2Index;
  private List<String[]> listOfString;
  private static final String regexFormat = "(\",\")|(\")";

  /**
   * method for reading supporters' information
   * @param csvPath file path
   * @throws IOException when file cannot be found
   */
  public ReadInformation(String csvPath) throws IOException {
    BufferedReader inputFile = null;
    try {
      inputFile = new BufferedReader(new FileReader(csvPath));
      String firstRow;
      firstRow = inputFile.readLine();
      this.keyWord2Index = keyWord2Index(firstRow);
      List<String[]> listOfString = new ArrayList<>();
      String nextRow;
      while((nextRow = inputFile.readLine()) != null) {
        String[] infoDetail = nextRow.split(regexFormat);
        infoDetail = Arrays.copyOfRange(infoDetail, 1, infoDetail.length);
        listOfString.add(infoDetail);
      }
      this.listOfString = listOfString;
    } catch (IOException ioe) {
      throw new IOException("Something went wrong! : " + ioe.getMessage());
    }
  }

  /**
   * method for reading templates
   * @param file file name
   * @return a list of strings which stores each line of the file
   * @throws IOException when file cannot be found
   */
  protected static List<String> readTemplateFile(String file) throws IOException {
    List<String> list = new ArrayList<>();
    String str = null;
    FileInputStream inputStream = null;
    try {
      inputStream = new FileInputStream(file);
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      while ((str = bufferedReader.readLine()) != null) {
        list.add(str);
      }
      inputStream.close();
      bufferedReader.close();
    } catch (IOException e) {
      throw new IOException("No such file");
    }
    return list;
  }

  /**
   * helper function to deal with the first line of the
   * supporters' information
   * @param title the first line of the file
   * @return a map which stores modified keywords and
   * corresponding indexes
   */
  private static Map<String, Integer> keyWord2Index(String title) {
    Map<String, Integer> mapping = new HashMap<>();
    String[] titles = title.split(",");
    for (int i = 0; i < titles.length; i++) {
      mapping.put("[[" + titles[i].replace("\"", "") + "]]", i);
    }
    return mapping;
  }

  /**
   * getter for the KeyWord2Index
   * @return the KeyWord2Index
   */
  public Map<String, Integer> getKeyWord2Index() {
    return keyWord2Index;
  }

  /**
   * getter for the listOfString
   * @return the listOfString
   */
  public List<String[]> getListOfString() {
    return listOfString;
  }

  @Override
  public String toString() {
    return "ReadSupportersInformation{" +
        "keyWord2Index=" + keyWord2Index +
        ", listOfString=" + listOfString +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReadInformation that = (ReadInformation) o;
    return Objects.equals(keyWord2Index, that.keyWord2Index) &&
        Objects.equals(listOfString, that.listOfString);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyWord2Index, listOfString);
  }
}
