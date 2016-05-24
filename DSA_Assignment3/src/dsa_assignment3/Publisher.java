package dsa_assignment3;

/**
 *
 * @author Naomi
 */
/**
   Class that represents a publisher holding a String name and
   String web site.
*/
public class Publisher
{
   private String name, webSite;
   
   /**
      Publisher constructor that creates a publisher with a given
      name and web site.
   */
   public Publisher(String name, String webSite)
   {  this.name = name;
      this.webSite = webSite;
   }
   
   /**
      Returns the name of the publisher.
   */
   public String getName()
   {  return name;
   }
   
   /**
      Changes the name of the publisher.
   */
   public void setName(String name)
   {  this.name = name;
   }
   
   /**
      Returns the web site of the publisher.
   */
   public String getWebSite()
   {  return webSite;
   }
   
   /**
      Changes the web site of the publisher.
   */
   public void setWebSite(String webSite)
   {  this.webSite = webSite;
   }
   
   /**
      Returns a string representation of the publisher.
   */
   public String toString()
   {  return name + "(" + webSite + ")";
   }
}