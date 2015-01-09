package source.MVC.view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import source.MVC.model.LangModel;
import source.lib.BUTTON_ACTIONS;

/**
*  angepasster Suchdialog, der beliebig beschriftete Buttons erlaubt
* @author Johannes Benzing
*/
public class SuchenDialog extends JDialog implements ActionListener{   
   private BUTTON_ACTIONS aktion = BUTTON_ACTIONS.CANCEL_BUTTON;
   
   public final static String OK = LangModel.getText(LangModel.getLangValue(LangModel.OK_BUTTONTEXT));
   public final static String CANCEL = LangModel.getText(LangModel.getLangValue(LangModel.CANCEL_BUTTONTEXT));
   
   private JLabel myJLabel;
   private JTextField myJTextField;
   private String savedStr;
   private JPanel panelForButtons;
   private JButton btnJa, btnAbbrechen;

   public SuchenDialog(JFrame owner, String title, String buttonbeschriftung){
      super(owner, title , true); //3. argument: modales fenster 
      
      
      setLayout(new BorderLayout()); 
      
      myJLabel = new JLabel(LangModel.getLangValue(LangModel.MESSAGE14));
      add(myJLabel, BorderLayout.NORTH);
      
      savedStr = "";
      myJTextField = new JTextField(savedStr);
      myJTextField.setEditable(true);
      add(myJTextField, BorderLayout.CENTER);      
    
      panelForButtons = new JPanel();
      btnJa = new JButton(OK);
      btnJa.setActionCommand(OK);
      btnJa.addActionListener(this);
      panelForButtons.add(btnJa, BorderLayout.WEST);
      btnAbbrechen = new JButton(CANCEL);
      btnAbbrechen.setActionCommand(CANCEL);
      btnAbbrechen.addActionListener(this);
      panelForButtons.add(btnAbbrechen, BorderLayout.EAST);
      add(panelForButtons, BorderLayout.SOUTH);      

      setSize(300,106);
      setResizable(false); 
   }
   
   public String getsavedStr(){
      return savedStr;
   }
   public void setsavedStr(String ns){
      savedStr =  ns;
      myJTextField.setText(ns);
   }
   public void deletesavedStr(){
      savedStr = "";
      myJTextField.setText("");
   }

   public BUTTON_ACTIONS getButton(){
      return aktion;
   }
   
            //LISTENER
   public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      savedStr = myJTextField.getText();
      
      if (cmd.equals(OK) ){
         aktion = BUTTON_ACTIONS.YES_BUTTON;
         setVisible(false);
      }else if(cmd.equals(CANCEL) ){
         aktion = BUTTON_ACTIONS.CANCEL_BUTTON;
         setVisible(false);
      }
   }
}