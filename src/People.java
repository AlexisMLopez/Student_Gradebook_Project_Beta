import java.io.IOException;

public class People extends Courses{

    //This instance call allows for the primaryLoop to have access to the FileHandler class.
    FileHandler fileH = new FileHandler();

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
         * 1.) courseSelector: (LINE: 95)
         * Locks in desired course along with desired 2d array using the course selector method.
         * @variable courseLock: The course is assigned to this String variable.
         * @variable gradeLock: The grade table is assigned to this double[][] variable. (which is used to calculate weights and
         * to assign grade letters.)
         *
         * 2.) enterEveryCourseGrade: (LINE: 98)
         * Allows the entering of all of the grades for a respective course depending on what assignment is entered.
         *
         * 3.) putCourse: (LINE: 101)
         * After the course, grade table, and grades are entered into the grade table, all of that information is placed
         * into a hashmap with the format <key: courses, value: grade table>.
         *
         * 4.) printAllGrades: (LINE: 103)
         * Prints all of the grades entered in order to check for errors.
         *
         * 5.) getCourseAverage: (LINE: 106)
         * Gets the individual course weight using whatever 2d array is assigned to gradeLock, via
         * @variable int classSelected.
         *
         * 6.) checkGrade: (LINE: 108)
         * method that is printed applies a letter grade to the previously calculated individual course weight.
         *
         * 7.) putAverage: (LINE: 110)
         * method unifies a course with an individual weighted average within a hashmap called averageHashmap.
         * this method takes gradeLock as an input. Within the method itself, gradeLock is the key and
         * @variable weightedIndividualCourse is put in as the value.
         *
         * 8.) putGradeLetter: (LINE: 112)
         * method unifies a course with the a grade letter within a hashmap called gradeLetterHashmap.
         * the grade letter portion is based off of the previously calculated from the weightedIndividualCourseAverages.
         * Where the course is the key and the weightedIndividualCourseAverage is the value.
         *
         * 9.) enterUserGradeFlag: (LINE: 114)
         * is called in order to receive user input that can either end or continue the loop.
         * The while loop is ended when the user inputs "No", that specific input sets the variable userGradePrompt to be false.
         *
         * } end of loop userGradePrompt = false
         *
         * 10.) (LINE: 120 && (LINE: 121)) Once we break out of the loop we get the total semester average and set a grade letter
         * to that average.
         *
         * 11.) (LINE: 123) We then write the file using the fileWriter method with all of the input variables.
         *
         * 12.) (LINE: 126) && (LINE: 129) Furthermore we read the file we just wrote and print the info from the file
         * to the console in order to check if we read it correctly.
         *
         * 13.) (LINE: 133) Finally, we have a simple if/else if/ else statement that reads the file and sees if it contains
         * a specific phrase with a grade letter to see whether the student has passed the semester or not.
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

        getSemesterAverage(averageHashmap.values());
        semesterLetterGrade = checkGrade(weightedSemesterAverage);

        fileH.fileWriter(studentName, dataStructure.keySet(), averageHashmap.values(),
                gradeLetterHashmap.values(), semesterLetterGrade);

        fileH.fileReader();

        //File reader check
        System.out.println(fileH.info);

        System.out.println("\n");

        if (fileH.info.contains("final grade for semester: a")) {
            System.out.println("Congratulations on passing the Semester! You got an A and did fantastic.");
        }
        else if (fileH.info.contains("final grade for semester: b")) {
            System.out.println("Well done on passing the Semester! You got a B and did well.");
        }
        else if (fileH.info.contains("final grade for semester: c")) {
            System.out.println("Good job, you passed the Semester. You got a C and did an average job.");
        }
        else {
            System.out.println("Better luck next year, you did not pass.");
        }

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



