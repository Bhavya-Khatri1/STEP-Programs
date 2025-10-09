public class ContactInfo implements Cloneable {
    public String email;
    public String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    @Override
    public ContactInfo clone() {
        return new ContactInfo(this.email, this.phone);
    }

    @Override
    public String toString() {
        return "ContactInfo{" + "email='" + email + '\'' + ", phone='" + phone + '\'' + '}';
    }
}