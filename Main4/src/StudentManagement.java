import java.util.Arrays;

public class StudentManagement {
    private StudentStack studentStack;

    public StudentManagement(int capacity) {
        studentStack = new StudentStack(capacity);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add null student.");
        }
        if (searchStudent(student.getId()) != null) {
            throw new IllegalArgumentException("A student with this ID already exists.");
        }
        studentStack.push(student);
    }

    public void deleteStudent(String studentId) {
        if (studentId == null || studentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        Student[] temp = new Student[studentStack.size()];
        int count = 0;
        boolean found = false;

        while (!studentStack.isEmpty()) {
            Student current = studentStack.pop();
            if (current.getId().equals(studentId)) {
                found = true;
            } else {
                temp[count++] = current;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
        }

        for (int i = count - 1; i >= 0; i--) {
            studentStack.push(temp[i]);
        }
    }

    public Student searchStudent(String studentId) {
        if (studentId == null || studentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        Student[] temp = new Student[studentStack.size()];
        int count = 0;
        Student result = null;

        while (!studentStack.isEmpty()) {
            Student current = studentStack.pop();
            if (current.getId().equals(studentId)) {
                result = current;
            }
            temp[count++] = current;
        }

        for (int i = count - 1; i >= 0; i--) {
            studentStack.push(temp[i]);
        }

        return result;
    }

    // Quicksort Implementation
    public void quickSort(Student[] students, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(students, low, high);
            quickSort(students, low, pivotIndex - 1);
            quickSort(students, pivotIndex + 1, high);
        }
    }

    private int partition(Student[] students, int low, int high) {
        Student pivot = students[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students[j].getScore() > pivot.getScore()) {
                i++;
                swap(students, i, j);
            }
        }
        swap(students, i + 1, high);
        return i + 1;
    }

    private void swap(Student[] students, int i, int j) {
        Student temp = students[i];
        students[i] = students[j];
        students[j] = temp;
    }

    // Bubblesort Implementation
    public void bubbleSort(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j].getScore() < students[j + 1].getScore()) {
                    swap(students, j, j + 1);
                }
            }
        }
    }

    public void sortStudentsByScore(String algorithm) {
        if (studentStack.isEmpty()) {
            throw new IllegalStateException("Cannot sort. The stack is empty.");
        }

        // Mảng chứa các sinh viên từ stack
        Student[] students = new Student[studentStack.size()];
        int count = 0;

        // Lấy tất cả sinh viên từ stack vào mảng
        while (!studentStack.isEmpty()) {
            students[count++] = studentStack.pop();
        }

        long startTime = System.nanoTime();
        if ("quicksort".equalsIgnoreCase(algorithm)) {
            quickSort(students, 0, students.length - 1);
        } else if ("bubblesort".equalsIgnoreCase(algorithm)) {
            bubbleSort(students);
        }

        else {
            throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
        }
        long endTime = System.nanoTime();
        System.out.println(algorithm + " sorting time: " + (endTime - startTime) + " nanoseconds.");

        // Đưa lại các sinh viên vào stack theo thứ tự đã sắp xếp
        for (int i = 0; i < students.length; i++) {
            studentStack.push(students[i]);
        }
    }

    public void displayAllStudents() {
        if (studentStack.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        Student[] temp = new Student[studentStack.size()];
        int count = 0;

        while (!studentStack.isEmpty()) {
            temp[count++] = studentStack.pop();
        }

        for (int i = count - 1; i >= 0; i--) {
            studentStack.push(temp[i]);
            System.out.println(temp[i]);
        }
    }

    public void searchStudentByScore(double score) {
        long startTime = System.nanoTime();
        boolean found = false;

        Student[] temp = new Student[studentStack.size()];
        int count = 0;

        while (!studentStack.isEmpty()) {
            Student current = studentStack.pop();
            if (current.getScore() == score) {
                System.out.println(current);
                found = true;
            }
            temp[count++] = current;
        }

        for (int i = count - 1; i >= 0; i--) {
            studentStack.push(temp[i]);
        }

        long endTime = System.nanoTime();
        System.out.println("Search time: " + (endTime - startTime) + " nanoseconds.");
        if (!found) {
            System.out.println("No student found with score: " + score);
        }
    }
    public Student binarySearch(String studentId) {
        if (studentStack.isEmpty()) {
            throw new IllegalStateException("Cannot perform binary search. The stack is empty.");
        }

        Student[] students = new Student[studentStack.size()];
        int count = 0;

        while (!studentStack.isEmpty()) {
            students[count++] = studentStack.pop();
        }

        Arrays.sort(students, (s1, s2) -> s1.getId().compareTo(s2.getId()));

        int low = 0;
        int high = students.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (students[mid].getId().equals(studentId)) {
                // Đưa lại sinh viên vào stack
                for (int i = 0; i < students.length; i++) {
                    studentStack.push(students[i]);
                }
                return students[mid];
            } else if (students[mid].getId().compareTo(studentId) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        for (int i = 0; i < students.length; i++) {
            studentStack.push(students[i]);
        }

        return null;
    }
}
