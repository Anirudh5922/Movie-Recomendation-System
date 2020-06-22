import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author Anirudh Mahehswari
 * @version 1.0
 */
public class FourthRatings {
    
    private double getAverageByID(String id,int minimalRaters){
       RaterDatabase rdb = new RaterDatabase();
       rdb.initialize("ratings_short.csv");
       ArrayList<Rater>raters = rdb.getRaters();
       int count=0;
       double sum=0;
       double avg=0;
        for(Rater R: raters ){
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
       ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       ArrayList<Rating>ratings = new ArrayList<Rating>();
       for(String id: movies){
          double avg = getAverageByID(id,minimalRaters);
          if(avg>0){
             Rating r = new Rating(id,avg);
             ratings.add(r);
            }
             
        }
       return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria) {
      ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
      ArrayList<Rating>ratings = new ArrayList<Rating>();
      for(String id: movies){
          double avg = getAverageByID(id,minimalRaters);
          if(avg>0){
             Rating r = new Rating(id,avg);
             ratings.add(r);
            }
             
        }
       return ratings;
    }
    
    private double dotProduct(Rater me,Rater r){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        double dp=0;
        for(String m:movies){
           if(me.hasRating(m)&&r.hasRating(m))
               dp += (me.getRating(m)-5)*(r.getRating(m)-5);
        }
        return dp;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
       ArrayList<Rating>ret = new ArrayList<Rating>();
       RaterDatabase rdb = new RaterDatabase();
       rdb.initialize("ratings.csv");
       Rater me = rdb.getRater(id);
       Rating rating=null;
       ArrayList<Rater>raters = rdb.getRaters();
       for(Rater r: raters){
          if(!r.getID().equals(id))
              {  double d = dotProduct(me,r);
                 if(d>0){
                   rating = new Rating(r.getID(),d);
                   ret.add(rating);
                 }
               }
        }
       Collections.sort(ret,Collections.reverseOrder()); 
       return ret;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
      ArrayList<Rating>ret = new ArrayList<Rating>();
      ArrayList<Rating>simRat = getSimilarities(id);
      ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
      RaterDatabase rdb = new RaterDatabase();
      rdb.initialize("ratings.csv");
      int size = Math.min(simRat.size(),numSimilarRaters);
      Rating rating=null;
      for(String m:movies){
         double total = 0;
         double denominator = 0;
         int count = 0;
         for(int k=0;k<size;k++){
             Rating r = simRat.get(k);
             Rater rat = rdb.getRater(r.getItem());
             if(rat.hasRating(m))
            {total += rat.getRating(m)*r.getValue();
              denominator += r.getValue();
              count++;
            }
            }
         if(count>=minimalRaters){
         double wavg = total/denominator;
         rating = new Rating(m,wavg);
         ret.add(rating);
        }
        }
      Collections.sort(ret,Collections.reverseOrder());  
      return ret;  
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filterCriteria){
      ArrayList<Rating>ret = new ArrayList<Rating>();
      ArrayList<Rating>simRat = getSimilarities(id);
      ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
      RaterDatabase rdb = new RaterDatabase();
      rdb.initialize("ratings.csv");
      int size = Math.min(simRat.size(),numSimilarRaters);
      Rating rating=null;
      for(String m:movies){
         double total = 0;
         double denominator = 0;
         int count = 0;
         for(int k=0;k<size;k++){
             Rating r = simRat.get(k);
             Rater rat = rdb.getRater(r.getItem());
             if(rat.hasRating(m))
            {total += rat.getRating(m)*r.getValue();
              denominator += r.getValue();
              count++;
            }
            }
         if(count>=minimalRaters){
         double wavg = total/denominator;
         rating = new Rating(m,wavg);
         ret.add(rating);
        }
        }
      Collections.sort(ret,Collections.reverseOrder());  
      return ret;   
    }
    
}
