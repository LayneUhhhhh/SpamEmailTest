import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface FileInput {

    public static List<String> loadEmails() {

        String filePath = "emails.txt"; // Replace this with the actual file path

        //store strings
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }

    public static String loadEmailMessage() {

        String filePath = "message.txt"; //file that holds the email's message
        StringBuilder fileContent = new StringBuilder(); //variable that holds the message in this function

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            //temporarily holds current line
            String line;

            //read next line and append it to the fileContent StringBuilder. Then append new line character.
            while ((line = reader.readLine()) != null)
                fileContent.append(line).append("\n");


        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileContent.toString();

    }

}