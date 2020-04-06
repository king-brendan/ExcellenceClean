package cs3500.animator.controller;

import java.awt.event.KeyEvent;

import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.view.AnimatorView;

/**
 * A Controller for Excellence that updates the animation according to button presses from the
 * user.
 */
public class ButtonPressController implements ExcellenceController {
  ExcellenceOperations model;
  AnimatorView view;

  public ButtonPressController(ExcellenceOperations m, AnimatorView v) {
    model = m;
    view = v;
  }

  @Override
  public void handleButtonPress(KeyEvent keyEvent) {
    view.refresh(0);
  }
}
