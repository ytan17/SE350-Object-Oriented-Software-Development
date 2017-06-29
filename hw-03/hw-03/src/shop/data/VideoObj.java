package shop.data;


/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   */
  VideoObj(String title, int year, String director) {
	  if (  (title == null)
				|| (director == null)
				|| (year <= 1800)
				|| (year >= 5000)) {
				throw new IllegalArgumentException();
	}
	else if(title.equals("") || director.equals("")){
		throw new IllegalArgumentException();
	}
	else if(title.equals(" ") || director.equals(" ")){
		throw new IllegalArgumentException();
	}
    this._title = title.trim();
    this._year = year;
    this._director = director.trim();
  }

  public String director() {
    // TODO
    return _director;
  }

  public String title() {
    // TODO
    return _title;
  }

  public int year() {
    // TODO
    return _year;
  }

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

  public int compareTo(Video that) {
    // TODO
	  int ititle = _title.compareTo(that.title());
	  if (ititle != 0)
	     return ititle;
	  else if(_year != that.year()){
		  return _year-that.year();
	  }
	  return _director.compareTo(that.director());
  }

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
