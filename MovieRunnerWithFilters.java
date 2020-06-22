import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class MovieRunnerWithFilters {
   public void printAverageRatings(){
      FourthRatings FR = new FourthRatings();
      //System.out.println("Raters : "+FR.getRaterSize());
      MovieDatabase mdb = new MovieDatabase();
      mdb.initialize("ratedmovies_short.csv");
      ArrayList<String> movies = mdb.filterBy(new TrueFilter());
      System.out.println("Movies : "+movies.size());
      //TR = new ThirdRatings();
      long s,e,t;
      s = System.nanoTime();
      ArrayList<Rating> ratings = FR.getAverageRatings(1);
      Collections.sort(ratings);
      for(Rating r:ratings){
         System.out.println((double)Math.round((100)*r.getValue())/100+" "
            +mdb.getTitle(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
    }
    
   public void printAverageRatingsByYear(){
      FourthRatings FR = new FourthRatings();
      //System.out.println("Raters : "+TR.getRaterSize());
      MovieDatabase mdb = new MovieDatabase();
      long s,e,t;
      s = System.nanoTime();
      mdb.initialize("ratedmovies_short.csv");
      ArrayList<String> movies = mdb.filterBy(new YearAfterFilter(2000));
      System.out.println("Movies : "+movies.size());
      //TR = new ThirdRatings();
      ArrayList<Rating> ratings = FR.getAverageRatingsByFilter(1,new YearAfterFilter(2000));
      Collections.sort(ratings);
      for(Rating r:ratings){
         System.out.println((double)Math.round((100)*r.getValue())/100+" "
           +mdb.getYear(r.getItem())+" "+mdb.getTitle(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
    }
    
   public void printAverageRatingsByGenre(){
      ThirdRatings TR = new ThirdRatings("ratings_short.csv");
      System.out.println("Raters : "+TR.getRaterSize());
      MovieDatabase mdb = new MovieDatabase();
      long s,e,t;
      s = System.nanoTime();
      mdb.initialize("ratedmovies_short.csv");
      ArrayList<String> movies = mdb.filterBy(new GenreFilter("Crime"));
      System.out.println("Movies : "+movies.size());
      //TR = new ThirdRatings();
      ArrayList<Rating> ratings = TR.getAverageRatingsByFilter(1,new GenreFilter("Crime"));
      Collections.sort(ratings);
      for(Rating r:ratings){
         System.out.println((double)Math.round((100)*r.getValue())/100+" "
         +mdb.getTitle(r.getItem())+"\n"+mdb.getGenres(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
    
    }
   
   public void printAverageRatingsByMinutes(){
      ThirdRatings TR = new ThirdRatings("ratings_short.csv");
      System.out.println("Raters : "+TR.getRaterSize());
      MovieDatabase mdb = new MovieDatabase();
      long s,e,t;
      s = System.nanoTime();
      mdb.initialize("ratedmovies_short.csv");
      ArrayList<String> movies = mdb.filterBy(new MinutesFilter(110,170));
      System.out.println("Movies : "+movies.size());
      //TR = new ThirdRatings();
      ArrayList<Rating> ratings = TR.getAverageRatingsByFilter(1,new MinutesFilter(110,170));
      Collections.sort(ratings);
      for(Rating r:ratings){
         System.out.println((double)Math.round((100)*r.getValue())/100+" "
         +mdb.getMinutes(r.getItem())+"  "+mdb.getTitle(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
    } 
    
   public void printAverageRatingsByDirectors(){
      ThirdRatings TR = new ThirdRatings("ratings_short.csv");
      System.out.println("Raters : "+TR.getRaterSize());
      MovieDatabase mdb = new MovieDatabase();
      long s,e,t;
      s = System.nanoTime();
      mdb.initialize("ratedmovies_short.csv");
      String d =  "Charles Chaplin,Michael Mann,Spike Jonze";
      ArrayList<String> movies = mdb.filterBy(new DirectorsFilter(d));
      System.out.println("Movies : "+movies.size());    
      //TR = new ThirdRatings();
      ArrayList<Rating> ratings = TR.getAverageRatingsByFilter(1,new DirectorsFilter(d));
      Collections.sort(ratings);
      for(Rating r:ratings){
         System.out.println((double)Math.round((100)*r.getValue())/100+" "
         +mdb.getTitle(r.getItem())+"\n"+mdb.getDirector(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
    } 
    
   public void printAverageRatingsByDirectorsAndMinutes(){
      ThirdRatings TR = new ThirdRatings("ratings_short.csv");
      System.out.println("Raters : "+TR.getRaterSize());
      MovieDatabase mdb = new MovieDatabase();
      String d =  "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
      AllFilters alf = new AllFilters();      
      alf.addFilter(new MinutesFilter(30,170));
      alf.addFilter(new DirectorsFilter(d));
      long s,e,t;
      s = System.nanoTime();
      mdb.initialize("ratedmovies_short.csv");
      ArrayList<String> movies = mdb.filterBy(alf);
      System.out.println("Movies : "+movies.size());    
      //TR = new ThirdRatings();
      ArrayList<Rating> ratings = TR.getAverageRatingsByFilter(1,alf);
      Collections.sort(ratings);
      for(Rating r:ratings){
         System.out.println((double)Math.round((100)*r.getValue())/100+" "
         +mdb.getMinutes(r.getItem())+" "
         +mdb.getTitle(r.getItem())+"\n"+mdb.getDirector(r.getItem()));   
        }
      e=System.nanoTime();
      t = e-s; 
      System.out.println(t/1e9);
    } 
}


