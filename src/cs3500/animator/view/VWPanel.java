package cs3500.animator.view;


import java.awt.*;
import java.util.List;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;

import javax.swing.JPanel;


public class VWPanel extends JPanel {
  private List<Shape> shapes;

  VWPanel(List<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Sets the list of shapes to a new list of shapes inserted.
   *
   * @param shapes the new list of shapes
   */
  void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
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
