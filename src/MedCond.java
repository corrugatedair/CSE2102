public class MedCond {
	String mdContact; //Name of medical contact for traveler (physicians name)
	String mdPhone; //Phone number of medical contact
	String algType; //Allergies, user must select from none, food, medication, other
	String[] validAlgTypes;
	String illType; //Illnesses, user must select from: none, heart, diabetes, asthma, other
	String[] validIllnessTypes;

	
	/* This class will throw Exceptions if the allergy type of the illness type is not
	 * listed in the valid Allergy/Illness lists;
	 */
	public MedCond(String _mdContact, String _mdPhone, String _algType, String _illType) throws Exception {
		//List of valid entries for algType
		validAlgTypes = new String[4];
		validAlgTypes[0] = "none";
		validAlgTypes[1] = "food";
		validAlgTypes[2] = "medication";
		validAlgTypes[3] = "other";
		
		//List of valid entries for illType
		validIllnessTypes = new String[5];
		validIllnessTypes[0] = "none";
		validIllnessTypes[1] = "heart";
		validIllnessTypes[2] = "diabetes";
		validIllnessTypes[3] = "asthma";
		validIllnessTypes[4] = "other";
		
		setMdContact(_mdContact);
		setMdPhone(_mdPhone);
		setAlgType(_algType);
		setIllType(_illType);
		
		
	}
	
	//Getters & Setters
	public String getMdContact()
	{
		return mdContact;
	}
	public void setMdContact(String _mdContact)
	{
		mdContact = _mdContact;
	}
	
	public String getMdPhone()
	{
		return mdPhone;
	}
	public void setMdPhone(String _mdPhone)
	{
		mdPhone = _mdPhone;
	}
	
	public String getAlgType()
	{
		return algType;
	}
	public void setAlgType(String _algType) throws Exception
	{
		if (!checkIfValid(_algType, validAlgTypes))
			throw new java.lang.Exception("Invalid Allergy Type");
		algType = _algType;
	}
	
	public String getIllType()
	{
		return illType;
	}
	public void setIllType(String _illType) throws Exception
	{
		if (!checkIfValid(_illType, validIllnessTypes))
			throw new java.lang.Exception("Invalid Illness Type");
		illType = _illType;
	}

	private boolean checkIfValid(String newEntry, String[] validEntries)
	{
		boolean found = false; //Change to true if match between valid entry and new entry
		for(int i=0; i<validEntries.length; i++)
		{
			if (validEntries[i].equalsIgnoreCase(newEntry))//Check for match between new and valid
				found = true; //Means there is a valid match
		}
		return found;
	}
}
