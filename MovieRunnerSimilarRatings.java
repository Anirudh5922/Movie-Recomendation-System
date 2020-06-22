import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class MovieRunnerSimilarRatings {
   public void printAverageRatings(){
      ThirdRatings TR = new ThirdRatings("ratings_short.csv");
      System.out.println("Raters : "+TR.getRaterSize());
      MovieDatabase mdb = new MovieDatabase();
      mdb.initialize("ratedmovies_short.csv");
      ArrayList<String> movies = mdb.filterBy(new TrueFilter());
      System.out.println("Movies : "+movies.size());
      //TR = new ThirdRatings();
      long s,e,t;
      s = System.nanoTime();
      ArrayList<Rating> ratings = TR.getAverageRatings(1);
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
   
   public void printAverageRatingsByDirectorsAndMinutes(){
      FourthRatings FR = new FourthRatings();
      //System.out.println("Raters : "+TR.getRaterSize());
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
      ArrayList<Rating> ratings = FR.getAverageRatingsByFilter(1,alf);
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
    
   public void printSimilarRatings(){
      FourthRatings FR = new FourthRatings();
      ArrayList<Rating> ratings = FR.getSimilarRatings("65",20,5);
      System.out.println("Ratings : "+ratings.size());
      MovieDatabase mdb = new MovieDatabase();
      mdb.initialize("ratedmovies.csv");
      for(int k=0;k<Math.min(20,ratings.size());k++){
          Rating r = ratings.get(k);
          System.out.println((double)Math.round((100)*r.getValue())/100+" "
            +mdb.getTitle(r.getItem()));   
        }
    }
    
   public void printSimilarRatingsByGenre(){
      FourthRatings FR = new FourthRatings();
      GenreFilter gf = new GenreFilter("Action"); 
      ArrayList<Rating> ratings = FR.getSimilarRatingsByFilter("65",20,5,gf);
      System.out.println("Ratings : "+ratings.size());
      MovieDatabase mdb = new MovieDatabase();
      mdb.initialize("ratedmovies.csv");
      for(int k=0;k<Math.min(20,ratings.size());k++){
          Rating r = ratings.get(k);
          System.out.println((double)Math.round((100)*r.getValue())/100+" "
            +mdb.getTitle(r.getItem())+"\n"+mdb.getGenres(r.getItem()));   
        }
      System.out.println();  
    } 
    
   public void printSimilarRatingsByDirector(){
      FourthRatings FR = new FourthRatings();
      DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"); 
      ArrayList<Rating> ratings = FR.getSimilarRatingsByFilter("1034",10,3,df);
      System.out.println("Ratings : "+ratings.size());
      MovieDatabase mdb = new MovieDatabase();
      mdb.initialize("ratedmovies.csv");
      for(int k=0;k<Math.min(20,ratings.size());k++){
          Rating r = ratings.get(k);
          System.out.println((double)Math.round((100)*r.getValue())/100+" "
            +mdb.getTitle(r.getItem())+"\n"+mdb.getDirector(r.getItem()));   
        }
      System.out.println();  
    } 
    
   public void printSimilarRatingsByGenreAndMinutes(){
      FourthRatings FR = new FourthRatings();
      AllFilters alf = new AllFilters();
      alf.addFilter(new GenreFilter("Adventure"));
      alf.addFilter(new MinutesFilter(100,200));
      ArrayList<Rating> ratings = FR.getSimilarRatingsByFilter("65",10,5,alf);
      System.out.println("Ratings : "+ratings.size());
      MovieDatabase mdb = new MovieDatabase();
      mdb.initialize("ratedmovies.csv");
      for(int k=0;k<Math.min(20,ratings.size());k++){
          Rating r = ratings.get(k);
          System.out.println((double)Math.round((100)*r.getValue())/100+" "
            +mdb.getTitle(r.getItem())+"\n"+mdb.getGenres(r.getItem())+
            " "+mdb.getMinutes(r.getItem()));   
        }
      System.out.println(); 
    }
    
   public void printSimilarRatingsByYearAfterAndMinutes(){
     FourthRatings FR = new FourthRatings();
      AllFilters alf = new AllFilters();
      alf.addFilter(new YearAfterFilter(2000));
      alf.addFilter(new MinutesFilter(80,100));
      ArrayList<Rating> ratings = FR.getSimilarRatingsByFilter("65",10,5,alf);
      System.out.println("Ratings : "+ratings.size());
      MovieDatabase mdb = new MovieDatabase();
      mdb.initialize("ratedmovies.csv");
      for(int k=0;k<Math.min(20,ratings.size());k++){
          Rating r = ratings.get(k);
          System.out.println((double)Math.round((100)*r.getValue())/100+" "
            +mdb.getTitle(r.getItem())+"\n"+mdb.getYear(r.getItem())+
            " "+mdb.getMinutes(r.getItem()));   
        }
      System.out.println(); 
    } 
    
}
