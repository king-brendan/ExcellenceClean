import org.junit.Test;

import java.awt.Color;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.ExcellenceOperations;

import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.SVGView;


/**
 * Methods for testing the functionality of our shape animator cs3500.animator.model to make sure it is without bugs
 * and runs smoothly according to its interface. Changed our tests from Assignment 5 to include
 * getShapesAt(int tick) and deleted tests for old methods.
 */
public class TestExcellence {
  private ExcellenceOperations hwExample;

  private Position p200x200;
  private Position p10x200;
  private Position p300x300;
  private Position p440x70;
  private Position p440x250;
  private Position p440x370;

  private Dimension d50x100;
  private Dimension d25x100;
  private Dimension d120x60;

  Color greenBlue;

  /**
   * Initializes examples for testing.
   */
  private void initExamples() {
    //From the assignment page
    hwExample = new ShapeAnimationModel();

    p10x200 = new Position(10, 200);
    p200x200 = new Position(200, 200);
    p300x300 = new Position(300, 300);
    p440x70 = new Position(440, 70);
    p440x250 = new Position(440, 250);
    p440x370 = new Position(440, 370);

    d25x100 = new Dimension(25, 100);
    d50x100 = new Dimension(50, 100);
    d120x60 = new Dimension(120, 60);

    greenBlue = new Color(5, 60, 73); //Found online

    hwExample.addShape("R", Shape.ShapeType.RECTANGLE);
    hwExample.addShape("C", Shape.ShapeType.OVAL);

    hwExample.addInstruction("R", 1, 10, p200x200, p10x200,
            d50x100, d50x100, Color.RED, Color.RED);
    hwExample.addInstruction("R", 10, 50, p10x200, p300x300,
            d50x100, d50x100, Color.RED, Color.RED);
    hwExample.addInstruction("R", 50, 51, p300x300, p300x300,
            d50x100, d50x100, Color.RED, Color.RED);
    hwExample.addInstruction("R", 51, 70, p300x300, p300x300,
            d50x100, d25x100, Color.RED, Color.RED);
    hwExample.addInstruction("R", 70, 100, p300x300, p200x200,
            d25x100, d25x100, Color.RED, Color.RED);

    hwExample.addInstruction("C", 6, 20, p440x70, p440x70,
            d120x60, d120x60, Color.BLUE, Color.BLUE);
    hwExample.addInstruction("C", 20, 50, p440x70, p440x250,
            d120x60, d120x60, Color.BLUE, Color.BLUE);
    hwExample.addInstruction("C", 50, 70, p440x250, p440x370,
            d120x60, d120x60, Color.BLUE, greenBlue);
    hwExample.addInstruction("C", 70, 80, p440x370, p440x370,
            d120x60, d120x60, greenBlue, Color.GREEN);
    hwExample.addInstruction("C", 80, 100, p440x370, p440x370,
            d120x60, d120x60, Color.GREEN, Color.GREEN);


  }

  //For some reason, this test always passes on its own, but when run with all of the tests
  // (using the button next to the test class), it flips the toText where the shapes are in a
  // different order. This does not effect the functionality of the toText method as it does not
  // matter which Shape and its instructions are printed out first, but it affects my tests.
  @Test
  public void testToText() {
    this.initExamples();
    System.out.print("\nThis is for your reference: Please note that the toText method does not " +
            "add\n" +
            "a new line after the last shape and its instructions. \nAfter the first example, I " +
            "added the spaces so you see the second example more clearly: \n\n");

    assertTrue(
            hwExample.toText().contains("shape R rectangle\n" +
                    "motion R 1 200.0 200.0 50.0 100.0 255 0 0     10 10.0 200.0 50.0 100.0 255 0 0\n" +
                    "motion R 10 10.0 200.0 50.0 100.0 255 0 0     50 300.0 300.0 50.0 100.0 255 0 0\n" +
                    "motion R 50 300.0 300.0 50.0 100.0 255 0 0     51 300.0 300.0 50.0 100.0 255 0 0\n" +
                    "motion R 51 300.0 300.0 50.0 100.0 255 0 0     70 300.0 300.0 25.0 100.0 255 0 0\n" +
                    "motion R 70 300.0 300.0 25.0 100.0 255 0 0     100 200.0 200.0 25.0 100.0 " +
                    "255 0 0") && hwExample.toText().contains("shape C oval\n" +
                    "motion C 6 440.0 70.0 120.0 60.0 0 0 255     20 440.0 70.0 120.0 60.0 0 0 255\n" +
                    "motion C 20 440.0 70.0 120.0 60.0 0 0 255     50 440.0 250.0 120.0 60.0 0 0 255\n" +
                    "motion C 50 440.0 250.0 120.0 60.0 0 0 255     70 440.0 370.0 120.0 60.0 5 60 73\n" +
                    "motion C 70 440.0 370.0 120.0 60.0 5 60 73     80 440.0 370.0 120.0 60.0 0 255 0\n" +
                    "motion C 80 440.0 370.0 120.0 60.0 0 255 0     100 440.0 370.0 120.0 60.0 0 255 0"));

    System.out.print(hwExample.toText());
    System.out.print("\n\n\n\nFor the Second Example:\n\n");

    hwExample.addShape("T", Shape.ShapeType.RECTANGLE);
    hwExample.addInstruction("T", 1, 10,
            p200x200, p10x200, d50x100, d50x100, Color.RED, Color.RED);

    assertTrue(hwExample.toText().contains("shape T rectangle\n" +
            "motion T 1 200.0 200.0 50.0 100.0 255 0 0     10 10.0 200.0 50.0 100.0 255 0 0" ));

    System.out.print(hwExample.toText());

  }


