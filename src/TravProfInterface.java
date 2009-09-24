import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class TravProfInterface {
	String[] menuChoices;
	String travAgentID;

	public TravProfInterface(String fileName) {
		new TravProfDB(fileName);
		menuChoices = new String[9];
		menuChoices[0] = "Create a new traveler profile";
		menuChoices[1] = "Delete a traveler by Name and Travel Agent ID";
		menuChoices[2] = "Find and display a Traveler Profile by Name and Travel Agent ID";
		menuChoices[3] = "Traveler Profile Modifications";
		menuChoices[4] = "Display all profiles";
		menuChoices[5] = "Write to Database";
		menuChoices[6] = "Initialize Database";
	}
	public void getUserChoice()
	{
		System.out.println("Menu:");
		for (int i=0; i<menuChoices.length; i++)
		{
			System.out.println(i + " - " + menuChoices[i]);
		}
		
		boolean valid = true;

		do {
			
			try
			{
				int menuOption = Integer.parseInt(inputString()[0]);
				
				switch (menuOption) 
				{
					case 0: 
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
					default:
						valid = false;
				}
			}
			catch (java.lang.Exception e)
			{
				valid = false;
			}
				
		} while(valid == false);
		String[] temp = inputString();
		valid = true;
		do 
		{
			if (temp[1].equalsIgnoreCase("failed")) {
				valid = false;
			}
		} while (valid==false);
	}

	
	private TravProf createNewTravProf()
	{
		String[][] requestedData = new String[8][2];
		requestedData[0][0] = "Traveler Agent ID";
		
	}
	
	private String[] inputString()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = new String[2];
		input[1] = "";
		try {
	         input[0] = br.readLine();
		} catch (IOException ioe) {
	         System.out.println("IO Error");
	         input[1] = "failed";
	         
		}
		return input;
	}
	}
	

}
