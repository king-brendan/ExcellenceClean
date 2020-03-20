package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

/**
 * Represents a Shape object to be altered in the animation. Contains all the shape conditions such
 * as its name, type, position, dimension, and color at any point. It is an immutable object with
 * immutable fields. Outside the package, it is read-only such that only the constructor and getters
 * are accessible publicly which return immutable fields or copies of mutable fields. From
 * assignment 5, we deleted assignBeginningConditions() since we have  no need for it anymore and
 * changed the applyInstructionToTick method so that it accounts  for the tweening formula in
 * assignment 6.
 */
public final class Shape {
  private final String name;
  private Color color;
  private final Dimension dimension;
  private final Position position;
  private final ShapeType type;

  /**
   * A class to represent the shape type, this will be accessed in the view through the getType
   * method to decide on how to draw it in the view. We are not sure how many shape types we are
   * expected to handle. Therefore, we have modeled a rectangle, oval, and triangle as the basic
   * three shapes. Out of which we can also make squares, circles, and ellipses. Other shapes such
   * as hexagons, pentagons, heptagons...etc can be added later if we are expected to handle them as
   * well.
   */
  public enum ShapeType {
    RECTANGLE, OVAL;


    @Override
    public String toString() {
      if (this.equals(ShapeType.RECTANGLE)) {
        return "rectangle";
      } else if (this.equals(ShapeType.OVAL)) {
        return "oval";
      } else {
        return "Please assign a toString to the ShapeType";
      }
    }
  }

  /**
   * Returns the ShapeType corresponding to the given String.
   *
   * @param s the String describing the ShapeType.
   * @return the corresponding ShapeType.
   * @throws IllegalArgumentException if the shape type name is invalid or if the parameters are
   * null.
   */
  //TODO: change to a switch statement if possible
  public static ShapeType getType(String s) {
    if (s.equals(ShapeType.RECTANGLE.toString())) {
      return ShapeType.RECTANGLE;
    } else if (s.equals(ShapeType.OVAL.toString())) {
      return ShapeType.OVAL;
    } else {
      throw new IllegalArgumentException("Invalid shape type");
    }
  }

