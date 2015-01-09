package source.EditorInterface;

import javax.swing.*;

/**
  Dieses Interface sorgt dafuer, das die einzelnen Komponenten des Interfaces miteinander kommunizieren koennen.
  @author Johannes Benzing
*/
public interface FrameInterface {
   public JFrame getFrame();    
   public MenueLeisteInterface getMenueLeiste();
   public EditorAreaInterface getEditorArea();
   public void setMessage(String nachricht);
}