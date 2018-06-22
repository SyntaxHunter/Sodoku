import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Sodoku2 implements ActionListener{

	static Board2 b;
	static BoardGUI gui;
	static int[][] sudoku;

	public static void main(String[] args){
		gui = new BoardGUI();
	}
	
	public Sodoku2(){
	}
	
	public static boolean findEasy(Board2 board) {
		for(int i = 0; i < 81; i++) { // For each box
			int possible = 0;
			ArrayList<Integer> nums = new ArrayList<Integer>();
			if(board.getCell(i/9, i%9).size() > 1) { // If value is not set
				for(int j = 1; j <= 9; j++) { // For each number
					board.setCell(i / 9, i % 9, j);
					if(board.checkPlace(i / 9, i % 9)) {
						possible++;
						nums.add(j);
					}
				}
				if (possible == 1) {
					board.setCell(i/9,i%9, nums.get(0));
					return true;
				} else if (possible < 9) {
					board.setCell(i/9, i%9, nums);
				} else {
					board.setCell(i/9, i%9, 0);
				}
			}
		}
		return false;
	}
	
	public static boolean onePossibility(Board2 board){
		for(int h = 0; h < 9; h++){ // Check 9 Boxes / Rows / Columns
			ArrayList<Integer> ultimateBox = new ArrayList<Integer>();
			ArrayList<Integer> ultimateRow = new ArrayList<Integer>();
			ArrayList<Integer> ultimateColumn = new ArrayList<Integer>();
			for(int i = 0; i < 9; i++){
				if(board.getCell((h / 3) * 3 + i / 3, (h % 3) * 3 + i % 3).size() != 1)
					ultimateBox.addAll(board.getCell((h / 3) * 3 + i / 3, (h % 3) * 3 + i % 3));
				if(board.getCell(h, i).size() != 1)
					ultimateRow.addAll(board.getCell(h, i));
				if(board.getCell(i, h).size() != 1)
					ultimateColumn.addAll(board.getCell(i, h));
			}
			Collections.sort(ultimateBox);
			Collections.sort(ultimateRow);
			Collections.sort(ultimateColumn);
			for(int i = 1; i <= 9; i++){ // For each number
				if(ultimateBox.indexOf(i) == ultimateBox.lastIndexOf(i) && ultimateBox.indexOf(i) != -1){
					for(int j = 0; j < 9; j++){ // For each box
						if(board.getCell((h / 3) * 3 + j / 3, (h % 3) * 3 + j % 3).contains(i)){
							board.setCell((h / 3) * 3 + j / 3, (h % 3) * 3 + j % 3, i);
							return true;
						}
					}
				}
				if(ultimateRow.indexOf(i) == ultimateRow.lastIndexOf(i) && ultimateRow.indexOf(i) != -1){
					for(int j = 0; j < 9; j++){
						if(board.getCell(h, j).contains(i)){
							board.setCell(h, j, i);
							return true;
						}
					}
				}
				if(ultimateColumn.indexOf(i) == ultimateColumn.lastIndexOf(i) && ultimateColumn.indexOf(i) != -1){
					for(int j = 0; j < 9; j++){
						if(board.getCell(j, h).contains(i)){
							board.setCell(j, h, i);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean breakItDown(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(b.getCell(i, j).size() > 1){
					int works = 0;
					Board2 tempWork = new Board2();
					for(int k = 0; k < b.getCell(i, j).size(); k++){
						Board2 temp = b.copyBoard();
						temp.setCell(i, j, b.getCell(i,j).get(k));
						while(!temp.broken() && (findEasy(temp) || onePossibility(temp))){
						}
						if(!temp.broken()){
							works++;
							tempWork = temp;
						}
					}
					if(works == 1){
						b = tempWork;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void printBoard(Board2 board){
		ArrayList<Integer>[][] values = board.getBoard();
		for(int i = 0; i < values.length; i++){
			for(int j = 0; j < values[i].length; j++){
				if(values[i][j].size() == 1){
					System.out.print(values[i][j].get(0) + " ");
				} else{
					System.out.print("0 ");
				}
				if((j + 1) % 3 == 0 && j != 8){
					System.out.print("| ");
				}
			}
			System.out.println();
			if((i + 1) % 3 == 0 && i != 8){
				System.out.println("----------------------");
			}
		}
		System.out.println("\n");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sudoku = new int[gui.nums.length][gui.nums[0].length];
		for(int i = 0; i < gui.nums.length; i++){
			for(int j = 0; j < gui.nums[i].length; j++){
				if(gui.nums[i][j].getText().equals("0")|| gui.nums[i][j].getText().equals("")){
					sudoku[i][j] = 0;
				}else{
					sudoku[i][j] = Integer.parseInt(gui.nums[i][j].getText());
				}
			}
		}
		gui.frame.dispose();
		b = new Board2(sudoku);
		printBoard(b);
		long startTime = System.currentTimeMillis();
		while(findEasy(b) || onePossibility(b) || breakItDown()){
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		printBoard(b);
		System.out.println(totalTime + " milliseconds");
	}

}
