
public class Board {
	private int[][] board;
	final static int SIZE = 9;
	
	public Board(){
		board = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				board[i][j] = 0;
			}
		}
	}
	
	public Board(int[][] board){
		this.board = board;
	}
	
	public void setCell(int x, int y, int value){
		board[x][y] = value;
	}
	
	public int getCell(int x, int y){
		return board[x][y];
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public boolean checkRow(int i){
		boolean[] horizontal = {false, false, false, false, false, false, false, false, false};
		for(int j = 0; j < board[i].length; j++){
			if(board[i][j] != 0){
				if(horizontal[board[i][j] - 1]){
					return false;
				} else {
					horizontal[board[i][j] - 1] = true;
				}
			}
		}
		return true;
	}
	
	public boolean checkColumn(int j){
		boolean[] vertical = {false, false, false, false, false, false, false, false, false};
		for(int i = 0; i < board.length; i++){
			if(board[i][j] != 0){
				if(vertical[board[i][j] - 1]){
					return false;
				} else {
					vertical[board[i][j] - 1] = true;
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
				if(board[i][j] != 0){
					if(box[board[i][j] - 1]){
						return false;
					} else {
						box[board[i][j] - 1] = true;
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

}
