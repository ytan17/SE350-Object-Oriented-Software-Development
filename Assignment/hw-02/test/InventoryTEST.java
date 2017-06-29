import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }

  InventorySet s = new InventorySet();
  final VideoObj v1 = new VideoObj( "A", 2000, "B" );
  final VideoObj v1copy = new VideoObj( "A", 2000, "B" );
  final VideoObj v2 = new VideoObj( "B", 2000, "B" );

  public void testSize() {
                                 Assert.assertEquals( 0, s.size() );
          s.addNumOwned(v1,  1); Assert.assertEquals( 1, s.size() );
          s.addNumOwned(v1,  2); Assert.assertEquals( 1, s.size() );
          s.addNumOwned(v2,  1); Assert.assertEquals( 2, s.size() );
          s.addNumOwned(v2, -1); Assert.assertEquals( 1, s.size() );
          s.addNumOwned(v1, -3); Assert.assertEquals( 0, s.size() );
    try { s.addNumOwned(v1, -3); } catch ( IllegalArgumentException e ) {}
                                 Assert.assertEquals( 0, s.size() );
  }

  public void testAddNumOwned() {
                                    Assert.assertEquals( 0, s.size() );
          s.addNumOwned(v1, 1);     Assert.assertEquals( v1, s.get(v1).video );
                                    Assert.assertEquals( 1, s.get(v1).numOwned );
          s.addNumOwned(v1, 2);     Assert.assertEquals( 3, s.get(v1).numOwned );
          s.addNumOwned(v1, -1);    Assert.assertEquals( 2, s.get(v1).numOwned );
          s.addNumOwned(v2, 1);     Assert.assertEquals( 2, s.get(v1).numOwned );
          s.addNumOwned(v1copy, 1); Assert.assertEquals( 3, s.get(v1).numOwned );
                                    Assert.assertEquals( 2, s.size() );
          s.addNumOwned(v1, -3);    
                                    Assert.assertEquals( 1, s.size() );
    try { s.addNumOwned(null, 1);   Assert.fail(); } catch ( IllegalArgumentException e ) {}
  }

  public void testCheckOutCheckIn() {
    try { s.checkOut(null);     Assert.fail(); } catch ( IllegalArgumentException e ) {}
    try { s.checkIn(null);      Assert.fail(); } catch ( IllegalArgumentException e ) {}
          s.addNumOwned(v1, 2); Assert.assertTrue( s.get(v1).numOut == 0 && s.get(v1).numRentals == 0 );
          s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 1 );
    try { s.addNumOwned(v1,-3); Assert.fail(); } catch ( IllegalArgumentException e ) {}
    try { s.addNumOwned(v1,-2); Assert.fail(); } catch ( IllegalArgumentException e ) {}
          s.addNumOwned(v1,-1); Assert.assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 1 );
          s.addNumOwned(v1, 1); Assert.assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 1 );
          s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut == 2 && s.get(v1).numRentals == 2 );
    try { s.checkOut(v1);       Assert.fail(); } catch ( IllegalArgumentException e ) {}
          s.checkIn(v1);        Assert.assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 2 );
          s.checkIn(v1copy);    Assert.assertTrue( s.get(v1).numOut == 0 && s.get(v1).numRentals == 2 );
    try { s.checkIn(v1);        Assert.fail(); } catch ( IllegalArgumentException e ) {}
    try { s.checkOut(v2);       Assert.fail(); } catch ( IllegalArgumentException e ) {}
          s.checkOut(v1);       Assert.assertTrue( s.get(v1).numOut == 1 && s.get(v1).numRentals == 3 );
  }

  public void testClear() {
          s.addNumOwned(v1, 2); Assert.assertEquals( 1, s.size() );
          s.addNumOwned(v2, 2); Assert.assertEquals( 2, s.size() );
          s.clear();            Assert.assertEquals( 0, s.size() );
    try { s.checkOut(v2);       Assert.fail(); } catch ( IllegalArgumentException e ) {}
  }

  public void testGet() {
    s.addNumOwned(v1, 1);
    Record r1 = s.get(v1);
    Record r2 = s.get(v1);
    Assert.assertFalse( r1.equals(r2) );
    Assert.assertTrue( r1 != r2 );
  }

  public void testToCollection() {
    // Be sure to test that changing records in the returned
    // collection does not change the original records in the
    // inventory.  ToCollection should return a COPY of the records,
    // not the records themselves.
    Assert.assertTrue(s.toCollection() != s.toCollection());
    
    s.addNumOwned(v1, 1);
    Record r1 = s.toCollection().iterator().next();
    Record r2 = s.toCollection().iterator().next();
    Assert.assertTrue( r1 != r2);
    Assert.assertTrue( r1.numOwned == r2.numOwned);
    
    s.addNumOwned(v1, 1);
    Record r3 = s.toCollection().iterator().next();
    Assert.assertTrue( r1.numOwned != r3.numOwned);

    s.clear();
    s.addNumOwned(v1,10);
    s.addNumOwned(v2,20);
    Set<VideoObj> expected = new HashSet<VideoObj>();
    expected.add(v1);
    expected.add(v2);
    
    Iterator<Record> i = s.toCollection().iterator();
    //  The following would make sure that the collection is
    //  immutable, but this is not case here 
    //try { i.remove(); Assert.fail(); }
    //catch (UnsupportedOperationException e) { }
    while(i.hasNext()) {
      Record r = i.next(); 
      Assert.assertTrue(expected.contains(r.video));
      expected.remove(r.video);
    }
    Assert.assertTrue(expected.isEmpty());
  }
}
