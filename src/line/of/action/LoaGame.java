package line.of.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;


/*
 * For the whole project in case of any array like move[]
 * <p> The first element move[0] will correspond to row
 * <p> The second element move[1] will correspond to column
 */

public class LoaGame {
	private static int depth;
	public static final char W='X',B='O',EMPTY='_';
	State gameState;
	Player player;
	static boolean debugging=true;
	static int defaultDepth=3;
	static int delay=3000;
	static int nodesExpanded=0;
	
	public LoaGame()
	{
		gameState= new State();
		depth=defaultDepth;
		initializeBoard();
		gameState.printBoard();
		player= new Player(W);
		player.setOtherPlayerName(B);
	}
	/*
	 * Initialize the board
	 */
	void initializeBoard()
	{
		for(int i=1;i<7;i++)
		{
			gameState.board[0][i]=B;
			gameState.board[7][i]=B;
			gameState.board[i][0]=W;
			gameState.board[i][7]=W;
		}
	}
	
	/*
	 * Get the next state as per the move of the player
	 */
	private State getNextState(State currentState, int[] nextMove,Player player) 
	{
		State nextState = new State(currentState);
		int row = nextMove[0];
		int col = nextMove[1]; 
		
		nextState.board[row][col] = player.name;

		if (col>0)
		{
			// perform left flip
			doLeftFlip(nextState, nextMove, player);
		}

		if (col<7) 
		{
			// perform right flip
			doRightFlip(nextState, nextMove, player);
		}

		if (row>0) 
		{
			// perform up flip
			doUpFlip(nextState, nextMove, player);
		}

		if (row<7) 
		{
			// perform down flip
			doDownFlip(nextState, nextMove, player);
		}
		
		if(row>0 && col<7)		{
			// perform north-east flip
			doNorthEastFlip(nextState,nextMove,player);
		}
		
		if(row>0 && col>0)
		{
			// perform north-west flip
			doNorthWestFlip(nextState,nextMove,player);
		}
		
		if(row<7 && col>0)
		{
			//perform south-west flip
			doSouthWestFlip(nextState,nextMove,player);
		}
		
		if(row<7 && col<7)
		{
			// perform south-east flip
			doSouthEastFlip(nextState,nextMove,player);
		}

		if(debugging)
		{
			System.out.println("Intermediate state >>");
			nextState.printBoard();
		}
		return nextState;
	}
	private void doLeftFlip(State nextState, int []nextMove, Player player) 
	{
		int row = nextMove[0];
		int col =nextMove[1],origCol=nextMove[1]; 
		boolean flipPossible = false;
		int position[] = new int[2];
		
		/*
		int row = nextMove[0];
		int col =nextMove[1],origCol=nextMove[1]; 
		boolean flipPossible = false;
		int position[] = new int[2];
		col--;
		if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
		{
			return;
		} 
		else 
		{
			col--;
			while (col >= 0) 
			{
				if (nextState.board[row][col] == player.name) 
				{
					position[0] = row;
					position[1] = col;
					flipPossible = true;
					break;
				} 
				else if (nextState.board[row][col] == EMPTY) 
				{
					flipPossible = false;
					break;
				} 
				else 
				{
					col--;
				}
			}
		}
		if (flipPossible) 
		{
			while (origCol > position[1]) 
			{
				origCol--;
				nextState.board[row][origCol] = player.name;
			}
		} 
	*/
		}
	private void doRightFlip(State nextState, int []nextMove, Player player) 
	{
		int row = nextMove[0];
		int col =nextMove[1],origCol=nextMove[1];
		int position[] = new int[2];
		boolean flipPossible = false;
		col++;
		if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
		{
			return;
		} 
		else 
		{
			col++;
			while (col <= 7) {
				if (nextState.board[row][col] == player.name) 
				{
					position[0] = row;
					position[1] = col;
					flipPossible = true;
					break;
				} 
				else if (nextState.board[row][col] == EMPTY) 
				{
					flipPossible = false;
					break;
				} 
				else 
				{
					col++;
				}
			}
		}
		if (flipPossible) 
		{
			while (origCol < position[1]) 
			{
				origCol++;
				nextState.board[row][origCol] = player.name;
			}
		}
	}
	
