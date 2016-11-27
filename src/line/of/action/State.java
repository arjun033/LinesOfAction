package line.of.action;

public class State 
{
	private static final int boardSize=8;
	public static final char W='X',B='O',EMPTY='_';

	char board[][]= new char[boardSize][boardSize];
	public State()
	{
		for(int i=0;i<boardSize;i++)
			for(int j=0;j<boardSize;j++)
				board[i][j]=EMPTY;
	}
	public State(State state)
	{
		for(int i=0;i<boardSize;i++)
			for(int j=0;j<boardSize;j++)
				this.board[i][j]=state.board[i][j];
	}
	public State(int [][]board)
	{
		for(int i=0;i<boardSize;i++)
			for(int j=0;j<boardSize;j++)
				board[i][j]=EMPTY;
		if(board[0].length!=boardSize || board.length!=boardSize)
		{
			System.out.println("Illegal size of board");
			System.exit(1);
		}
	}
	public char[][] getBoard() {
		return board;
	}
	public void setBoard(char[][] board) {
		this.board = board;
	}
	public void printBoard()
	{
		StringBuilder sb= new StringBuilder();
		sb.append("    0 1 2 3 4 5 6 7");
		System.out.println(sb.toString());

		for(int i=0;i<boardSize;i++)
		{
			sb= new StringBuilder();
			sb.append(i+"-> ");
			for(int j=0;j<boardSize;j++)
			{
				if(board[i][j]==W)
					sb.append(W);
				if(board[i][j]==B)
					sb.append(B);
				if(board[i][j]==EMPTY)
					sb.append(EMPTY);
				if(j<boardSize-1)
					sb.append(' ');
			}
			System.out.println(sb.toString());
		}
	}
	public static void main(String args[])
	{
		State a= new State();
		/*State b= new State(a);
		a.board[0][0]='p';*/
		a.printBoard();
	}
}