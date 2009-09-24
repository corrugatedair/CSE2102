
public class TravProfInterface {
	String[] menuChoices;

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
		System.out.println();
	}

}
