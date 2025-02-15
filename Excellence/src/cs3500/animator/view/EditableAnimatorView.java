package cs3500.animator.view;

import cs3500.animator.controller.ExcellenceController;

/**
 * An interface that represents an AnimatorView that takes in user input as the animation is
 * playing.
 */
public interface EditableAnimatorView extends AnimatorView {

  /**
   * Adds a Controller as a listener to the view, so that the Controller can handle
   * user input.
   *
   * @param c the Controller to be used as a listener for events from the user.
   */
  public void addListener(ExcellenceController c);

  /**
   * Changes the tick of the animation; that is, begins it at a different tick.
   *
   * @param tick the tick the animation should be set to.
   */
  public void changeTick(int tick);

  /**
   * Either pauses or plays the animation, depending on whether it is currently paused or playing.
   */
  public void pauseResume();

  /**
   * Either enables or disables the loop functionality, depending on whether it is enabled or not.
   */
  public void toggleLooping();

  /**
   * Change the speed of an animation by a given amount.
   *
   * @param s the amount the animation speed should be changed by.
   */
  public void changeSpeed(int s);

  /**
   * Enables a pop-up text window to be typed in when the user presses 'e'.
   */
  public void enablePopup(ExcellenceController c);

  /**
   * Displays error messages thrown by the model as a message to the user.
   *
   * @param error the error message.
   */
  public void handleException(String error);

  /**
   * Displays a pop-up of all of the shapes in the animation at the current tick.
   */
  void displayShapes();
}