  @Test
  public void testGetShapesAtTickWithTweening() {
    initExamples();

    List<Shape> shapes = hwExample.getShapesAt(9);

    Shape r = shapes.get(shapes.indexOf(new Shape("R", Shape.ShapeType.RECTANGLE)));

    //Since the ticks are changing the shape's position using doubles with decimals, and the total
    // change per tick around 21.1 which is >> 1, I can use 1 as delta to make sure that the change
    // is happening without finding the exact X value of the position.
    assertEquals(31, r.getPosition().getX(), 1);
    assertEquals(200, r.getPosition().getY(), 1);

    shapes = hwExample.getShapesAt(10);
    r = shapes.get(shapes.indexOf(new Shape("R", Shape.ShapeType.RECTANGLE)));

    assertEquals(p10x200, r.getPosition());
    assertEquals(d50x100, r.getDimension());
    assertEquals(Color.RED, r.getColor());

    shapes = hwExample.getShapesAt(20);

    Shape c = shapes.get(shapes.indexOf(new Shape("C", Shape.ShapeType.OVAL)));
    assertEquals(p440x70, c.getPosition());
    assertEquals(d120x60, c.getDimension());
    assertEquals(Color.BLUE, c.getColor());

    //to test that a tick outside of range returns nothing:
    shapes = hwExample.getShapesAt(200);
    assertEquals(0, shapes.size());


  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameWithNoInstructions() {
    new ShapeAnimationModel().getShapesAt(1);
  }

  /*@Test(expected = IllegalArgumentException.class)
  public void testPlayGameWithNegativeTick() {
    this.initExamples();
    hwExample.playAnimation(-1);
  }

  @Test
  public void testAddShape() {
    initExamples();
    assertNull(hwExample.getShapes().get("T"));

    hwExample.addShape("T", Shape.ShapeType.TRIANGLE);
    assertNotNull(hwExample.getShapes().get("T"));
  }

  @Test
  public void testAddInstruction() {
    initExamples();
    Shape t = new Shape("T", Shape.ShapeType.TRIANGLE);

    hwExample.addShape("T", Shape.ShapeType.TRIANGLE);
    assertEquals(0, hwExample.getInstructions().get(t).size());

    hwExample.addInstruction("T", 1, 10, p440x370, p440x370,
            d120x60, d120x60, greenBlue, greenBlue);
    assertEquals(1, hwExample.getInstructions().get(t).size());
  }
*/
  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartTick() {
    initExamples();

    hwExample.addInstruction("R", 10, 11, p440x370, p440x370,
            d120x60, d120x60, greenBlue, greenBlue);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionEndTick() {
    initExamples();

    hwExample.addInstruction("R", 100, 10, p200x200, p440x370,
            d25x100, d120x60, Color.RED, greenBlue);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartPosition() {
    initExamples();

    hwExample.addInstruction("R", 100, 150, p440x370, p440x370,
            d25x100, d120x60, Color.RED, greenBlue);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartDimension() {
    initExamples();
    hwExample.addInstruction("R", 100, 150, p200x200, p440x370,
            d120x60, d120x60, Color.RED, greenBlue);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionStartColor() {
    initExamples();
    hwExample.addInstruction("R", 100, 150, p200x200, p440x370,
            d25x100, d120x60, greenBlue, greenBlue);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionNonExistentShape() {
    initExamples();
    hwExample.addInstruction("X", 100, 150, p200x200, p440x370,
            d25x100, d120x60, Color.RED, greenBlue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidInstructionNullInstruction() {
    initExamples();
    hwExample.addInstruction("R", 100, 150, null, p440x370,
            d25x100, d120x60, Color.RED, greenBlue);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddAlreadyExistingShape() {
    initExamples();

    hwExample.addShape("R", Shape.ShapeType.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullType() {
    initExamples();
    hwExample.addShape("X", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullName() {
    initExamples();
    hwExample.addShape(null, Shape.ShapeType.RECTANGLE);
  }

  @Test
  public void testSVGDescription() {
    initExamples();
    AnimatorView view = new SVGView(hwExample, System.out);


    view.displayAnimation();

  }
}
