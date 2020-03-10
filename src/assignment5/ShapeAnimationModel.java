package assignment5;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A concrete implementation of the ExcellenceOperations interface.
 */
public class ShapeAnimationModel implements ExcellenceOperations {
  private final Map<String, Shape> shapes;
  private final Map<Shape, List<Instruction>> instructions;


  public ShapeAnimationModel() {
    this.shapes = new HashMap<>();
    this.instructions = new HashMap<>();
  }


  @Override
  public void playAnimation(int tick) {
    if (instructions.size() == 0) {
      throw new IllegalStateException("There are no shapes to animate in the game");
    }

    for (Map.Entry<Shape, List<Instruction>> e : instructions.entrySet()) {

      List<Instruction> instructs = e.getValue();
      
      for (Instruction i : instructs) {
        if (tick >= i.getStartTick() && tick <= i.getEndTick()) {
          Shape s = shapes.get(i.getShapeName());
          s.applyInstruction(i);
        }
      }
    }
  }

  @Override
  public String toText() {
    return null;
  }

  @Override
  public void addInstruction(String shapeName, int startTick,
                             int endTick, Position startPos,
                             Position endPos, Dimension startDim,
                             Dimension endDim, Color startColor, Color endColor) {
    try {
      Instruction i = new Instruction(shapeName, startTick, endTick, startPos, endPos, startDim,
              endDim, startColor, endColor);

      Shape s = shapes.get(i.getShapeName());

      if (s == null) {
        throw new IllegalArgumentException("Shape does not exist in model");
      }

      addInstructToList(i, instructions.get(s));


    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException("Instruction is not valid: " + iae.getMessage());
    }
  }

  /**
   * Adds an instruction to a list of instructions if its startTick is equal to the endTick of the
   * last instruction in the list.
   *
   * @param i         is the instruction
   * @param instructs is the list of instructions
   * @throws IllegalArgumentException if the conditions of startTick == endTick are not met.
   */
  private void addInstructToList(Instruction i, List<Instruction> instructs) {
    if (instructs.size() == 0) {
      instructs.add(i);
    } else {
      if (instructs.get(instructs.size() - 1).getEndTick() == i.getStartTick()) {
        instructs.add(i);
      } else {
        throw new IllegalArgumentException("There is a gap in instructions for this shape");
      }
    }
  }

  @Override
  public void addShape(String shapeName, String shapeType) {
    //TODO needs to initalize a shape's list of instructions as well!!!
  }


}

//Extra functionality that might be needed later, so commented out for possible future  use.
  /*
  public int getLastTick() {
    int tick = 0;

    for (Map.Entry<Shape, List<Instruction>> e : instructions.entrySet()) {

      List<Instruction> instructs = e.getValue();
      int endTick = instructs.get(instructs.size() - 1).getEndTick();

      if (endTick > tick) {
        tick = endTick;
      }
    }

    return tick;
  }*/


