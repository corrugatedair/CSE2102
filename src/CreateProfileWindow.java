import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class CreateProfileWindow extends JFrame implements ActionListener{
	private JLabel[] lblData;
	private JTextField[] txtData;
	private TravProfInterface parent;
	private JComboBox illnessTypes;
	private JComboBox allergyTypes;
	public CreateProfileWindow(TravProfInterface travProfInter)
	{
		super("Create Profile");
		parent = travProfInter;
		setSize(500,500);

		setVisible(true);
		setLayout(new BorderLayout());
		int dataFields = 12;
		JPanel pnlCenter = new JPanel(new GridLayout(dataFields, 2)); 
		JLabel title = new JLabel("Create Profile");
		add(title, BorderLayout.NORTH);
		add(pnlCenter,BorderLayout.CENTER);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(this);
		add(submit,BorderLayout.SOUTH);
		lblData = new JLabel[dataFields];
		lblData[0] = new JLabel("Traveler Agent ID");
		lblData[1] = new JLabel("First Name:");
		lblData[2] = new JLabel("Last Name:");
		lblData[3] = new JLabel("Address:");
		lblData[4] = new JLabel("Phone:");
		lblData[5] = new JLabel("Trip Cost:");//Float, NOT A STRING
		lblData[6] = new JLabel("Travel Type:");
		lblData[7] = new JLabel("Payment Type:");
		lblData[8] = new JLabel("Medical Contact:");
		lblData[9] = new JLabel("Medical Phone");	
		lblData[10] = new JLabel("Allergy Type");
		lblData[11] = new JLabel("Illness Type");
		
		txtData = new JTextField[dataFields-2];
		for (int i = 0; i<dataFields-2; i++)
		{
			txtData[i]=new JTextField();
			txtData[i].setSize(50, 15);
			pnlCenter.add(lblData[i]);
			pnlCenter.add(txtData[i]);
		}
		try {
			MedCond retrieveValid = new MedCond("","","none","none");
			
			allergyTypes = new JComboBox(retrieveValid.getValidAlgTypes());
			pnlCenter.add(lblData[10]);
			pnlCenter.add(allergyTypes);
			
			illnessTypes = new JComboBox(retrieveValid.getValidIllnessTypes());
			pnlCenter.add(lblData[11]);
			pnlCenter.add(illnessTypes);
			pack();
		} 
		catch(Exception e){};

	}
	public void actionPerformed(ActionEvent e)
	{
		try 
		{
			TravProf newProf = new TravProf(txtData[0].getText(), txtData[1].getText(), txtData[2].getText(), txtData[3].getText(), txtData[4].getText(), Float.parseFloat(txtData[5].getText()), txtData[6].getText(), txtData[7].getText(), new MedCond(txtData[8].getText(), txtData[9].getText(), allergyTypes.getSelectedItem().toString(), illnessTypes.getSelectedItem().toString()));
			parent.createNewTravProf(newProf);
			dispose();
		}
		catch (NumberFormatException numException)
		{
			JOptionPane.showMessageDialog(this, "Trip Cost must be a number");
		}
		catch (Exception exeption)
		{
			JOptionPane.showMessageDialog(this, "Failed to create new profile. Check your entries.");
		}
	}
}
