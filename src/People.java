import java.io.IOException;

public class People extends Courses{



    //The students name is stored within this String variable.
    String studentName;

    //This boolean takes user input in order to continue or end the while loop in the primaryLoop method on line 33.
    boolean userGradePrompt = true;

    //default constructor
    People () {

    }


    protected void setStudentName () {
        /**
         * Sets the student's name to the input from the user.
         * @variable studentName: name of student.
         */

        System.out.println("Welcome to the GradeBook program, What is your name? (First, Last)");
        this.studentName = scan.nextLine();
        System.out.println("Thank you, " + this.studentName + " let's enter some grades.");
    }



    protected void primaryLoop() throws IOException {
        /**
         * This is the central loop of this program, where the majority of the methods are executed.
         * Further information of what goes on here is described within the following methods in the code.
         *
         * All within brackets is looped: {
         * 1.) courseSelector: (LINE: 91)
         * Locks in desired course along with desired 2d array using the course selector method.
         * @variable courseLock: The course is assigned to this String variable.
         * @variable gradeLock: The grade table is assigned to this double[][] variable. (which is used to calculate weights and
         * to assign grade letters.)
         *
         * 2.) enterEveryCourseGrade: (LINE: 94)
         * Allows the entering of all of the grades for a respective course depending on what assignment is entered.
         *
         * 3.) putCourse: (LINE: 97)
         * After the course, grade table, and grades are entered into the grade table, all of that information is placed
         * into a hashmap with the format <key: courses, value: grade table>.
         *
         * 4.) printAllGrades: (LINE: 99)
         * Prints all of the grades entered in order to check for errors.
         *
         * 5.) getCourseAverage: (LINE: 102)
         * Gets the individual course weight using whatever 2d array is assigned to gradeLock, via
         * @variable int classSelected.
         *
         * 6.) checkGrade: (LINE: 104)
         * method that is printed applies a letter grade to the previously calculated individual course weight.
         *
         * 7.) putAverage: (LINE: 106)
         * method unifies a course with an individual weighted average within a hashmap called averageHashmap.
         * this method takes gradeLock as an input. Within the method itself, gradeLock is the key and
         * @variable weightedIndividualCourse is put in as the value.
         *
         * 8.) putGradeLetter: (LINE: 108)
         * method unifies a course with the a grade letter within a hashmap called gradeLetterHashmap.
         * the grade letter portion is based off of the previously calculated from the weightedIndividualCourseAverages.
         * Where the course is the key and the weightedIndividualCourseAverage is the value.
         *
         * 9.) enterUserGradeFlag: (LINE: 110)
         * is called in order to receive user input that can either end or continue the loop.
         * The while loop is ended when the user inputs "No", that specific input sets the variable userGradePrompt to be false.
         *
         * } end of loop userGradePrompt = false
         *
         * 10.) (LINE: 117) We then write the file using the fileWriter method with all of the input variables.
         *
         * 11.) (LINE: 120) && (LINE: 123) Furthermore we read the file we just wrote and print the info from the file
         * to the console in order to check if we read it correctly.
         *
         * 12.) (LINE: 125) Finally, we have the method coursePassCheck that reads the file and sees if it contains
         * a specific phrase with a grade letter to see whether the student has passed their courses or not.
         *
         * END PROGRAM
         */

        while (userGradePrompt) {

            //Locks in your desired course along with desired 2d array using the course selector method.
            courseSelector();

            //Entering specific assignment grades for a specific course.
            enterEveryCourseGrade(gradeLock);

            //Unifying the String COURSE and 2D array GRADE TABLE into a hashmap.
            putCourse(classLock, gradeLock);

            printAllGrades(classLock, gradeLock);
            //System.out.println(user1.classLock + Arrays.deepToString(user1.finalGradeLock));

            getCourseAverage(gradeLock);

            System.out.println(checkGrade(weightedIndividualCourseAverage));

            putAverage();

            putGradeLetter();

            enterUserGradeFlag();



        }


        fileH.fileWriter(studentName, dataStructure.keySet(), averageHashmap.values(),
                gradeLetterHashmap.values());

        fileH.fileReader();

        //File reader checks reportCard.txt contents and prints to console
        System.out.println(fileH.info);

        coursePassCheck(dataStructure.keySet());

    }


    protected void enterUserGradeFlag () {
        /**
         * This method acts as a flag that can be switched on or off via scanned user input.
         * if true: (user input is "no"), this method continues the loops in the larger primaryLoop method.
         * if false: (user input is "yes"), this method exits the while loop.
         * if default: continues the loop anyway.
         *
         * @variable userGradePrompt: the boolean variable that can be switched on or off (true or false)
         */

    System.out.println("Are you finished entering grades? (Y/N)");
    String input = scan.next().toLowerCase().substring(0,1);

    switch (input) {
        case "y":
            this.userGradePrompt = false;
            System.out.println("We're done entering grades, moving on!");
            System.out.println();
            break;

        case "n":
            this.userGradePrompt = true;
            System.out.println("Alright, let's keep adding grades. Adding grades where" + "\n" +
                                "you have previously added, will overwrite their previously assigned values.");
            System.out.println();
            break;

        default:
            this.userGradePrompt = true;
            System.out.println("Not a valid selection, please enter either (Y/N)."  + "\n" +
                                "sending you back to the start of the loop." + "\n"  +
                                "(your input was still accepted)");
            System.out.println();

        }

    }


}



