package source.MVC.model;

import java.io.File;
import java.util.Properties;

import source.lib.FktLib;

/**
* liest und verwaltet die INI-Datei
* @author Johannes Benzing
*/
public class IniModel {
  // Konstanten für den Namen der INI-Datei
  public final static String INIFILE = "editor.ini";
  public final static String INIFILE_HEADER = "Standard Editor INI-File";

  // Keys für die Ini-Property
  public final static String TITLE = "frame.title";
  public final static String X_SIZE = "frame.x.size";
  public final static String Y_SIZE = "frame.y.size";
  public final static String X_POS = "frame.x.position";
  public final static String Y_POS = "frame.y.position";
  public final static String T_ROWS = "textarea.rows";
  public final static String T_COLUMNS = "textarea.columns";
  public final static String LANG = "current.language";
  public final static String COUNTRY = "current.country";
  public final static String FONT1 = "t1.font";
  public final static String FONT1_SIZE = "t1.font.size";
  public final static String FONT2 = "t2.font";
  public final static String FONT2_SIZE = "t2.font.size";
  public final static String FILTER = "file.filter";
  public final static String FILTER_DESCRIPTION = "message.filter";
  public final static String BACKUP = "file.backup";


//Als modifiziertes Singleton implementiert
	public static IniModel model;
	private Properties iniFile;
	private File datei;
	

  private IniModel() {    
	  datei = new File(INIFILE);
	  try {      
      iniFile = FktLib.readIniFile(datei);
	  } catch (Exception e) {
		  try{
			  iniFile = new StandardIniProperty();
			  FktLib.storeIniFile(datei, iniFile);
		  }
		  catch(Exception e1){
			  System.out.println("Fehler beim lesen der Inidatei");			  
		  }
    }
  }
  
  /**
 * instanziert das Inimodel(Diese Klasse nutzt ein modifiziertes Singleton)
 * @author Johannes Benzing
 */
  public static void createIniModel() {
     if(model == null){
    	 model = new IniModel();    	 
     }    
  }

/**
* Diese Klasse nutzt ein modifiziertes Singleton
* @author Johannes Benzing
*/
  public static IniModel getIniModel() {
    return model;
  }
    
  /**
	* liefert den STRING-Value zu einem Wert in der INI-datei
	* @author Johannes Benzing
	*/
  public String getIniValue(String key) {
	  return iniFile.getProperty(key) ;
  }

 /**
* liefert den INT-Value zu einem Wert in der INI-datei
* @author Johannes Benzing
*/
  public int getIniValueToInt(String key) {
	  //return (new Integer(getIniValue(key))).intValue() ;
	   return Integer.parseInt(getIniValue(key));
  }

/**
*  Innere Klasse für die StandardIni-Property, falls keine INI-Datei vorhanden ist.
* @author Johannes Benzing
*/
  public class StandardIniProperty extends Properties {
    public StandardIniProperty() {
      setProperty(TITLE, "SwingEditor");
      setProperty(X_SIZE, "500");
      setProperty(Y_SIZE, "400");
      setProperty(X_POS, "100");
      setProperty(Y_POS, "100");
      setProperty(T_ROWS, "25");
      setProperty(T_COLUMNS, "60");
      setProperty(LANG, "de");
      setProperty(COUNTRY, "DE");
      setProperty(FONT1, "Courier");
      setProperty(FONT1_SIZE, "14");
      setProperty(FONT2, "Courier");
      setProperty(FONT2_SIZE, "12");
      setProperty(FILTER, "*txt,*java,*html");
      setProperty(FILTER_DESCRIPTION, "Textdateien");
      setProperty(BACKUP, "0");    
    }
  }
}
