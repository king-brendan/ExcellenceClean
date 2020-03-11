package assignment5;

import java.awt.*;
import java.util.Map;

public interface ExcellenceOperations {


  /**
   * Advances the animation of the given model according to which tick it is on.
   *
   * @throws IllegalStateException if there is nothing to play in the game (no instructions or
   *                               shapes in the model)
   */
  public void playAnimation(int tick);

  /**
   * Renders the animation as text.
   *
   * @return the animation, rendered as text.
   */
  public String toText();

  /**
   * Creates an instruction given the right parameters then adds the Instruction to the list of
   * instructions of the model.
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
   * @throws IllegalArgumentException if the instruction is not valid or if there is no shape in the
   *                                  model corresponding to that instruction or its start tick is
   *                                  not equal to the previous instructions endtick (i.e. if there
   *                                  are gaps in instructions for the shape).
   */
  public void addInstruction(String shapeName, int startTick,
                             int endTick, Position startPos,
                             Position endPos, Dimension startDim,
                             Dimension endDim, Color startColor, Color endColor);

  /**
   * Creates and adds a shape to the model.
   *
   * @throws IllegalArgumentException if the shape has a wrong type.
   */
  public void addShape(String shapeName, String shapeType);

  /**
   * Returns a copy of the the shapes in the model as a hashmap.
   *
   */
  public Map<String,Shape> getShapes();


}
