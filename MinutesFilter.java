
/**
 * Write a description of MinutesFilter here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class MinutesFilter implements Filter {
   private int minMinutes;
   private int maxMinutes;  
      public MinutesFilter(int min,int max) {
		minMinutes = min;
		maxMinutes  = max;
	}
	
     @Override
      public boolean satisfies(String id) {
	return MovieDatabase.getMinutes(id)>=minMinutes
	    &&MovieDatabase.getMinutes(id)<=maxMinutes;
	}

}
