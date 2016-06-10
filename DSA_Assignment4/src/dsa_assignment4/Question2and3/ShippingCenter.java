package dsa_assignment4.Question2and3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Naomi
 */
public class ShippingCenter
{
    private Connection conn;
    private Statement stmt;
    private List<Journey> journeyList;
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String DB_URL="jdbc:mysql://raptor2.aut.ac.nz:3306/testRestricted";
    
    public ShippingCenter(String userName, String password)
    {
        try 
        {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, userName, password);
        }
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(ShippingCenter.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ShippingCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> readAllPorts()
    {
        List<String> ports = new LinkedList<String>();
        try  
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT portName FROM Ports");
            while(rs.next()){
                ports.add(rs.getString(1));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ShippingCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ports;
    }
    
    public List<Journey> getAllJourneys(String startPort, String startDate, String endPort)
    {
        return null;
    }
    
    private void findPaths(Journey currentJourney, String startDate, String startPort, String endPort)
    {
        
    }
    
    public void close()
    {
        try 
        {
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShippingCenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the username");
        String userName = input.nextLine();
        System.out.println("Please enter the password");
        String password = input.nextLine();
        ShippingCenter sc = new ShippingCenter(userName, password);
        System.out.println(sc.readAllPorts());
    }
    
}
