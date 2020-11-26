import java.util.*;

public class Courses extends Assignments {

    Scanner scan = new Scanner(System.in);
    FileHandler fileH = new FileHandler(); //Allows for the Courses class to have access to the FileHandler class.

    //Class variables.
    public int classSelected;
    public String classLock; //variable that gets String from the COURSES array assigned to it by the courseSelector() method.
    boolean userCoursePrompt = true;

    final public static String[] COURSES = {"Programming", "Math", "Science", "English"};
    protected HashMap<String, double[][]> dataStructure = new HashMap(); //Hashmap for storing a course with a grade table.
    protected HashMap<String, Double> averageHashmap = new HashMap<>(); //Hashmap for storing a course with a weighted grade.
    protected HashMap<String, Character> gradeLetterHashmap = new HashMap<>(); //Hashmap for storing a course with a grade letter.

    Courses () {
        super();
    }    //Calling the super class in the constructor

    /**
     * This method takes in a course and a 2d array and unifies them within a hashmap called dataStructure.
     *
     * @param insertCourse For the course, String classLock is chosen.
     * @param insert2dArray For the 2dArray, double[][] gradeLock is chosen.
     */
    protected void putCourse (String insertCourse, double[][] insert2dArray) {
        dataStructure.put(insertCourse, insert2dArray);
    }

    /**
     * This void method unifies a course with an individual weighted average within a hashmap called averageHashmap.
     *
     * @variable classLock: This represents the course chosen by the user using the courseSelector() method.
     * @variable weightedIndividualCourse: This represents the weighted average we get via the getCourseAverage method.
     */
    protected void putAverage (){
        averageHashmap.put(classLock, weightedIndividualCourseAverage);
    }

    /**
     * This void method unifies a course with a char grade letter derived from the previously stated weighted average.
     * These are both put into a hashmap called gradeLetterHashmap.
     *
     * @variable classLock:  This represents the course chosen by the user using the courseSelector() method.
     * @return checkGrade(weightedIndividualCourseAverage): This is a method call that returns a char; The grade letter
     * associated with a weighted average of one chosen course.
     */
    protected void putGradeLetter () {
        gradeLetterHashmap.put(classLock, checkGrade(weightedIndividualCourseAverage));
    }

    /**
     * the courseSelector() method takes a scanned input from the user from the integer values of 1 through 4.
     * each value from 1 through 4 gets both a course and an 2d array assigned to it.
     * For example: if 1 is selected, @variable classLock would be assigned to COURSES[0].
     * (the course selected would be the course at element 0 of our String COURSES array which is "Programming" )
     * This would also assign the 2d array c1GradeTable to @variable finalGradeLock.
     *
     * @variable userCoursePrompt: this boolean serves as flag that either ends or continues the while loop in this method.
     *
     * After a valid choice is selected, userCoursePrompt is set to false, and it breaks out of the while loop and
     * continues the code.
     *
     * If the user does not select a valid integer choice, the method sets userCoursePrompt = true which
     * restarts the method until the user picks a valid numerical option.
     */
    protected void courseSelector() {
        userCoursePrompt = true;
        while (userCoursePrompt) {
            System.out.println("Which class would you like to select?: (1-4)");
            System.out.println(Arrays.deepToString(COURSES));
            this.classSelected = scan.nextInt();

            if (this.classSelected == 1) {
                System.out.println("You have chosen: " + COURSES[0]);
                this.classLock = COURSES[0];
                this.gradeLock = c1GradeTable;
                this.userCoursePrompt = false;
            } else if (this.classSelected == 2) {
                System.out.println("You have chosen: " + COURSES[1]);
                this.classLock = COURSES[1];
                this.gradeLock = c2GradeTable;
                this.userCoursePrompt = false;
            } else if (this.classSelected == 3) {
                System.out.println("You have chosen: " + COURSES[2]);
                this.classLock = COURSES[2];
                this.gradeLock = c3GradeTable;
                this.userCoursePrompt = false;
            } else if (this.classSelected == 4) {
                System.out.println("You have chosen: " + COURSES[3]);
                this.classLock = COURSES[3];
                this.gradeLock = c4GradeTable;
                this.userCoursePrompt = false;
            } else {
                System.out.println("Not a valid choice, please pick from 1 through 4.");
                this.userCoursePrompt = true;
            }
        }
    }

    /**
     * This method takes in a 2d array and allows the user to enter all of the grades within the 2d array with nested for loops.
     * the for loops first go through columns of the 2d array and then the rows in order to enter the grades by user input.
     * @param insert2dArray gradeLock which was chose by the method courseSelector() is inputted here.
     */
    protected void enterEveryCourseGrade(double[][] insert2dArray) {
        System.out.println("Please enter all course grades for: " + classLock);
        for (int i = 0; i < insert2dArray.length; i++) {
            for (int j = 0; j < insert2dArray[i].length; j++) {
                System.out.print(ASSIGNMENTS[i] + " " + (j + 1) + ": ");
               insert2dArray[i][j] = scan.nextInt();
            }
            System.out.println();
        }
    }

    /**
     * This is just acts as a check to see if the correct grades were added to the correct class.
     * @param insertCourse takes the classLock variable as input.
     * @param insert2dArray For the 2dArray, double[][] gradeLock is chosen.
     */
        protected void printAllGrades (String insertCourse, double[][] insert2dArray) {
            System.out.println("All grades for: " + classLock);
            for (int i = 0; i < insert2dArray.length; i++) {
                for (int j = 0; j < insert2dArray[i].length; j++) {
                    System.out.print(ASSIGNMENTS[i] + " " + (j + 1) + ": " + dataStructure.get(insertCourse)[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

    /**
     * This method checks the contents of the reportCard.txt to see what grade the student got for their classes.
     * It gives the student information on whether on not they passed their courses.
     *
     * @param insertCourse: This takes in the key set from the dataStructure hashmap which is the COURSES String array.
     * We then convert this set<String> into an array.
     * @variable courseArray: This array is then looped through, printing messages based on what course got what grade.
     */
        protected void coursePassCheck (Set<String> insertCourse) {
            String[] courseArray = insertCourse.toArray(new String[insertCourse.size()]);
            System.out.println("\n");

            for(int i = 0; i < insertCourse.size(); i++) {
                if ((fileH.info.contains(courseArray[i].toLowerCase() + ": " + "a"))) {
                    System.out.println("Congratulations, you passed! You got an A in: " + courseArray[i]);
                } else if ((fileH.info.contains(courseArray[i].toLowerCase() + ": " + "b"))) {
                    System.out.println("Well done, you passed! You got a B in: " + courseArray[i]);
                } else if ((fileH.info.contains(courseArray[i].toLowerCase() + ": " + "c"))) {
                    System.out.println("Good job, you passed. You got a C in: " + courseArray[i]);
                } else {
                    System.out.println("You received a D or lower. You did not pass: " + courseArray[i]);
                }
            }
        }
}