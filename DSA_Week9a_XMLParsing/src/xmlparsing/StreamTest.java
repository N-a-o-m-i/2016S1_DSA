package xmlparsing;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author Naomi
 */
public class StreamTest 
{
    private static class Person
    {
        String name;
        int age;
        float weight;
        
        public Person(String name, int age, float weight)
        {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        public int getAge() 
        {
            return age;
        }

        public String getName() 
        {
            return name;
        }

        public float getWeight() 
        {
            return weight;
        }

        public void setAge(int age) 
        {
            this.age = age;
        }

        public void setName(String name) 
        {
            this.name = name;
        }

        public void setWeight(float weight) 
        {
            this.weight = weight;
        }

        @Override
        public String toString() 
        {
            return super.toString(); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
    public static void main(String[] args)
    {
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(new Person("111", 19, 120.5f));
        list.add(new Person("222", 45, 150.3f));
        list.add(new Person("333", 63, 160.3f));
        list.add(new Person("444", 12, 80.2f));
        list.add(new Person("555", 37, 110.9f));
        list.add(new Person("666", 28, 108.4f));
        list.add(new Person("777", 23, 103.5f));
        list.add(new Person("888", 26, 105.0f));
        list.add(new Person("999", 25, 99.3f));
        list.add(new Person("101010", 27, 115.9f));
        
        try 
        {
            //obtain output stream to file and layer with data output stream
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("personOutput.txt"));
            dos.writeInt(list.size());
            for(Person person:list)
            {
                //write the name as UTF string, int for age and float for weight to file
                dos.writeUTF(person.getName());
                dos.writeInt((int)person.getAge());
                dos.writeFloat((float)person.getWeight());
                dos.writeUTF("\n");
            }
            dos.flush();
            dos.close();
            
            
            
        } 
        catch (IOException e) 
        {
            System.out.println("Data output stream problem writing to data file");
        }
        
        ArrayList<Person> personList = new ArrayList<Person>();
        try 
        {
            //obtain input stream to file and layer with a data input stream
            DataInputStream dis = new DataInputStream(new FileInputStream("peopleOutput.txt"));
            //read in first the amount of Person objects held in file
            int numElements = dis.readInt();
            //perform a loop reading in each Person object(protocol of file must match)
            for(int i=0; i<numElements; i++)
            {
                //read in name(UTF string), age(int) and weight(float) from file stream
                //Order is very important otherwise data gets corrupted.
                //Create Person Object
                Person p = new Person(dis.readUTF(), dis.readInt(), dis.readFloat());
                personList.add(p);
                System.out.println(personList);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Data input stream problem writing to data file");
        }
        
    }
 
}