  /**
   * Initializes a shape with only its name and type. Other values are assigned their real states
   * when the first instruction is added. In the view, a shape will never be drawn unless it has an
   * instruction being applied to it. Therefore, they can have arbitrary values that are not null
   * when being created and will not be shown to the user until an instruction is applied to it.
   *
   * @param name the  shape  name
   * @param type is the shape type
   * @throws IllegalArgumentException if any parameters are null
   */
  public Shape(String name, ShapeType type) {
    if (name == null || type == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.name = name;
    this.type = type;
    this.color = Constants.BACKGROUND_COLOR;
    this.dimension = new Dimension(0, 0);
    this.position = new Position(0, 0);
  }

  /**
   * Makes a copy of the shape. Set to default for now since a user has no need for it. It might be
   * refactored later if it is needed in other packages.
   */
  Shape makeCopy() {
    Shape s = new Shape(getName(), getType());
    s.color = getColor();
    s.dimension.setDimension(getDimension());
    s.position.setPosition(getPosition());
    return s;
  }


  /**
   * Applies the given instruction to the Shape. It will only apply a change to position, dimension,
   * or color if there is one, and will apply a fraction of the change per tick since the
   * instruction is applied over several ticks. Set as default since it will only happen within the
   * cs3500.animator.model.
   *
   * @param instruction is the given Instruction
   * @param tick        is the tick to which the instruction will be applied to
   * @throws IllegalArgumentException if the instruction is null.
   */
  void applyInstructionToTick(Instruction instruction, int tick) {
    if (instruction == null) {
      throw new IllegalArgumentException("Instruction cannot be null");
    }

    double startConstant = getStartStateConstant(instruction.getStartTick() * 1.0,
            instruction.getEndTick() * 1.0, tick * 1.0);
    double endConstant = getEndStateConstant(instruction.getStartTick() * 1.0,
            instruction.getEndTick() * 1.0,
            tick * 1.0);

    this.changeColor(instruction.getStartColor(), instruction.getEndColor(), startConstant,
            endConstant);
    this.changeDimension(instruction.getStartDimension(), instruction.getEndDimension(),
            startConstant, endConstant);
    this.changePosition(instruction.getStartPosition(), instruction.getEndPosition(), startConstant,
            endConstant);
  }

  /**
   * Returns the constant to which the start state will be multiplied with, according to the linear
   * interpolation formula given in assignment 6.
   *
   * @param currentTick is the current tick
   * @param endTick     is the last tick in the instruction
   * @param startTick   is the first tick in the instruction
   */
  private double getStartStateConstant(double startTick, double endTick, double currentTick) {
    return (endTick - currentTick) / (endTick - startTick);
  }

  /**
   * Returns the constant to which the end state will be multiplied with, according to the linear
   * interpolation formula given in assignment 6.
   *
   * @param currentTick is the current tick
   * @param endTick     is the last tick in the instruction
   * @param startTick   is the first tick in the instruction
   */
  private double getEndStateConstant(double startTick, double endTick, double currentTick) {
    return (currentTick - startTick) / (endTick - startTick);
  }


  /**
   * Changes the shape to the end color if it is different than the start color.
   *
   * @param startColor    is the starting color
   * @param endColor      is the ending color
   * @param startConstant is the constant to be multiplied by the start state
   * @param endConstant   is the constant to be multiplied by the end state
   */
  private void changeColor(Color startColor, Color endColor, double startConstant,
                           double endConstant) {

    double rDiff = endColor.getRed() * endConstant + startColor.getRed() * startConstant;
    double gDiff = endColor.getGreen() * endConstant + startColor.getGreen() * startConstant;
    double bDiff = endColor.getBlue() * endConstant + startColor.getBlue() * startConstant;

    int newR = checkColorRange((int) rDiff);
    int newG = checkColorRange((int) gDiff);
    int newB = checkColorRange((int) bDiff);

    this.color = new Color(newR, newG, newB);

  }


  /**
   * Makes sure that the int is within the range 0-255 and returns the max or min number if it is
   * not. This is used since issues in rounding might cause the numbers to be slightly out of range
   * for RGB in Color.
   *
   * @param i is the integer.
   * @return an int between 0-255
   */
  private int checkColorRange(int i) {
    int ret = i;
    if (i < 0) {
      ret = 0;
    }
    if (i > 255) {
      ret = 255;
    }
    return ret;
  }

  /**
   * Changes the shape position to the end position if it is different than the start position.
   *
   * @param startPos      is the Starting position
   * @param endPos        is the ending Position
   * @param startConstant is the constant to be multiplied by the start state
   * @param endConstant   is the constant to be multiplied by the end state
   */
  private void changePosition(Position startPos, Position endPos, double startConstant,
                              double endConstant) {

    double newX = startPos.getX() * startConstant + endPos.getX() * endConstant;
    double newY = startPos.getY() * startConstant + endPos.getY() * endConstant;

    Position newPos = new Position(newX, newY);

    this.position.setPosition(newPos);


  }

  /**
   * Changes the dimension of the shape to the end dimension if it is different than the start
   * dimension.
   *
   * @param startDim      is the current Dimension.
   * @param endDim        the new dimension to be set.
   * @param startConstant is the constant to be multiplied by the start state
   * @param endConstant   is the constant to be multiplied by the end state
   */
  private void changeDimension(Dimension startDim, Dimension endDim, double startConstant,
                               double endConstant) {


    double newX = endDim.getX() * endConstant + startDim.getX() * startConstant;
    double newY = endDim.getY() * endConstant + startDim.getY() * startConstant;

    Dimension newDim = new Dimension(newX, newY);

    this.dimension.setDimension(newDim);

  }

  /**
   * Returns the name of the shape.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns a copy of the color of the shape.
   */
  public Color getColor() {
    return new Color(this.color.getRGB());
  }

  /**
   * Returns a copy of the current position.
   */
  public Position getPosition() {
    return new Position(position);
  }

  /**
   * Returns a copy of the current dimension.
   */
  public Dimension getDimension() {
    return new Dimension(dimension);
  }

  /**
   * Returns the type of the shape.
   */
  public ShapeType getType() {
    return type;
  }

  @Override
  public String toString() {
    return "shape " + name + " " + type.toString();
  }

  //A shape is equal according to its name and type. Our cs3500.animator.model assures that no two shapes will
  // have the same name, therefore this cannot give a false positive for shapes in the cs3500.animator.model.
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shape shape = (Shape) o;
    return name.equals(shape.name) &&
            type == shape.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }
}
