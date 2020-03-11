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
        if (tick > i.getStartTick() && tick <= i.getEndTick()) {
          e.getKey().applyInstruction(i);
        }
      }
    }
  }

  @Override
  public String toText() {
    String text = "";

    //for every shape, print out the create statement and individual instructions
    for (Map.Entry<Shape, List<Instruction>> e : instructions.entrySet()) {
      Shape s = e.getKey();
      text = text.concat(s.toString() + "\n");
      for (Instruction i : e.getValue()) {
        text = text.concat(i.toString() + "\n");
      }
      text = text.concat("\n");
    }
    return text;
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
   * Adds an instruction to a list of instructions if its start state for the shape is equal to the
   * end state of the shape in the last instruction in the list.
   *
   * @param i         is the instruction
   * @param instructs is the list of instructions
   * @throws IllegalArgumentException if the conditions of startTick == endTick are not met.
   */
  private void addInstructToList(Instruction i, List<Instruction> instructs) {
    if (instructs.size() == 0) {
      instructs.add(i);
      Shape s = shapes.get(i.getShapeName());
      s.assignBeginningConditions(i);
    } else {
      canAddInstructions(i, instructs);
      instructs.add(i);

    }
  }


  /**
   * @throws IllegalArgumentException if the instruction does not have the same start position,
   *                                  start dimension, start color, or start tick equal to the last
   *                                  instructions (in the list of instruction) end position, end
   *                                  dimension, end color, or end tick.
   */
  private void canAddInstructions(Instruction i, List<Instruction> instructs) {
    Instruction lastI = instructs.get(instructs.size() - 1);

    if (lastI.getEndTick() != i.getStartTick()) {
      throw new IllegalArgumentException("There is a gap in instruction times");
    }
    if (!lastI.getEndDimension().equals(i.getStartDimension())) {
      throw new IllegalArgumentException("There is a gap in dimensions");
    }
    if (!lastI.getEndPosition().equals(i.getStartPosition())) {
      throw new IllegalArgumentException("There is a gap in positions");
    }
    if (!lastI.getEndColor().equals(i.getStartColor())) {
      throw new IllegalArgumentException("There is a gap in colors");
    }

  }

  @Override
  public void addShape(String shapeName, String shapeType) {
    Shape s;
    /*
    We Used a switch statement so that if we need to add more shape types in the future we can
    just adjust this. Since we do not have all the details for what kinds of shapes we are going
    to draw, this might be changed later.
     */
    switch (shapeType) {
      case "rectangle":
        s = new Rectangle(shapeName);
        shapes.put(shapeName, s);
        instructions.put(s, new ArrayList<>());
        break;
      case "oval":
        s = new Oval(shapeName);
        shapes.put(shapeName, s);
        instructions.put(s, new ArrayList<>());
        break;
      default:
        throw new IllegalArgumentException("Shape type does not exist");
    }
  }

  @Override
  public Map<String, Shape> getShapes() {
    HashMap<String, Shape> copy = new HashMap<>();

    for (Map.Entry<String, Shape> entry : shapes.entrySet()) {
      Shape s = entry.getValue().makeCopy();
      String str = entry.getKey();
      copy.put(str, s);
    }
    return copy;
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


