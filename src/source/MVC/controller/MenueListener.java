package source.MVC.controller;

import source.EditorInterface.FrameInterface;
import source.MVC.view.MenueLeiste;

import java.awt.event.*;

/**
 Handelt die geklickten Menueinträge.
 @author Johannes Benzing
*/
public class MenueListener implements ActionListener {
   private FrameInterface fInt;
   
   public MenueListener(FrameInterface fIntVonMenueLeiste) {
      fInt = fIntVonMenueLeiste;   
   }      
   
   public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      
      if (cmd.equals(MenueLeiste.NEW)) {
         fInt.getEditorArea().neuDialog();
      }else if( cmd.equals(MenueLeiste.OPEN) ){
         fInt.getEditorArea().oeffnenDialog();
      }else if( cmd.equals(MenueLeiste.SAVE) ){
         fInt.getEditorArea().speichernDialog();
      }else if( cmd.equals(MenueLeiste.SAVE_AS) ){
         fInt.getEditorArea().speichernUnterDialog();
      }else if( cmd.equals(MenueLeiste.EXIT) ){
         fInt.getEditorArea().beendenDialog();
      }else if( cmd.equals(MenueLeiste.SEARCH) ){
         fInt.getEditorArea().suchenDialog();
      }else if( cmd.equals(MenueLeiste.CONT_SEARCH) ){
         fInt.getEditorArea().weiterSuchenDialog();
      }else if( cmd.equals(MenueLeiste.SUBSTITUTE) ){
         fInt.getEditorArea().ersetzenDialog();
      }      
   }
}

