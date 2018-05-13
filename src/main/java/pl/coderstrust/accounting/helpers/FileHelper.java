package pl.coderstrust.accounting.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

  public static void writeToFile(List<String> lines, String filePath) throws IOException {
    if (lines == null) {
      throw new IllegalArgumentException("Parameter lines may not be null");
    }
    if (filePath == null) {
      throw new IllegalArgumentException("Parameter filePath may not be null");
    }
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
      for (String line : lines) {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
    }
  }

  public static List<String> readFromFile(String file) throws IOException {
    ArrayList<String> lines = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
      }
    }
    return lines;
  }

  public static void writeOneInvoiceToFile(String oneInvoice, String filePath) throws IOException {
    if (oneInvoice == null) {
      throw new IllegalArgumentException("Parameter lines may not be null");
    }
    if (filePath == null) {
      throw new IllegalArgumentException("Parameter filePath may not be null");
    }
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
      bufferedWriter.append(oneInvoice);
      bufferedWriter.newLine();
    }
  }
}