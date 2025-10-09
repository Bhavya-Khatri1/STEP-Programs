import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

public class Employee
{
    private String empCode;
    private String name;
    
    public Employee(String empCode,String name)
    {
        this.empCode=empCode;
        this.name=name;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        if(!(o instanceof Employee))
        return false;
        Employee other=(Employee) o;
        return Objects.equals(this.empCode,other.empCode);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(empCode);
    }
    
    @Override
    public String toString()
    {
        return "Employee{"+"empCode='"+empCode+'\''+", name='"+name+'\''+'}';
    }
}