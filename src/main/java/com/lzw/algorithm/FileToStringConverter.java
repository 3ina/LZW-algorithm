package com.lzw.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileToStringConverter {
    public static String convertFileToString(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }


}
