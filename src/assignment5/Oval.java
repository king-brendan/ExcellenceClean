package assignment5;

import java.awt.*;

/**
 * Represents an oval.
 */
public class Oval extends Shape {
  public Oval(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return "shape " + getName() + " oval";
  }

}
