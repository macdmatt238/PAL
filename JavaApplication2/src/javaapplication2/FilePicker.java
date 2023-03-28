package javaapplication2;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
       private int gotfile = 1;	//gotfile is set to 1 for false and 0 to true to save space
        JMenuBar menuBar;
        JMenu File;
        JMenu download;
        JMenuItem Open;
        JLabel label;
	FilePicker(){
		
		ImageIcon icon = new ImageIcon("src\\LaseficheIcon.jpg");
		ImageIcon BackIcon = new ImageIcon("src\\BackGround.jpg");
                
                menuBar = new JMenuBar();
                File = new JMenu("Open file");
                download = new JMenu("Download");
                Open =new JMenuItem("Open");
                label = new JLabel();
                
                menuBar.add(File);
                menuBar.add(download);
                File.add(Open);
                Open.addActionListener(this);
        
                
                label.setIcon(BackIcon);
                label.setBounds(0, 0, 1920, 1080);
                
                
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Lazerfische file picker");
		this.setIconImage(icon.getImage());
                this.setSize(new Dimension(500, 500));
		this.setLayout(null);
                this.add(label);
		this.setJMenuBar(menuBar);
                
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1920, 1080));
		panel.setBackground(Color.WHITE);					//set background colour
                panel.setSize(new Dimension(1920, 1080));
		panel.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		
               
                
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
		
}
