package source.MVC.model;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import source.MVC.view.EditorException;

/**
*  ermoeglicht Mehrsprachigkeit(mometan werden Englisch und Deutsch unterstuetzt)
* @author Johannes Benzing
*/
public class LangModel {
   // Basisname der Sprachdatei, mit Unterverzeichnis source.resource
   public final static String LANGUAGE_FILE = "source.resource.lang" ;
    
   // Name der Sprachdatei
   public final static String RESOURCE_FILE = "resource.file" ; 
     // Keys fÃ¼r die MenÃ¼s
   public final static String FILE = "menu.file" ;
   public final static String FILE_NEW = "menu.file.new" ;
   public final static String FILE_OPEN = "menu.file.open" ;
   public final static String FILE_SAVE = "menu.file.save" ;
   public final static String FILE_SAVE_AS = "menu.file.save.as" ;
   public final static String FILE_EXIT = "menu.file.exit" ;

   public final static String EDIT = "menu.edit" ;
   public final static String EDIT_SEARCH = "menu.edit.search" ;
   public final static String EDIT_CONT_SEARCH = "menu.edit.continue.search" ;
   public final static String EDIT_SUBSTITUTE = "menu.edit.substitute" ;

   // Keys fÃ¼r die Buttons
   public final static String NO_BUTTONTEXT = "no.button" ;
   public final static String YES_BUTTONTEXT = "yes.button" ;
   public final static String OK_BUTTONTEXT = "ok.button" ;
   public final static String CANCEL_BUTTONTEXT = "cancel.button" ;
   
   // Texte
   public final static String TEXT_LINE = "text.line" ; // Label Meldungszeile
   public final static String TEXT_FILTER_DESCRIPTION = "text.filter.description" ; // JChooser-Filter
   public final static String TEXT_UNKNOWN = "text.unkwown" ; // Standard-Dateiname

   public final static String MESSAGE1 = "message1" ; // Datei Ã¼berschreiben?
   public final static String MESSAGE2 = "message2" ; // Standard-Ini-Datei geladen
   public final static String MESSAGE3 = "message3" ; // Programm beenden
   public final static String MESSAGE4 = "message4" ; // Ã„nderungen speichern
   public final static String MESSAGE5 = "message5" ; // Dateidialog abgebrochen!
   public final static String MESSAGE6 = "message6" ; // Zur Zeit keine Funkion!
   public final static String MESSAGE7 = "message7" ; // Datei zuerst speichern!
   public final static String MESSAGE8 = "message8" ; // Datei gespeichert!
   public final static String MESSAGE9 = "message9" ; // Datei erfolgreich geöffnet
   public final static String MESSAGE10 = "message10" ; // erfolgreich gespeichert
   public final static String MESSAGE11 = "message11" ; // gesucht nach
   public final static String MESSAGE12 = "message12" ; // Suche nicht erfolgreich
   public final static String MESSAGE13 = "message13" ; // Änderungen NICHT gespeichert! Wirklich beenden?
   public final static String MESSAGE14 = "message14" ; // Bitte suchtext eingeben

   public final static String CONFIRM_DIALOG_TITLE = "confirm.dialog.title" ;
   public final static String CONFIRM_DIALOG_TEXT = "confirm.dialog.text" ;
   
   // Fehlermeldungen
   public final static String ERROR1 = "error1" ; // Datei nicht gefunden.
   public final static String ERROR2 = "error2" ; // Fehler beim Lesen der Datei.
   public final static String ERROR3 = "error3" ; // Fehler beim Schreiben der Datei.
   public final static String ERROR4 = "error4" ; // Sprachdatei nicht gefunden
   public final static String ERROR5 = "error5" ;
   public final static String ERROR6 = "error6" ;
   public final static String ERROR7 = "error7" ;
   public final static String ERROR8 = "error8" ;

   
  // Nur Klassenvariablen und Klassenmethoden! 
  private static ResourceBundle currentLang = null;
  
	/**
*  Sprachdatei einlesen
* @author Johannes Benzing
*/
  public static void readLangFile(Locale loc) throws EditorException {
   	  try{
   		  currentLang = readResourceBundle(LANGUAGE_FILE, loc );
   	  }
   	  catch(EditorException e){
   		  throw new EditorException("Fehler öffnen ressource");
   	  }
  }  
 /**
*  Hilfsmethode fuer readLangFile
* @author Johannes Benzing
*/
  public static ResourceBundle readResourceBundle(String pfad, Locale lokale) throws EditorException {
	    ResourceBundle lang = null;
	    try {
	       lang = ResourceBundle.getBundle(pfad, lokale);
	    }
	    catch (MissingResourceException mre) {
	       throw new EditorException("NF");
	    }
	    return lang;
  }  

  /**
*  returnt STRING-value zu Key in der Sprachdatei
* @author Johannes Benzing
*/
  public static String getLangValue(String key) {
	  return currentLang.getString(key);
  }
/**
*  returnt INT-value zu Key in der Sprachdatei
* @author Johannes Benzing
*/
   public static int getLangValueToInt(String key) {
	   return Integer.parseInt(currentLang.getString(key));
  }
   
   						//HILFSMETHODEN
/**
*  liest aus einem String das erste Zeichen hinter *. Dies ist das mnemonische Zeichen für die Bedienung mit der Tastatur.
* @author Johannes Benzing
*/
   public static char getMnemonic(String zKette) {
      // Mnemonics nicht setzen, falls 0 zurück gegeben wird!
      char mnemonic = 0 ;
      int pos = zKette.indexOf('*');
      String s = zKette.toUpperCase();
      
      if (pos != -1) {
         mnemonic = s.charAt(pos + 1);
      }
      
      return mnemonic ;
   }
/**
*  Schneidet aus einem String das Zeichen * und fügt die Teilstrings davor und dahinter wieder zu einem String zusammen und gibt diesen zurück.
* @author Johannes Benzing
*/
   public static String getText(String zKette) {
      int pos = zKette.indexOf('*');
      String z = zKette;

      if (pos != -1) {
         z = zKette.substring(0, pos) + zKette.substring(pos + 1);
      }

      return z ;
   }

}
