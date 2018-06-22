import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testBoardConstructor() {
		Board b1 = new Board();
	}
	
	@Test
	public void testCheckBoard(){
		Board b1 = new Board();
		assertTrue(b1.checkBoard());
		Board b2 = new Board(new int[][]{	{5,3,1, 6,4,2, 8,9,7},
											{2,8,9, 7,1,5, 3,4,6},
											{0,0,0, 8,9,3, 2,1,5},
											{9,0,5, 4,3,0, 6,2,8},
		                      				{0,0,8, 9,0,0, 7,5,4},
		                      				{7,4,0, 5,0,8, 9,3,1},
		                      				{6,9,3, 1,8,4, 5,7,2},
		                      				{8,5,4, 2,7,9, 1,6,3},
		                      				{2,1,7, 3,5,6, 4,8,9}	});
		assertFalse(b2.checkBoard());
		b2.setCell(1, 0, 0);
		assertTrue(b2.checkBoard());
	}

}