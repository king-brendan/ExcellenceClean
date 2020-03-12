package assignment5;

import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 * An interface to include all the functionality required of a model for an animator.
 */
public interface ExcellenceOperations {


  /**
   * Advances the animation of the given model according to which tick it is on, i.e., where the
   * animation is in terms of time.
   *
   * @throws IllegalStateException    if there is nothing to play in the game (no shapes in the
   *                                  model)
   * @throws IllegalArgumentException if the tick is negative.
   */
  public void playAnimation(int tick);

  /**
   * Renders the animation as text. Starts with a shape name and its type, then follows with a
   * representation of the Instructions that that shape has in order of ticks. Appends all shapes
   * and their instructions in a similar fashion to the output.
   *
   * @return the animation, rendered as text.
   */
  public String toText();

  /**
   * Creates an instruction given the right parameters then adds the Instruction to the list of
   * instructions of the model. This function expects valid Positions, Dimensions, and Colors. This
   * is done so that the controller can parse for errors in user input after getting the full
   * instruction and output the specific problem with the output, then only ask for that input again
   * instead of the whole instruction. This will create a better, more detailed GUI.
   *
   * @param shapeName  is  the shape name
   * @param startTick  is when the instruction is applied to the model
   * @param endTick    is when the instruction ends
   * @param startPos   starting position of the shape
   * @param endPos     ending position of the shape
   * @param startDim   initial dimensions of the shape
   * @param endDim     ending dimensions of the shape
   * @param startColor initial color of the shape
   * @param endColor   end color of the shape if changed
   * @throws IllegalArgumentException if the instruction is not valid (check instruction validity in
   *                                  Instruction class) or if there is no shape in the model
   *                                  corresponding to that instruction or if the start conditions
   *                                  do not correspond to the shapes previous instruction's end
   *                                  states.
   */
  public void addInstruction(String shapeName, int startTick,
                             int endTick, Position startPos,
                             Position endPos, Dimension startDim,
                             Dimension endDim, Color startColor, Color endColor);

  /**
   * Creates and adds a shape to the model. Expects only a name and a type to create the shape
   * object.
   *
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public void addShape(String shapeName, Shape.ShapeType shapeType);

  /**
   * Returns a copy of the the shapes in the model as a Map of String to Shape representing the
   * shapeName and the Shape, respectively.
   */
  public Map<String, Shape> getShapes();

  /**
   * Returns a copy of the shapes and their instructions as a Map of shape to list of instruction
   * representing the Shape and its list of instructions, respectively.
   */
  public Map<Shape, List<Instruction>> getInstructions();


}
