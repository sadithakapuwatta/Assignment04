package Assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Prescription {
    private String firstName;
    private String lastName;
    private String address;
    private double sphere;
    private double cylinder;
    private int axis;
    private String examinationDate;
    private String optometristName;

    private static final String PRESCRIPTION_FILE = "presc.txt";
    private static final String REVIEW_FILE = "review.txt";

    public Prescription(String firstName, String lastName, String address, double sphere, double cylinder, int axis,
                        String examinationDate, String optometristName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.cylinder = cylinder;
        this.axis = axis;
        this.examinationDate = examinationDate;
        this.optometristName = optometristName;
    }

    public boolean addPrescription() {
        // Condition 1: Name length validation
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            System.out.println("Name validation failed. First name or last name does not meet the length criteria.");
            return false;
        }

        // Condition 2: Address length validation
        if (address.length() < 20) {
            System.out.println("Address validation failed. Address is too short.");
            return false;
        }

        // Condition 3: Validate sphere, cylinder, and axis
        if (sphere < -20.00 || sphere > 20.00) {
            System.out.println("Sphere validation failed. Sphere value is out of range.");
            return false;
        }
        if (cylinder < -4.00 || cylinder > 4.00) {
            System.out.println("Cylinder validation failed. Cylinder value is out of range.");
            return false;
        }
        if (axis < 0 || axis > 180) {
            System.out.println("Axis validation failed. Axis value is out of range.");
            return false;
        }

        // Condition 4: Validate examination date
        if (!isValidDate(examinationDate)) {
            System.out.println("Date validation failed. Examination date is invalid.");
            return false;
        }

        // Condition 5: Validate optometrist's name length
        if (optometristName.length() < 8 || optometristName.length() > 25) {
            System.out.println("Optometrist name validation failed. Name length is invalid.");
            return false;
        }

        // Save prescription to file
        try (FileWriter writer = new FileWriter(PRESCRIPTION_FILE, true)) {
            writer.write(String.format("Prescription: %s %s, Address: %s, Sphere: %.2f, Cylinder: %.2f, Axis: %d, Date: %s, Optometrist: %s%n",
                    firstName, lastName, address, sphere, cylinder, axis, examinationDate, optometristName));
            System.out.println("Prescription saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    private boolean isValidDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean addRemark(String remark, String category) {
        // Condition 1: Validate remark length and capitalization
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(remark.charAt(0))) {
            return false;
        }

        // Condition 2: Validate remark category
        if (!category.equalsIgnoreCase("client") && !category.equalsIgnoreCase("optometrist")) {
            return false;
        }

        // Ensure that only 2 remarks can be added
        int remarkCount = getRemarkCount();
        if (remarkCount >= 2) {
            return false;
        }

        // Save remark to file
        try (FileWriter writer = new FileWriter(REVIEW_FILE, true)) {
            writer.write(String.format("Remark: %s, Category: %s%n", remark, category));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private int getRemarkCount() {
        // A simple implementation that reads the file and counts the remarks
        // This can be implemented later based on how you want to handle remark persistence
        return 0;  // Placeholder for now
    }
}
