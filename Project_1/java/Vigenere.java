import java.util.*;
import java.io.*;

public class Vigenere {
  private String plaintext, ciphertext, key;
  
  private static class VigenereCipher {
    
    public VigenereCipher(){
      super();
    }
    
    public static char encipherCharacter(char message_char, char key_char){
      return (char)(((int)(message_char) + (int)(key_char) - 194)%26 + 97);
    }
  
    public static String encipher(String plaintext, String key){
      String ciphertext = "";
      
      for(int i = 0; i < plaintext.length(); i++)
        
        ciphertext += encipherCharacter(plaintext.charAt(i), key.charAt((i % key.length())));
        
      return ciphertext;
    }
  }
  
  private static class DataHandler {
    private static int MAX_STRING_LENGTH = 512;
    private static int OUTPUT_LINE_LENGTH = 80;
    
    // manages retrieval of source texts from the filesystem
    public static class FileHandler {
      public FileHandler(){
        super();
      }
      
      public static String readFile(String filename){
        
        String file_line = "";
        String file_contents = "";
        
        try {
          FileReader reader = new FileReader(filename);
          BufferedReader buffer = new BufferedReader(reader);
          
          while((file_line = buffer.readLine()) != null) {
            file_contents += file_line;
          }
          
          buffer.close();
        }
        
        catch(IOException e) {
          System.out.printf("*** An error was encountered while loading the file: ***%n");
          e.printStackTrace();
        }

        return file_contents;
        
      }
      
    }
    
    // Cleans/pads/formats input files
    public static class InputFormatter {
      public InputFormatter(){
        super();
      }
      
      public static String clean(String input){
        return input.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
      }
      
      public static String addPadding(String plaintext, String key){
        int padAmount = ((2 * key.length()) - plaintext.length());

        for(int i = 0; i < padAmount; i++)
          plaintext += 'x';
        
        return plaintext;
      }
      
    }
    
    // handles program output display (formatting, etcetera)
    public static class OutputFormatter {
      public OutputFormatter(){
        super();
      }
      
      public static void printBlockOutput(String input){
        int i = 0;
        int j = 0;
        
        while(j < input.length()){
          String fmt = "%c";
          
          if(i == (OUTPUT_LINE_LENGTH - 1)){
            fmt += "%n";
            i = 0;
          } else {
            i++;
          }
            
          System.out.printf(fmt, input.charAt(j));
          j++;
        }
      }
    }
    
    public DataHandler(){
      super();
    }
    
    public static String loadKey(String filename){
      String key = InputFormatter.clean(FileHandler.readFile(filename));
      MAX_STRING_LENGTH = (key.length() >= 512) ? 512 : key.length();
      return key;
    }
    
    public static String loadPlaintext(String filename){
      String plaintext = InputFormatter.clean(FileHandler.readFile(filename));
      return plaintext;
    }
    
  }
  
  public static void main(String[] args){
    
    DataHandler datahandler = new DataHandler();
    
    String key = DataHandler.loadKey(args[1]);
    
    System.out.printf("%n%nVigenere Key:%n%n");
    DataHandler.OutputFormatter.printBlockOutput(key);
    
    String plaintext = DataHandler.loadPlaintext(args[0]);
    plaintext = DataHandler.InputFormatter.addPadding(plaintext, key);
    
    System.out.printf("%n%n%nPlaintext:%n%n");
    DataHandler.OutputFormatter.printBlockOutput(plaintext);
    
    String ciphertext = VigenereCipher.encipher(plaintext, key);
    
    System.out.printf("%n%n%nCiphertext:%n%n");
    DataHandler.OutputFormatter.printBlockOutput(ciphertext);
    
    System.out.printf("%n");
  }
  
}
