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
public class TestExcellence {
  ExcellenceOperations eo1;

  /**
   * Initializes examples for testing.
   */
  private void initExamples() {
    eo1 = new ShapeAnimationModel();
    eo1.addShape("Rect 1", "rectangle");
    eo1.addInstruction("Rect 1", 1, 11, new Position(5, 5),
            new Position(15, 15), new Dimension(100, 100),
            new Dimension(200, 200), Color.GREEN, Color.RED);
  }

  @Test
  public void testToText() {
    initExamples();
    eo1.addShape("Oval 1", "oval");
    eo1.addInstruction("Oval 1", 6, 20, new Position(4, 4),
            new Position(10, 10), new Dimension(100, 100),
            new Dimension(500, 500), Color.BLUE, Color.BLACK);
    assertEquals("shape Oval 1 oval\nmotion Oval 1 6 4.0 4.0 100.0 100.0 0 0 255     "
            + "20 10.0 10.0 500.0 500.0 0 0 0\n\nshape Rect 1 rectangle\n"
            + "motion Rect 1 1 5.0 5.0 100.0 100.0 0 255 0     11 15.0 15.0 200.0 200.0 255 0 0",
            eo1.toText());
  }

  @Test
  public void testPlayAnimationTick1ChangesNothing() {
    initExamples();
    eo1.playAnimation(1);
    //TODO tests showing that nothing has changed
  }

  @Test
  public void testPlayAnimation() {
    initExamples();
    eo1.playAnimation(2);
    assertEquals(6, eo1.getShapes().get("Rect 1").getPosition().getX(), .001);
    assertEquals(110, eo1.getShapes().get("Rect 1").getDimension().getX(), .001);
    assertEquals(null, eo1.getShapes().get("Rect 1").getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartTick() {
    initExamples();
    eo1.addInstruction("Rect 1", 6, 15, new Position(15, 15),
            new Position(20, 20), new Dimension(200, 200),
            new Dimension(300, 300), Color.RED, Color.BLUE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionEndTick() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartPosition() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionEndPosition() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartDimension() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionEndDimension() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartColor() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionEndColor() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionNonexistantShape() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionNullInstruction() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayAnimationNoShapes() {

  }

  @Test
  public void testPlayAnimationNonexistantTick() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalidType() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateInvalidShape() {

  }

  @Test(expected = IllegalStateException.class)
  public void testAddAlreadyExistingShape() {
    initExamples();
    eo1.addShape("Rect 1", "rectangle");
    System.out.println(eo1.toText());
  }
}
