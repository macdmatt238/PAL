package com.mycompany.aquaticlazer;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author macdm
 */
public class GUI extends JFrame implements ActionListener{
    
     private static final long serialVersionUID = 1L;           //private varibles
	private File file;
       private int gotfile = 1, firstPrint = 0, text_count = 0;	//gotfile is set to 1 for false and 0 to true to save space
       private final JMenuBar menuBar;
       private final JMenu File;
       private final JMenu download;
       private final JMenuItem Open;
       private final JLabel label;
       private final JLabel[] textLab = new JLabel[66];
       private String text = "Please open a file";
	public GUI(){       //has to be public so it can be used out of package
		
            
		ImageIcon icon = new ImageIcon("src\\main\\java\\LaseficheIcon.jpg");   //makes image icon
		ImageIcon BackIcon = new ImageIcon("src\\main\\java\\BackGround.jpg");  //makes baground
                
                menuBar = new JMenuBar();           //makes menu bar
                File = new JMenu("Open file");    //makes open file drop down
                download = new JMenu("Download");   //makes download drop down (sadly didn't have time to expand upon
                Open =new JMenuItem("Open");        //makes an open button
                label = new JLabel();                   //makes a jkabel called label
                
                
                menuBar.add(File);      //adds the file drop down in the menu bar
                menuBar.add(download);  //adds the download drop down to the menu bar
                File.add(Open);     //adds the open button to the file drop down
                Open.addActionListener(this);   //adds a action listner to the open button 
        
                
                label.setIcon(BackIcon);    //adds the background to label
                label.setBounds(0, 0, 1920, 1080);  //sets the size and position to label
               
                
                for(int i = 0; i < 66; i++){    //loops 66 times
                textLab[i] = new JLabel("");    //sets the text in textLab to nothing
                if(i <= 33){                         //if it is the first 33 lines of text then
                textLab[i].setBounds(30, -500+25*i, 1920, 1080);    //set there position to this well incromenting them down
                }else
                {textLab[i].setBounds(30, -1325+25*i, 2920, 1080);}     //move them back up to the top of the gui and to the right of the other text, then incroment it down
                textLab[i].setFont(new Font("MV Boil",Font.PLAIN,20));  //set the font and text size
                textLab[i].setForeground(Color.WHITE);  //set the colour to white
                label.add(textLab[i]);      //add the text lines to label
                }
                textLab[0].setText(text);   //set the first line of text to Please open a file
                
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //if the x button is pressed close the window
		this.setTitle("Lazerfische file picker");   //set the windows name to Lazerfische file picker
		this.setIconImage(icon.getImage());         //set the window icon to the Laserfiche icon
                this.setSize(new Dimension(500, 500));  //set the size of the window
		this.setLayout(null);   //set the layout to null
                this.add(label);            //add label (the back ground) to the window
                
		this.setJMenuBar(menuBar);  //add the menu bar to the window
                
                
               
		
		
               
		
		this.setVisible(true);  //set the window visablle
		
		
	}
	
	public File GetFile() {		//gets file path
		
		return file;
		
	}
	
	public int IfFileGot() {	//returns if the file has been gotten or not
		
		return gotfile;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {	

		if(e.getSource() == Open) {			//when button is pressed
			
			JFileChooser FChoose = new JFileChooser();	//makes a JFile Chooser
			int ans = FChoose.showOpenDialog(null);		//saves what button the user presses: cancel, open, exit
			
			if(ans == JFileChooser.APPROVE_OPTION) {		//if the user clicks select then...
				
				File file = new File(FChoose.getSelectedFile().getAbsolutePath()); //get the path of the file
				this.file = file;	//save the path to the file variable
				this.gotfile = 0;	//set the gotfile variable to true
			}
                }
                if(e.getSource() == download) {
                            //when button is pressed
                }
			
                        
               
	}
		
       public void GUIPrintln(String input){    //prints to the gui with a enter key after the text
            
            firstPrint = 1;     //says to the program that it is it is no longer the programs first print after this code
            
            System.out.println(input);          //prints to console
            textLab[text_count].setText(input); //prints to the line of the gui it is on
            text_count++;       //sets to the next line of the gui
        }
        
        public void GUIPrint(String input){
            
            String holder = textLab[text_count].getText();  //sets the text on the gui to holder
            
            if(firstPrint == 0){    //if it is the first print
            
                text = input;       //replace the opening text
                
            firstPrint = 1;
            }
            else{
            
            
                
            text = holder.concat(input);    //add to the text line
            
            }
            textLab[text_count].setText(text);  //sets the text to the line
        }
}
