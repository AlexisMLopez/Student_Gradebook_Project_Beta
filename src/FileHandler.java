import java.io.*;
import java.util.*;

public class FileHandler {

    public List<String> info = new ArrayList<>();   //This arraylist is used in the fileReader() method.

    /**
     * This method takes in a ton of info and writes it all to a file called reportCard.txt.
     * The method creates a file if it does not exist and then proceeds to write all of the inputted values into the file and closes the file.
     * If it cannot write the information to the file, that means we have encountered an IOException and the method will
     * print an error message and give us the stack trace.
     * Append is set to false so the file contents are removed the next time the program is ran.
     *
     * @param insertName this takes input from String studentName.
     * @param insertCourse this takes input from the hashmap dataStructure. Specifically it takes in the key set of the hashmap
     * which are all of the Courses that we have input into the hashmap via the String classLock variable.
     * @param insertWeight this takes input from the hashmap averageHashmap. It takes in the values from averageHashmap which are the
     * double individual course weighted averages.
     * @param insertLetterGrade this takes input from the hashmap gradeLetterHashmap. It takes the values from gradeLetterHashmap which are
     * the char letterGrade variables derived from the individual course weighted averages.
     *
     */
    public void fileWriter(String insertName, Set<String> insertCourse, Collection<Double> insertWeight,
                           Collection<Character> insertLetterGrade) throws IOException {
        String[] courseArray = insertCourse.toArray(new String[insertCourse.size()]);
        Character[] letterGradeArray = insertLetterGrade.toArray(new Character[insertCourse.size()]);

        File studentInfo = new File("src/reportCard.txt");
        if (!studentInfo.exists()) {
            studentInfo.createNewFile();
        } try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(studentInfo, false));
            writer.write(insertName + " " + insertCourse.toString() + " " + insertWeight + "\n");
            for(int i = 0; i < insertCourse.size(); i++) {
                writer.write(courseArray[i] + ": " + letterGradeArray[i] + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException writerEX) {
            System.out.println("Cannot write to file, permission issue: " + Arrays.toString(writerEX.getStackTrace()));
        }
    }

    /**
     * This method reads from the reportCard.txt file line by line until it reaches the end of the contents.
     * If the method fails to read the file, it will handle the FileNotFoundException and the IOException with an error
     * followed by getting the stack trace.
     * This method is later printed to check if the correct values were created in the file in the fileWriter() method properly.
     */
    public void fileReader() {
        try {
            FileReader reader = new FileReader("src/reportCard.txt");
            BufferedReader bReader = new BufferedReader(reader);

            String line;
            System.out.println();
            while ((line = bReader.readLine()) != null) {
                info.add(line.toLowerCase());
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist: " + Arrays.toString(ex.getStackTrace()));
        } catch (IOException e) {
            System.out.println("Problem reading file: " + Arrays.toString(e.getStackTrace()));
        }
    }
}