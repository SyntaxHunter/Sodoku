import java.util.ArrayList;

public class Board2 {
	
	private ArrayList<Integer>[][] board;
	final static int SIZE = 9;
	
	public Board2(){
		board = new ArrayList[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				board[i][j] = new ArrayList<Integer>();
				set0(board[i][j]);
			}
		}
	}
	
	public Board2(int[][] board){
		this();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				if(board[i][j] != 0){
					setCell(i, j, board[i][j]);
				}
			}
		}
	}
	
	public void setCell(int x, int y, int value){
		if(value == 0){
			set0(board[x][y]);
		} else {
			board[x][y].clear();
			board[x][y].add(value);
		}
	}
	
	public void setCell(int x, int y, int[] values){
		board[x][y].clear();
		for(int i = 0; i < values.length; i++){
			if(values[i] != 0){
				board[x][y].add(values[i]);
			}
		}
	}
	
	public void setCell(int x, int y, ArrayList<Integer> values){
		board[x][y] = values;
	}
	
	public ArrayList<Integer> getCell(int x, int y){
		return board[x][y];
	}
	
	public ArrayList<Integer>[][] getBoard(){
		return board;
	}
	
	public Board2 copyBoard(){
		Board2 b = new Board2();
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				ArrayList<Integer> values = new ArrayList<Integer>();
				for(int k = 0; k < board[i][j].size(); k++){
					values.add(board[i][j].get(k));
				}
				b.setCell(i, j, values);
			}
		}
		return b;
	}
	
	private void set0(ArrayList<Integer> a){
		a.clear();
		a.add(1); a.add(2); a.add(3); a.add(4); a.add(5);
		a.add(6); a.add(7); a.add(8); a.add(9);
	}
	
	public boolean checkRow(int i){
		boolean[] horizontal = {false, false, false, false, false, false, false, false, false};
		for(int j = 0; j < board[i].length; j++){
			if(board[i][j].size() == 1){
				if(horizontal[board[i][j].get(0) - 1]){
					return false;
				} else {
					horizontal[board[i][j].get(0) - 1] = true;
				}
			}
		}
		return true;
	}
	
	public boolean checkColumn(int j){
		boolean[] vertical = {false, false, false, false, false, false, false, false, false};
		for(int i = 0; i < board.length; i++){
			if(board[i][j].size() == 1){
				if(vertical[board[i][j].get(0) - 1]){
					return false;
				} else {
					vertical[board[i][j].get(0) - 1] = true;
				}
			}
		}
		return true;
	}
	
	public int findBoxNum(int i, int j){
		return (int)(i / 3) * 3 + (int)(j / 3);
	}
	
	public boolean checkBox(int boxNum){
		boolean[] box = {false, false, false, false, false, false, false, false, false};
		for(int i = (int) (boxNum / 3) * 3; i < (int) (boxNum / 3) * 3 + 3; i++){
			for(int j = (boxNum % 3) * 3; j < (boxNum % 3) * 3 + 3; j++){
				if(board[i][j].size() == 1){
					if(box[board[i][j].get(0) - 1]){
						return false;
					} else {
						box[board[i][j].get(0) - 1] = true;
					}
				}
			}
		}
		return true;
	}
	
	public boolean checkPlace(int i, int j){
		if(!checkRow(i)) return false;
		if(!checkColumn(j)) return false;
		if(!checkBox(findBoxNum(i,j))) return false;
		return true;
	}
	
	public boolean checkBoard(){
		for(int i = 0; i < 9; i++){
			if(!checkRow(i)) return false;
			if(!checkColumn(i)) return false;
			if(!checkBox(i)) return false;
		}
		return true;
	}

	public boolean broken(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j].size() == 0){
					return true;
				}
			}
		}
		return false;
	}

}
