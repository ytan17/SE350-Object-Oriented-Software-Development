package hw1;

import junit.framework.Assert;
import junit.framework.TestCase;

public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  public void testConstructorAndAttributes() {
    String title1 = "XX";
    String director1 = "XY";
    String title2 = " XX ";
    String director2 = " XY ";
    int year = 2002;

    VideoObj v1 = new VideoObj(title1, year, director1);
    Assert.assertSame(title1, v1.title());
    Assert.assertEquals(year, v1.year());
    Assert.assertSame(director1, v1.director());
    VideoObj v2 = new VideoObj(title2, year, director2);
    Assert.assertEquals(title1, v2.title());
    Assert.assertEquals(director1, v2.director());
  }

  public void testConstructorExceptionYear() {
    try {
      new VideoObj("X", 1800, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 5000, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("X", 1801, "Y");
      new VideoObj("X", 4999, "Y");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  public void testConstructorExceptionTitle() {
    try {
      new VideoObj(null, 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj("", 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      new VideoObj(" ", 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
  }

  public void testConstructorExceptionDirector() {
  }

  public void testHashCode() {
    Assert.assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    Assert.assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }

  public void testEquals() { 
    String title = "A";
    int year = 2009;
    String director = "Zebra";
    VideoObj a = new VideoObj(title,year,director);
    Assert.assertTrue( a.equals(a) );
    Assert.assertTrue( a.equals( new VideoObj(title, year, director) ) );
    Assert.assertTrue( a.equals( new VideoObj(new String(title), year, director) ) );
    Assert.assertTrue( a.equals( new VideoObj(title, year, new String(director)) ) );
    Assert.assertFalse( a.equals( new VideoObj(title+"1", year, director) ) );
    Assert.assertFalse( a.equals( new VideoObj(title, year+1, director) ) );
    Assert.assertFalse( a.equals( new VideoObj(title, year, director+"1") ) );
    Assert.assertFalse( a.equals( new Object() ) );
    Assert.assertFalse( a.equals( null ) );
  }

  public void testCompareTo() { 
    String title = "A", title2 = "B";
    int year = 2009, year2 = 2010;
    String director = "Zebra", director2 = "Zzz";
    VideoObj a = new VideoObj(title,year,director);
    VideoObj b = new VideoObj(title2,year,director);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );
    Assert.assertTrue( a.compareTo(a) == 0 );

    b = new VideoObj(title,year2,director);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title,year,director2);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title2,year2,director2);
    Assert.assertTrue( a.compareTo(b) < 0 );
    Assert.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    try {
      a.compareTo(null);
      fail();
    } catch ( NullPointerException e ) {}
      catch ( ClassCastException e ) {}
  }

  public void testToString() { 
    String s = new VideoObj("A",2000,"B").toString();
    Assert.assertEquals( s, "A (2000) : B" );
    s = new VideoObj(" A ",2000," B ").toString();
    Assert.assertEquals( s, "A (2000) : B" );
  }
}
