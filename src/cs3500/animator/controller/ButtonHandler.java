package cs3500.animator.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    System.out.println("key pressed");

  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {

  }
}
