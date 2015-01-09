package source.MVC.view;

import source.EditorInterface.MenueLeisteInterface;
import source.MVC.controller.MenueListener;
import source.MVC.model.LangModel;
import source.EditorInterface.FrameInterface;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

/**
   Leiste am oberen Rand des Fensters mit den Menueinträgen.
   @author Johannes Benzing
*/
public class MenueLeiste extends JMenuBar implements MenueLeisteInterface {
   final int CTRL_COMMAND_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); 
   
   public final static String NEW = "Neu";
   public final static String OPEN = "Oeffnen";
   public final static String SAVE = "Speichern";
   public final static String SAVE_AS = "speichernUnter";
   public final static String EXIT = "Beenden";
   public final static String SEARCH = "Suchen";
   public final static String CONT_SEARCH = "Weitersuchen";
   public final static String SUBSTITUTE = "Ersetzen";

   private JMenu menueDatei;
   private JMenu menueBearbeiten;
   private JMenuItem dateiNeu;
   private JMenuItem dateiOeffnen;
   private JMenuItem dateiSpeichern;
   private JMenuItem dateiSpeichernUnter;
   private JMenuItem dateiEnde;
   private JMenuItem bearbeitenSuchen;
   private JMenuItem bearbeitenWeiterSuchen;
   private JMenuItem bearbeitenErsetzen;
   private MenueListener mListener;
   
   public MenueLeiste(FrameInterface fInt){
      mListener = new MenueListener(fInt);
      
      menueDatei = new JMenu(LangModel.getText(LangModel.getLangValue(LangModel.FILE)));
      menueDatei.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE)) );
      dateiNeu = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.FILE_NEW)));
      dateiNeu.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE_NEW)));
      dateiNeu.setActionCommand(NEW);
      dateiNeu.addActionListener(mListener);
      menueDatei.add(dateiNeu);
      dateiOeffnen = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.FILE_OPEN)));
      dateiOeffnen.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE_OPEN)));
      KeyStroke ks0 = KeyStroke.getKeyStroke(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE_OPEN)) , CTRL_COMMAND_MASK);
      dateiOeffnen.setAccelerator(ks0);
      dateiOeffnen.setActionCommand(OPEN);
      dateiOeffnen.addActionListener(mListener);
      menueDatei.add(dateiOeffnen);
      dateiSpeichern = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.FILE_SAVE)));
      dateiSpeichern.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE_SAVE)));
      KeyStroke ks1 = KeyStroke.getKeyStroke(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE_SAVE)) , CTRL_COMMAND_MASK);
      dateiSpeichern.setAccelerator(ks1);
      dateiSpeichern.setActionCommand(SAVE);
      dateiSpeichern.addActionListener(mListener);
      menueDatei.add(dateiSpeichern);      
      dateiSpeichernUnter = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.FILE_SAVE_AS)));
      dateiSpeichernUnter.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE_SAVE_AS)));
      dateiSpeichernUnter.setActionCommand(SAVE_AS);
      dateiSpeichernUnter.addActionListener(mListener);
      menueDatei.add(dateiSpeichernUnter);                  
      menueDatei.addSeparator();
      dateiEnde = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.FILE_EXIT)));
      dateiEnde.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.FILE_EXIT)));
      dateiEnde.setActionCommand(EXIT);
      dateiEnde.addActionListener(mListener);
      menueDatei.add(dateiEnde);   
      
      menueBearbeiten = new JMenu(LangModel.getText(LangModel.getLangValue(LangModel.EDIT)));
      menueBearbeiten.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.EDIT)));
      bearbeitenSuchen = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.EDIT_SEARCH)));
      bearbeitenSuchen.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.EDIT_SEARCH)));
      KeyStroke ks2 = KeyStroke.getKeyStroke(LangModel.getMnemonic(LangModel.getLangValue(LangModel.EDIT_SEARCH)) , CTRL_COMMAND_MASK);
      bearbeitenSuchen.setAccelerator(ks2);
      bearbeitenSuchen.setActionCommand(SEARCH);
      bearbeitenSuchen.addActionListener(mListener);
      menueBearbeiten.add(bearbeitenSuchen);   
      bearbeitenWeiterSuchen = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.EDIT_CONT_SEARCH)));
      bearbeitenWeiterSuchen.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.EDIT_CONT_SEARCH)));
      KeyStroke ks3 = KeyStroke.getKeyStroke(KeyEvent.VK_F3, CTRL_COMMAND_MASK);
      bearbeitenWeiterSuchen.setAccelerator(ks3);
      bearbeitenWeiterSuchen.setActionCommand(CONT_SEARCH);
      bearbeitenWeiterSuchen.addActionListener(mListener);
      menueBearbeiten.add(bearbeitenWeiterSuchen);         
      bearbeitenErsetzen = new JMenuItem(LangModel.getText(LangModel.getLangValue(LangModel.EDIT_SUBSTITUTE)));
      bearbeitenErsetzen.setMnemonic(LangModel.getMnemonic(LangModel.getLangValue(LangModel.EDIT_SUBSTITUTE)));
      KeyStroke ks4 = KeyStroke.getKeyStroke(LangModel.getMnemonic(LangModel.getLangValue(LangModel.EDIT_SUBSTITUTE)) , CTRL_COMMAND_MASK);
      bearbeitenErsetzen.setAccelerator(ks4);
      bearbeitenErsetzen.setActionCommand(SUBSTITUTE);
      bearbeitenErsetzen.addActionListener(mListener);
      menueBearbeiten.add(bearbeitenErsetzen);         
      
      add(menueDatei);
      add(menueBearbeiten);
      
      
      deAktiveMenuefunktionen();
   }
   public void deAktiveMenuefunktionen(){
         dateiNeu.setEnabled(true);
         dateiOeffnen.setEnabled(true);
         dateiSpeichern.setEnabled(false);
         dateiSpeichernUnter.setEnabled(true);
         bearbeitenSuchen.setEnabled(true);
         bearbeitenWeiterSuchen.setEnabled(true);
         bearbeitenErsetzen.setEnabled(false);
   }
/**
*  (de)aktiviert Menueintraege nach Klick auf Neu in der Menueleiste
* @author Johannes Benzing
*/
   public void clickedNew(){
         dateiNeu.setEnabled(true);
         dateiOeffnen.setEnabled(true);
         dateiSpeichern.setEnabled(false);
         dateiSpeichernUnter.setEnabled(true);
         bearbeitenSuchen.setEnabled(true);
         bearbeitenWeiterSuchen.setEnabled(true);
         bearbeitenErsetzen.setEnabled(false);
   }
/**
*  (de)aktiviert Menueintraege nach Klick auf Oeffnen oder Speichern in der Menueleiste
* @author Johannes Benzing
*/   
   public void clickedOpenOrSave(){
       dateiNeu.setEnabled(true);
       dateiOeffnen.setEnabled(true);
       dateiSpeichern.setEnabled(true);
       dateiSpeichernUnter.setEnabled(true);
       bearbeitenSuchen.setEnabled(true);
       bearbeitenWeiterSuchen.setEnabled(true);
       bearbeitenErsetzen.setEnabled(false);
 }

}
