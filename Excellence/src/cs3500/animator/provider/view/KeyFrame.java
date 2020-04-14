package cs3500.animator.provider.view;

import java.awt.Color;
import java.util.Objects;

/**
 * Represents the attributes and location of one of the added shapes at a specific point in time. We
 * assume the that all shapes allowed in an animation can be represented the same way, which allows
 * us to simplify our code considerably. More specifically, we can have the Animator classes deal
 * with the types of shapes being created, while this class just stores their change in state,
 * making things easier.
 */
public class KeyFrame {

  private final Color color;
  private final int w;
  private final int h;
  private final Position2D position;

  /**
   * Constructs a KeyFrame.
   *
   * @param color    The color of the shape.
   * @param w        The width of the shape.
   * @param h        The height of the shape.
   * @param position Where the shape should appear on the scene.
   * @throws IllegalArgumentException If given null arguments or an invalid tick or dimensions.
   */
  public KeyFrame(Color color, int w, int h, Position2D position) {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null");
    }
    if (w < 0) {
      throw new IllegalArgumentException("shape height cannot be negative");
    }
    if (h < 0) {
      throw new IllegalArgumentException("shape width cannot be negative");
    }
    if (position == null) {
      throw new IllegalArgumentException("position cannot be null");
    }

    this.color = color;
    this.w = w;
    this.h = h;
    this.position = position;
  }

  @Override
  public String toString() {
    return this.position.toString()
        + "\tHeight: " + this.h
        + "\tWidth: " + this.w
        + "\tColor: " + this.color.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    KeyFrame keyFrame = (KeyFrame) o;
    return w == keyFrame.w
        && h == keyFrame.h
        && color.equals(keyFrame.color)
        && position.equals(keyFrame.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, w, h, position);
  }

  /**
   * Returns the color of the KeyFrame.
   **/
  public Color getColor() {
    return this.color;
  }

  /**
   * Returns the height of the KeyFrame.
   **/
  public int getHeight() {
    return this.h;
  }

  /**
   * Returns the width of the KeyFrame.
   **/
  public int getWidth() {
    return this.w;
  }

  /**
   * Returns the position of the KeyFrame.
   **/
  public Position2D getPosition() {
    return this.position;
  }
}
