package hw1;


// TODO: complete the methods
/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @object type Immutable Data Class
 * @object invariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @object invariant
 *   Year is greater than 1800, less than 5000.
 * @object invariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable<VideoObj> {
  /** @object invariant non-null, no leading or final spaces, not empty string */
  private final String _title;
  /** @object invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @object invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
   * 
   */
  
  VideoObj(String title, int year, String director) {
    // TODO
	if (  (title == null)
		|| (director == null)
		|| (year <= 1800)
		|| (year >= 5000)) {
		throw new IllegalArgumentException();
	}
	else if(title.equals("") || director.equals("")){
		throw new IllegalArgumentException();
	}
//	else if(String.valueOf(title.charAt(0)).equals(" ")
//			|| title.substring(title.length() - 1).equals(" ")){
//		throw new IllegalArgumentException();
//	}
//	else if(String.valueOf(director.charAt(0)).equals(" ")
//			|| director.substring(director.length() - 1).equals(" ")){
//		throw new IllegalArgumentException();
//	}
	else if(title.equals(" ") || director.equals(" ")){
		throw new IllegalArgumentException();
	}
    this._title = title.trim();
    this._year = year;
    this._director = director.trim();

  }

  /**
   * Return the value of the attribute.
   */
  public String director() {
    // TODO
    return _director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    // TODO
    return _title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    // TODO
    return _year;
  }

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
  public boolean equals(Object thatObject) {
    // TODO
	  if(thatObject == null)
		  return false;
	  if (this == thatObject)
	      return true;
	    // equals should not throw ClassCastException
	    if (!(this.getClass().equals(thatObject.getClass())))
	      return false;
	    
	    VideoObj that = (VideoObj) thatObject;
	    return _title.equals(that._title)
	        && _director.equals(that._director)
	        && _year == that._year;
  }

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  private int hcode;
  public int hashCode() {
    // TODO
	  if (hcode == 0) {
	      hcode = 17;
	      hcode = 37*hcode + _title.hashCode();
	      hcode = 37*hcode + Integer.valueOf(_year).hashCode();
	      hcode = 37*hcode + _director.hashCode();
	    }
	  return hcode;
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param that the VideoObj to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater than that object.
   */
  public int compareTo(VideoObj that) {
    // TODO
	  int ititle = _title.compareTo(that._title);
	  if (ititle != 0)
	     return ititle;
	  else if(_year != that._year){
		  return _year-that._year;
	  }
	  return _director.compareTo(that._director);
  }

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
    // TODO
    StringBuilder buffer = new StringBuilder();
	buffer.append(_title);
	buffer.append(" (");
	buffer.append(_year);
	buffer.append(") : ");
	buffer.append(_director);
	return buffer.toString();
  }
  
}


