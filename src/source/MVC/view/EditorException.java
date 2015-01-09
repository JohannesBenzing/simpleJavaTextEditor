package source.MVC.view;

/**
*  eigene Exception Klasse
* @author Johannes Benzing
*/
public class EditorException extends Exception{
  public EditorException(String fehlerMeldung){
    super(fehlerMeldung);
  }
}