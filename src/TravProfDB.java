import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class TravProfDB {

	/**
	 * @param args
	 */
	int numTravelers = 0;
	ArrayList<TravProf> travelerList = new ArrayList<TravProf>();
	TravProf mytravelerList[] = new TravProf[10];
	int currentTravelerIndex = 0;
	String fileName = "";
	
	public static void main(String[] args) {

	}

	public TravProfDB(String textFile)
	{
		fileName = textFile;
		// Read the text file, saving the data
		// in it to a local variable
		
	}
	
	public void insertNewProfile(TravProf myTravProf)
	{
		travelerList.add(currentTravelerIndex, myTravProf);
	}
	
	public TravProf findProfile(String myTravAgentID, String lastName)
	{
		currentTravelerIndex = FindTravProfint(myTravAgentID, lastName);
		return travelerList.get(currentTravelerIndex);
	}

	public TravProf findFirstProfile()
	{
		currentTravelerIndex = 0;
		return travelerList.get(0);
	}

	public TravProf findNextProfile()
	{
		currentTravelerIndex++;
		return travelerList.get(currentTravelerIndex);
	}
	
	public boolean deleteProfile(String myTravAgentID, String lastName)
	{
		int myTravProfNum = FindTravProfint(myTravAgentID, lastName);
		travelerList.remove(myTravProfNum);
		numTravelers--;
		
		for (int ii = myTravProfNum+1; ii < travelerList.size(); ii++)
		{
			travelerList.set(ii-1,travelerList.get(ii));
		}
		currentTravelerIndex--;
		
		return false;
	}
	
	public int FindTravProfint(String myTravAgentID, String lastName)
	{
		for (int ii = 0; ii < travelerList.size(); ii++)
		{
			TravProf myTempTraveler = travelerList.get(ii);
			
			if (myTempTraveler.getLastName().equals(lastName))
			{
				if (myTempTraveler.getTravAgentID().equals(myTravAgentID))
				{
					return ii;
				}
			}
		}
		return -1;
	}

	public TravProf FindTravProf(String myTravAgentID, String lastName)
	{
		for (int ii = 0; ii < travelerList.size(); ii++)
		{
			TravProf myTempTraveler = travelerList.get(ii);
			
			if (myTempTraveler.getLastName().equals(lastName))
			{
				if (myTempTraveler.getTravAgentID().equals(myTravAgentID))
				{
					currentTravelerIndex = ii;
					return myTempTraveler;
				}
			}
		}
		return null;
	}
	
	public boolean writeAllTravProf()
	{
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
	        
			for (int ii = 0; ii < travelerList.size(); ii++)
			{
				TravProf myTempTravProf = travelerList.get(ii);
				String travAgentID = myTempTravProf.getTravAgentID();
				String firstName = myTempTravProf.getFirstName();
				String lastName = myTempTravProf.getLastName();
				String address = myTempTravProf.getAddress();
				String phone = myTempTravProf.getPhone();
				float tripCost = myTempTravProf.getTripCost();
				String travelType = myTempTravProf.getTravelType();
				String paymentType = myTempTravProf.getPaymentType();
				MedCond medCondInfo = myTempTravProf.getMedCondInfo();
				String mdContact = medCondInfo.getMdContact();
				String mdPhone = medCondInfo.getMdPhone();
				String algType = medCondInfo.getAlgType();
				String illType = medCondInfo.getIllType();

				out.write(travAgentID + ", " + firstName + ", " + lastName + ", " + address
						+ ", " + phone + ", " + tripCost + ", " + travelType + ", " + paymentType
						+ ", " + mdContact + ", " + mdPhone + ", " + algType + ", " + illType + "\n");

			}
	        out.close();
	    } catch (Exception e) {
	    }

		return true;
	}
	
	public void initializeDatabase()
	{
		try {
	        BufferedReader in = new BufferedReader(new FileReader(fileName));
	        String str;
	        while ((str = in.readLine()) != null) {
	            process(str);
	        }
	        in.close();
	    } catch (Exception e) {
	    }

	}

	private void process(String str) {

		String[] mystr = new String[12];
		mystr = str.split(",");
		
		MedCond medCondInfo;
		try {
			medCondInfo = new MedCond(
					mystr[8],
					mystr[9],
					mystr[10],
					mystr[11]);
		} catch (Exception e) {
			medCondInfo = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		insertNewProfile(new TravProf(
				mystr[0],
				mystr[1],
				mystr[2],
				mystr[3],
				mystr[4],
				(float)Integer.parseInt(mystr[5]),
				mystr[6],
				mystr[7],
				medCondInfo));

	}
}
