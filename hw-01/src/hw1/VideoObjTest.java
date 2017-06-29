package hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class VideoObjTest {

	@Test
	public void testHashCode() {
		VideoObj t1 = new VideoObj("xx",2017,"yy");
		VideoObj t2 = new VideoObj("xx",2017,"yy");
		VideoObj t3 = new VideoObj("aa",2017,"yy");
		VideoObj t4 = new VideoObj("xx",2017,"zz");
		assertEquals(t1.hashCode(),t1.hashCode());
		assertEquals(t1.hashCode(),t2.hashCode());
		assertTrue(t1.hashCode() != t3.hashCode());
		assertTrue(t1.hashCode() != t4.hashCode());
		int t = "xx".hashCode();
		//System.out.println(t);
		int y = 2017;
		int d = "yy".hashCode();
		//System.out.println(d);
		int result = ((((37 * 17) + t) * 37 + y) * 37 + d);
//		System.out.println(result);
//		System.out.println(t1.hashCode());
		assertEquals(result,t1.hashCode());
		
	}

	@Test
	public void testVideoObj() {
		String title = "xx";
		String director = "yy";
		int year = 2017;
		VideoObj test1 = new VideoObj(title,year,director);
		assertSame(title,test1.title());
		assertEquals(year,test1.year());
		assertSame(director,test1.director());
		
		String title1 = "ii";
		String director1 = "jj";
		int year1 = 2016;
		VideoObj test2 = new VideoObj(title1,year1,director1);
		assertSame(title1,test2.title());
		assertEquals(year1,test2.year());
		assertSame(director1,test2.director());
		
	}

	@Test
	public void testDirector() {
		try{
			new VideoObj("xx",2017,null);
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx",2017," yy");
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx",2017,"yy ");
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx",2017,"");
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx",2017," ");
			fail();
		} catch (IllegalArgumentException e){}
	}

	@Test
	public void testTitle() {
		try{
			new VideoObj(null,2017,"yy");
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj(" xx",2017,"yy");
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx ",2017,"yy");
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj("",2017,"yy");
			fail();
		} catch (IllegalArgumentException e){}
		try{
			new VideoObj(" ",2017,"yy");
			fail();
		} catch (IllegalArgumentException e){}
	}

	@Test
	public void testYear() {
		try{
			new VideoObj("xx",1700,"yy");
			fail();
		}catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx",1800,"yy");
			fail();
		}catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx",5000,"yy");
			fail();
		}catch (IllegalArgumentException e){}
		try{
			new VideoObj("xx",5001,"yy");
			fail();
		}catch (IllegalArgumentException e){}
	}

	@Test
	public void testEqualsObject() {
		VideoObj t1 = new VideoObj("xx",2017,"yy");
		VideoObj t2 = new VideoObj("xx",2017,"yy");
		VideoObj t3 = new VideoObj("xy",2017,"yx");
		VideoObj t4 = new VideoObj("yx",2017,"xy");
		assertTrue(t1.equals(t1));
		assertTrue(t1.equals(t2));
		assertFalse(t1.equals(t3));
		assertFalse(t1.equals(t4));
		assertFalse(t1.equals(new Object()));
	}

	@Test
	public void testCompareTo() {
		VideoObj t1 = new VideoObj("xx",2017,"yy");
		VideoObj t2 = new VideoObj("xx",2017,"yy");
		VideoObj t3 = new VideoObj("aa",2017,"yy");
		VideoObj t4 = new VideoObj("xx",2017,"aa");
		assertTrue(0 == t1.compareTo(t1));
		assertTrue(0 == t1.compareTo(t2));
		assertTrue(0 < t1.compareTo(t3));
		assertTrue(0 < t1.compareTo(t4));
		assertTrue(0 == t2.compareTo(t1));
		assertTrue(0 > t3.compareTo(t1));
		assertTrue(0 > t4.compareTo(t1));
	}

	@Test
	public void testToString() {
		VideoObj test = new VideoObj("xx",2017,"yy");
		test.toString();
		assertEquals("xx (2017) : yy",test.toString());
	}

}
