package dsa_assignment4.Question1;

import dsa_assignment4.Question1.Person;

/**
 *
 * @author Naomi
 */
public class HashTableTest 
{
    public static void main(String args[])
    {
        Person person1 = new Person("Naomi","0220239403",22);
        Person person2 = new Person("a","aaa",22);
        Person person3 = new Person("h","bbb",23);
        Person person4 = new Person("g","ccc",24);
        Person person5 = new Person("aa","ddd",25);
        Person person6 = new Person("e","eee",26);
        Person person7 = new Person("f","fff",27);
        Person person8 = new Person("g","ggg",28);
        Person person9 = new Person("h","hhh",29);
        Person person10 = new Person("i","iii",30);
        
        HashTableWithChaining ht = new HashTableWithChaining();
        ht.add(person1.hashCode(), person1.getName());
        ht.add(person2.hashCode(), person2.getName());
        ht.add(person3.hashCode(), person3.getName());
        ht.add(person4.hashCode(), person4.getName());
        ht.add(person5.hashCode(), person5.getName());
        ht.add(person6.hashCode(), person6.getName());
        ht.add(person7.hashCode(), person7.getName());
        ht.add(person8.hashCode(), person8.getName());
        ht.add(person9.hashCode(), person9.getName());
        ht.add(person10.hashCode(), person10.getName());
        System.out.println(ht.output());
        ht.remove(person10.hashCode());
        System.out.println(ht.output());
        System.out.println("Is the table contain person8? " + ht.contains(person8.hashCode()));
        ht.clear();
        System.out.println("\t\n" + "clear done" + "\t\n" + ht.output() );
        
    }
}
