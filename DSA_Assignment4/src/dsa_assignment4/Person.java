package dsa_assignment4;

/**
 *
 * @author Naomi
 */
public class Person 
{
    private String name;
    private String phoneNumber;
    private int age;

    public String getName() 
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }
    
    
    @Override
    public int hashCode() 
    {
        
        return name.hashCode();
    }
    
    
    
    
    
}
