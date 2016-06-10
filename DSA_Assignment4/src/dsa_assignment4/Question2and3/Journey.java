package dsa_assignment4.Question2and3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Naomi
 */
public class Journey
{
    private LinkedList<BoatTrip> boatTripList;
    
    public Journey()
    {
        boatTripList = new LinkedList<BoatTrip>();
    }
    
    public Journey(List<BoatTrip> trips)
    {
        this.boatTripList = new LinkedList(trips);
    }
    
    public boolean addTrip(BoatTrip trip)
    {
        Iterator<BoatTrip> it = boatTripList.iterator();
        while(it.hasNext())
        {
            BoatTrip boatTrip = it.next();
            if(trip.arrivalPort.equals(boatTrip.arrivalPort))
            {
                return false;
            }
        }
        boatTripList.add(trip);
        return true;
    }
    
    public boolean removeLastTrip()
    {
        if(boatTripList.size() != 0)
        {
            boatTripList.remove(boatTripList.size()-1);
            return true;
        }
        return false;
    }
    
    public boolean containsPort(String port)
    {
        Iterator<BoatTrip> it = boatTripList.iterator();
        while(it.hasNext())
        {
            BoatTrip boatTrip = it.next();
            if(boatTrip.arrivalPort.equals(port))
            {
                return true;
            }
            else if(boatTrip.departPort.equals(port))
            {
                return true;
            }
        }
        return false;
    }
    
    public String getStartPort()
    {
        if(boatTripList.size() != 0)
        {
            return boatTripList.get(0).departPort;
        }
        return null;
    }
    
    public String getEndPort()
    {
        if(boatTripList.size() != 0)
        {
            return boatTripList.get(boatTripList.size() - 1).arrivalPort;
        }
        return null;
    }
    
    public String getEndDate()
    {
        if(boatTripList.size() != 0)
        {
            return boatTripList.get(boatTripList.size() - 1).arrivalDate;
        }
        return null;
    }
    
    public Journey createClone()
    {
        return new Journey(boatTripList);
    }
    
    public int getTotalJourneyCost()
    {
        Iterator<BoatTrip> it = boatTripList.iterator();
        int totalPrice = 0;
        while(it.hasNext())
        {
            BoatTrip boatTrip = it.next();
            totalPrice += boatTrip.cost;
        }
        return totalPrice;
    }
    
    public String toString()
    {
        Iterator<BoatTrip> it = boatTripList.iterator();
        String journeyString = "";
        while(it.hasNext())
        {
            journeyString += it.next().toString() + "\t\n";
        }
        return journeyString += "Total price is: " + getTotalJourneyCost();
    }
    
    public static void main(String args[])
    {
        Journey boatTripList = new Journey();
        
        BoatTrip bt1 = new BoatTrip("Trip1", "01062016","Auckalnd","02062016","Shanghai",1500);
        BoatTrip bt2 = new BoatTrip("Trip2", "10062016","QueensTown","11062016","Sydney",1500);
        BoatTrip bt3 = new BoatTrip("Trip3", "20062016","Nanjin","21062016","Tokyo",1500);
        
        boatTripList.addTrip(bt1);
        boatTripList.addTrip(bt2);
        boatTripList.addTrip(bt3);
        boatTripList.removeLastTrip();
        boatTripList.createClone();
        System.out.println((boatTripList.createClone()).toString());
        
        System.out.println(boatTripList);
    }
}
