package source.EditorInterface;

import source.MVC.view.EditorException;

/**
 * @author Prof. Dr. Manfred Scheer
 * @version WS 2011/2012
 */
public interface TextModelInterface {
  public String readText() throws EditorException;
  public void writeText(String t) throws EditorException;
  public String getFileName() ;
}
