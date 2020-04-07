package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.animator.util.AnimationBuilder;

/**
 * A concrete implementation of the ExcellenceOperations interface. It is an immutable object that
 * includes immutable fields for the shapes and Keyframes represented as Maps. It is a
 * cs3500.animator.model for an animator that creates an animation based on user string input
 * detailing the animation. This was changed to hold a map of shapes to keyframes instead of a map
 * of shapes to instructions.
 */
public final class ShapeAnimationModel implements ExcellenceOperations {
  private final Map<String, Shape> shapes;
  private final Map<Shape, List<Keyframe>> keyFrames;
  private final Position topLeft;
  private double width;
  private double height;


  /**
   * A public constructor for the cs3500.animator.model that initializes the fields to empty Maps.
   */
  public ShapeAnimationModel() {
    this.shapes = new HashMap<>();
    this.keyFrames = new HashMap<>();

    this.topLeft = new Position(0, 0);
    this.width = 1000;
    this.height = 1000;
  }

  /**
   * Builder for our model.
   */
  public static final class Builder implements AnimationBuilder<ExcellenceOperations> {
    ExcellenceOperations e;

    public Builder() {
      e = new ShapeAnimationModel();
    }

    @Override
    public ExcellenceOperations build() {
      return e;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> setBounds(int x, int y, int width, int height) {
      this.e.setTopLeft(x, y);
      this.e.setHeight(height);
      this.e.setWidth(width);
      return this;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> declareShape(String name, String type) {
      Shape.ShapeType s = Shape.getTypeFromString(type);
      e.addShape(name, s);
      return this;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> addMotion(String name, int t1, int x1, int y1,
                                                            int w1, int h1, int r1, int g1, int b1,
                                                            int t2, int x2, int y2, int w2, int h2,
                                                            int r2, int g2, int b2) {
      Position p1;
      Position p2;
      Dimension d1;
      Dimension d2;
      Color c1;
      Color c2;
      try {
        p1 = new Position(x1, y1);
        p2 = new Position(x2, y2);
        d1 = new Dimension(w1, h1);
        d2 = new Dimension(w2, h2);
        c1 = new Color(r1, g1, b1);
        c2 = new Color(r2, g2, b2);
        e.addInstruction(name, t1, t2, p1, p2, d1, d2, c1, c2);
      } catch (IllegalArgumentException exc) {
        throw new IllegalArgumentException("Illegal parameters: " + exc.getMessage());
      }

      return this;
    }

    @Override
    public AnimationBuilder<ExcellenceOperations> addKeyframe(String name, int t, int x, int y,
                                                              int w, int h, int r, int g, int b) {
      Position pos;
      Dimension dim;
      Color col;

      try {
        pos = new Position(x, y);
        dim = new Dimension(w, h);
        col = new Color(r, g, b);

        e.addKeyframe(name, t, pos, dim, col);
      } catch (IllegalArgumentException exc) {
        throw new IllegalArgumentException("Illegal parameters: " + exc.getMessage());
      }
      return this;
    }
  }

  @Override
  public void deleteKeyframe(String shapeName, int atTick) {
    if (shapeName == null) {
      throw new IllegalArgumentException("the shape name cannot be null");
    }
    Shape s = shapes.get(shapeName);

    if (s == null) {
      throw new IllegalArgumentException("Shape does not exist");
    }
    if (!alreadyHasKeyframe(s, atTick)) {
      throw new IllegalArgumentException("There is no keyframe for that shape at that tick");
    }

    List<Keyframe> keys = keyFrames.get(s);
    List<Keyframe> copy = new ArrayList<>(keys);

    for (Keyframe k : copy) {
      if (k.getTick() == atTick) {
        keys.remove(k);
      }
    }

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

    Map<Shape, List<Instruction>> instructions = getInstructionsFromKeyframes(keyFrames);

    for (Map.Entry<Shape, List<Instruction>> e : instructions.entrySet()) {

      Shape s = e.getKey().makeCopy();


      List<Instruction> instructs = e.getValue();

      for (Instruction i : instructs) {
        if (tick >= i.getStartTick() && tick <= i.getEndTick()) {

          s.applyInstructionToTick(i, tick);

          if (!copy.contains(s)) {
            copy.add(s);
          }
        }
      }
    }

    return copy;
  }

  /**
   * Returns a hashmap of Shape to List of Instructions from a hashmap of Shape to Keyframe.
   *
   * @param keyframes is the hashmap of shapes to keyframes
   */
  private Map<Shape, List<Instruction>> getInstructionsFromKeyframes(Map<Shape,
          List<Keyframe>> keyframes) {

    HashMap<Shape, List<Instruction>> instructions = new HashMap<>();
    Keyframe oldKey = null;
    for (Map.Entry<Shape, List<Keyframe>> entry : keyframes.entrySet()) {


      Shape s = entry.getKey();
      instructions.put(s, new ArrayList<>());

      List<Keyframe> keys = entry.getValue();

      if (keys.size() == 0) {
        continue;
      }

      oldKey = keys.get(0);

      for (int i = 1; i < keys.size(); i++) {
        Keyframe k = keys.get(i);
        Instruction instruct = new Instruction(s.getName(), oldKey.getTick(), k.getTick(),
                oldKey.getPosition(), k.getPosition(), oldKey.getDimension(), k.getDimension(),
                oldKey.getColor(), k.getColor());

        instructions.get(s).add(instruct);
        oldKey = k;
      }
    }
    return instructions;
  }


  @Override
  public String toText() {
    Map<Shape, List<Instruction>> instructions = getInstructionsFromKeyframes(keyFrames);

    return toText(instructions);
  }

  /**
   * Uses our old toText method that uses a hashmap of shapes to instructions to create a text
   * output of the model.
   *
   * @param instructions is the hashmap of shapes to instructions.
   */
  private String toText(Map<Shape, List<Instruction>> instructions) {
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
  public Map<Shape, List<Instruction>> getInstructions() {
    //Makes a copy of the keyframes
    Map<Shape, List<Keyframe>> keys = getKeyframes();

    return getInstructionsFromKeyframes(keys);
  }

  @Override
  public Map<Shape, List<Keyframe>> getKeyframes() {
    HashMap<Shape, List<Keyframe>> newMap = new HashMap<>();

    for (Map.Entry<Shape, List<Keyframe>> e : keyFrames.entrySet()) {
      Shape newShape = e.getKey().makeCopy();
      List<Keyframe> newKeys = new ArrayList<>();

      for (Keyframe k : e.getValue()) {
        Keyframe newK = new Keyframe(k);
        newKeys.add(newK);
      }

      newMap.put(newShape, newKeys);
    }
    return newMap;
  }


  @Override
  public void addKeyframe(String shapeName, int tick, Position position, Dimension dimension, Color color) {
    try {
      Keyframe k = new Keyframe(shapeName, tick, position, dimension, color);
      Shape s = shapes.get(k.getShapeName());

      if (s == null) {
        throw new IllegalArgumentException("Shape does not exist in cs3500.animator.model");
      }
      if (alreadyHasKeyframe(s, k.getTick())) {
        throw new IllegalArgumentException("A keyframe at that time already exists");
      }

      addWithinKeyframes(k, keyFrames.get(s));

    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException("Keyframe not valid: " + iae.getMessage());
    }
  }

  /**
   * Adds the given keyframe in the proper position within the keyframes in the list of keyframes.
   * The proper position of a keyframe is relative to its tick, where the list is in increasing
   * order.
   *
   * @param kFrames is the list of keyframes to be added to
   * @param key     is the keyframe to be added
   */
  private void addWithinKeyframes(Keyframe key, List<Keyframe> kFrames) {
    int originalSize = kFrames.size();
    if (originalSize == 0) {
      kFrames.add(key);
    } else {
      Keyframe oldKey = kFrames.get(0);

      for (int i = 1; i < originalSize; i++) {
        if (key.getTick() < kFrames.get(i).getTick() && key.getTick() > oldKey.getTick()) {
          kFrames.add(i, key);
          i++;
        }
        oldKey = kFrames.get(i);
      }

      if (kFrames.size() == originalSize) {
        kFrames.add(key);
      }
    }
  }

  /**
   * Checks if this model already has a keyframe for that specific tick for the same shape.
   *
   * @param shape is the shape
   * @param tick  is the tick to be checked
   * @throws IllegalArgumentException if the shape does not exist in the model
   */
  private boolean alreadyHasKeyframe(Shape shape, int tick) {

    List<Keyframe> keys = keyFrames.get(shape);

    if (keys == null) {
      throw new IllegalArgumentException("Shape does not exist");
    }

    for (Keyframe k : keys) {
      if (k.getTick() == tick) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addInstruction(String shapeName, int startTick,
                             int endTick, Position startPos,
                             Position endPos, Dimension startDim,
                             Dimension endDim, Color startColor, Color endColor) {
    if (startTick < 0 || (startTick > endTick)) {
      throw new IllegalArgumentException("Ticks are invalid");
    }
    /*
    This method was changed from previous assignments so it deals with keyframes and adds them to
     the model instead of adding whole motions to the model.
     */

    try {
      Keyframe firstFrame = new Keyframe(shapeName, startTick, new Position(startPos),
              new Dimension(startDim), startColor);
      Keyframe secondFrame = new Keyframe(shapeName, endTick, new Position(endPos),
              new Dimension(endDim), endColor);

      Shape s = shapes.get(shapeName);
      if (s == null) {
        throw new IllegalArgumentException("Shape does not exist in model");
      }

      List<Keyframe> shapeKeys = keyFrames.get(s);
      if (shapeKeys.size() == 0) {
        shapeKeys.add(firstFrame);
        shapeKeys.add(secondFrame);
      } else {
        Keyframe lastKeyForShape = shapeKeys.get(shapeKeys.size() - 1);

        if (!firstFrame.equals(lastKeyForShape)) {
          throw new IllegalArgumentException("There are gaps in the motion of the shape");
        }
        shapeKeys.add(secondFrame);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Instruction is not valid: " + e.getMessage());
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
    keyFrames.put(s, new ArrayList<>());

  }

  @Override
  public void deleteShape(String shapeName) {
    if (shapeName == null) {
      throw new IllegalArgumentException("Shape name cannot be null");
    }
    Shape s = shapes.get(shapeName);

    if (s == null) {
      throw new IllegalArgumentException("Shape does not exist within the model");
    }

    shapes.remove(shapeName);
    keyFrames.remove(s);
  }

  @Override
  public void setTopLeft(int x, int y) {
    this.topLeft.setPosition(new Position(x, y));
  }

  @Override
  public void setHeight(double height) {
    this.height = height;
  }

  @Override
  public void setWidth(double width) {
    this.width = width;
  }

  @Override
  public int getLastTick() {
    int tick = 0;

    for (Map.Entry<Shape, List<Keyframe>> e : keyFrames.entrySet()) {
      try {
        List<Keyframe> keyframes = e.getValue();
        int kTick = keyframes.get(keyframes.size() - 1).getTick();

        if (kTick > tick) {
          tick = kTick;
        }
      } catch (IndexOutOfBoundsException ioe) {
        /*
        Does nothing as variable 'tick' already has some value.
         */
      }
    }

    return tick;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public Position getTopLeft() {
    return new Position(topLeft);
  }
}


//These are functions from previous assignments when we were dealing with only adding motions.
/*
private void addInstructToList(Instruction i, List<Instruction> instructs) {
  if (instructs.size() == 0) {
    instructs.add(i);

  } else {
    canAddInstructions(i, instructs);
    instructs.add(i);
  }
}

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
*/


