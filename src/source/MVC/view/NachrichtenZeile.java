//ä
package source.MVC.view;

import source.EditorInterface.NachrichtenZeileInterface;

import java.awt.*;
import javax.swing.*;

/**
   Die Nachrichtenzeile am unteren Bildschirmrand bestehend aus einem JPanel und einem JTextfield.
   @author Johannnes Benzing
*/
public class NachrichtenZeile extends JPanel
implements NachrichtenZeileInterface {
   private JLabel tLabel;
   private JTextField tField;
   
   public NachrichtenZeile() {
      this("ohne Text", false);
   }                          
   public NachrichtenZeile(String label, boolean aktiv) {
      this(label, aktiv, "Courier New", Font.PLAIN, 12);
   }              
   public NachrichtenZeile(String label, boolean aktiv, String f, int fart, int fs) {
      tLabel = new JLabel(label);
      tLabel.setFont(new Font(f, fart, fs));
      
      tField = new JTextField();
      tField.setEditable(aktiv);     
      tField.setFont(new Font(f, fart, fs));
      
      setLayout(new BorderLayout()); 
      add(tLabel, BorderLayout.WEST);
      add(tField, BorderLayout.CENTER); 
   }
/**
*  zeige uebergebenen String in der Nachrichtenzeile
* @author Johannes Benzing
*/
   public void setMessage(String n){
      tField.setText(n);
   }
   
   public String getMessage(){
      return tField.getText();
   }           
}
