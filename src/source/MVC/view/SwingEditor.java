//ä
package source.MVC.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import source.EditorInterface.EditorAreaInterface;
import source.EditorInterface.FrameInterface;
import source.EditorInterface.MenueLeisteInterface;
import source.MVC.controller.FensterListener;
import source.MVC.model.IniModel;
import source.MVC.model.LangModel;

/**
   Hauptfenster.
   @author Johannes Benzing
*/
public class SwingEditor extends JFrame {
  private JFrame fenster;
  private MenueLeiste menueLeiste;
  private EditorArea tArea;
  private NachrichtenZeile nZeile;
  
  public SwingEditor() {
    try{
      UIManager.setLookAndFeel(new NimbusLookAndFeel());
    }   catch(UnsupportedLookAndFeelException ex){
      ex.printStackTrace();         
    }    
    
    
    
    
    boolean iniWarVorhanden = false;
    if(new File(IniModel.INIFILE).isFile()){
      iniWarVorhanden = true;    
    }
    IniModel.createIniModel();   
    
    
    try{
      LangModel.readLangFile(new Locale( IniModel.getIniModel().getIniValue(IniModel.LANG) , IniModel.getIniModel().getIniValue(IniModel.COUNTRY)) );
    }
    catch (EditorException e) {
      System.out.println("Sprachdatei fehlt! Language File is missing");
    }
    
    
    FrameAdapter fInt = new FrameAdapter();
    //fenster = new JFrame();                      
    fenster = this;  
    nZeile = new NachrichtenZeile(LangModel.getLangValue(LangModel.TEXT_LINE), false, IniModel.getIniModel().getIniValue(IniModel.FONT1) ,Font.PLAIN, IniModel.getIniModel().getIniValueToInt(IniModel.FONT1_SIZE));  
    tArea = new EditorArea(fInt);
    
    if(!iniWarVorhanden){
      nZeile.setMessage(LangModel.getLangValue(LangModel.MESSAGE2));
    }
    
    
    Container cp = fenster.getContentPane();
    cp.setLayout(new BorderLayout());  
    cp.add(tArea, BorderLayout.CENTER);
    cp.add(nZeile, BorderLayout.SOUTH);
    
    
    fenster.setTitle(IniModel.getIniModel().getIniValue(IniModel.TITLE));   
    fenster.setSize(IniModel.getIniModel().getIniValueToInt(IniModel.X_SIZE) , IniModel.getIniModel().getIniValueToInt(IniModel.Y_SIZE));
    fenster.setMinimumSize(new Dimension(300, 300));
    fenster.setLocation(IniModel.getIniModel().getIniValueToInt(IniModel.X_POS) , IniModel.getIniModel().getIniValueToInt(IniModel.Y_POS));   
    fenster.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);      
    menueLeiste = new MenueLeiste(fInt);
    fenster.setJMenuBar(menueLeiste);
    fenster.addWindowListener(new FensterListener(fInt));
    
    fenster.setVisible(true);
  }
  
  class FrameAdapter
  implements FrameInterface{               
    public JFrame getFrame(){
      return fenster;
    }       
    public MenueLeisteInterface getMenueLeiste(){
      return menueLeiste;
    }
    public EditorAreaInterface getEditorArea(){
      return tArea;
    }
    public void setMessage(String nachricht){
      nZeile.setMessage(nachricht);
    }      
  }
}
