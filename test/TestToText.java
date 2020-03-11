import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

import assignment5.Dimension;
import assignment5.ExcellenceOperations;
import assignment5.Position;
import assignment5.ShapeAnimationModel;


/**
 * Methods for testing the text output of the model.
 */
public class TestToText {
  @Test
  public void testToText() {
    ExcellenceOperations eo1 = new ShapeAnimationModel();
    eo1.addShape("Rect 1", "rectangle");
    eo1.addInstruction("Rect 1", 1, 11, new Position(5, 5),
            new Position(10, 10), new Dimension(100, 100),
            new Dimension(200, 200), Color.GREEN, Color.RED);
    eo1.addShape("Oval 1", "oval");
    eo1.addInstruction("Oval 1", 6, 20, new Position(4, 4),
            new Position(10, 10), new Dimension(100, 100),
            new Dimension(500, 500), Color.BLUE, Color.BLACK);
    System.out.println(eo1.toText());
  }

  @Test
  public void testPlayAnimation() {
    ExcellenceOperations eo1 = new ShapeAnimationModel();
    eo1.addShape("Rect 1", "rectangle");
    eo1.addInstruction("Rect 1", 1, 11, new Position(5, 5),
            new Position(15, 15), new Dimension(100, 100),
            new Dimension(200, 200), Color.GREEN, Color.RED);
    eo1.playAnimation(1);
    eo1.playAnimation(2);
    assertEquals(6, eo1.getShapes().get("Rect 1").getPosition().getX(), .001);
  }


}
