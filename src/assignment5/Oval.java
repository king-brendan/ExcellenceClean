package assignment5;

import java.awt.*;

/**
 * Represents an oval.
 */
public class Oval extends Shape {
  public Oval(String name) {
    super(name, ShapeType.OVAL);
  }

  @Override
  public Shape makeCopy() {
    Oval o = new Oval(getName());
    o.dimension.setDimension(this.getDimension());
    o.position.setPosition(this.getPosition());
    o.color = this.getColor();

    return o;
  }

  @Override
  public String toString() {
    return "shape " + getName() + " oval";
  }
}
