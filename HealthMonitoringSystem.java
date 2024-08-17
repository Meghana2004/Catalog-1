import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Diagnosis {
    private String patientId;
    private String patientName;
    private String diagnosisDate;
    private String condition;
    private String treatment;

    public Diagnosis(String patientId, String patientName, String diagnosisDate, String condition, String treatment) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.diagnosisDate = diagnosisDate;
        this.condition = condition;
        this.treatment = treatment;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    public String getCondition() {
        return condition;
    }

    public String getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "Patient ID='" + patientId + '\'' +
                ", Patient Name='" + patientName + '\'' +
                ", Date='" + diagnosisDate + '\'' +
                ", Condition='" + condition + '\'' +
                ", Treatment='" + treatment + '\'' +
                '}';
    }
}

public class HealthMonitoringSystem {
    private List<Diagnosis> diagnoses = new ArrayList<>();
    private static Random random = new Random();

    public String generateUniqueId() {
        String id;
        do {
            id = String.format("%04d", random.nextInt(10000)); // Generate a 4-digit number
        } while (diagnoses.stream().anyMatch(d -> d.getPatientId().equals(id))); // Ensure the ID is unique
        return id;
    }

    public void addDiagnosis(String patientName, String diagnosisDate, String condition, String treatment) {
        String patientId = generateUniqueId();
        Diagnosis diagnosis = new Diagnosis(patientId, patientName, diagnosisDate, condition, treatment);
        diagnoses.add(diagnosis);
        System.out.println("Diagnosis added successfully with Patient ID: " + diagnosis.getPatientId());
    }

    public void removeDiagnosis(String patientId) {
        diagnoses.removeIf(diagnosis -> diagnosis.getPatientId().equals(patientId));
        System.out.println("Diagnosis removed successfully.");
    }

    public void listDiagnoses() {
        if (diagnoses.isEmpty()) {
            System.out.println("No diagnoses to display.");
        } else {
            System.out.printf("%-10s %-20s %-15s %-20s %-30s%n", "Patient ID", "Patient Name", "Diagnosis Date", "Condition", "Treatment");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            for (Diagnosis diagnosis : diagnoses) {
                System.out.printf("%-10s %-20s %-15s %-20s %-30s%n", diagnosis.getPatientId(), diagnosis.getPatientName(),
                        diagnosis.getDiagnosisDate(), diagnosis.getCondition(), diagnosis.getTreatment());
            }
        }
    }

    public static void main(String[] args) {
        HealthMonitoringSystem system = new HealthMonitoringSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHealth Monitoring System");
            System.out.println("1. Add Diagnosis");
            System.out.println("2. Remove Diagnosis");
            System.out.println("3. List Diagnoses");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter diagnosis date (YYYY-MM-DD): ");
                    String diagnosisDate = scanner.nextLine();
                    System.out.print("Enter condition: ");
                    String condition = scanner.nextLine();
                    System.out.print("Enter treatment: ");
                    String treatment = scanner.nextLine();
                    system.addDiagnosis(patientName, diagnosisDate, condition, treatment);
                    break;
                case 2:
                    System.out.print("Enter patient ID to remove diagnosis: ");
                    String idToRemove = scanner.nextLine();
                    system.removeDiagnosis(idToRemove);
                    break;
                case 3:
                    system.listDiagnoses();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }
}
