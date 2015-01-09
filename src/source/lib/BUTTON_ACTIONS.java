package source.lib;

/**
 * Konstanten fuer Buttons, die z.b. von Dialogen genutzt werden
 * @author Johannes Benzing
 */
public enum BUTTON_ACTIONS {
  YES_BUTTON(1) , OK_BUTTON(1) , NO_BUTTON(2) ,
  CANCEL_BUTTON(3) , X_BUTTON(3) , OPEN_BUTTON(4) , 
  SAVE_BUTTON(5) ;

  private int value ;

  private BUTTON_ACTIONS(int wert) {
    value = wert ;
  }

  public int getValue() { return value ; }
}
