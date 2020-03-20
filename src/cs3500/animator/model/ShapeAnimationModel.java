package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A concrete implementation of the ExcellenceOperations interface. It is an immutable object that
 * includes immutable fields for the shapes and Instructions represented as Maps. It is a cs3500.animator.model for
 * an animator that creates an animation based on user string input detailing the animation.
 */
public final class ShapeAnimationModel implements ExcellenceOperations {
  private final Map<String, Shape> shapes;
  private final Map<Shape, List<Instruction>> instructions;


  /**
   * A public constructor for the cs3500.animator.model that initializes the fields to empty Maps.
   */
  public ShapeAnimationModel() {
    this.shapes = new HashMap<>();
    this.instructions = new HashMap<>();
  }

  @Override
  public List<Shape> getShapesAt(int tick) {
    if (shapes.size() == 0) {
      throw new IllegalStateException("There are no shapes to animate in the game");
    }
    if (tick < 0) {
      throw new IllegalArgumentException("Tick cannot be negative");
    }

    List<Shape> copy = new ArrayList<>();

    for (Map.Entry<Shape, List<Instruction>> e : instructions.entrySet()) {

      List<Instruction> instructs = e.getValue();

      for (Instruction i : instructs) {
        if (tick >= i.getStartTick() && tick < i.getEndTick()) {

          Shape s = e.getKey().makeCopy();
          s.applyInstructionToTick(i, tick);

          copy.add(s);
        }
      }
    }

    return copy;
  }



  @Override
  public String toText() {
    String text = "";

    for (Map.Entry<Shape, List<Instruction>> e : instructions.entrySet()) {
      Shape s = e.getKey();
      List<Instruction> instructs = e.getValue();

      text = text.concat(s.toString() + "\n");

      for (Instruction i : instructs) {
        text = text.concat(i.toString() + "\n");
      }

      text = text.concat("\n");
    }
    return text.substring(0, text.length() - 2);
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
        throw new IllegalArgumentException("Shape does not exist in cs3500.animator.model");
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

    } else {
      canAddInstructions(i, instructs);
      instructs.add(i);
    }
  }


  /**
   * Checks if an instruction cannot be added to a list of instruction by throwing an exception.
   *
   * @throws IllegalArgumentException if the instruction does not have the same start position,
   *                                  start dimension, start color, or start tick equal to the last
   *                                  instruction's (in the list of instruction) end position, end
   *                                  dimension, end color, or end tick.
   */
  private void canAddInstructions(Instruction i, List<Instruction> instructs) {
    Instruction lastI = instructs.get(instructs.size() - 1);

    if (lastI.getEndTick() != i.getStartTick()) {
      throw new IllegalArgumentException("Instructions times don't match up");
    }
    if (!lastI.getEndDimension().equals(i.getStartDimension())) {
      throw new IllegalArgumentException("Instructions dimensions don't match up");
    }
    if (!lastI.getEndPosition().equals(i.getStartPosition())) {
      throw new IllegalArgumentException("Instructions positions don't match up");
    }
    if (!lastI.getEndColor().equals(i.getStartColor())) {
      throw new IllegalArgumentException("Instructions colors don't match up");
    }

  }

  @Override
  public void addShape(String shapeName, Shape.ShapeType shapeType) {

    if (shapeName == null || shapeType == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }

    if (shapes.get(shapeName) != null) {
      throw new IllegalArgumentException("Shape already exists");
    }

    Shape s = new Shape(shapeName, shapeType);

    shapes.put(shapeName, s);
    instructions.put(s, new ArrayList<>());

  }




}
/*@Override
  public Map<Shape, List<Instruction>> getInstructions() {
    HashMap<Shape, List<Instruction>> copy = new HashMap<>();

    for (Map.Entry<Shape, List<Instruction>> entry : instructions.entrySet()) {
      Shape s = entry.getKey().makeCopy();

      List<Instruction> newInstructions = new ArrayList<>();
      for (Instruction i : entry.getValue()) {

        Instruction newI = new Instruction(i.getShapeName(), i.getStartTick(), i.getEndTick(),
                i.getStartPosition(), i.getEndPosition(), i.getStartDimension(), i.getEndDimension()
                , i.getStartColor(), i.getEndColor());
        newInstructions.add(newI);
      }
      copy.put(s, newInstructions);
    }
    return copy;
  }*/

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


