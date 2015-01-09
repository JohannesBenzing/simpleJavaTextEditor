package source.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Die Klasse enthaelt Methoden, die keiner speziellen
 * Klasse zugeordnet werden koennen. Alle Methoden sind als
 * Klassenmethoden impementiert. Die Klasse wird als
 * Funktionsbibliothek benutzt.
 *
 * @author  Johannes Benzing
 * @version 1.0
 */
public class FktLib {
  public final static String CR_LF = (String) System.getProperty("line.separator");
  public final static String TEXTAREA_LF = "\n";
  
  /**
  * f ist das Fenster, an das das Dialogfenster gebunden ist.
  * titel enthÃ¤lt den Fenstertitel des Dialogfensters,
  * nachricht die ausgegeben Nachricht.
  */
  public static boolean anfrage(JFrame f, String titel, String nachricht) {
    Object[] options = { "Ja", "Nein", "Abbrechen"};
    int n = JOptionPane.showOptionDialog(
    f,
    nachricht,
    titel,
    JOptionPane.DEFAULT_OPTION, // hier keine Wirkung
    JOptionPane.QUESTION_MESSAGE,
    null, //kein eigenes Icon
    options, //Buttonbeschriftungen
    options[0]) ; //aktiver Button
    
    if (n == 0){
      return true;
    }     
    return false;    
  }  
		/**
		 * char-weises lesen einer Textdatei
		 * @author Johannes Benzing
		 */
  public static String readFileChar(File datei)throws FileNotFoundException, IOException{            
        int iChar; 
       char zeichen ; 
       boolean eof = false; 
       StringBuffer puffer = new StringBuffer(200); 
       String erg = "";                   
     
     FileReader iDatei = new FileReader(datei); 
        iChar = iDatei.read(); 
        eof = (iChar == -1); 
      while (!eof) { 
        zeichen = (char) iChar; 
        puffer.append(zeichen); 
        
        iChar = iDatei.read(); 
        eof = iChar == -1; 
      }                       
      erg = puffer.toString(); 
      iDatei.close(); 
    
      return erg;                  
   }
		/**
		 * zeilenweises Lesen einer Textdatei
		 * @author Johannes Benzing
		 */
   public static String readFile(File datei) throws FileNotFoundException, IOException{
      FileReader iDatei ;
      BufferedReader ibDatei ;
      String zeile ;
      boolean eof = false;
      StringBuffer puffer = new StringBuffer(200);
      String erg = "";  
     
         iDatei = new FileReader(datei);
         ibDatei = new BufferedReader(iDatei) ;
         zeile = ibDatei.readLine();
         eof = zeile == null ;

         while (!eof) {
            puffer.append(zeile + CR_LF);
            zeile = ibDatei.readLine();
            
            if (zeile == null) System.out.println("****EOF erreicht!****");
            eof = zeile == null ;
         }

         erg = puffer.toString();
         ibDatei.close();
         iDatei.close();         
 
      return erg;
   }
		/**
		 * speichert eine Textdatei
		 * @author Johannes Benzing
		 */
   public static void writeFile(File datei, String textPuffer) throws FileNotFoundException, IOException{
      BufferedWriter obDatei ;
      FileWriter oDatei;    
    
         oDatei = new FileWriter(datei);
         obDatei = new BufferedWriter(oDatei) ;

         obDatei.write(textPuffer) ;
         obDatei.flush();
         oDatei.close();        
   }
		/**
		 * Lese eine Textdatei
		 * @author Johannes Benzing
		 */
   public static Properties readIniFile(File datei) throws Exception{
	   Properties iniFile = new Properties();
	   try {
		// auch als FileInputStream möglich
		// FileInputStreamin = new FileInputStream(f);
		FileReader in = new FileReader(datei);
		iniFile.load(in);
		in.close();
		}
		catch(Exception e) {
		 throw new Exception("inidatei konnte nicht gelesen werden");
		}
		return iniFile;	   
   }
		/**
		 * Speichert eine Textdatei
		 * @author Johannes Benzing
		 */
   public static void storeIniFile(File datei, Properties iniFile) throws Exception{
	   try {
		   // auch als FileOutputStream möglich
		   // FileOutputStream out = new FileOutputStream(f);
		   FileWriter out = new FileWriter(datei);
		   // iniFile siehe Klasse IniModel
		   iniFile.store(out, "STANDARD INI File");
		   out.close();
		   }
		 catch(IOException ioe) {
		   // Sonst irgendwas ist schief gegangen
		   throw new Exception("inidatei konnte nicht gespeichert werden");
		  }	   
   }
   
}
