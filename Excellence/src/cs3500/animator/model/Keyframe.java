package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

/**
 * A new class added to assignment 7 in order to represent motions as key frames. This class
 * represents a state of a shape (defined by its shapeName) at an instance of time.
 */
public class Keyframe {
  private final int tick;
  private final String shapeName;
  private Color color;
  private final Dimension dimension;
  private final Position position;

  /**
   * A Constructor to create a new Keyframe, which contains all information for a state of a shape
   * defined by its shapeName.
   *
   * @param shapeName is the name of the shape
   * @param color     is the color of the shape
   * @param dimension is the x and y dimension of the shape
   * @param position  is the x and y coordinate of the shape
   * @throws IllegalArgumentException if params are null
   */
  Keyframe(String shapeName, int tick, Position position, Dimension dimension, Color color) {
    if (shapeName == null || color == null || dimension == null || position == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    if (tick < 0) {
      throw new IllegalArgumentException("Cannot have a negative time");
    }
    this.tick = tick;
    this.shapeName = shapeName;
    this.color = color;
    this.position = position;
    this.dimension = dimension;
  }


  /**
   * A copy constructor for keyframe that returns a safe copy of the inserted keyframe.
   *
   * @param k the keyframe to be copied
   * @throws IllegalArgumentException if params are null
   */
  public Keyframe(Keyframe k) {
    if (k == null) {
      throw new IllegalArgumentException("Parameter cannot be null");
    }
    this.tick = k.getTick();
    this.shapeName = k.getShapeName();
    this.color = k.getColor();
    this.position = k.getPosition();
    this.dimension = k.getDimension();
  }

  /**
   * Returns the name of the shape.
   */
  public String getShapeName() {
    return shapeName;
  }

  /**
   * Returns the color of the shape.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Returns a copy of the shapes dimension.
   */
  public Dimension getDimension() {
    return new Dimension(dimension);
  }

  /**
   * Returns a copy of the shapes position.
   */
  public Position getPosition() {
    return new Position(position);
  }

  /**
   * Returns the tick of the keyframe.
   */
  public int getTick() {
    return tick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Keyframe keyframe = (Keyframe) o;
    return tick == keyframe.tick &&
            shapeName.equals(keyframe.shapeName) &&
            color.equals(keyframe.color) &&
            dimension.equals(keyframe.dimension) &&
            position.equals(keyframe.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tick, shapeName, color, dimension, position);
  }
}
