package cs3500.animator.view;

import java.awt.*;

import javax.swing.*;

/**
 * A JPanel for use in the EditorView class. This JPanel displays all of the actions available
 * to the user while the animation is running.
 */
public class ActionsPanel extends JPanel {
  private boolean isPaused;
  private boolean isLooping;
  private int speed;
  private static int LEFT_COL_X = 10;
  private static int RIGHT_COL_X = 200;

  /**
   * Constructs an ActionsPanel, with all of the default settings for playback (speed, pause/play,
   * etc).
   */
  public ActionsPanel(int speed) {
    isPaused = false;
    isLooping = false;
    this.speed = speed;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    if (isPaused) {
      g2d.drawString("Paused", LEFT_COL_X, 20);
    } else {
      g2d.drawString("Playing", LEFT_COL_X, 20);
    }

    if (isLooping) {
      g2d.drawString("Looping enabled", LEFT_COL_X, 35);
    } else {
      g2d.drawString("Looping disabled", LEFT_COL_X, 35);
    }

    g2d.drawString("Speed: " + speed, LEFT_COL_X, 50);

    g2d.drawString("Press spacebar to toggle play/pause.", RIGHT_COL_X, 20);
    g2d.drawString("Press L to toggle looping.", RIGHT_COL_X, 35);
    g2d.drawString("Press Z to decrease speed, X to increase speed.", RIGHT_COL_X, 50);
    g2d.drawString("Press R to reset.", RIGHT_COL_X, 65);
    g2d.drawString("Exit the window to end the animation.", LEFT_COL_X, 80);


  }

  /**
   * Toggles whether the screen displays if the animation is paused or not.
   */
  protected void pauseResume(boolean b) {
    isPaused = b;
    repaint();
  }

  /**
   * Toggles whether the animation is looping or not.
   */
  protected void toggleLooping() {
    isLooping = !isLooping;
    repaint();
  }

  /**
   * Changes what speed is displayed on screen.
   *
   * @param speed the speed to be changed to.
   */
  protected void changeSpeed(int speed) {
    this.speed = speed;
    repaint();
  }

}
