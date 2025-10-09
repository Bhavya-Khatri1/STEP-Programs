import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class EmployeeAuth
{
    public static void main(String args[])
    {
        Employee e1=new Employee("BL001","Ritika");
        Employee e2=new Employee("BL001","Ritika S.");
        System.out.println("e1==e2: "+(e1==e2));
        System.out.println("e1.equals(e2): "+e1.equals(e2));
        
        Set<Employee> set=new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println("HashSet size (should be 1 if equals/hashcode correct): "+set.size());
        System.out.println("Set contents: "+set);
    }
}