package cs3500.animator.view;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import cs3500.animator.model.Instruction;
import cs3500.animator.model.ReadOnlyExcellenceOperations;
import cs3500.animator.model.Shape;

/**
 * A view that appends a textual output to its appendable. It will output the text in SVG format for
 * the user to read.
 */
public class SVGView implements AnimatorView {

  private final ReadOnlyExcellenceOperations model;
  private final Appendable ap;
  private final double speed;

  /**
   * Public Constructor for SVG View that takes in the model and appendable it will output to.
   *
   * @param readOnlyModel the read only model.
   * @param ap            the appendable object it will output to
   * @param speed         is the speed of the animation
   */
  public SVGView(ReadOnlyExcellenceOperations readOnlyModel, Appendable ap, double speed) {
    model = readOnlyModel;
    this.ap = ap;
    this.speed = speed;
  }

  @Override
  public void refresh(int tick) {
    /*
    Similar to the textual view, this does not change anything, it will output to the
    user (through the appendable) that it does nothing.
     */
    try {
      ap.append("The refresh method does nothing in this case, this is a SVG view \n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Appendable cannot be appended to");
    }
  }

  @Override
  public void displayAnimation() {
    try {
      if (ap instanceof FileWriter) {
        FileWriter fw = (FileWriter) ap;
        fw.append(getSVGText(model));
        fw.close();
      } else {
        ap.append(getSVGText(model));
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Appendable cannot be appended to");
    }
  }

  /**
   * Returns a textual description of the model in SVG format.
   *
   * @param model the described model
   */
  private String getSVGText(ReadOnlyExcellenceOperations model) {
    String text = "<?xml version=\"1.0\" standalone=\"no\"?>\n" +
            "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n" +
            "    \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
            "<svg " + makeAttributeDescription("viewbox",
            model.getTopLeft().getX() + " " + model.getTopLeft().getY()
                    + " " + model.getWidth() + " " + model.getHeight()) + "\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n";


    for (Map.Entry<Shape, List<Instruction>> e : model.getInstructions().entrySet()) {
      text = text.concat(makeShapeDescription(e.getKey()));

      for (Instruction i : e.getValue()) {
        text = text.concat(makeInstructDescription(e.getKey().getType(), i));
      }
    }

    text = text.concat("</g>\n</svg>");


    return text;
  }

  /**
   * Returns a string that describes a shape in SVG format.
   *
   * @param shape the shape to be described
   */
  private String makeShapeDescription(Shape shape) {
    String desc = "";

    if (shape.getType().equals(Shape.ShapeType.RECTANGLE)) {
      desc = desc.concat("<rect");

      desc = desc.concat(" " + makeAttributeDescription("id", shape.getName()));

      desc = desc.concat(" " + makeAttributeDescription("x",
              Double.toString(shape.getPosition().getX())));

      desc = desc.concat(" " + makeAttributeDescription("y",
              Double.toString(shape.getPosition().getY())));

      desc = desc.concat(" " + makeAttributeDescription("width",
              Double.toString(shape.getDimension().getX())));

      desc = desc.concat(" " + makeAttributeDescription("height",
              Double.toString(shape.getDimension().getY())));

      desc = desc.concat(" " + makeAttributeDescription("fill",
              "rgb(" + shape.getColor().getRed() + ","
                      + shape.getColor().getGreen() + "," + shape.getColor().getBlue() + ")"));

      desc = desc.concat("/>\n");
    }
    if (shape.getType().equals(Shape.ShapeType.OVAL)) {
      desc = desc.concat("<ellipse");

      desc = desc.concat(" " + makeAttributeDescription("id", shape.getName()));

      desc = desc.concat(" " + makeAttributeDescription("cx",
              Double.toString(shape.getPosition().getX())));

      desc = desc.concat(" " + makeAttributeDescription("cy",
              Double.toString(shape.getPosition().getY())));

      desc = desc.concat(" " + makeAttributeDescription("rx",
              Double.toString(shape.getDimension().getX())));

      desc = desc.concat(" " + makeAttributeDescription("ry",
              Double.toString(shape.getDimension().getY())));

      desc = desc.concat(" " + makeAttributeDescription("fill",
              "rgb(" + shape.getColor().getRed() + ","
                      + shape.getColor().getGreen() + "," + shape.getColor().getBlue() + ")"));

      desc = desc.concat(">\n");
    }


    return desc;
  }

  /**
   * Returns the description of an attribute of some sort in the format attName="attDescription".
   *
   * @param attName        the name of the attribute
   * @param attDescription the description of the attribute
   */
  private String makeAttributeDescription(String attName, String attDescription) {
    String text = attName;
    text = text.concat("=\"" + attDescription + "\"");

    return text;
  }

  /**
   * Returns the description of an instruction in SVG format.
   *
   * @param type        the type of shape
   * @param instruction the instruction to be described
   */
  private String makeInstructDescription(Shape.ShapeType type, Instruction instruction) {
    String desc = "";

    String startColor = "rgb(" + instruction.getStartColor().getRed() + ","
            + instruction.getStartColor().getGreen() + "," + instruction.getStartColor().getBlue() +
            ")";
    String endColor = "rgb(" + instruction.getEndColor().getRed() + ","
            + instruction.getEndColor().getGreen() + "," + instruction.getEndColor().getBlue() +
            ")";

    if (type.equals(Shape.ShapeType.RECTANGLE)) {
      desc = desc.concat(makeSingleMotionDescription(instruction, "x",
              Double.toString(instruction.getStartPosition().getX()),
              Double.toString(instruction.getEndPosition().getX()), speed));

      desc = desc.concat(makeSingleMotionDescription(instruction, "y",
              Double.toString(instruction.getStartPosition().getY()),
              Double.toString(instruction.getEndPosition().getY()), speed));

      desc = desc.concat(makeSingleMotionDescription(instruction, "width",
              Double.toString(instruction.getStartDimension().getX()),
              Double.toString(instruction.getEndDimension().getX()), speed));

      desc = desc.concat(makeSingleMotionDescription(instruction, "height",
              Double.toString(instruction.getStartDimension().getY()),
              Double.toString(instruction.getEndDimension().getY()), speed));


      desc = desc.concat(makeSingleMotionDescription(instruction, "fill", startColor, endColor,
              speed));

    } else {

      desc = desc.concat(makeSingleMotionDescription(instruction, "cx",
              Double.toString(instruction.getStartPosition().getX()),
              Double.toString(instruction.getEndPosition().getX()), speed));

      desc = desc.concat(makeSingleMotionDescription(instruction, "cy",
              Double.toString(instruction.getStartPosition().getY()),
              Double.toString(instruction.getEndPosition().getY()), speed));

      desc = desc.concat(makeSingleMotionDescription(instruction, "rx",
              Double.toString(instruction.getStartDimension().getX()),
              Double.toString(instruction.getEndDimension().getX()), speed));

      desc = desc.concat(makeSingleMotionDescription(instruction, "ry",
              Double.toString(instruction.getStartDimension().getY()),
              Double.toString(instruction.getEndDimension().getY()), speed));


      desc = desc.concat(makeSingleMotionDescription(instruction, "fill", startColor, endColor,
              speed));
    }
    return desc;
  }

  /**
   * Returns the description of a motion for a single shape attribute (such as xPosition or
   * yPosition) in SVG format.
   *
   * @param instruction   is the instruction in question
   * @param attributeName is the attribute name
   * @param startState    is the start state for that attribute
   * @param endState      is the end state for that attribute
   * @param speed         is the speed of the animation
   */
  private String makeSingleMotionDescription(Instruction instruction, String attributeName,
                                             String startState, String endState, double speed) {
    String desc = "";
    desc = desc.concat("<animate ");
    desc = desc.concat(makeAttributeDescription("attributeName", attributeName) + " ");
    desc = desc.concat(makeAttributeDescription("attributeType", "XML")
            + " ");

    desc = desc.concat(makeAttributeDescription("begin",
            (instruction.getStartTick() * 1.0 / speed) + "s") + " ");
    desc = desc.concat(makeAttributeDescription("dur",
            ((instruction.getEndTick()
                    - instruction.getStartTick()) * 1.0 / speed) + "s") + " ");

    desc = desc.concat(makeAttributeDescription("fill", "freeze") + " ");

    desc = desc.concat(makeAttributeDescription("from", startState) + " ");
    desc = desc.concat(makeAttributeDescription("to", endState) + " ");

    desc = desc.concat("/>\n");


    return desc;
  }


}

