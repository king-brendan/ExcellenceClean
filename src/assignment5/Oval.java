package assignment5;

import java.awt.*;

/**
 * Represents an oval.
 */
public class Oval extends Shape {
  public Oval(String name, Color color, Dimension dimension, Position position) {
    super(name, color, dimension, position);
  }

  @Override
  public String toString() {
    return "shape " + getName() + " oval";
  }

}
