import java.util.*;
/**
 * Write a description of GenreFilter here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */
public class GenreFilter implements Filter{
     private String myGenre;
     
      public GenreFilter(String genre) {
		myGenre = genre;
	}
	
     @Override
      public boolean satisfies(String id) {
	return MovieDatabase.getGenres(id).contains(myGenre);
	}

}
