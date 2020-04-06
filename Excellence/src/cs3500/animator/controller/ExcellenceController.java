package cs3500.animator.controller;

import java.awt.event.KeyEvent;

/**
 * Represents a Controller for Excellence. The Controller handles user moves by executing them on
 * the model, and then conveys the result to the user.
 */
public interface ExcellenceController {

  /**
   * Handles any button press on the keyboard and adjusts the view as necessary.
   *
   * @param keyEvent the keyboard button press.
   */
  void handleButtonPress(KeyEvent keyEvent);
}
