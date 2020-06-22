
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */

import java.util.*;



public class RecommendationRunner implements Recommender {

    

    public ArrayList<String> getItemsToRate () {

        ArrayList<String> itemsToRate = new ArrayList<String> ();

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for (int i=0; i < 20; i++) {

            Random rand = new Random();

            int random = rand.nextInt(movies.size());

            if (! itemsToRate.contains(movies.get(random))) {

                itemsToRate.add(movies.get(random));

            }

        }

        return itemsToRate;

    }

    

    public void printRecommendationsFor (String webRaterID,Filter filter) {

        FourthRatings fourthRatings = new FourthRatings ();

        MovieDatabase.initialize("ratedmoviesfull.csv");

        RaterDatabase.initialize("ratings.csv");

        

        System.out.println("<h3>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</h3>");

        System.out.println("<h3>Read data for " + Integer.toString(MovieDatabase.size()) + " movies</h3>");

        

        int numSimilarRaters = 50; // variable

        int minNumOfRatings = 5; // variable

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(webRaterID, 
               numSimilarRaters, minNumOfRatings,filter);

            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th>  <th>Genres</th> </tr>\n");

            String body = "";
           HashSet<String>ratingsSeen = new HashSet<String>();
            for (Rating rating : similarRatings) {

                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td> <td>" 

                + Double.toString(rating.getValue()) + "</td> <td>" + MovieDatabase.getGenres(rating.getItem())

                + "</td> </tr> \n";
                ratingsSeen.add(rating.getItem());

            }

          if(similarRatings.size()<40){
             //ArrayList<String> movies = MovieDatabase.filterBy(filter);
             FourthRatings FR = new FourthRatings();
             ArrayList<Rating> ratings = FR.getAverageRatingsByFilter(1,filter);
             Collections.sort(ratings,Collections.reverseOrder());
             int j=0;
             for(int k=0;k<ratings.size();k++){
                if(!ratingsSeen.contains(ratings.get(k).getItem()))
                {  body += "<tr> <td>" + MovieDatabase.getTitle(ratings.get(k).getItem()) + "</td> <td>" 

                  + Double.toString(ratings.get(k).getValue()) + "</td> <td>" + MovieDatabase.getGenres(ratings.get(k).getItem())

                  + "</td> </tr>\n ";  
                  j++;
                }
                if(j>40-similarRatings.size())
                   break;
                }
               
           } 
            System.out.println(header  + body + "</table>\n");
            

        }

    }


