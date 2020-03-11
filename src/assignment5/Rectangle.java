package assignment5;

import java.awt.*;

/**
 * Represents a Rectangle.
 */
public class Rectangle extends Shape {
  public Rectangle(String name) {
    super(name);
  }

  @Override
  public Shape makeCopy() {
    Rectangle r = new Rectangle(getName());
    r.dimension.setDimension(this.getDimension());
    r.position.setPosition(this.getPosition());
    r.color = this.getColor();

    return r;
  }

  @Override
  public String toString() {
    return "shape " + getName() + " rectangle";
  }
}
