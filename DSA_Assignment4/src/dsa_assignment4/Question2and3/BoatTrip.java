package dsa_assignment4.Question2and3;

/**
 *
 * @author Naomi
 */
public class BoatTrip 
{
    public String boatID;
    public String departPort;
    public String arrivalPort;
    public String departDate;
    public String arrivalDate;
    public int cost;

    public BoatTrip(String boatID,String departPort,String departDate,
            String arrivalPort, String arrivalDate, int cost)
    {
        this.boatID = boatID;
        this.departDate = departDate;
        this.departPort = departPort;
        this.arrivalDate = arrivalDate;
        this.arrivalPort = arrivalPort;
        this.cost = cost;
    }
    
    @Override
    public String toString()
    {
        return "BoatID: "+boatID+" from "+departPort+" at "+departDate+" TO "+arrivalPort+" at "+arrivalDate+" COST = "+cost;
    }

  
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof BoatTrip)
        {
            BoatTrip trip = (BoatTrip)o;
            if(trip.arrivalPort.equals(arrivalPort) && trip.arrivalDate.equals(arrivalDate))
            {
                return trip.boatID.equals(boatID);
            }
            else return false;
        }
        else return false;
    }
}