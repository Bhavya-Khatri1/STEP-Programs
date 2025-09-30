// Base class
class MedicalStaff {
    String name;
    String staffID;
    
    MedicalStaff(String name, String staffID) {
        this.name = name;
        this.staffID = staffID;
    }
    
    void commonDuties() {
        System.out.println(name + " (" + staffID + ") is performing general hospital duties.");
    }
}

// Doctor class
class Doctor extends MedicalStaff {
    Doctor(String name, String staffID) {
        super(name, staffID);
    }
    
    void diagnose() {
        System.out.println(name + " is diagnosing patients.");
    }
    
    void prescribe() {
        System.out.println(name + " is prescribing medicines.");
    }
    
    void performSurgery() {
        System.out.println(name + " is performing surgery.");
    }
}

// Nurse class
class Nurse extends MedicalStaff {
    Nurse(String name, String staffID) {
        super(name, staffID);
    }
    
    void administerMedicine() {
        System.out.println(name + " is administering medicine.");
    }
    
    void monitorPatients() {
        System.out.println(name + " is monitoring patients.");
    }
}

// Technician class
class Technician extends MedicalStaff {
    Technician(String name, String staffID) {
        super(name, staffID);
    }
    
    void operateEquipment() {
        System.out.println(name + " is operating medical equipment.");
    }
    
    void runTests() {
        System.out.println(name + " is running diagnostic tests.");
    }
}

// Administrator class
class Administrator extends MedicalStaff {
    Administrator(String name, String staffID) {
        super(name, staffID);
    }
    
    void scheduleAppointments() {
        System.out.println(name + " is scheduling appointments.");
    }
    
    void manageRecords() {
        System.out.println(name + " is managing patient records.");
    }
}

// Driver
public class Medical {
    public static void main(String[] args) {
        // Upcasting examples
        MedicalStaff staff1 = new Doctor("Dr. Alice", "D101");  
        MedicalStaff staff2 = new Nurse("Nurse Bob", "N202");
        MedicalStaff staff3 = new Technician("Tech Charlie", "T303");
        MedicalStaff staff4 = new Administrator("Admin Diana", "A404");
        
        // All can perform common duties
        staff1.commonDuties();
        staff2.commonDuties();
        staff3.commonDuties();
        staff4.commonDuties();
        
        // But their *specific methods* are accessible only if we downcast back
        System.out.println("\n--- Accessing Specific Roles ---");
        ((Doctor)staff1).performSurgery();
        ((Nurse)staff2).monitorPatients();
        ((Technician)staff3).runTests();
        ((Administrator)staff4).manageRecords();
    }
}