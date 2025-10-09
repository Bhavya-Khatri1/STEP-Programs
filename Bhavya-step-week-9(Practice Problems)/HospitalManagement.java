public class HospitalManagement {
    public static void main(String[] args) {
        Hospital h = new Hospital("City Hospital");
        Hospital.Department d1 = h.new Department("Cardiology");
        d1.display();

        Hospital.Department d2 = h.createDepartment("Neurology");
        d2.display();
    }
}