package LIon;

import java.io.*;

/**
 * Created by ivanm on 03.02.2017.
 */
public class Main {
  public static void main(String...args){
      Object objSave = new Test("School",12);
      File file = new File("Text.txt");

          try(FileWriter writer = new FileWriter(file,false)){
              ByteArrayOutputStream os = new ByteArrayOutputStream();
              ObjectOutput oos = new ObjectOutputStream(os);
              oos.writeObject(objSave);

              byte[] array = os.toByteArray();
              for(byte i : array){
                  System.out.print((char)i);
                  writer.write(i);
              }

          } catch (IOException e) {
              e.printStackTrace();
          }
  }
}
