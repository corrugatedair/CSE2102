
public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TravProfDB myDB = new TravProfDB("mysavefile.csv");
		
			MedCond myMedCond;
			try
			{
			myMedCond = new MedCond("Mr. Mister","860-123-4567","other","other");
			}
			catch(Exception ex)
			{
				myMedCond = null;
				System.out.println(ex);
			}
			TravProf myTravProf = new TravProf("TA1","Smithy","Rogerman","5c","d",(float)4,"t","r",myMedCond);
			myDB.insertNewProfile(myTravProf);
			
			myTravProf = new TravProf("BU1","William","Boberton","c","d",(float)4,"t","r",myMedCond);
			myDB.insertNewProfile(myTravProf);
			
			myTravProf = new TravProf("TA2","Robert","Howardson","c","d",(float)4,"t","r",myMedCond);
			myDB.insertNewProfile(myTravProf);
			
			myTravProf = new TravProf("TA3","Tammy","Doe","c","d",(float)4,"t","r",myMedCond);
			myDB.insertNewProfile(myTravProf);
			
			myTravProf = new TravProf("TA2","Willio","Howardson","c","d",(float)4,"t","r",myMedCond);
			myDB.insertNewProfile(myTravProf);

			myTravProf = new TravProf("TA2","Willia","Howardson","c","d",(float)4,"t","r",myMedCond);
			myDB.insertNewProfile(myTravProf);
			
			try
			{
				DisplayStuff(myDB);
				
				myDB.writeAllTravProf();
				
				TravProf TP1 = myDB.findProfile("TA2","Howardson");
				System.out.print(TP1.getFirstName() + " ");
				System.out.println(TP1.getLastName());
				
				myDB.deleteProfile("TA2","Howardson");

				DisplayStuff(myDB);
				
				TP1 = myDB.findProfile("TA2","Howardson");
				System.out.print(TP1.getFirstName() + " ");
				System.out.println(TP1.getLastName());
				
				myDB.deleteProfile("TA2","Howardson");

				DisplayStuff(myDB);
				
				TP1 = myDB.findProfile("TA2","Howardson");
				System.out.print(TP1.getFirstName() + " ");
				System.out.println(TP1.getLastName());
			
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}

	}
	
	private static void DisplayStuff(TravProfDB myDB)
	{

		try
		{
		TravProf tempTrav;
		System.out.println();
		System.out.println();
		System.out.println("List of TravProfs in DB:");
		tempTrav = myDB.findFirstProfile();
		System.out.print(tempTrav.getFirstName() + " ");
		System.out.println(tempTrav.getLastName());

		tempTrav = myDB.findNextProfile();
		System.out.print(tempTrav.getFirstName() + " ");
		System.out.println(tempTrav.getLastName());

		tempTrav = myDB.findNextProfile();
		System.out.print(tempTrav.getFirstName() + " ");
		System.out.println(tempTrav.getLastName());

		tempTrav = myDB.findNextProfile();
		System.out.print(tempTrav.getFirstName() + " ");
		System.out.println(tempTrav.getLastName());

		tempTrav = myDB.findNextProfile();
		System.out.print(tempTrav.getFirstName() + " ");
		System.out.println(tempTrav.getLastName());

		tempTrav = myDB.findNextProfile();
		System.out.print(tempTrav.getFirstName() + " ");
		System.out.println(tempTrav.getLastName());
		System.out.println();
		System.out.println();
		}
		catch(Exception ex)
		{}
		
	}

}
