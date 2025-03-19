import java.io.*;

class Student implements Serializable {
    int id;
    String name;
    double GPA;

    Student(int id, String name, double GPA) {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', GPA=" + GPA + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "John Doe", 3.75);
        String filename = "student.ser";

        try {
            // Serialization
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(student);
            out.close();
            fileOut.close();

            System.out.println("Serialized student object: " + student);

            // Deserialization
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Student deserializedStudent = (Student) in.readObject();
            in.close();
            fileIn.close();

            System.out.println("Deserialized student object: " + deserializedStudent);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}
