package line.of.action;

import java.util.Arrays;

public class SearchNode {
	
	public int[] move;
	public int value;
	
	public SearchNode()
	{}
	
	public SearchNode(int[] move, int val) 
	{
		this.move=move;
		this.value=val;
	}

	public int[] getMove() {
		return move;
	}

	public void setMove(int[] move) {
		this.move = move;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SearchNode [move=" + Arrays.toString(move) + "]";
	}
	
}
