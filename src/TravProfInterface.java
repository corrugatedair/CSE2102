import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TravProfInterface extends JFrame{
	private String[] menuChoices;
	private String travAgentID;
	private TravProfDB database;
	private JPanel mainContent;
	private JPanel centerContent;
	int options = 5;
	private JRadioButton rbMenuOptions[];
	private TravProfInterface test;

	public TravProfInterface(String fileName) {
		test = this;
		database = new TravProfDB(fileName);
		database.initializeDatabase();
		//Sets up JFrame
		
		this.setTitle("Integrated Travel System");
		this.setVisible(true);
		this.setSize(600,600);
		mainContent = new JPanel(new BorderLayout());
		mainContent.setSize(this.getWidth(), this.getHeight());
		add(mainContent);
		JLabel lblTitle = new JLabel("Integrated Travel System");
		lblTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
		mainContent.add(lblTitle, BorderLayout.NORTH);
		centerContent = new JPanel(new GridLayout(options,1));
		mainContent.add(centerContent,BorderLayout.CENTER);
		JButton btnSelect = new JButton("Select");
		btnSelect.setSize(40, 20);
		btnSelect.addActionListener(new menuButtonListener());
		JPanel SouthPanel = new JPanel();
		mainContent.add(SouthPanel,BorderLayout.SOUTH);
		SouthPanel.add(btnSelect);

		//Array of menu options
		
		menuChoices = new String[options];
		menuChoices[0] = "Create Profile";
		menuChoices[1] = "Delete Profile";
		menuChoices[2] = "Update Profile";
		menuChoices[3] = "Find/Display Profile";
		menuChoices[4] = "Display all profiles";

		ButtonGroup menuGroup = new ButtonGroup();
		
		rbMenuOptions = new JRadioButton[options];
	
		for (int i = 0; i<options; i++)
		{
			rbMenuOptions[i] = new JRadioButton(menuChoices[i]);
			centerContent.add(rbMenuOptions[i], BorderLayout.CENTER);
			menuGroup.add(rbMenuOptions[i]);
		}
		this.pack();

		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        database.writeAllTravProf();
		    }
		});
	    
	}
	public class menuButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int selection=-1;
			for(int i=0; i<options; i++)
			{
				if (rbMenuOptions[i].isSelected())
					selection = i;
			}
			switch (selection) // Runs a method based on the user choice
			{
			case 0:
				//Creates a new travel profile
				new CreateProfileWindow(TravProfInterface.this);
				break;
			case 1: //Deletes a specific profile
				new DeleteProfileWindow(TravProfInterface.this);
				break;
			case 2: //Finds and displays a specific profile
				new UpdateProfile(TravProfInterface.this);
				break;
			case 3: // Modifies a specific profile
				findTravProf();
				break;
			case 4: //Shows all profiles that have the inputted travel agent id
				displayAllProfiles();
				break;
			default: //Nothing selected, click does nothing
				break;
			}
			
		}
	}

	/* Main class, this class doesn't exit until the program terminates. It keeps 
	 * repeating itself in a loop in order to ask the user for a menu choice after
	 * every action. 
	 */
	

	/*
	 * Asks user for info for creating a new travel profile
	 */
	public void createNewTravProf(TravProf newTravProf)
	{
		database.insertNewProfile(newTravProf);
	}


	public boolean deleteTravProf(String travAgentID, String lastName)
	{
		boolean success = true; //Used to tell if deletion was successful
		try
		{
			success = database.deleteProfile(travAgentID, lastName);
		}
		catch (java.lang.Exception e)
		{
			success = false;
		}
		return success;
	}



	private void findTravProf() //Finds a profile and displays it
	{
		try
		{	//Requests last name
			TravProf temp = database.findProfile(travAgentID, inputString("Last name:","string"));
			displayTravProf(temp);
		}
		catch (java.lang.Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	private void displayTravProf(TravProf temp)//Displays travel profile
	{
		System.out.println("Travel Agent ID:" + temp.getTravAgentID());
		System.out.println("Last Name:" + temp.getLastName());
		System.out.println("First Name:" + temp.getFirstName());
		System.out.println("1 - Address:" + temp.getAddress());
		System.out.println("2 - Phone:" + temp.getPhone());
		System.out.println("3 - Payment Type:" + temp.getPaymentType());
		System.out.println("4 - Travel Type:" + temp.getTravelType());
		System.out.println("5 - Trip Cost:" + temp.getTripCost());
		System.out.println("6 - Medical Contact:" + temp.getMedCondInfo().getMdContact());
		System.out.println("7 - Medical Phone:" + temp.getMedCondInfo().getMdPhone());
		System.out.println("8 - Allergy Type:" + temp.getMedCondInfo().getAlgType());
		System.out.println("9 - Illness Type:" + temp.getMedCondInfo().getIllType());
	}
	/* Displays the profiles that match the inputted travel agent ID
	 * 
	 */
	public TravProf updateFindTravProf(String travAgentID, String lastName) throws Exception
	{
		return database.findProfile(travAgentID, lastName);
	}
	
	private void displayAllProfiles()
	{
		try {
			//Gets first profile, checks for matching travagentID
			TravProf temp = database.findFirstProfile();
			if (temp.getTravAgentID().equalsIgnoreCase(travAgentID))
				displayTravProf(temp);
			boolean done = false;
			while (done == false) {//Loop to go through all the profiles
				try
				{
					temp = database.findNextProfile();
					//Check for matching IDs
					if (temp.getTravAgentID().equalsIgnoreCase(travAgentID))
						displayTravProf(temp);
				}
				catch (java.lang.Exception e)
				{
					done = true;//findProfile throws an exception when it has no more
					//profiles, so if there's an exception, we're done cycling through
				}
			}
		}
		catch (java.lang.Exception e)//This is if findfirstprofile throws an exception (no profiles)
		{
			System.out.println(e.getMessage());
		}
	}
	/*
	 * This is for modifying an existing profile. Data is displayed and then user chooses
	 * which data to modify.
	 */
	private void modifyTravProf()
	{
		boolean valid;
		do {
			valid = true;
			try
			{
				TravProf temp = database.findProfile(travAgentID, inputString("Last name? ", "string"));
				displayTravProf(temp);
				int choice = Integer.parseInt(inputString("Modify which field? (Enter the number)","int"));
				switch (choice) {//Picks a function to run based on user choice
				case 1:
					temp.setAddress(inputString("New Address?", "string"));
					break;
				case 2:
					temp.setPhone(inputString("New Phone?", "string"));
					break;
				case 3:
					temp.setPaymentType((inputString("New Payment Type?", "string")));
					break;
				case 4:
					temp.setTravelType(inputString("New Travel Type?", "string"));
					break;
				case 5:
					temp.setTripCost(Float.parseFloat(inputString("New Trip Cost?", "float")));
					break;
				case 6:
					MedCond tempMed = temp.getMedCondInfo();
					tempMed.setMdContact(inputString("New Medical Contact?","string"));
					break;
				case 7:
					MedCond tempMed1 = temp.getMedCondInfo();
					tempMed1.setMdPhone(inputString("New Medical Phone?","string"));
					break;
				case 8:
					MedCond tempMed2 = temp.getMedCondInfo();
					boolean validAlg;//Making sure a valid allergy is entered
					do {
						validAlg = true;
						try
						{
							tempMed2.setAlgType(inputString("New Allergy?","string"));
						}
						catch (java.lang.Exception e)
						{
							validAlg = false;
						}

					} while (validAlg == false);
					break;
				case 9:
					MedCond tempMed3 = temp.getMedCondInfo();
					boolean validIllness;//Checking for valid illness
					do {
						validIllness = true;
						try
						{
							tempMed3.setIllType(inputString("New Illness?","string"));
						}
						catch (java.lang.Exception e)
						{
							validIllness = false;
						}

					} while (validIllness == false);
					break;
				default:
					valid = false;
					break;
				}
			}
			catch (java.lang.Exception e)
			{
				System.out.println(e.getMessage());
			}
		} while (valid == false);

	}
	/* This method is used for getting user input. The method prompts the user
	 * for data with the prompt string argument. If there is an IO error, or the
	 * data isn't valid for the question (ex - entering a word instead of a number
	 * for a question that requires a numerical answer), the method will continue
	 * to prompt the user until it gets valid data.
	 */
	private String inputString(String prompt, String type)
	{
		boolean valid;
		String input = "";
		do {
			System.out.println(prompt);
			valid = true;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = br.readLine();

			} catch (IOException ioe) {
				System.out.println("IO Error. Try entering your input again.");
				valid = false;

			}
			//Making sure that the type that the input matches the type to be returned
			if (type == "string")
			{}
			else if (type == "int")
			{
				try
				{
					Integer.parseInt(input);
				}
				catch (java.lang.Exception e)
				{
					valid = false;
				}
			}
			else if (type == "float")
			{
				try
				{
					Float.parseFloat(input);
				}
				catch (java.lang.Exception e)
				{
					valid = false;
				}
			}//If the input type doesn't match the data type, ask for input again
			if (valid==false)
				System.out.println("That wasn't a valid entry for the question. Try again.");

		} while (valid==false);
		return input;
	}



}
