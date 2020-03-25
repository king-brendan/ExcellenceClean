package cs3500.animator.model;

import java.util.List;

/**
 * A new interface we added since assignment 5 that represents a read-only model. It will be used
 * as a restriction on an ExcellenceOperations where it cannot mutate the model in any way, but
 * only return information. As such, the toText and getShapesAt(int tick) were moved here from
 * the ExcellenceOperations interface. Moreover, a getLastTick, getWidth, getHeight, and
 * getTopLeft methods were added so that a view can know what size to draw its panel in, as well
 * as when to stop the animation.
 */
public interface ReadOnlyExcellenceOperations {
  /**
   * Renders the animation as text. Starts with a shape name and its type, then follows with a
   * representation of the Instructions that that shape has in order of ticks. Appends all shapes
   * and their instructions in a similar fashion to the output.
   *
   * @return the animation, rendered as text.
   */
  public String toText();

  /**
   * Returns a copy of the the shapes in the cs3500.animator.model in a List with their states
   * updated to the specified tick. In other words, it returns a copy of the shapes with states that
   * correspond to the tick given.
   *
   * @param tick is the tick at which the shape states are requested
   * @throws IllegalStateException    if there are no shapes in the cs3500.animator.model
   * @throws IllegalArgumentException if the tick is negative.
   */
  public List<Shape> getShapesAt(int tick);

  /**
   * Returns the last tick of the animation.
   *
   * @return the last tick. returns 0 if there is no animation.
   */
  public int getLastTick();

  /**
   * Returns the width of the animation window.
   *
   * @return the width of the window
   */
  public double getWidth();

  /**
   * Returns the height of the animation window.
   *
   * @return the width of the window
   */
  public double getHeight();

  /**
   * Returns the top-left point of the animation window as a position.
   *
   * @return the top-left point of the window
   */
  public Position getTopLeft();

}
