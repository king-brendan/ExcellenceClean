package cs3500.animator.provider.view;

import java.awt.event.ActionListener;

import model.Animator;

/**
 * Represents a general interface for a view.
 * Used to visualize the model.
 */
public interface IView {

  /**
   * Performs whatever actions are necessary for the user to see the output of the view, depending
   * on the type of view.
   *
   * @param model The animation model.
   * @param ticksPerSecond The number of ticks in a second.
   */
  void render(Animator model, int ticksPerSecond);

  /**
   * Sets the output path for any files that may or may not be written by the view.
   *
   * @param w The object to which the view should output its text.
   */
  void setOutput(Appendable w);

  /**
   * Add some actionListener (the controller) as a listener for events that involve the model.
   *
   * @param listener A controller.
   */
  public void setListener(ActionListener listener);
}
