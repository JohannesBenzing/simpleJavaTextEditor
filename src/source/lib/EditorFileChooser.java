package source.lib;
import java.io.File;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import source.MVC.model.IniModel;
import source.MVC.model.LangModel;
import source.MVC.view.EditorException;

/**
 * Datei-dialog, der fuer das Auswaehlen der Datei beim Oeffnen oder Speichern zustaendig ist
 * @author Johannes Benzing
 */
public class EditorFileChooser extends JFileChooser {
   // globale Konstanten für den Filedialog
   public final static int OPEN_DIALOG = 1;
   public final static int SAVE_DIALOG = 2;

   public final static char EXTENTION_SEPARATOR = '.';
   public final static char FILTER_SEPARATOR = ',';
   public final static String CURRENT_DIRECTORY = ".";

   // Attribute des EditorFileChooser
   private JFrame fenster;
   private FileFilter dateiFilter;
   private String filterBeschreibung;
   private String filter;

   public EditorFileChooser(JFrame fenster, String filter,
         String filterBeschreibung) {
      this.fenster = fenster;
      this.filter = filter;
      this.filterBeschreibung = filterBeschreibung;

      setCurrentDirectory(new File(CURRENT_DIRECTORY));
      setFileSelectionMode(JFileChooser.FILES_ONLY);

      dateiFilter = new TextFilter();
      addChoosableFileFilter(dateiFilter);
      setFileFilter(dateiFilter);
   }

   
   /**
    *  Oeffnet einen Dialog zum Einlesen eines Files 
    *
    * @param mod
    * @return file
    * @throws EditorException
    */
   public File getFileName(int mod) throws EditorException {
      File datei = null;
      int auswahl = 0;

      if (mod == OPEN_DIALOG) {
         auswahl = showOpenDialog(fenster);        
      }

      if (mod == SAVE_DIALOG) {
         auswahl = showSaveDialog(fenster);
      }

      if (auswahl == JFileChooser.APPROVE_OPTION) {
         datei = getSelectedFile();
      }
      if (datei == null) {
         throw new EditorException(LangModel.getLangValue(LangModel.MESSAGE5));
      }

      return datei;
   }


/**
 * Innere Klasse dient als Filter
 * @author Johannes Benzing
 */
   public class TextFilter extends FileFilter {
      private String[] filterExt;

      public TextFilter() {
         // Filter wird hier in seine Bestandteile zerlegt
         filterExt = filter.split(FILTER_SEPARATOR + "");
         for (int i = 0; i < filterExt.length; i++) {
            filterExt[i] = filterExt[i].trim().substring(1);
         }
      }
      /**
       * Akzeptiere alle Verzeichnisse und alle Files entsprechend der Filter-Extensions.
       * Diese Methode wird fuer jede Datei, die sich im momentan im FileChooser ausgewaehlten Verzeichnis befindet, automatisch ausgefaehrt.
       * @author Johannes Benzing
       */
      public boolean accept(File f) {
         String fileExtention = getExtension(f);
         if (fileExtention != null) {
            for (int i=0; i< filterExt.length; i++) {          
               if (fileExtention.equalsIgnoreCase(filterExt[i])) {   
                  return true;               
               }
            }
         }
         return false;
      }

      /**
       * Beschreibung des Filters
       * @author Johannes Benzing
       */
      public String getDescription() {
         return filterBeschreibung + " (" + filter + ")";
      }

      // Hilfsmethoden
      private String getExtension(File f) {
         String ext = null;
         String s = f.getName();
         int i = s.lastIndexOf(EXTENTION_SEPARATOR);

         if ((i > 0) && (i < (s.length() - 1))) {
            ext = s.substring(i + 1).toLowerCase().trim();
         }

         return ext;
      }

      public boolean istVorhanden(String ext, String[] filter) {

         for (String s : filter) {
            if (s.equals(ext)) {
               return true;
            }
         }
         return false;
      }

   }

}
