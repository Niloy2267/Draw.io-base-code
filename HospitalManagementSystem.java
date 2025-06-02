import java.util.ArrayList;
import java.util.Scanner;

// Patient class
class Patient {
    String name;
    int age;
    String disease;

    Patient(String name, int age, String disease) {
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age + ", Disease: " + disease);
    }
}

// Doctor class
class Doctor {
    int id;
    String name;
    String gender;
    String specialization;
    String phone;

    public Doctor(int id, String name, String gender, String specialization, String phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.specialization = specialization;
        this.phone = phone;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Gender: " + gender +
                           ", Specialization: " + specialization + ", Phone: " + phone);
    }
}

public class HospitalManagementSystem {

    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        while (true) {
            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Add Patient");
            System.out.println("4. View Patients");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    viewDoctors();
                    break;
                case 3:
                    addPatient();
                    break;
                case 4:
                    viewPatients();
                    break;
                case 5:
                    System.out.println("Exiting system. Thanks!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Doctor methods
    public static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        Doctor doctor = new Doctor(id, name, gender, specialization, phone);
        doctors.add(doctor);
        System.out.println("Doctor added successfully.");
    }

    public static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("\n--- Doctor List ---");
        for (Doctor d : doctors) {
            d.display();
        }
    }

    // Patient methods
    public static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter disease: ");
        String disease = scanner.nextLine();

        Patient patient = new Patient(name, age, disease);
        patients.add(patient);
        System.out.println("Patient added successfully!");
    }

    public static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\n--- Patient List ---");
        for (Patient p : patients) {
            p.display();
        }
    }
}