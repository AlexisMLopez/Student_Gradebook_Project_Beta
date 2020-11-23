import java.util.Collection;
import java.util.Map;

public class Assignments {

    final String[] ASSIGNMENTS = {"Quiz", "Exam", "Homework", "Project"};

    //Our grade tables that are associated with specific courses
    protected double[][] c1GradeTable =  new double[4][3];
    protected double[][] c2GradeTable =  new double[4][3];
    protected double[][] c3GradeTable =  new double[4][3];
    protected double[][] c4GradeTable =  new double[4][3];

    //This 2d array becomes whatever grade table we assign it to via the courseSelector() method in the Courses class.
    protected double[][] gradeLock = new double[4][3];

    //The weighted average for an individual course.
    protected double weightedIndividualCourseAverage;
    //The letter grade associated with weightedIndividualCourseAverage.
    protected char letterGrade;
    //The weighted average of the averages of all of the individual courses.
    protected double weightedSemesterAverage;
    //the letter grade associated with weightedSemesterAverage.
    protected char semesterLetterGrade;

    //Weighted percentages for Assignments
    final protected double quizW = 0.20;
    final protected double examW = 0.30;
    final protected double homeworkW = 0.25;
    final protected double projectW = 0.25;





    protected void getCourseAverage(double[][] insert2dArray) {
        /**
         * Gets the average of the values in a specific individual course.
         *
         * @param insert2dArray  For the 2dArray, double[][] gradeLock is chosen.
         * @variable weightedIndividualCourseAverage: this double value is calculated by adapting the weighted value formula
         * to all of the elements within the 2d array. All the Assignments are multiplied to their respective weighted percentages
         * and then added together. Finally, they are divided by the sum of the weights.
         */

        weightedIndividualCourseAverage = (((insert2dArray[0][0] * quizW) + (insert2dArray[0][1] * quizW) + (insert2dArray[0][2] * quizW) +
                (insert2dArray[1][0] * examW) + (insert2dArray[1][1] * examW) + (insert2dArray[1][2] * examW) +
                (insert2dArray[2][0] * homeworkW) + (insert2dArray[2][1] * homeworkW) + (insert2dArray[2][2] * homeworkW)  +
                (insert2dArray[3][0] * projectW) + (insert2dArray[3][1] * projectW) + (insert2dArray[3][2] * projectW))
                /((3 * quizW) + (3 * examW) + (3 * homeworkW) + (3 * projectW)));

        System.out.println("Final weight: " + weightedIndividualCourseAverage);

    }

    protected void getSemesterAverage(Collection<Double> insertCollection) {
        /**
         * This method calculates the weighted average of all of the weights previously recorded by the program with a for loop
         * and through division after the loop.
         * The for loop compares it's iterator with the size of the collection, so its value  dependant on the user's input.
         * This makes it so the user can enter any amount of weighted averages in that can be calculated into one final semester average.
         *
         * @param insertCollection this takes input from our averageHashmap, specifically the values which are all double weights.
         * @variable addedSemesterAverage: is a variable that adds all of the calculated individual course averages together.
         * @variable weightedSemesterAverage: is the final calculated semester average. This is calculated by taking the addedSemesterAverage
         * value and dividing it by the size of the collection that was inserted into the method.
         */

        Double[] array = insertCollection.toArray(new Double[insertCollection.size()]);
        double addedSemesterAverage = 0;

        for (int i = 0; i < insertCollection.size(); i++) {
            addedSemesterAverage += array[i];
        }
        weightedSemesterAverage = addedSemesterAverage / insertCollection.size();
    }



    protected char checkGrade(double insertWeight) {
        /**
         * This method takes an individual course average and assigns and returns a char value to it based on
         * what letter grade corresponds to it.
         *
         * @param insertWeight the individual course average is taken as input.
         * @variable letterGrade: this is a char class variable that is set to grade letter that comes from the individual course weight.
         * this letter grade is inputted into the gradeLetterHashmap
         */

        if (insertWeight >= 90) {
            this.letterGrade = 'A';
            return 'A';
        }
        else if ((insertWeight >= 80) && (insertWeight <= 89)) {
            this.letterGrade = 'B';
            return 'B';
        }
        else if ((insertWeight >= 70) && (insertWeight <= 79)) {
            this.letterGrade = 'C';
            return 'C';
        }
        else if ((insertWeight >= 60) && (insertWeight <= 69)) {
            this.letterGrade = 'D';
            return 'D';
        }
        else {
            this.letterGrade = 'F';
            return 'F';
        }

    }



    }



