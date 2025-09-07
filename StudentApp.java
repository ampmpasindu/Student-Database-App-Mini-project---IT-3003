import java.util.List;
import java.util.Scanner;

public class StudentApp {
    private DatabaseManager dbManager;
    private Scanner scanner;
    
    public StudentApp(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.scanner = new Scanner(System.in);
    }

    
    public static void main(String[] args) {
        // Database config
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = ""; 
        
        try {
            // Init db manager
            DatabaseManager dbManager = new DatabaseManager(url, username, password);
            dbManager.createStudentsTable();
            
            StudentApp app = new StudentApp(dbManager);
            app.run();
            
            dbManager.close();
        } catch (Exception e) {
            System.out.println("Failed to initialize app: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void run() {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n--- Student Database App ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student by Index No");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Please choose an option: ");
            
            int choice = getIntInput();
            
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        viewStudentByIndexNo();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Exiting app.!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    }
    
    private void addStudent() throws Exception {
        System.out.println("\n--- Add New Student ---");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter index number (ex : s12345): ");
        String indexNo = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter combination (p1/p2/p3/p4/p5/p6): ");
        String combination = scanner.nextLine();
        
        Student student = new Student(0, name, indexNo, email, combination);
        boolean success = dbManager.addStudent(student);
        
        if (success) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student. Index number or email might already exist.");
        }
    }
    
    private void viewAllStudents() throws Exception {
        System.out.println("\n--- All Students ---");
        List<Student> students = dbManager.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found in the database.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    
    private void viewStudentByIndexNo() throws Exception {
        System.out.println("\n--- View Student by Index No ---");
        System.out.print("Enter student index number: ");
        String indexNo = scanner.nextLine();
        
        Student student = dbManager.getStudentByIndexNo(indexNo);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student with index number " + indexNo + " not found.");
        }
    }
    
    private void updateStudent() throws Exception {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter student index number to update: ");
        String indexNo = scanner.nextLine();
        
        Student existingStudent = dbManager.getStudentByIndexNo(indexNo);
        if (existingStudent == null) {
            System.out.println("Student with index number " + indexNo + " not found.");
            return;
        }
        
        System.out.println("Current details: " + existingStudent);
        System.out.println("Enter new details (leave blank to keep current value):");
        
        System.out.print("Name (" + existingStudent.getName() + "): ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            name = existingStudent.getName();
        }
        
        System.out.print("Index No (" + existingStudent.getIndexNo() + "): ");
        String newIndexNo = scanner.nextLine();
        if (newIndexNo.trim().isEmpty()) {
            newIndexNo = existingStudent.getIndexNo();
        }
        
        System.out.print("Email (" + existingStudent.getEmail() + "): ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            email = existingStudent.getEmail();
        }
        
        System.out.print("Combination (" + existingStudent.getCombination() + "): ");
        String combination = scanner.nextLine();
        if (combination.trim().isEmpty()) {
            combination = existingStudent.getCombination();
        }
        
        Student updatedStudent = new Student(existingStudent.getId(), name, newIndexNo, email, combination);
        boolean success = dbManager.updateStudent(updatedStudent);
        
        if (success) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Failed to update student. The new index number or email might already exist.");
        }
    }
    
    private void deleteStudent() throws Exception {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter student index number to delete: ");
        String indexNo = scanner.nextLine();
        
        Student student = dbManager.getStudentByIndexNo(indexNo);
        if (student == null) {
            System.out.println("Student with index number " + indexNo + " not found.");
            return;
        }
        
        System.out.println("Student to delete: " + student);
        System.out.print("Are you sure you want to delete this student? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            boolean success = dbManager.deleteStudent(student.getId());
            if (success) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Failed to delete student.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}