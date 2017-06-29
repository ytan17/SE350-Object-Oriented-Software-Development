
package hw1;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

// TODO: complete the methods
/**
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * @objecttype Mutable Collection of Records
 * @objectinvariant
 *   Every key and value in the map is non-<code>null</code>.
 * @objectinvariant
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 */
final class InventorySet {
  /** @invariant <code>_data != null</code> */
  private final Map<VideoObj,Record> _data = new HashMap<VideoObj,Record>();

  InventorySet() { }

  /**
   * Return the number of Records.
   */
  public int size() {
    // TODO
    return _data.size();
  }

  /**
   *  Return a copy of the record for a given Video; if not present, return <code>null</code>.
   */
  public Record get(VideoObj v) {
    // TODO
    Record r = _data.get(v);
    if(r!=null)
    	return r.copy();
    else return null;
  }

  /**
   * Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection<Record> toCollection() {
    // Recall that an ArrayList is a Collection.
    // TODO
    ArrayList<Record> copyOfRecords = new ArrayList<Record> (_data.size());
    for (Record r : _data.values()){
    	copyOfRecords.add(r.copy());
    }
    return copyOfRecords;
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be zero,
   * the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null, change is zero,
   *  if attempting to remove more copies than are owned, or if
   *  attempting to remove copies that are checked out.
   * @postcondition changes the record for the video
   */
  public void addNumOwned(VideoObj video, int change) {
    // TODO
	  Record r = _data.get(video);
	  if((video == null || change == 0)){
		  throw new IllegalArgumentException();
	  }
	  else{
		  if(r == null && change < 1){
			  throw new IllegalArgumentException();
		  }
		  else if(r == null){
			  Record newR = new Record(video,change,0,0);
			  _data.put(video, newR);
		  }
		  else if(r != null){
//			  r.numOwned += change;
			  if(r.numOwned+change < r.numOut) {
				  throw new IllegalArgumentException();
			  }
			  else if(r.numOwned+change < 1){
				  _data.remove(video);
			  }
			  else{
				  Record updatedR = new Record(video,r.numOwned+change,r.numOut,r.numRentals);
				  _data.put(video, updatedR);
			  }
		  }
	  }
	  
	  
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * @postcondition changes the record for the video
   */
  public void checkOut(VideoObj video) {
    // TODO
	  Record r = _data.get(video);
	  if(r == null ||(r.numOut == r.numOwned)){
		  throw new IllegalArgumentException();
	  }
	  else {
		  r.numOut +=1;
		  r.numRentals +=1;
		  Record updatedR = new Record(video, r.numOwned,r.numOut,r.numRentals);
		  _data.put(video, updatedR);
	  }
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * @postcondition changes the record for the video
   */
  public void checkIn(VideoObj video) {
    // TODO
	  Record r = _data.get(video);
	  if(r == null ||(r.numOut == 0)){
		  throw new IllegalArgumentException();
	  }
	  else {
		  r.numOut -=1;
		  //r.numRentals -=1;
		  Record updatedR = new Record(video, r.numOwned,r.numOut,r.numRentals);
		  _data.put(video, updatedR);
	  }
  }
  
  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
    // TODO
	  _data.clear();
  }

  /**
   * Return the contents of the inventory as a string.
   */
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append("Database:\n");
    for (Record r : _data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }
}
