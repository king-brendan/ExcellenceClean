package cs3500.animator.controller;

/**
 * Represents a Controller for Excellence. The Controller handles user moves by executing them on
 * the model, and then conveys the result to the user.
 */
public interface ExcellenceController {

  /**
   * Handles any input from the user and adjusts the view as necessary.
   *
   * @param input the input from the user.
   */
  void handleButtonPress(char input);
}
