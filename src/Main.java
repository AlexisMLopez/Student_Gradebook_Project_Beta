import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {

        //Creating an instance of the people class (which is a subclass of Courses),
        //to run the setStudentName method and the primaryLoop method.
        People user1 = new People();

        //Method that sets the student's name.
        user1.setStudentName();

        //This method contains a loop that enacts all of the other methods.
        user1.primaryLoop();
    }
}