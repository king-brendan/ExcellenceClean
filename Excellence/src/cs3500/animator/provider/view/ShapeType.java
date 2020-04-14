package cs3500.animator.provider.view;

/**
 * The types of shapes allowed in an animation.
 */
public enum ShapeType {
  RECTANGLE("rectangle"),
  ELLIPSE("ellipse");

  public String name;

  ShapeType(String name) {
    this.name = name;
  }

  public String toString() {
    return this.name;
  }
}