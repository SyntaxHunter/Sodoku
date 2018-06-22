import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Sodoku{
	
	static int depth = 0;
	static Board b;
	static int[][] board = {	{5,0,0, 0,0,2, 8,9,7},
								{0,8,9, 0,0,5, 0,0,0},
								{0,0,0, 8,9,0, 0,1,5},
								
								{9,0,0, 4,0,0, 0,2,8},
								{0,0,8, 9,0,0, 7,0,0},
								{0,4,0, 0,0,8, 9,0,1},
								
								{6,9,0, 0,8,0, 0,7,0},
								{8,0,0, 2,7,9, 1,6,0},
								{2,1,7, 3,0,0, 5,8,9}	};

	public static void main(String[] args){
		b = new Board(board);
		//b.setCell(0, 1, 1);
		try{
			solve();	
		} catch(Exception e){
			
		}
		printBoard(b.getBoard());
		while (findEasy())
			printBoard(b.getBoard());
		while(onePossibility())
			printBoard(b.getBoard());
	}
	
	public static boolean findEasy() {
		for(int i = 0; i < 81; i++) {
			int possible = 0;
			int number = 0;
			if(b.getCell(i/9, i%9) == 0) {
				for(int j = 1; j <= 9; j++) {
					b.setCell(i / 9, i % 9, j);
					if(b.checkPlace(i / 9, i % 9)) {
						possible++;
						number = j;
					}
				}
				if (possible == 1) {
					b.setCell(i/9,i%9, number);
					return true;
				} else {
					b.setCell(i/9, i%9, 0);
				}
			}
		}
		return false;
	}
	
	public static boolean onePossibility(){
		for(int h = 0; h < 9; h++){ // Check 9 Boxes
			int possible = 0;
			int place = 0;
			for(int i = 0; i < 9; i++){ // Check 9 Numbers
				for(int j = 0; j < 9; j++){ // Check 9 Squares
					b.setCell(h/3 + j/3, h%3 + j%3, value);
				}
			}
		}
	}
	
	public static void solve() /*throws InterruptedException*/{
		depth++;
		//System.out.println(depth);
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				//if(i == 8 && j == 8 && b.checkBoard()){
				//	printBoard(b.getBoard());
				//	return;
				//}
				if(b.getCell(i, j) == 0){
					for(int k = 1; k <= 9; k ++){
						//System.out.println(i + " " + j + " " + k);
						//Thread.sleep(10);
						b.setCell(i, j, k);
						if(b.checkPlace(i, j)){
							if (i==8 && j == 8) {
								printBoard(b.getBoard());
								return;
							}
							solve();
						//} else if (k == 9) {
						//	b.setCell(i, j, 0);
						}
					}
					b.setCell(i, j, 0);
				}
			}
		}
		depth--;
	}
	
	public static void printBoard(int[][] values){
		for(int i = 0; i < values.length; i++){
			for(int j = 0; j < values[i].length; j++){
				System.out.print(values[i][j] + " ");
				if((j + 1) % 3 == 0 && j != 8){
					System.out.print("| ");
				}
			}
			System.out.println();
			if((i + 1) % 3 == 0 && i != 8){
				System.out.println("----------------------");
			}
		}
		System.out.println();
	}
	
}
