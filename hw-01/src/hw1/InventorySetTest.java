package hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class InventorySetTest {
	InventorySet data = new InventorySet();
	final VideoObj v0 = null;
	final VideoObj v1 = new VideoObj("xx",2017,"yy");
	final VideoObj v2 = new VideoObj("ii",2017,"jj");

	@Test
	public void testSize() {
		assertEquals(0,data.size());
		data.addNumOwned(v1, 1);
		assertEquals(1,data.size());
		data.addNumOwned(v2, 1);
		assertEquals(2,data.size());
		data.addNumOwned(v2, 2);
		assertEquals(2,data.size());
	}

	@Test
	public void testGet() {
		Record r = data.get(v0);
		data.addNumOwned(v1, 1);
		Record r1 = data.get(v1);
		Record r2 = data.get(v1);
		assertEquals(null,r);
		assertFalse(r1 == null);
		//they are not the same because they are the copy of the records?
		assertFalse(r1.equals(r2));
		assertFalse(r1 == r2);
	}

	@Test
	public void testToCollection() {
		data.addNumOwned(v1, 1);
		Record r1 = data.toCollection().iterator().next();
		Record r2 = data.toCollection().iterator().next();
		//not return the actual records.
		assertFalse(data.toCollection().equals(data.toCollection()));
		//but return a copy of the records which should have same value
		assertEquals(r1.numOwned,r2.numOwned);
		//add NumOwned
		data.addNumOwned(v1, 2);
		Record r3 = data.toCollection().iterator().next();
		assertFalse(r1.numOwned == r3.numOwned);
	}

	@Test
	public void testAddNumOwned() {
		//video null
		try{
			data.addNumOwned(null, 1);
			fail();
		} catch (IllegalArgumentException e){}
		//change is zero
		try{
			data.addNumOwned(v1, 0);
			fail();
		} catch (IllegalArgumentException e){}
		//video record not present
		data.addNumOwned(v1, 1);
		Record r1 = data.get(v1);
		assertEquals(1,r1.numOwned);
		//video record already present
		data.addNumOwned(v1, 3);
		Record r2 = data.get(v1);
		assertEquals(4,r2.numOwned);
		data.addNumOwned(v1, -1);
		Record r3 = data.get(v1);
		assertEquals(3,r3.numOwned);
		//if attempting to remove more copies than are owned,
		//or if attempting to remove copies that are checked out.
		try{
			data.addNumOwned(v1, -4);
			Record r4 = data.get(v1);
			fail();
		} catch (IllegalArgumentException e){}
		
	}

	@Test
	public void testCheckOut() {
		//video null
		try{
			data.addNumOwned(null, 1);
			fail();
		} catch (IllegalArgumentException e){}
		//numOut(0) equals numOwned(0).
		try{
			data.addNumOwned(v1, 0);
			data.checkOut(v1);
			fail();
		} catch (IllegalArgumentException e){}
		
		data.addNumOwned(v1, 2);
		data.checkOut(v1);
		assertEquals(1,data.get(v1).numOut);
		assertEquals(1,data.get(v1).numRentals);
		data.checkOut(v1);
		assertEquals(2,data.get(v1).numOut);
		assertEquals(2,data.get(v1).numRentals);
		//numOut equals numOwned.
		try{
			data.checkOut(v1);
			fail();
		} catch (IllegalArgumentException e){}
	}

	@Test
	public void testCheckIn() {
		//video null
		try{
			data.addNumOwned(null, 1);
			fail();
		} catch (IllegalArgumentException e){}
		//numOut non-positive.
		try{
			data.addNumOwned(v1, 0);
			data.checkOut(v1);
			data.checkIn(v1);
			fail();
		} catch (IllegalArgumentException e){}
		
		data.addNumOwned(v1, 2);
		data.checkOut(v1);
		data.checkOut(v1);
		data.checkIn(v1);
		assertEquals(1,data.get(v1).numOut);
		assertEquals(1,data.get(v1).numRentals);
		data.checkIn(v1);
		assertEquals(0,data.get(v1).numOut);
		assertEquals(0,data.get(v1).numRentals);
		
	}

	@Test
	public void testClear() {
		data.addNumOwned(v1, 1);
		data.addNumOwned(v2, 1);
		assertTrue(data.size() == 2);
		data.clear();
		assertTrue(data.size() == 0);
	}

//	@Test
//	public void testToString() {
//		data.addNumOwned(v1, 1);
//		data.addNumOwned(v2, 1);
//		String database = 
//				"Database:\n"
//				+"  "
//				+"xx (2017) : yy [1,0,0]" + "\n"
//				+"ii (2017) : jj [1,0,0]" + "\n";
//		assertEquals(database,data.toString());
//		
//	}

}
