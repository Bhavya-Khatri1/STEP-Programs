// Q8_HospitalManagementSystem

class Patient {
    String patientId;
    String patientName;
    int age;
    String gender;
    String contactInfo;
    String[] medicalHistory;
    String[] currentTreatments;
    int historyCount;
    int treatmentCount;

    public Patient(String id, String name, int age, String gender, String contact) {
        this.patientId = id;
        this.patientName = name;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contact;
        this.medicalHistory = new String[10];
        this.currentTreatments = new String[5];
        this.historyCount = 0;
        this.treatmentCount = 0;
    }

    public void addHistory(String rec) {
        if (historyCount < medicalHistory.length) {
            medicalHistory[historyCount] = rec;
            historyCount++;
        }
    }

    public void addTreatment(String treatment) {
        if (treatmentCount < currentTreatments.length) {
            currentTreatments[treatmentCount] = treatment;
            treatmentCount++;
        }
    }
}

class Doctor {
    String doctorId;
    String doctorName;
    String specialization;
    String[] availableSlots;
    int slotCount;
    int patientsHandled;
    double consultationFee;

    public Doctor(String id, String name, String spec, double fee) {
        this.doctorId = id;
        this.doctorName = name;
        this.specialization = spec;
        this.consultationFee = fee;
        this.availableSlots = new String[5];
        this.slotCount = 0;
        this.patientsHandled = 0;
    }

    public void addAvailableSlot(String slot) {
        if (slotCount < availableSlots.length) {
            availableSlots[slotCount] = slot;
            slotCount++;
        }
    }
}

class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String appointmentDate;
    String appointmentTime;
    String status; // Scheduled, Cancelled, Completed

    public Appointment(String id, Patient p, Doctor d, String date, String time) {
        this.appointmentId = id;
        this.patient = p;
        this.doctor = d;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.status = "Scheduled";
    }
}

class Hospital {
    static int totalPatients = 0;
    static int totalAppointments = 0;
    static String hospitalName = "Campus Hospital";
    static double totalRevenue = 0.0;

    Patient[] patients;
    Doctor[] doctors;
    Appointment[] appointments;
    int pCount, dCount, aCount;

    public Hospital(int pSize, int dSize, int aSize) {
        patients = new Patient[pSize];
        doctors = new Doctor[dSize];
        appointments = new Appointment[aSize];
        pCount = dCount = aCount = 0;
    }

    public void addPatient(Patient p) {
        if (pCount < patients.length) {
            patients[pCount] = p;
            pCount++;
            totalPatients++;
        }
    }

    public void addDoctor(Doctor d) {
        if (dCount < doctors.length) {
            doctors[dCount] = d;
            dCount++;
        }
    }

    public Appointment scheduleAppointment(Patient p, Doctor d, String date, String time) {
        if (aCount < appointments.length) {
            String aid = "A" + (++totalAppointments);
            Appointment a = new Appointment(aid, p, d, date, time);
            appointments[aCount] = a;
            aCount++;
            d.patientsHandled++;
            totalRevenue += d.consultationFee;
            return a;
        }
        return null;
    }

    public boolean cancelAppointment(String id) {
        for (int i = 0; i < aCount; i++) {
            if (appointments[i] != null && appointments[i].appointmentId.equals(id)) {
                appointments[i].status = "Cancelled";
                totalRevenue -= appointments[i].doctor.consultationFee;
                return true;
            }
        }
        return false;
    }

    public void generateBill(Appointment a) {
        if (a != null && a.status.equals("Scheduled")) {
            System.out.println("\nBill for Appointment " + a.appointmentId);
            System.out.println("Patient: " + a.patient.patientName);
            System.out.println("Doctor: " + a.doctor.doctorName);
            System.out.println("Fee: " + a.doctor.consultationFee);
        }
    }

    public void dischargePatient(Patient p) {
        System.out.println("\nDischarging " + p.patientName);
        p.addHistory("Discharged on request");
        p.treatmentCount = 0; // clear current treatments
    }

    public void getDoctorUtilization() {
        System.out.println("\nDoctor Utilization:");
        for (int i = 0; i < dCount; i++) {
            Doctor d = doctors[i];
            System.out.println(d.doctorName + " handled " + d.patientsHandled + " patients.");
        }
    }

    public void getPatientStatistics() {
        System.out.println("\nPatient Statistics:");
        System.out.println("Total Patients: " + totalPatients);
        System.out.println("Total Appointments: " + totalAppointments);
        System.out.println("Total Revenue: " + totalRevenue);
    }
}

public class Q8_HospitalManagementSystem {
    public static void main(String[] args) {
        Hospital h = new Hospital(10, 5, 20);

        // Add doctors
        Doctor d1 = new Doctor("D1", "Dr. Smith", "Cardiology", 500);
        Doctor d2 = new Doctor("D2", "Dr. Alice", "Neurology", 700);
        h.addDoctor(d1);
        h.addDoctor(d2);

        // Add patients
        Patient p1 = new Patient("P1", "John", 30, "Male", "9999999999");
        Patient p2 = new Patient("P2", "Emma", 25, "Female", "8888888888");
        h.addPatient(p1);
        h.addPatient(p2);

        // Schedule appointments
        Appointment a1 = h.scheduleAppointment(p1, d1, "2025-09-10", "10:00 AM");
        Appointment a2 = h.scheduleAppointment(p2, d2, "2025-09-11", "11:00 AM");

        // Generate bills
        h.generateBill(a1);
        h.generateBill(a2);

        // Cancel one appointment
        h.cancelAppointment(a2.appointmentId);

        // Discharge a patient
        h.dischargePatient(p1);

        // Reports
        h.getDoctorUtilization();
        h.getPatientStatistics();
    }
}