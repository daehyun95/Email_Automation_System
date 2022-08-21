package problem1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * the class dealing with the writing part
 */
public class WriteTemplateFile {
  private static final Integer ADD_TWO_INDEX = 2;
  private static final Integer OUT_INDEX = -1;
  /**
   * the ultimate logic of writing emails or letters
   * @param readFile the supporter's information
   * @param path the output directory path
   * @param tempLine email or letter template data
   * @param nameStartsWith email_ or letter_
   * @throws IOException
   */
  public WriteTemplateFile(ReadInformation readFile, Path path, List<String> tempLine, String nameStartsWith) throws IOException {
    int i = 0;
    List<String[]> listOfString = readFile.getListOfString();
    try {
      while (i < listOfString.size()) {
        String[] infoDetail = listOfString.get(i);
        generateEmailOrLetter(tempLine, readFile, path, nameStartsWith, infoDetail);
        i++;
      }
    } catch (keywordNotExistException e) {
    System.out.println(e.getMessage());
    }
  }

  /**
   * helper function to generate one email or letter
   * @param template email or letter template data
   * @param readFile the supporter's information
   * @param path the output directory path
   * @param nameStartsWith email_ or letter_
   * @param infoDetail one supporter's information
   * @return
   * @throws keywordNotExistException if keywords don't exist in the file
   */
  private static List<String> generateEmailOrLetter(List<String> template, ReadInformation readFile, Path path,
      String nameStartsWith, String[] infoDetail) throws keywordNotExistException {
    List<String> list = new ArrayList<>();

   Map<String, Integer> keyWord2Index = readFile.getKeyWord2Index();

    for (String temp : template) {
      String line = convertTemplate(temp, Arrays.asList(infoDetail), keyWord2Index);
      list.add(line);
    }

    String fileName = nameStartsWith + infoDetail[keyWord2Index.get("[[first_name]]")] + "_" + infoDetail[keyWord2Index.get("[[last_name]]")];

    File file = new File(path.toFile(), fileName);
    writeFile(list, file);
    return list;
  }

  /**
   * a helper function to write the contents
   * @param contents an email or letter data
   * @param fileName a supporter's email or letter filename
   */
  private static void writeFile(List<String> contents, File fileName) {
    try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
      for (String content : contents) {
        printWriter.print(content + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * a helper function for the following convertTemplate function
   * @param line one line in the template
   * @return a list of strings which store these keywords
   */
  private static List<String> extractKeyWord(String line) {
    int startIndex = 0;
    List<String> list = new ArrayList<>();
    while (true) {
      int i1 = line.indexOf("[[", startIndex);
      if (i1 == OUT_INDEX)
        break;
      int i2 = line.indexOf("]]", i1);
      if (i2 == OUT_INDEX)
        break;
      String key = line.substring(i1, i2 + ADD_TWO_INDEX);
      list.add(key);
      startIndex = i2 + ADD_TWO_INDEX;
    }
    return list;
  }

  /**
   * a helper function for replacing the keywords with supporter's information
   * @param tempLine one line in the template
   * @param fileLine one supporter's information
   * @param keyWord2Index keywords map
   * @return a string which is a new line for the template
   * @throws keywordNotExistException if keywords don't exist in the file
   */
  private static String convertTemplate(String tempLine, List<String> fileLine, Map<String, Integer> keyWord2Index)
      throws keywordNotExistException {
    List<String> keyWord = extractKeyWord(tempLine);
    if (keyWord.isEmpty())
      return tempLine;
    String ret = tempLine;
    for (String key : keyWord) {
      if (!keyWord2Index.containsKey(key)) {
        throw new keywordNotExistException("ERROR: " + key + " not exist in csv file");
      }
      int index = keyWord2Index.get(key);
      String content = fileLine.get(index);
      ret = ret.replace(key, content);
    }
    return ret;
  }

  @Override
  public String toString() {
    return "WriteTemplateFile{}";
  }
}
