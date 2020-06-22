
/**
 * Write a description of DirectorsFilter here.
 * 
* @author Anirudh Maheshwari 
 * @version 1.0
 */
public class DirectorsFilter implements Filter{
     private String myDirector;
     
      public DirectorsFilter(String Director) {
		myDirector = Director;
	}
	
     @Override
      public boolean satisfies(String id) {
	return myDirector.contains(MovieDatabase.getDirector(id));
	}
}
