package cs3500.animator.view;

/**
 * A view that outputs an animation.
 */
public interface AnimatorView {
  /**
   * Refreshes the view to reflect the current animation state.
   */
  public void refresh();

  /**
   * Makes the view visible to start the animation.
   */
  public void displayAnimation();

}
