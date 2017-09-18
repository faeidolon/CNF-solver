/**
 * 
 * @author Faelan S.
 * 
 * Solves strings of CNF input given a specific set of formal rules
 *
 */
public class Main 
{

	public static void main(String[] args) 
	{
		/*if(args.length != 1)
		{
			System.out.println("usage: java HW4 <dataFile>");
			System.exit(-1);
		}*/
		
		int numTokens = 0;
		numTokens = 5;
		
		String[] bottomRow = new String[numTokens];
		
		bottomRow[0] = "A";
		bottomRow[1] = "B";
		bottomRow[2] = "A";
		bottomRow[3] = "A";
		bottomRow[4] = "C";
		
		//TODO: set up to handle conflicts
		//linked lists???
		
		// basing this off of bottom-up parsing?
		String parseBlock[][] = new String[numTokens][numTokens];
		
		for(int i = 0; i < numTokens; i++)
		{
			parseBlock[numTokens - 1][i] = bottomRow[i];
		}
		
		int depth = numTokens - 2;
		int maxTries = 1;
		while(depth >= 0)
		{
			for(int col = 0; col <= depth; col++)
			{
				for(int currTry = 0; currTry < maxTries; currTry++)
				{
					String result = getCNK(parseBlock[depth + maxTries - currTry][col], 
							parseBlock[depth + currTry + 1][currTry + col + 1]);
					if(result != null) parseBlock[depth][col] = result;
				}
			}
			
			maxTries++;
			depth--;
		}
		
		for(int i = 0; i < numTokens; i++)
		{
			for(int j = 0; j < i + 1; j++)
			{
				if(parseBlock[i][j] == null) System.out.print("0  ");
				else System.out.print(parseBlock[i][j] + "  ");
			}
			System.out.println("");
		}
	}

	private static String getCNK(String lhs, String rhs) 
	{
		if(lhs == null || rhs == null) return null;
		//TODO: use a linked list of lists for rules, instead of an an array to save space?
		if(lhs.equals("A"))
		{
			if(rhs.equals("B")) return "Z";
			if(rhs.equals("C")) return "F";
			if(rhs.equals("F")) return "D";
		}
		if(lhs.equals("Z"))
		{
			if(rhs.equals("D")) return "S";
		}
		return null;
	}
}