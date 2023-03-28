package javaapplication2;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author macdm
 */
public class FilePicker extends JFrame implements ActionListener{
    
     private static final long serialVersionUID = 1L;
	private File file;
	private JButton SelectButton;
	private int gotfile = 1;	//gotfile is set to 1 for false and 0 to true to save space
	FilePicker(){
		
		ImageIcon icon = new ImageIcon("LazerficheIcon.jpg");
		Color color = new Color(255, 128, 0);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Lazerfische file picker");
		this.setIconImage(icon.getImage());
		this.setLayout(null);
		this.setSize(new Dimension(500, 500));
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setBackground(Color.WHITE);					//set background colour
		panel.setLayout(null);
		panel.setSize(new Dimension(500, 500));
		
		
		SelectButton = new JButton("Select File");	//set button text
		SelectButton.setBounds(140,190,200,70);
		SelectButton.addActionListener(this);
		SelectButton.setBackground(color);
		SelectButton.setFocusable(false);
		SelectButton.setForeground(Color.WHITE);			//set button colour
		SelectButton.setFont(new Font("Comic Sans", Font.BOLD, 25));
		SelectButton.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(SelectButton);
		this.add(panel);
		
		this.setVisible(true);
		
		
	}
	
	public File GetFile() {		//gets file path
		
		return file;
		
	}
	
	public int IfFileGot() {	//returns if the file has been gotten or not
		
		return gotfile;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {	

		if(e.getSource() == SelectButton) {			//when button is pressed
			
			JFileChooser FChoose = new JFileChooser();	//makes a JFile Chooser
			int ans = FChoose.showOpenDialog(null);		//saves what button the user presses: cancel, open, exit
			
			if(ans == JFileChooser.APPROVE_OPTION) {		//if the user clicks select then...
				
				File file = new File(FChoose.getSelectedFile().getAbsolutePath()); //get the path of the file
				this.file = file;	//save the path to the file variable
				this.gotfile = 0;	//set the gotfile variable to true
			}
		}
		
	}
}
