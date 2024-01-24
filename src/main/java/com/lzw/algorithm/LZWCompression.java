package com.lzw.algorithm;
import java.io.*;
import java.util.*;

public class LZWCompression {
    public static List<Integer> encode(String text) {
        int dictsize = 256;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictsize; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }

        String foundChars = "";
        List<Integer> result = new ArrayList<>();
        for (char character : text.toCharArray()) {
            String charsToAdd = foundChars + character;
            if (dictionary.containsKey(charsToAdd)) {
                foundChars = charsToAdd;
            } else {
                result.add(dictionary.get(foundChars));
                dictionary.put(charsToAdd, dictsize++);
                foundChars = String.valueOf(character);
            }
        }
        if (!foundChars.isEmpty()) {
            result.add(dictionary.get(foundChars));
        }
        return result;
    }

    public static String decode(List<Integer> encodedText) {
        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        String characters = String.valueOf((char) encodedText.remove(0).intValue());
        StringBuilder result = new StringBuilder(characters);
        for (int code : encodedText) {
            String entry;
            if(dictionary.containsKey(code)){
                entry = dictionary.get(code);
            }else{
                entry = characters + characters.charAt(0);
            }
            result.append(entry);
            dictionary.put(dictSize++, characters + entry.charAt(0));
            characters = entry;
        }
        return result.toString();
    }

    public static String fileCompress(String inputFilePath) throws IOException {

            FileInputStream inputFile = new FileInputStream(inputFilePath);
            FileOutputStream outputFile = new FileOutputStream("compression.lzw");

            HashMap<String, Integer> dictionary = new HashMap<>();
            for (int i = 0; i < 256; i++) {
                dictionary.put("" + (char) i, i);
            }

            String currentString = "";
            int code = 256;

            int inputByte;
            while ((inputByte = inputFile.read()) != -1) {
                char inputChar = (char) inputByte;
                String newString = currentString + inputChar;
                if (dictionary.containsKey(newString)) {
                    currentString = newString;
                } else {
                    outputFile.write(dictionary.get(currentString));
                    dictionary.put(newString, code);
                    code++;
                    currentString = "" + inputChar;
                }
            }

            if (!currentString.equals("")) {
                outputFile.write(dictionary.get(currentString));
            }

            inputFile.close();
            outputFile.close();

            File originalFile = new File(inputFilePath);
            File compressedFile = new File("compression.lzw");

            double originalFileSize = originalFile.length();
            double compressedFileSize = compressedFile.length();

            double compressionRatio = (1 - (compressedFileSize / originalFileSize)) * 100;

            return  "Compression ratio: " + compressionRatio + "%" + "\n original size : " + originalFileSize + " bytes" + "\n compression size : " + compressedFileSize + " bytes";



    }
}






