import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the number of students
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        // Create the StudentManagement system with a capacity of numStudents
        StudentManagement management = new StudentManagement(numStudents);

        // Generate and add random students
        Random rand = new Random();
        for (int i = 0; i < numStudents; i++) {
            String id = "S" + String.format("%04d", i + 1);  // Generating student ID like S0001, S0002, ...
            String name = "Student" + (i + 1);
            double score = 1 + (10 - 1) * rand.nextDouble();  // Random score between 1.0 and 10.0
            management.addStudent(new Student(id, name, score));
        }

        // Sort with QuickSort
        System.out.println("\nSorting using QuickSort:");
        management.sortStudentsByScore("quicksort");

//        // Sort with BubbleSort
        System.out.println("\nSorting using BubbleSort:");
        management.sortStudentsByScore("bubblesort");


        // Close scanner
        scanner.close();
    }
}
