package dsa_assignment4.Question1;

/**
 *
 * @author Naomi
 */
public class Person 
{
    private String name;
    private String phoneNumber;
    private int age;
    
    public Person()
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }
    
    public Person(String name, String phoneNumber, int age)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }
    
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
        char[] letters = name.toCharArray();
        int hashcode = 0;
        for(int i=0; i< letters.length; i++)
        {
            hashcode += (int)letters[i];
        }
        return hashcode;
    }

}
