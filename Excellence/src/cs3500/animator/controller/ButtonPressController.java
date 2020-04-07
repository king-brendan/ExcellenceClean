package cs3500.animator.controller;

import java.awt.event.KeyEvent;

import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.EditableAnimatorView;

/**
 * A Controller for Excellence that updates the animation according to button presses from the
 * user.
 */
public class ButtonPressController implements ExcellenceController {
  ExcellenceOperations model;
  EditableAnimatorView view;
  static int DELTA_SPEED = 2;

  /**
   * Constructs a ButtonPressController with a given model and view.
   *
   * @param m the model.
   * @param v the view.
   */
  public ButtonPressController(ExcellenceOperations m, EditableAnimatorView v) {
    model = m;
    view = v;
  }

  @Override
  public void handleButtonPress(char input) {
      switch (input) {
        case 'r':
          view.changeTick(0);
          break;
        case ' ':
          view.pauseResume();
          break;
        case 'l':
          view.toggleLooping();
          break;
        case 'z':
          view.changeSpeed(-1 * DELTA_SPEED);
          break;
        case 'x':
          view.changeSpeed(DELTA_SPEED);
          break;
      }
  }
}
