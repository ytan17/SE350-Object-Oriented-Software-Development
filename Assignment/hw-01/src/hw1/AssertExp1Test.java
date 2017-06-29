package hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AssertExp1Test extends AssertExp1{

	@Test
	public void testMinPosition() {
		assertTrue(0 == minPosition(new double[] { -7 }));
		assertTrue(2 == minPosition(new double[] { 1, -4, -7, 7, 8, 11 }));
		assertTrue(0 == minPosition(new double[] { -13, -4, -7, 7, 8, 11 }));
		assertTrue(6 == minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 }));
	}

	@Test
	public void testNumUnique() {
		assertTrue(0 == numUnique(new double[] { }));
		assertTrue(1 == numUnique(new double[] { 11 }));
		assertTrue(1 == numUnique(new double[] { 11, 11, 11, 11 }));
		assertTrue(8 == numUnique(new double[] { 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }));
		assertTrue(8 == numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 }));
	}

	@Test
	public void testRemoveDuplicates() {
//		System.out.println("work");
//		System.out.println(removeDuplicates(new double[] { }).length);
//		assertTrue(new double[] { }.equals( removeDuplicates(new double[] { })));
//		System.out.println("work?");
//		assertTrue(new double[] { 11 }.equals(removeDuplicates(new double[] { 11 })));
//		System.out.println("work??");
//		assertTrue(new double[] { 11 }.equals(removeDuplicates(new double[] { 11, 11, 11, 11 })));
//		System.out.println("work???");
//		assertTrue(new double[] { 11, 22, 33, 44, 55, 66, 77, 88 }.equals( removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 })));
//		assertTrue(new double[] { 11, 22, 33, 44, 55, 66, 77, 88 }.equals( removeDuplicates(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })));
//	}
		double[] list0 = new double[] { };
		double[] list1 = removeDuplicates(new double[] { });
		for(int i = 0;i<list0.length; i++){
			assertTrue(list0[i] == list1[i]);
		}
		double[] list2 = new double[] { 11 };
		double[] list3 = removeDuplicates(new double[] { 11 });
		for(int i = 0;i<list2.length; i++){
			assertTrue(list2[i] == list3[i]);
		}
		double[] list4 = new double[] { 11 };
		double[] list5 = removeDuplicates(new double[] { 11, 11, 11, 11 });
		for(int i = 0;i<list4.length; i++){
			assertTrue(list4[i] == list5[i]);
		}
		double[] list6 = new double[] { 11, 22, 33, 44, 55, 66, 77, 88 };
		double[] list7 = removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 });
		for(int i = 0;i<list6.length; i++){
			assertTrue(list6[i] == list7[i]);
		}
		double[] list8 = new double[] { 11, 22, 33, 44, 55, 66, 77, 88 };
		double[] list9 = removeDuplicates(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 });
		for(int i = 0;i<list8.length; i++){
			assertTrue(list8[i] == list9[i]);
		}
	}

}
