import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie>movies = new  ArrayList<Movie>();
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser(); 
        for(CSVRecord rec : parser){
           Movie m = new Movie(rec.get("id"),rec.get("title"),rec.get("year"),
           rec.get("genre"),rec.get("director"),rec.get("country"),
           rec.get("poster"),Integer.parseInt(rec.get("minutes")));
           movies.add(m);
        }
        return movies;
    }  
    
    public void testLoadMovies(){
       ArrayList<Movie>movies = loadMovies("ratedmoviesfull.csv");
       int count1=0;
       int count2=0;
       for(Movie m:movies){
         if(m.getGenres().contains("Comedy"))
            count1++;
         if(m.getMinutes()>150)
            count2++;
        }
        
       System.out.println("Size : "+movies.size()+"\n Count : "+count1
             +"\n Numbers : "+count2); 
       HashMap<String,Integer>Director= new HashMap<String,Integer>();
       for(Movie m:movies){
          if(Director.containsKey(m.getDirector()))
             Director.put(m.getDirector(),Director.get(m.getDirector())+1);
          else
             Director.put(m.getDirector(),1); 
        }
       int max = 0; 
       ArrayList<String> Dmax = new ArrayList<String>();
       for(String D:Director.keySet()){
           if(Director.get(D)>max)
              max = Director.get(D);
        } 
       
       for(String D:Director.keySet()){
           if(Director.get(D)==max)
              Dmax.add(D);
        }  
       System.out.println(" Director with size "+max +" : "+Dmax); 
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater>raters = new  ArrayList<Rater>();
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        String prevId = null;
        Rater R=null;
        for(CSVRecord rec : parser){
           String Id = rec.get("rater_id");
           if(!Id.equals(prevId))
             { //prevR = R;
               if(prevId!=null)  
                  raters.add(R);  
               R = new EfficientRater(Id);  
                }
           R.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
           prevId = Id;
        }
        raters.add(R);
        return raters;
    }  
    
    public void testLoadRaters(){
       ArrayList<Rater>raters  = loadRaters("ratings.csv");
       for(Rater r: raters){
        if(r.getID().equals("193"))
           System.out.println(r.numRatings());
        }
       int max=0;
       for(Rater r:raters){
          //System.out.println(r);
          if(max<r.numRatings())
             max= r.numRatings();
        }
       ArrayList<Rater>rmax = new ArrayList<Rater>();
       for(Rater r:raters){
          //System.out.println(r);
          if(max==r.numRatings())
             rmax.add(r);
        }
       System.out.println("Maximum size is "+max+" and it has "+rmax.size()
             +" : "+rmax);
       System.out.println();
      ArrayList<Rater>rp = new ArrayList<Rater>(); 
       for(Rater r:raters){
          if(r.hasRating("1798709"))
               rp.add(r);
        }
      System.out.println("Rated by "+rp.size()+" rater : "+rp);
      HashSet<String>difmov = new HashSet<String>();
       for(Rater r: raters){
          ArrayList<String> rat = r.getItemsRated();
          for(String s:rat){
               if(!difmov.contains(s)){
                   difmov.add(s);
                }
            }
        }
       
      System.out.println("Total no. of movies are : "+difmov.size());  
    }
}
