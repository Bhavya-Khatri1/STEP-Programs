public class Vehicle
{
    private String registrationNo;
    private String type;
    private double ratePerDay;
    
    public Vehicle(String registrationNo,String type,double ratePerDay)
    {
        this.registrationNo=registrationNo;
        this.type=type;
        this.ratePerDay=ratePerDay;
    }
    
    @Override
    public String toString()
    {
        return "Vehicle: "+registrationNo+", Type: "+type+", Rate: $" +ratePerDay+"/day";
    }
    
    public String getRegistraion()
    {
        return registrationNo;
    }
    public String getType()
    {
        return type;
    }
    public double getRate()
    {
        return ratePerDay;
    }
}