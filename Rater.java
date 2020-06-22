import java.util.*;
/**
 * Write a description of Rater here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public interface Rater {
   public void addRating(String item, double rating);
   
   public boolean hasRating(String item);
   
   public String getID();
   
   public double getRating(String item);
   
   public int numRatings();
   
   public String toString();
   
   public ArrayList<String> getItemsRated();
}
