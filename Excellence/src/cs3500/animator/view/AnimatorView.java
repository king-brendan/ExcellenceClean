package cs3500.animator.view;

/**
 * A view that outputs an animation.
 */
public interface AnimatorView {
  /**
   * Refreshes the view to reflect the current animation state according to the tick given.
   * @param tick is the time of the animation
   */
  public void refresh(int tick);

  /**
   * Makes the view visible to start the animation.
   */
  public void displayAnimation();

}
