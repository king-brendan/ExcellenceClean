package cs3500.animator.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A Listener class that listens for key input.
 * Button presses are registered and then passed off to the controller, where they are used to
 * update the model/view.
 */
public class ButtonHandler implements KeyListener {
  ExcellenceController c;

  public ButtonHandler(ExcellenceController c) {
    this.c = c;
  }


  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    char input = keyEvent.getKeyChar();
    c.handleButtonPress(input);
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {

  }
}