	private void doUpFlip(State nextState, int []nextMove,Player player) 
	{
		int row = nextMove[0],origRow=nextMove[0];
		int col =nextMove[1];
		boolean flipPossible = false;
		int position[] = new int[2];
		row--;
		if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
		{
			return;
		} else {
			row--;
			while (row >= 0) {
				if (nextState.board[row][col] == player.name) 
				{
					position[0] = row;
					position[1] = col;
					flipPossible = true;
					break;
				} 
				else if (nextState.board[row][col] == EMPTY) 
				{
					flipPossible = false;
					break;
				} 
				else 
				{
					row--;
				}
			}
		}
		if (flipPossible) 
		{
			while (origRow > position[0]) 
			{
				origRow--;
				nextState.board[origRow][col] = player.name;
			}
		} 
	}
	private void doDownFlip(State nextState, int []nextMove,Player player) 
	{
		int row = nextMove[0],origRow=nextMove[0];
		int col =nextMove[1];
		boolean flipPossible = false;
		int position[] = new int[2];
		row++;
		if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
		{
			return;
		} 
		else 
		{
			row++;
			while (row <= 7) 
			{
				if (nextState.board[row][col] == player.name) 
				{
					flipPossible = true;
					position[0] = row;
					position[1] = col;
					break;
				} 
				else if (nextState.board[row][col] == EMPTY) 
				{
					flipPossible = false;
					break;
				} 
				else 
				{
					row++;
				}
			}
		}
		if (flipPossible) 
		{
			while (origRow < position[0]) 
			{
				origRow++;
				nextState.board[origRow][col] = player.name;
			}
		} 
	}
	private void doNorthEastFlip(State nextState, int []nextMove,Player player) 
	{
		int row = nextMove[0],origRow=nextMove[0];
		int col =nextMove[1],origCol=nextMove[1];
		row--;
		col++;
		boolean flipPossible = false;
		int position[] = new int[2];

		if (row >= 0 && col <= 7) {
			if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
			{
				return;
			} 
			else 
			{
				row--;
				col++;
				while (row >= 0 && col <= 7) 
				{
					if (nextState.board[row][col] == player.name) 
					{
						flipPossible = true;
						position[0] = row;
						position[1] = col;
						break;
					} 
					else if (nextState.board[row][col] == EMPTY) 
					{
						flipPossible = false;
						break;
					} 
					else 
					{
						col++;
						row--;
					}
				}
			}
		}
		if (flipPossible) 
		{
			while (origRow > position[0] && origCol < position[1]) 
			{
				origRow--;
				origCol++;
				nextState.board[origRow][origCol] = player.name;
			}
		} 
	}
	private void doNorthWestFlip(State nextState, int []nextMove,Player player) 
	{
		int row = nextMove[0],origRow=nextMove[0];
		int col =nextMove[1],origCol=nextMove[1];
		row--;
		col--;
		boolean flipPossible = false;
		int position[] = new int[2];

		if (row >= 0 && col >= 0) 
		{
			if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
			{
				return;
			} 
			else 
			{
				col--;
				row--;
				while (row >= 0 && col >= 0 ) 
				{
					if (nextState.board[row][col] == player.name) 
					{
						position[0] = row;
						position[1] = col;
						flipPossible = true;
						break;
					} 
					else if (nextState.board[row][col] == EMPTY) 
					{
						flipPossible = false;
						break;
					} 
					else 
					{
						col--;
						row--;
					}
				}
			}
		}
		if (flipPossible) 
		{
			while (origRow > position[0] && origCol > position[1]) 
			{
				origRow--;
				origCol--;
				nextState.board[origRow][origCol] = player.name;
			}
		} 
	}
	private void doSouthWestFlip(State nextState, int []nextMove,Player player) 
	{
		int row = nextMove[0],origRow=nextMove[0];
		int col =nextMove[1],origCol=nextMove[1];
		row++;
		col--;
		boolean flipPossible = false;
		int position[] = new int[2];

		if (row <= 7 && col >= 0) 
		{
			if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
			{
				return;
			} 
			else 
			{
				row++;
				col--;
				while (row <= 7 && col >= 0) 
				{
					if (nextState.board[row][col] == player.name) 
					{
						flipPossible = true;
						position[0] = row;
						position[1] = col;
						break;
					} 
					else if (nextState.board[row][col] == EMPTY) 
					{
						flipPossible = false;
						break;
					} 
					else 
					{
						col--;
						row++;
					}
				}
			}
		}
		if (flipPossible) 
		{
			while (origRow < position[0] && origCol > position[1]) 
			{
				origRow++;
				origCol--;
				nextState.board[origRow][origCol] = player.name;
			}
		} 
	}
	private void doSouthEastFlip(State nextState, int []nextMove,Player player) 
	{
		int row = nextMove[0],origRow=nextMove[0];
		int col =nextMove[1],origCol=nextMove[1];
		row++;
		col++;
		boolean flipPossible = false;
		int position[] = new int[2];

		if (row <= 7 && col <= 7) 
		{
			if (nextState.board[row][col] == EMPTY || nextState.board[row][col] == player.name) 
			{
				return;
			} 
			else 
			{
				row++;
				col++;
				while (row <= 7 && col <=7) 
				{
					if (nextState.board[row][col] == player.name) 
					{
						flipPossible = true;
						position[0] = row;
						position[1] = col;
						break;
					} 
					else if (nextState.board[row][col] == EMPTY) 
					{
						flipPossible = false;
						break;
					} 
					else 
					{
						col++;
						row++;
					}
				}
			}
		}
		if (flipPossible) 
		{
			while (origRow < position[0] && origCol < position[1]) 
			{
				origRow++;
				origCol++;
				nextState.board[origRow][origCol] = player.name;
			}
		} 
	}
	
