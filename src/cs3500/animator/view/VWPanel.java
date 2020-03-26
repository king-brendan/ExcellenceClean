package cs3500.animator.view;


import java.awt.*;

import java.util.List;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;

import javax.swing.JPanel;

/**
 * A VisualView Panel (VWPanel) that draws a list of shapes supplied to it. It has support for
 * rectangles and Ovals (including circles and squares) and draws them according the the shape's
 * color, position, and dimension. Look at the cs3500.animator.model.Shape class for more details.
 */
public class VWPanel extends JPanel {
  private final List<Shape> shapes;

  /**
   * A Default constructor for VWPanel that initializes the first list of shapes as the ones given.
   *
   * @param shapes is the list of shapes
   */
  VWPanel(List<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Sets the list of shapes to a new list of shapes inserted. It is set as default as it is only
   * accessed in this package and should not be public.
   *
   * @param shapes the new list of shapes
   */
  void setShapes(List<Shape> shapes) {
    this.shapes.clear();
    this.shapes.addAll(shapes);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    for (Shape s : shapes) {
      String sType = s.getType().toString();

      Position p = s.getPosition();
      Dimension d = s.getDimension();
      g2d.setPaint(s.getColor());

      if (sType.equals("rectangle")) {
        g2d.fillRect((int) p.getX(), (int) p.getY(), (int) d.getX(), (int) d.getY());
      } else {
        g2d.fillOval((int) p.getX(), (int) p.getY(), (int) d.getX(), (int) d.getY());
      }
    }
  }
}
