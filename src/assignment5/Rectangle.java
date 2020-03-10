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
  public String toString() {
    return "shape " + getName() + " rectangle";
  }
}
