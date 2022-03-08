import java.io.*;

public class Launcher {
    public static void main(String[] args) throws IOException {
        try {
            //Creating FileReader object
            File file = new File("C:/TextFile1.txt");
            FileReader reader = new FileReader(file);
            char chars[] = new char[(int) file.length()];
            //Reading data from the file
            reader.read(chars);

            //Writing data to another file
            File out = new File("C:/TextFile2.txt");
            FileWriter writer = new FileWriter(out);
            //Writing data to the file
            writer.write(chars);
            writer.flush();
            System.out.println("Data successfully written to the specified file");
        } catch (IOException e) {
            System.out.print("Exception" + e.getMessage());
        }
    }
}