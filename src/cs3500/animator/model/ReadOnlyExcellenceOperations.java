package cs3500.animator.model;

import java.util.List;

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
   * Returns a copy of the the shapes in the cs3500.animator.model in a List with their states updated to the
   * specified tick. In other words, it returns a copy of the shapes with states that correspond to
   * the tick given.
   *
   * @param tick is the tick at which the shape states are requested
   * @throws IllegalStateException    if there are no shapes in the cs3500.animator.model
   * @throws IllegalArgumentException if the tick is negative.
   */
  public List<Shape> getShapesAt(int tick);
}
