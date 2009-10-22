import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class DeleteProfileWindow extends JFrame implements ActionListener{
	TravProfInterface parent;
	private JLabel[] lblData;
	private JTextField[] txtData;
	
	
	public DeleteProfileWindow(TravProfInterface travProfInter)
	{
		super("Delete Profile");
		parent = travProfInter;
		setSize(500,500);
		JPanel pnlTotal = new JPanel();
		pnlTotal.setSize(500, 500);
		add(pnlTotal);
		setVisible(true);
		setLayout(new BorderLayout());
		int dataFields = 12;
		JPanel pnlCenter = new JPanel(new GridLayout(2, 2)); 
		pnlTotal.add(pnlCenter,BorderLayout.CENTER);
		JButton submit = new JButton("Delete");
		submit.addActionListener(this);
		pnlTotal.add(submit,BorderLayout.SOUTH);
		lblData = new JLabel[2];
		txtData = new JTextField[2];
		lblData[0] = new JLabel("Travel Agent ID");
		lblData[1] = new JLabel("Last Name");
		for (int i =0;i<2;i++)
		{
			txtData[i]=new JTextField();
			txtData[i].setSize(50, 15);
			pnlCenter.add(lblData[i]);
			pnlCenter.add(txtData[i]);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		boolean success = parent.deleteTravProf(txtData[0].getText(),txtData[1].getText());
		String message = "";
		if (success)
			message = "Profile successfully deleted";
		else
			message = "No profiles deleted. Check the ID and Last name";
		JOptionPane.showMessageDialog(this, message);
		this.dispose();
	}
}
