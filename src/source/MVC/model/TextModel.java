package source.MVC.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JComponent;

import source.EditorInterface.TextModelInterface;
import source.MVC.view.EditorException;
import source.lib.EditorFileChooser;
import source.lib.FktLib;

/**
 * ermöglicht Speichern und Laden von Dateien
 * @author Johannes Benzing
 * @version WS 2013/2014
 */
public class TextModel implements TextModelInterface {
  private boolean dateiGeladen = false;  
  private File datei = new File("unknown.txt");
  
  
  private String editorText = "";
  private EditorFileChooser myEditorFileChooser;
  
  public TextModel() {	 
	  
     if( (IniModel.getIniModel().getIniValue(IniModel.LANG)).equals("en") ){    	
    	  Locale.setDefault(Locale.ENGLISH);
    	  JComponent.setDefaultLocale(Locale.ENGLISH);    	  
      }
     
	  this.myEditorFileChooser = new EditorFileChooser(null, IniModel.getIniModel().getIniValue(IniModel.FILTER),
			  LangModel.getLangValue(LangModel.TEXT_FILTER_DESCRIPTION));
  }
/**
*  oeffnet Filedialog um zu oeffnende Datei auszuwaehlen
* @author Johannes Benzing
*/
  public String readText() throws EditorException{
    String s = "";
    try{
    	datei = myEditorFileChooser.getFileName(myEditorFileChooser.OPEN_DIALOG);
      s = FktLib.readFileChar(datei);
    }
    catch(FileNotFoundException e){
      throw new EditorException("Textdatei nicht gefunden");
    }
    catch(IOException e){
      throw new EditorException("Lesefehler beim Laden der Textdatei");               
    }
    dateiGeladen = true;    
    return s;
  }
/**
*  speichert Datei ohne Dialog als unknown.txt falls noch kein Name ausgewaehlt wurde
* @author Johannes Benzing
*/
  public void writeText(String text)throws EditorException{
    try{
      FktLib.writeFile(datei, text);
    }
    catch(FileNotFoundException e){
      throw new EditorException("Textdatei konnte nicht gespeichert werden");
    }
    catch(IOException e){
      throw new EditorException("Textdatei konnte nicht gespeichert werden");               
    }
 }
/**
*  oeffnet Filedialog um zu speichernde Datei(namen) auszuwaehlen
* @author Johannes Benzing
*/
  public void writeTextWithFileChooser(String text)throws EditorException{
	  try{
		  datei = myEditorFileChooser.getFileName(myEditorFileChooser.SAVE_DIALOG);
		  FktLib.writeFile(datei, text);
	  }
	  catch(FileNotFoundException e){
		  throw new EditorException("Textdatei konnte nicht gespeichert werden");
	  }
	  catch(IOException e){
		  throw new EditorException("Textdatei konnte nicht gespeichert werden");               
	  }
  }
  /**
* @author Johannes Benzing
*/
  public String getFileName(){
    return datei.getName();
  };
}
