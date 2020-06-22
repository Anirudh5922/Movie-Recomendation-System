
/**
 * Write a description of SecondRatings here.
 * 
* @author Anirudh Maheshwari 
 * @version 1.0
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        this("ratedmoviesfull.csv","ratings.csv");
     }
    
    public SecondRatings(String moviefile,String ratingfile){
       FirstRatings FR = new FirstRatings();
        myMovies = FR.loadMovies(moviefile);
        myRaters = FR.loadRaters(ratingfile);
    } 
    
    public int getMovieSize(){
        return myMovies.size();
    } 
    
    public int getRaterSize(){
        return myRaters.size();
    } 
    
    private double getAverageByID(String id,int minimalRaters){
       int count=0;
       double sum=0;
       double avg=0;
        for(Rater R: myRaters ){
           if(R.hasRating(id)){
              count++;
              sum += R.getRating(id);
            }
               
        }
       if(count>=minimalRaters)
          avg = sum/count;
       return avg;
    } 
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
       ArrayList<Rating>ratings = new ArrayList<Rating>();
       for(Movie m: myMovies){
          double avg = getAverageByID(m.getID(),minimalRaters);
          if(avg>0){
             Rating r = new Rating(m.getID(),avg);
             ratings.add(r);
            }
             
        }
       return ratings;
    }
    
    public String getTitle(String id){
       for(Movie m:myMovies){
          if(m.getID().equals(id))
             return m.getTitle();
        }
       return "NO SUCH TITLE."; 
    }
    
    public String getID(String title){
       for(Movie m:myMovies){
          if(m.getTitle().equals(title))
             return m.getID();
        }
       return "Title was not found"; 
    }
    
}
