package Assignment;

public class PrescriptionTest {
    public static void main(String[] args) {
        Prescription prescription = new Prescription(
            "JohnD", "DoeBBC", "123 Example Street, Suburb, 45678, Country", 
            -2.50, 1.75, 90, "22/10/24", "Dr. Andrew Smith"
        );

        boolean result = prescription.addPrescription();
        System.out.println("Prescription added: " + result);
    }
}

