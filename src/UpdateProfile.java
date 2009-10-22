import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateProfile extends JFrame implements ActionListener{
	private TravProfInterface parent;
	private JTextField txtTravID;
	private JTextField txtLastName;
	private JComboBox cmbModify;
	private TravProf modProf;
	private int modifyIndex;
	private JTextField[] txtData;
	private JComboBox allergyTypes, illnessTypes;
	private JFrame updateFrame;
	
	public UpdateProfile(TravProfInterface travProfInter)
	{
		super("Update Profile");
		parent = travProfInter;
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new GridLayout(3,2));
		add(pnlCenter,BorderLayout.CENTER);
		JButton submit = new JButton("Submit");
		submit.addActionListener(this);
		JPanel pnlSouth = new JPanel();
		add(pnlSouth,BorderLayout.SOUTH);
		pnlSouth.add(submit);
		
		String[] comboOptions = new String[11];
		/* Note - These are ONE OFF from the corresponding JLabels and
		 * JTextFields. That is because TravelAgentID is not a modifiable
		 * field. When accessing the combobox, make sure you offset the
		 * index by 1.
		 */
		comboOptions[0] = "First Name:";
		comboOptions[1] = "Last Name:";
		comboOptions[2] = "Address:";
		comboOptions[3] = "Phone:";
		comboOptions[4] = "Trip Cost:";//Float, NOT A STRING
		comboOptions[5] = "Travel Type:";
		comboOptions[6] = "Payment Type:";
		comboOptions[7] = "Medical Contact:";
		comboOptions[8] = "Medical Phone";	
		comboOptions[9] = "Allergy Type";
		comboOptions[10] = "Illness Type";
		
		JLabel lblTravID = new JLabel("Travel Agent ID");
		JLabel lblLastName = new JLabel("Last Name");
		JLabel lblModify = new JLabel("Field to Modify");
		
		txtTravID = new JTextField();
		txtTravID.setSize(50, 15);
		txtLastName = new JTextField();
		txtTravID.setSize(50,15);
		cmbModify = new JComboBox(comboOptions);
		
		pnlCenter.add(lblTravID);
		pnlCenter.add(txtTravID);
		pnlCenter.add(lblLastName);
		pnlCenter.add(txtLastName);
		pnlCenter.add(lblModify);
		pnlCenter.add(cmbModify);
		
		pack();
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		try {
			modProf = parent.updateFindTravProf(txtTravID.getText(), txtLastName.getText());
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(this, "Traveler Profile not found");
			dispose();
			return;
		}
		
		updateFrame = new JFrame("Update Profile");
		updateFrame.setVisible(true);

		updateFrame.setSize(500,500);

		updateFrame.setLayout(new BorderLayout());
		int dataFields = 12;
		JPanel pnlCenter = new JPanel(new GridLayout(dataFields, 2)); 
		JLabel title = new JLabel("Update Profile");
		updateFrame.add(title, BorderLayout.NORTH);
		updateFrame.add(pnlCenter,BorderLayout.CENTER);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new updateListener());
		updateFrame.add(submit,BorderLayout.SOUTH);
		JLabel[] lblData = new JLabel[dataFields];
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
		txtData[0] = new JTextField(modProf.getTravAgentID());
		txtData[1] = new JTextField(modProf.getFirstName());
		txtData[2] = new JTextField(modProf.getLastName());
		txtData[3] = new JTextField(modProf.getAddress());
		txtData[4] = new JTextField(modProf.getPhone());
		txtData[5] = new JTextField(String.valueOf(modProf.getTripCost()));
		txtData[6] = new JTextField(modProf.getTravelType());
		txtData[7] = new JTextField(modProf.getPaymentType());
		txtData[8] = new JTextField(modProf.getMedCondInfo().getMdContact());
		txtData[9] = new JTextField(modProf.getMedCondInfo().getMdPhone());
		//txtData[10] = new JTextField(modProf.getMedCondInfo().getAlgType());
		//txtData[11] = new JTextField(modProf.getMedCondInfo().getIllType());
		for (int i = 0; i<dataFields-2; i++)
		{
			txtData[i].setSize(50, 15);
			txtData[i].setEditable(false);
			pnlCenter.add(lblData[i]);
			pnlCenter.add(txtData[i]);
		}
		
		try {
			MedCond retrieveValid = new MedCond("","","none","none");
			
			allergyTypes = new JComboBox(retrieveValid.getValidAlgTypes());
			pnlCenter.add(lblData[10]);
			allergyTypes.setSelectedItem(modProf.getMedCondInfo().getAlgType());
			allergyTypes.setEnabled(false);
			pnlCenter.add(allergyTypes);
			
			illnessTypes = new JComboBox(retrieveValid.getValidIllnessTypes());
			pnlCenter.add(lblData[11]);
			illnessTypes.setSelectedItem(modProf.getMedCondInfo().getIllType());
			pnlCenter.add(illnessTypes);
			illnessTypes.setEnabled(false);
			
			modifyIndex = cmbModify.getSelectedIndex() + 1; //Offset by one. See combobox.
			if (modifyIndex < dataFields-2 && modifyIndex >= 1)
				txtData[modifyIndex].setEditable(true);
			else if (modifyIndex == 10)
				allergyTypes.setEnabled(true);
			else if (modifyIndex == 11)
				illnessTypes.setEnabled(true);
			
			updateFrame.pack();
		} 
		catch(Exception exception){};
		
	}
	/*
	 * Based on the index of the selected item in cmbModify, update the field
	 * that was enabled. If there's an invalid entry (ie, trip cost is not a 
	 * number) the user is notified and given opportunity to change it.
	 */
	public class updateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			switch(modifyIndex)
			{
				case 1:
					modProf.setFirstName(txtData[modifyIndex].getText());
					break;
				case 2:
					modProf.setLastName(txtData[modifyIndex].getText());
					break;
				case 3:
					modProf.setAddress(txtData[modifyIndex].getText());
					break;
				case 4:
					modProf.setPhone(txtData[modifyIndex].getText());
					break;
				case 5:
					try 
					{
						float cost = Float.parseFloat(txtData[modifyIndex].getText());
						modProf.setTripCost(cost);
					}
					catch (Exception exception)
					{
						JOptionPane.showMessageDialog(UpdateProfile.this, "Trip cost must be a number");
						return;
					}
					break;
				case 6:
					modProf.setTravelType(txtData[modifyIndex].getText());
					break;
				case 7:
					modProf.setPaymentType(txtData[modifyIndex].getText());
					break;
				case  8:					
					modProf.getMedCondInfo().setMdContact((txtData[modifyIndex].getText()));
					break;
				case  9:					
					modProf.getMedCondInfo().setMdPhone((txtData[modifyIndex].getText()));
					break;
				case 10:
					try {
						modProf.getMedCondInfo().setAlgType(allergyTypes.getSelectedItem().toString());
					}
					catch (Exception exception)
					{
						JOptionPane.showMessageDialog(UpdateProfile.this, "Despite there being a dropdown, somehow you entered an invalid allergy type");
						return;
					}
					break;
				case 11:
					try {
						modProf.getMedCondInfo().setIllType((illnessTypes.getSelectedItem().toString()));
					}
					catch (Exception exception)
					{
						JOptionPane.showMessageDialog(UpdateProfile.this, "Despite there being a dropdown, somehow you entered an invalid illness type");
						return;
					}
					break;
			}
			//Get rid of both windows
			updateFrame.dispose();
			UpdateProfile.this.dispose();
			
			
			
		}
	}
}
