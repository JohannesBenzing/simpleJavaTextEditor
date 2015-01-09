package source.MVC.view;

import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import source.EditorInterface.EditorAreaInterface;
import source.EditorInterface.FrameInterface;
import source.MVC.model.IniModel;
import source.MVC.model.LangModel;
import source.MVC.model.TextModel;
import source.lib.BUTTON_ACTIONS;

/**
   Textfeld des Fensters.
   @author Johannes Benzing
*/
public class EditorArea extends JTextArea implements EditorAreaInterface {
  private boolean textGeaendert = false;
  private FrameInterface fInt;   
  private TextModel myTextModel;
  private String letzterSuchtext;
  private SuchenDialog mySuchenDialog;
 
  
  public EditorArea(FrameInterface fInt) {
    DocumentListener dlis = new MyDocListener();
    this.getDocument().addDocumentListener(dlis); 
    this.fInt = fInt;
    this.myTextModel = new TextModel();
    
    mySuchenDialog = new SuchenDialog(fInt.getFrame(), LangModel.getText(LangModel.getLangValue(LangModel.EDIT_SEARCH)), "TODO BUttonbeschriftungen");
  }
  /**
  *  wird aufgerufen, wenn NEU in der Menuleiste angeklickt wurde
  */
  public void neuDialog(){
	  if(textGeaendert){
		  fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE7));
	  }
	  else{
		  setText("");	  
	  }
    }
  /**
   *  wird aufgerufen, wenn OEFFNEN in der Menuleiste angeklickt wurde
   */
  public void oeffnenDialog(){
    if(!textGeaendert){
      try{
        setText(myTextModel.readText());
        fInt.getFrame().setTitle(myTextModel.getFileName());
        letzterSuchtext = "";
        textGeaendert = false;
        fInt.getMenueLeiste().clickedOpenOrSave();
        fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE9));
      }
      catch(EditorException e){
        fInt.setMessage(e.getMessage());                    
      }
    }
    else{
      fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE7));
    }
  }
  /**
   *  wird aufgerufen, wenn SPEICHERN in der Menuleiste angeklickt wurde
   */
  public void speichernDialog(){
	  try{	  	
		  	myTextModel.writeText(this.getText());
	        fInt.getMenueLeiste().clickedOpenOrSave();
	        fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE8));
	        textGeaendert = false;
	        letzterSuchtext = "";
	        fInt.getFrame().setTitle(myTextModel.getFileName());
	      }
	      catch(EditorException e){
	        fInt.setMessage(e.getMessage());                    
	   }
  }
  /**
   *  wird aufgerufen, wenn SPEICHERNUNTER in der Menuleiste angeklickt wurde
   */
  public void speichernUnterDialog(){
	  try{	  	
		  	myTextModel.writeTextWithFileChooser(this.getText());
	        fInt.getMenueLeiste().clickedOpenOrSave();
	        fInt.setMessage(myTextModel.getFileName() + " " + LangModel.getLangValue(LangModel.MESSAGE10));
	        textGeaendert = false;
	        letzterSuchtext = "";
	        fInt.getFrame().setTitle(myTextModel.getFileName());
	      }
	      catch(EditorException e){
	        fInt.setMessage(e.getMessage());                    
	   }
  }
  /**
   *  wird aufgerufen, wenn BEENDEN in der Menuleiste angeklickt wurde
   */
  public void beendenDialog(){
	  if(textGeaendert){
		  int w;
		  w = JOptionPane.showConfirmDialog(fInt.getFrame(), LangModel.getLangValue(LangModel.MESSAGE13), LangModel.getLangValue(LangModel.MESSAGE3),
		  JOptionPane.YES_NO_CANCEL_OPTION,
		  JOptionPane.QUESTION_MESSAGE);
		  if (w == JOptionPane.YES_OPTION) {
			  	try{
			  		myTextModel.writeText(this.getText());
			  		 fInt.getFrame().setVisible(false);
					  fInt.getFrame().dispose();
					  System.exit(0);
			  	}
			  	catch(EditorException e){
			  		fInt.setMessage(LangModel.getLangValue(LangModel.ERROR3));
			  	}			  
		  }
		  else if (w == JOptionPane.NO_OPTION){			  
			  fInt.getFrame().setVisible(false);
			    fInt.getFrame().dispose();
			    System.exit(0);
		  }
	  }
	  else{
		  	fInt.getFrame().setVisible(false);
		    fInt.getFrame().dispose();
		    System.exit(0);
	  }
  }
  /**
   *  wird aufgerufen, wenn SUCHEN in der Menuleiste angeklickt wurde
   */
  public void suchenDialog(){
    mySuchenDialog.setVisible(true);
    // Auf das Schließen
    // des Dialogs warten...
    if (mySuchenDialog.getButton() == BUTTON_ACTIONS.YES_BUTTON) {
       letzterSuchtext = mySuchenDialog.getsavedStr();
       int success = search(letzterSuchtext, getCaretPosition());
       if (success == -1){
    	   fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE12));
       }
       else{
    	   fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE11) + " \""+mySuchenDialog.getsavedStr() +"\"");
       }
     }
   }         
  /**
   *  wird aufgerufen, wenn WEITERSUCHEN in der Menuleiste angeklickt wurde
   */
  public void weiterSuchenDialog(){
	  int success = search(letzterSuchtext, getCaretPosition());
      if (success == -1){
    	  fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE12));
      }
      else{
    	  fInt.setMessage(LangModel.getLangValue(LangModel.MESSAGE11) + " \""+mySuchenDialog.getsavedStr() +"\"");
      }
  }
  /**
   *  wird aufgerufen, wenn ERSETZEN in der Menuleiste angeklickt wurde
   */
  public void ersetzenDialog(){
    fInt.setMessage("MenÃ¼eintrag SUBSTITUTE gedrÃ¼ckt!");
  }
/**
*  sucht String in Textfeld ab der Startposition(2. Parameter).
* @author Johannes Benzing
*/
  public int search(String suchtext, int start) {
	    String s = this.getText(); 
	    int pos = s.indexOf(suchtext, start);
	    if (pos != -1){
	    	this.select(pos, pos + suchtext.length());
	    }
	    return pos;
  }
  
  
  class MyDocListener implements DocumentListener{
    public void insertUpdate(DocumentEvent e){
      if ( !textGeaendert ) {
      }
      textGeaendert = true;   
      fInt.getFrame().setTitle(myTextModel.getFileName() + "*");
    }
    public void removeUpdate(DocumentEvent e){
      if ( !textGeaendert ) {
      }
      textGeaendert = true;
      fInt.getFrame().setTitle(myTextModel.getFileName() + "*");
    }
    public void changedUpdate(DocumentEvent e){   
    }
  }
}