	private int[][] movesPossible(State currentState, Player player) 
	{
		ArrayList<int[]> movesPossible = new ArrayList<int[]>();
		
		for (int i = 0; i < currentState.board.length; i++) 
		{
			for (int j = 0; j < currentState.board[i].length; j++) 
			{
				if (currentState.board[i][j] == player.name) 
				{
					if (j > 0) 
					{
						// get legal moves on left side horizontally
						int[] leftMove = checkLeftHorizontal(currentState, player,i,j);
						addMove(movesPossible, leftMove);
					}

					if (j < 7) 
					{
						// get legal moves on right side horizontally
						int[] rightMove = checkRightHorizontal(currentState, player,i,j );
						addMove(movesPossible, rightMove);
					}

					if (i > 0) 
					{
						// get legal moves in up direction
						int[] upMove = checkUpMove(currentState, player, i, j);
						addMove(movesPossible, upMove);
					}

					if (i < 7) 
					{
						// get legal moves in down direction
						int[] downMove = checkDownMove(currentState, player, i, j);
						addMove(movesPossible, downMove);
					}
					
					if(i > 0 && j < 7)
					{
						// get legal moves in north-east direction
						int[] neMove=checkNorthEastMove(currentState, player, i, j);
						addMove(movesPossible, neMove);
					}
					
					if(i>0 && j > 0)
					{
						// get legal moves in north-west direction
						int nwMove[]= checkNorthWestMove(currentState, player, i, j);
						addMove(movesPossible, nwMove);
					}
					
					if(i<7 && j<7)
					{
						// get legal moves in south-east direction
						int seMove[]= checkSouthEastMove(currentState, player , i ,j);
						addMove(movesPossible, seMove);
					}
					
					if(i< 7 && j > 0)
					{
						//get legal moves in south-west direction
						int swMove[] = checkSouthWestMove(currentState, player , i , j);
						addMove(movesPossible, swMove);
						
					}

				}
			}
		}
		int [][] result= new int[movesPossible.size()][2];
		for(int i=0;i<movesPossible.size();i++)
		{
			result[i]=movesPossible.get(i);
		}
		return result;
	}
	private int[] checkLeftHorizontal(State state, Player player, int row,int column) 
	{

		boolean leftMovePossible = false;
		if (state.board[row][column - 1] == EMPTY || state.board[row][column - 1] == player.name) 
		{
			// the location is either empty or the same player is present
			return null;
		} 
		else 
		{
			column--;
			while (column > 0) 
			{
				column--;
				if (state.board[row][column] == player.name) 
				{
					return null;
				} 
				else if (state.board[row][column] == EMPTY) 
				{
					leftMovePossible = true;
					break;
				}
			}
		}
		if (leftMovePossible) 
		{
			return new int[]{row,column};
		} 
		else 
		{
			return null;
		}
	}
	private int[] checkRightHorizontal(State state, Player player, int row,int column) 
	{

		boolean rightMovePossible = false;
		if (state.board[row][column + 1] == EMPTY || state.board[row][column + 1] == player.name) 
		{
			// the location is either empty or the same player is present
			return null;
		} 
		else 
		{
			column++;
			while (column < 7) 
			{
				column++;
				if (state.board[row][column] == player.name) 
				{
					return null;
				} 
				else if (state.board[row][column] == EMPTY) 
				{
					rightMovePossible = true;
					break;
				}
			}
		}
		if (rightMovePossible) 
		{
			return new int[]{row,column};
		} 
		else 
		{
			return null;
		}
	}
	private int[] checkUpMove(State state, Player player, int row,int column ) 
	{
		boolean upFlipPossible = false;
		if (state.board[row - 1][column] == EMPTY || state.board[row - 1][column] == player.name) 
		{
			// the location is either empty or the same player is present
			return null;
		} 
		else 
		{
			row--;
			while (row > 0) 
			{
				row--;
				if (state.board[row][column] == player.name) 
				{
					return null;
				} 
				else if (state.board[row][column] == EMPTY) 
				{
					upFlipPossible = true;
					break;
				}
			}
		}
		if (upFlipPossible) 
		{
			return new int[]{row,column};
		} 
		else 
		{
			return null;
		}
	}
	private int[] checkDownMove(State state, Player player, int row,int column ) 
	{
		boolean downFlipPossible = false;
		if (state.board[row + 1][column] == EMPTY || state.board[row + 1][column] == player.name) 
		{
			// the location is either empty or the same player is present
			return null;
		} 
		else 
		{
			row++;
			while (row < 7) 
			{
				row++;
				if (state.board[row][column] == player.name) 
				{
					return null;
				} else if (state.board[row][column] == EMPTY) 
				{
					downFlipPossible = true;
					break;
				}
			}
		}
		if (downFlipPossible) 
		{
			return new int[]{row,column};
		} 
		else {
			return null;
		}
	}
	
