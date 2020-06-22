import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
* @author Anirudh Maheshwari 
 * @version 1.0
 */
public class MovieRunnerAverage {
   public void printAverageRatings(){
      SecondRatings SR = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
      System.out.println("Movies : "+SR.getMovieSize()+"  Raters : "+SR.getRaterSize());
      SR = new SecondRatings();
      long s,e,t;
      s = System.nanoTime();
      ArrayList<Rating> ratings = SR.getAverageRatings(3);
      Collections.sort(ratings);
      for(Rating r:ratings){
         System.out.println((double)Math.round((100)*r.getValue())/100+" "+SR.getTitle(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
    }
   
   public void getAverageRatingOneMovie(){
      SecondRatings SR = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
      SR = new SecondRatings();
      long s,e,t;
      s = System.nanoTime();
      ArrayList<Rating> ratings = SR.getAverageRatings(3);
      String id  = SR.getID("The Godfather");
      for(Rating r:ratings){
         if(r.getItem().equals(id))
          System.out.println((double)Math.round((100)*r.getValue())/100+" "+SR.getTitle(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
      System.out.println();
    }
    
}
