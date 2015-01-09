import javax.swing.*;

import source.lib.BUTTON_ACTIONS;

import java.awt.*;
import java.awt.event.*;


public class EinloggenDialog extends JDialog implements ActionListener{   
   private BUTTON_ACTIONS aktion = BUTTON_ACTIONS.CANCEL_BUTTON;
   
   public final static String OK = "OK";
   public final static String CANCEL = "Abbrechen";
   
   private JPanel panelForUser;
   private JLabel myUserJLabel;
   private JTextField myUserJTextField;
   
   private JPanel panelForPassword;
   private JLabel myPasswordJLabel;
   private JPasswordField myJPasswordField;
   
   private JPanel panelForButtons;
   private JButton btnJa, btnAbbrechen;
   
   private String enteredUser, enteredPassword;

   public EinloggenDialog(JFrame owner, String title, String buttonbeschriftung){
      super(owner, title , true); //3. argument: modales fenster 
      
      panelForUser = new JPanel();
      panelForUser.setLayout(new BorderLayout());
      myUserJLabel = new JLabel("Student:");
      panelForUser.add(myUserJLabel, BorderLayout.WEST);
      myUserJTextField = new JTextField();
      myUserJTextField.setEditable(true);
      panelForUser.add(myUserJTextField, BorderLayout.CENTER);
      
      panelForPassword = new JPanel();
      panelForPassword.setLayout(new BorderLayout());
      myPasswordJLabel = new JLabel("Kennwort:");
      panelForPassword.add(myPasswordJLabel, BorderLayout.WEST);
      myJPasswordField = new JPasswordField();
      myJPasswordField.setEditable(true);
      panelForPassword.add(myJPasswordField, BorderLayout.CENTER);        
    
      panelForButtons = new JPanel();
      panelForButtons.setLayout(new BorderLayout());
      btnJa = new JButton(OK);
      btnJa.setActionCommand(OK);
      btnJa.addActionListener(this);
      panelForButtons.add(btnJa, BorderLayout.WEST);
      btnAbbrechen = new JButton(CANCEL);
      btnAbbrechen.setActionCommand(CANCEL);
      btnAbbrechen.addActionListener(this);
      panelForButtons.add(btnAbbrechen, BorderLayout.CENTER);
      
      
      setLayout(new BorderLayout());    
      add(panelForUser, BorderLayout.NORTH);   
      add(panelForPassword, BorderLayout.CENTER);   
      add(panelForButtons, BorderLayout.SOUTH);   

      setSize(250, 100);
      setResizable(false); 
   }
   
   public String getEnteredUser(){
      return enteredUser;
   }
   public String getEnteredPassword(){
      return enteredPassword;
   }
   
   public BUTTON_ACTIONS getButton(){
      return aktion;
   }
   
            //LISTENER
   public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      enteredUser = myUserJTextField.getText();
      enteredPassword = myJPasswordField.getText();
            
      if (cmd.equals(OK) ){
         aktion = BUTTON_ACTIONS.YES_BUTTON;
         setVisible(false);
      }else if(cmd.equals(CANCEL) ){
         aktion = BUTTON_ACTIONS.CANCEL_BUTTON;
         setVisible(false);
      }
   }
}