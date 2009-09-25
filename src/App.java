
public class App {

	public App() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App run = new App();
		run.execute();

	}
	public void execute()
	{
		TravProfInterface program = new TravProfInterface("database.txt");
	}

}
