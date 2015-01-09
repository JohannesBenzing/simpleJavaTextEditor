package source.MVC.controller;

import source.EditorInterface.FrameInterface;

import javax.swing.*;
import java.awt.event.*;

/**
 Handelt Windowevents.
 @author Johannes Benzing
*/
public class FensterListener implements WindowListener{
   private FrameInterface fInt;                     
   
   public FensterListener(FrameInterface fInt) {
      this.fInt = fInt;   
   }      
   
   @Override
   public void windowClosing(WindowEvent e) {
      fInt.getEditorArea().beendenDialog();
   }
   public void windowActivated(WindowEvent e){
   }
   public void windowDeactivated(WindowEvent e){
   }
   public void windowOpened(WindowEvent e){
   }
   public void windowClosed(WindowEvent e){
   }
   public void windowIconified(WindowEvent e){
   }
   public void windowDeiconified(WindowEvent e){
   }   
}