	private int[] checkNorthEastMove(State state, Player player, int row,int col) 
	{
		boolean northEastMovePossible = false;
		if (state.board[row - 1][col + 1] == EMPTY || state.board[row - 1][col + 1] == player.name) 
		{
			return null;
		} 
		else 
		{
			row--;
			col++;
			while (row > 0 && col < 7) 
			{
				row--;
				col++;
				if (state.board[row][col] == player.name) 
				{
					return null;
				} 
				else if (state.board[row][col] == EMPTY) 
				{
					northEastMovePossible = true;
					break;
				}
			}
		}
		if (northEastMovePossible) 
		{

			return new int[]{row,col};
		} 
		else 
		{
			return null;
		}
	}
	private int[] checkNorthWestMove(State state, Player player, int row,int col) 
	{
		boolean northWestMovePossible = false;
		if (state.board[row - 1][col - 1] == EMPTY || state.board[row - 1][col - 1] == player.name) 
		{
			return null;
		} 
		else 
		{
			row--;
			col--;
			while (row > 0 && col > 0) 
			{
				row--;
				col--;
				if (state.board[row][col] == player.name) 
				{
					return null;
				} 
				else if (state.board[row][col] == EMPTY) 
				{
					northWestMovePossible = true;
					break;
				}
			}
		}
		if (northWestMovePossible) 
		{

			return new int[]{row,col};
		} 
		else 
		{
			return null;
		}
	}
	private int[] checkSouthEastMove(State state, Player player, int row,int col) 
	{
		boolean southEastMovePossible = false;
		if (state.board[row + 1][col + 1] == EMPTY || state.board[row + 1][col + 1] == player.name) 
		{
			return null;
		} 
		else 
		{
			row++;
			col++;
			while (row < 7 && col < 7) 
			{
				row++;
				col++;
				if (state.board[row][col] == player.name) 
				{
					return null;
				} else if (state.board[row][col] == EMPTY) 
				{
					southEastMovePossible = true;
					break;
				}
			}
		}
		if (southEastMovePossible) 
		{

			return new int[]{row,col};
		} 
		else 
		{
			return null;
		}
	}
	private int[] checkSouthWestMove(State state, Player player, int row,int col) 
	{
		boolean southEastMovePossible = false;
		if (state.board[row + 1][col - 1] == EMPTY || state.board[row + 1][col - 1] == player.name) 
		{
			return null;
		} 
		else 
		{
			row++;
			col--;
			while (row < 7 && col > 0) 
			{
				row++;
				col--;
				if (state.board[row][col] == player.name) 
				{
					return null;
				} else if (state.board[row][col] == EMPTY) 
				{
					southEastMovePossible = true;
					break;
				}
			}
		}
		if (southEastMovePossible) 
		{

			return new int[]{row,col};
		} 
		else 
		{
			return null;
		}
	}
	private void addMove(ArrayList<int[]> legalMoves, int[] nextMove) 
	{
		if(nextMove==null)
		{
			return;
		}
		String toCheck=nextMove[0]+""+nextMove[1];
		for(int i=0;i<legalMoves.size();i++)
		{
			String check=legalMoves.get(i)[0]+""+legalMoves.get(i)[1];
			if(check.equals(toCheck))
				return;
		}
		legalMoves.add(nextMove);
	}
	private boolean isTerminalState(State state) 
	{
		// check for terminal state
		boolean hasEmptySquare = false,whitePiece = false,blackPiece = false;

		for (int i = 0; i < state.board.length; i++) 
		{
			for (int j = 0; j < state.board[i].length; j++) 
			{
				if (state.board[i][j] == EMPTY) 
				{
					hasEmptySquare = true;
				}

				if (state.board[i][j] == W) 
				{
					whitePiece = true;
				}

				if (state.board[i][j] == B) 
				{
					blackPiece = true;
				}
			}
		}

		if (!hasEmptySquare) 
		{
			return true;
		} 
		else if (!whitePiece || !blackPiece) 
		{
			return true;
		} 
		return false;
	}
	
