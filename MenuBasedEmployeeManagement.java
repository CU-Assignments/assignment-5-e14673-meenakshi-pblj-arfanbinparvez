import java.io.*;
import java.util.*;

class Employee implements Serializable {
    String name;
    int id;
    String designation;
    double salary;

    Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', designation='" + designation + "', salary=" + salary + "}";
    }
}

public class Main {
    private static final String FILE_NAME = "employees.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addEmployee(scanner, employees);
                    break;
                case 2:
                    displayEmployees(employees);
                    break;
                case 3:
                    saveEmployees(employees);
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner, List<Employee> employees) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter employee designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(name, id, designation, salary);
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            System.out.println("\nEmployee Details:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static List<Employee> loadEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Employee>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}
