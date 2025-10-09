public class Student implements Cloneable {
    public String id;
    public String name;
    public ContactInfo contact;

    public Student(String id, String name, ContactInfo contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    public Student deepClone() throws CloneNotSupportedException {
        Student s = (Student) super.clone();
        s.contact = this.contact.clone();
        return s;
    }

    @Override
    public String toString() {
        return "Student{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", contact=" + contact + '}';
    }
}