	private int getUtilityValue(State state,Player player) 
	{
		int playerPieces = 0;
		int opponentPieces = 0;

		for (int i = 0; i < state.board.length; i++) 
		{
			for (int j = 0; j < state.board[i].length; j++) 
			{
				if (state.board[i][j] != EMPTY) 
				{
					if (state.board[i][j] == player.name) 
					{
						playerPieces++;
					} 
					else 
					{
						opponentPieces++;
					}
				}
			}
		}
		int result=playerPieces - opponentPieces;
		return result;
	}
	
	private int getUtilityValueImproved(State state,Player player) 
	{
		// improved version. add 
		int playerPieces = 0;
		int opponentPieces = 0;

		for (int i = 0; i < state.board.length; i++) 
		{
			for (int j = 0; j < state.board[i].length; j++) 
			{
				if (state.board[i][j] != EMPTY) 
				{
					if (state.board[i][j] == player.name) 
					{
						playerPieces++;
						playerPieces += Math.abs(3-i);
					} 
					else 
					{
						opponentPieces++;
						opponentPieces += Math.abs(3-j);
					}
				}
			}
		}
		int result=playerPieces - opponentPieces;
		return result;
	}
	public int[] alphaBetaSearch(State currentState, Player player, int maxDepth)
	{
		State nextState=new State(currentState);
		Integer alpha=Integer.MIN_VALUE;
		Integer beta=Integer.MAX_VALUE;
		int depth=0;
		
		ArrayList<SearchNode> nodesBucket= new ArrayList<SearchNode>();
		int[][] possibleActions = movesPossible(nextState, player);
		if(isTerminalState(currentState))
		{
			System.out.println("Terminal state reached ");
			return null;	
		}
		if(possibleActions.length>0)
		{
			for(int[]move : possibleActions)
			{
				State newState= getNextState(nextState,move,player);
				nodesExpanded++;
				int val = minValue(newState,move,player.getOpponent(),depth+1,maxDepth,alpha,beta);
				SearchNode node= new SearchNode(move,val);
				nodesBucket.add(node);
				sortBucketNodesForMax(nodesBucket);
				alpha = nodesBucket.get(0).getValue();
			}
		}
		if(nodesBucket.size()>0)
		{
			sortBucketNodesForMax(nodesBucket);
			System.out.println("Moves to take : " + nodesBucket.get(0).toString());
			return nodesBucket.get(0).getMove();
		}
		return null;
	}
	private void sortBucketNodesForMax(ArrayList<SearchNode> nodesBucket) 
	{
		Collections.sort(nodesBucket, new Comparator<SearchNode>() 
				{
			@Override
			public int compare(SearchNode n1, SearchNode n2) 
			{
				if (n1.getValue()!=n2.getValue()) 
				{
					// order highest to lowest
					return (n2.getValue()-n1.getValue());
				} 
				else 
				{
					// if value is same, pick the smallest row number
					int row1 = n1.getMove()[0];
					int col1 = n1.getMove()[1];
					int row2 = n2.getMove()[0];
					int col2 = n2.getMove()[1];
					if (row1 != row2) 
					{
						return row1 - row2;
					} 
					else 
					{
						return col1 - col2;
					}
				}
			}
				});

	}
	private int minValue(State state, int[] prevMove, Player currPlayer, int depth,int maxDepth, Integer alpha, Integer beta) 
	{
		if(debugging) System.out.println("After minValue is called");
		State nextState=new State(state);
		ArrayList<SearchNode> nodesBucket= new ArrayList<SearchNode>();
		if(depth==maxDepth || isTerminalState(nextState))
		{
			int value=getUtilityValue(nextState, currPlayer.getOpponent());
			/*
			 * Use an improved version of utility function in which you can more weightage is given to corner and sides
			 */
			//int value=getUtilityValueImproved(nextState,currPlayer.getOpponent());
			if(debugging) System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ min Depth reached ");
			return value;
		}
		int[][] possibleActions = movesPossible(nextState, currPlayer);
		if(possibleActions.length>0)
		{
			for(int[]move : possibleActions)
			{
				State newState= getNextState(nextState,move,currPlayer);
				nodesExpanded++;
				int val = maxValue(newState,move,currPlayer.getOpponent(),depth+1,maxDepth,alpha,beta);
				SearchNode node= new SearchNode(move,val);
				nodesBucket.add(node);
				sortBucketNodesForMin(nodesBucket);
				int minVal=nodesBucket.get(0).getValue();
				if(minVal<alpha)
				{
					return minVal;
				}
				if(minVal<beta)
				{
					beta=minVal;
				}
				nextState= new State(state);
			}
		}
		sortBucketNodesForMin(nodesBucket);
		return nodesBucket.get(0).getValue();
	}
	
	
	private void sortBucketNodesForMin(ArrayList<SearchNode> nodesBucket) 
	{
		Collections.sort(nodesBucket, new Comparator<SearchNode>() 
				{
			@Override
			public int compare(SearchNode n1, SearchNode n2) 
			{
				if (n1.getValue() != n2.getValue()) 
				{
					return (n1.getValue() - n2.getValue());
				} 
				else 
				{
					// if value is same, pick the smallest row number
					int row1 = n1.getMove()[0];
					int col1 = n1.getMove()[1];
					int row2 = n2.getMove()[0];
					int col2 = n2.getMove()[1];
					if (row1 != row2) 
					{
						return row1 - row2;
					} 
					else 
					{
						return col1 - col2;
					}
				}
			}
				});
	}
	private int maxValue(State state, int[] prevMove, Player currPlayer, int depth,int maxDepth, Integer alpha, Integer beta) 
	{

		if(debugging) System.out.println("After maxvalue is called ");
		State nextState=new State(state);
		ArrayList<SearchNode> nodesBucket= new ArrayList<SearchNode>();
		if(depth==maxDepth || isTerminalState(nextState))
		{
			int value=getUtilityValue(nextState, currPlayer);
			/*
			 * Use an improved version of utility function in which you can more weightage is given to corner and sides
			 */
			//int value=getUtilityValueImproved(nextState,currPlayer);
			if(debugging) System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ max Depth reached");
			return value;
		}
		int[][] possibleActions = movesPossible(nextState, currPlayer);
		if(possibleActions.length>0)
		{
			for(int[]move : possibleActions)
			{
				State newState= getNextState(nextState,move,currPlayer);
				nodesExpanded++;
				int val = minValue(newState,move,currPlayer.getOpponent(),depth+1,maxDepth,alpha,beta);
				SearchNode node= new SearchNode(move,val);
				nodesBucket.add(node);
				sortBucketNodesForMax(nodesBucket);
				int maxVal=nodesBucket.get(0).getValue();
				if(maxVal>=beta)
				{
					return maxVal;
				}
				if(maxVal> alpha)
				{
					alpha=maxVal;
				}
				nextState= new State(state);
			}
		}
		sortBucketNodesForMax(nodesBucket);
		return nodesBucket.get(0).getValue();
	}
	public static void main(String[] args) {
		LoaGame game= new LoaGame();
		State state2 = null;
		int[][] possibleActions=null;
		System.out.println("Please enter the value of depth ");
		Scanner sc=new Scanner(System.in);
		depth=Integer.parseInt(sc.nextLine());
		System.out.println("X corresponds to WHITE, O corresponds to BLACK");
		System.out.println("X has first turn");
		try{
			do{
				System.out.println("Please enter a move like r:c-fr:fc ");
				String input = sc.nextLine();
				String coor[] = input.split("-");
				String startPos[]=coor[0].split(":");
				String finalPos[]=coor[0].split(":");
				int r = Integer.parseInt(startPos[0]);
				int c = Integer.parseInt(startPos[1]);
				
				int fr = Integer.parseInt(finalPos[0]);
				int fc = Integer.parseInt(finalPos[1]);

				if(debugging) System.out.println("R >> " + r + " C >> " + c);
				
				State state=game.getLoaNextState(game.gameState,new int[]{r,c},new int[]{fr,fc},game.player);
				//State state=game.getNextState(game.gameState,new int[]{r,c},game.player);
				state.printBoard();
				//game.gameState.printBoard();
				System.out.println("Now Agent will generate the move");
				Thread.sleep(delay);
				State origState=new State(state);

				long start= new Date().getTime();
				int move[]=game.alphaBetaSearch(state,game.player.getOpponent(),depth);
				System.out.println("Agent generated the move. Nodes expanded >> "+ nodesExpanded);
				long stop=new Date().getTime();
				System.out.println("Moves generated in " + (stop-start) +" ms" );
				nodesExpanded=0;
				
				if(move!=null)
				{
					System.out.println("New State ");
					state2=game.getNextState(origState,move,game.player.getOpponent());
					state2.printBoard();
					game.gameState=state2;
				}
				if(state2!=null)
				{
					possibleActions = game.movesPossible(state2, game.player);
				}
			}while(possibleActions!=null && possibleActions.length>0);

		}catch(Exception e)
		{
			System.out.println("Incorrect input format");
			e.printStackTrace();
		}
		sc.close();
	}
	/**
	 * @param gameState
	 * @param moveStart
	 * @param moveStart 
	 * @param moveStart
	 * @return 
	 */
	private State getLoaNextState(State gameState, int[] moveStart, int[] moveEnd, Player player) 
	{
		if(!isValidMove(gameState,moveStart,moveEnd,player))
			return null;
		State nextState = new State(gameState);
		int r=moveStart[0];
		int c=moveStart[1];
		int fr=moveEnd[0];
		int fc=moveEnd[1];
		nextState.board[fr][fc]=player.name;
		nextState.board[r][c]='-';
		
		return nextState;
	}
	/**
	 * @param gameState
	 * @param moveStart
	 * @param moveEnd
	 * @param player2
	 * @return
	 */
	private boolean isValidMove(State gameState, int[] moveStart,int[] moveEnd, Player player) 
	{
		int r=moveStart[0];
		int c=moveStart[1];
		int fr=moveEnd[0];
		int fc=moveEnd[1];
		
		// check left move
		if(r==fr && fc<c)
		{
			int noCoin=0;
			for(int j=0;j<8;j++)
			{
				if(gameState.board[r][j]!=EMPTY)
				{
					noCoin++;
				}
					
			}
			if(noCoin!=(c-fc))
				return false;
			for(int i=fc;i<c;i++)
			{
				if(gameState.board[r][i]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		
		// check right move
		if(r==fr && fc>c)
		{
			int noCoin=0;
			for(int j=0;j<8;j++)
			{
				if(gameState.board[r][j]!=EMPTY)
				{
					noCoin++;
				}
					
			}
			if(noCoin!=(fc-c))
				return false;
			for(int i=c;i<fc;i++)
			{
				if(gameState.board[r][i]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		
		// check north move
		if(c==fc && fr<r)
		{
			int noCoin=0;
			for(int j=0;j<8;j++)
			{
				if(gameState.board[j][c]!=EMPTY)
				{
					noCoin++;
				}
					
			}
			if(noCoin!=(r-fr))
				return false;
			for(int i=fr;i<r;i++)
			{
				if(gameState.board[i][c]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		
		// check south move
		if(c==fc && r<fc)
		{
			int noCoin=0;
			for(int j=0;j<8;j++)
			{
				if(gameState.board[j][c]!=EMPTY)
				{
					noCoin++;
				}
					
			}
			if(noCoin!=(fc-c))
				return false;
			for(int i=r;i<fr;i++)
			{
				if(gameState.board[i][c]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		
		// check south-east move
		if(r<fr && c<fc && (fr-r == fc-c))
		{
			int noCoin=0;
			if(r>=c)
			{
				int startR=r-c,startC=0;
				for(int k=startR;k<8;k++,startC++)
				{
					if(gameState.board[k][startC]!=EMPTY)
					{
						noCoin++;
					}
						
				}
				
			}
			else
			{
				int startC=r-c,startR=0;
				for(int k=startC;k<8;k++,startR++)
				{
					if(gameState.board[startR][k]!=EMPTY)
					{
						noCoin++;
					}
						
				}
			}
			if(noCoin!=(r-fr))
				return false;
			
			for(int i=r,j=c;i<fr;i++,j++)
			{
				if(gameState.board[i][j]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		
		// check north-east move
		if(r>fr && c<fc && (r-fr == fc-c))
		{
			int noCoin=0;
			if(r>=c)
			{
				int startR=r-c,startC=0;
				for(int k=startR;k<8;k++,startC++)
				{
					if(gameState.board[k][startC]!=EMPTY)
					{
						noCoin++;
					}
						
				}
				
			}
			else
			{
				int startC=r-c,startR=0;
				for(int k=startC;k<8;k++,startR++)
				{
					if(gameState.board[startR][k]!=EMPTY)
					{
						noCoin++;
					}
						
				}
			}
			if(noCoin!=(r-fr))
				return false;
			for(int i=r,j=c;i>fr;i--,j++)
			{
				if(gameState.board[i][j]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		
		// check north-west move
		if(r>fr && c>fc && (r-fr == c-fc))
		{
			int noCoin=0;
			if(r>=c)
			{
				int startR=r-c,startC=0;
				for(int k=startR;k<8;k++,startC++)
				{
					if(gameState.board[k][startC]!=EMPTY)
					{
						noCoin++;
					}
						
				}
				
			}
			else
			{
				int startC=r-c,startR=0;
				for(int k=startC;k<8;k++,startR++)
				{
					if(gameState.board[startR][k]!=EMPTY)
					{
						noCoin++;
					}
						
				}
			}
			if(noCoin!=(r-fr))
				return false;
			for(int i=r,j=c;i>fr;i--,j--)
			{
				if(gameState.board[i][j]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		
		// check south-west move
		if(r<fr && c>fc && (fr-r == c-fc))
		{
			int noCoin=0;
			if(r>=c)
			{
				int startR=r-c,startC=0;
				for(int k=startR;k<8;k++,startC++)
				{
					if(gameState.board[k][startC]!=EMPTY)
					{
						noCoin++;
					}
						
				}
				
			}
			else
			{
				int startC=r-c,startR=0;
				for(int k=startC;k<8;k++,startR++)
				{
					if(gameState.board[startR][k]!=EMPTY)
					{
						noCoin++;
					}
						
				}
			}
			if(noCoin!=(r-fr))
				return false;
			for(int i=r,j=c;i<fr;i++,j--)
			{
				if(gameState.board[i][j]==player.getOpponent().name)
					return false;
			}
			return true;
		}
		return false;
	}
}
