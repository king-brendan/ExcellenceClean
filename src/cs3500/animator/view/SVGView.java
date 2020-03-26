package cs3500.animator.view;


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

  private ReadOnlyExcellenceOperations model;
  private Appendable ap;

  /**
   * Public Constructor for SVG View that takes in the model and appendable it will output to.
   *
   * @param readOnlyModel the read only model.
   * @param ap            the appendable object it will output to
   */
  public SVGView(ReadOnlyExcellenceOperations readOnlyModel, Appendable ap) {
    model = readOnlyModel;
    this.ap = ap;
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
      ap.append(getSVGText(model));
    } catch (IOException ioe) {
      throw new IllegalStateException("Appendable cannot be appended to");
    }
  }

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

      desc = desc.concat(">\n");
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

  private String makeAttributeDescription(String attName, String attDescription) {
    String text = attName;
    text = text.concat("=\"" + attDescription + "\"");

    return text;
  }

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
              Double.toString(instruction.getEndPosition().getX())));

      desc = desc.concat(makeSingleMotionDescription(instruction, "y",
              Double.toString(instruction.getStartPosition().getY()),
              Double.toString(instruction.getEndPosition().getY())));

      desc = desc.concat(makeSingleMotionDescription(instruction, "width",
              Double.toString(instruction.getStartDimension().getX()),
              Double.toString(instruction.getEndDimension().getX())));

      desc = desc.concat(makeSingleMotionDescription(instruction, "height",
              Double.toString(instruction.getStartDimension().getY()),
              Double.toString(instruction.getEndDimension().getY())));


      desc = desc.concat(makeSingleMotionDescription(instruction, "fill", startColor, endColor));

    } else {

      desc = desc.concat(makeSingleMotionDescription(instruction, "cx",
              Double.toString(instruction.getStartPosition().getX()),
              Double.toString(instruction.getEndPosition().getX())));

      desc = desc.concat(makeSingleMotionDescription(instruction, "cy",
              Double.toString(instruction.getStartPosition().getY()),
              Double.toString(instruction.getEndPosition().getY())));

      desc = desc.concat(makeSingleMotionDescription(instruction, "rx",
              Double.toString(instruction.getStartDimension().getX()),
              Double.toString(instruction.getEndDimension().getX())));

      desc = desc.concat(makeSingleMotionDescription(instruction, "ry",
              Double.toString(instruction.getStartDimension().getY()),
              Double.toString(instruction.getEndDimension().getY())));


      desc = desc.concat(makeSingleMotionDescription(instruction, "fill", startColor, endColor));
    }
    return desc;
  }

  private String makeSingleMotionDescription(Instruction instruction, String attributeName,
                                             String startState, String endState) {
    String desc = "";
    desc = desc.concat("<animate ");
    desc = desc.concat(makeAttributeDescription("attributeName", attributeName) + " ");
    desc = desc.concat(makeAttributeDescription("attributeType", "XML") + " ");

    desc = desc.concat(makeAttributeDescription("begin",
            Integer.toString(instruction.getStartTick())) + " ");
    desc = desc.concat(makeAttributeDescription("dur",
            (Integer.toString(instruction.getEndTick() - instruction.getStartTick()))) + " ");

    desc = desc.concat(makeAttributeDescription("fill", "freeze") + " ");

    desc = desc.concat(makeAttributeDescription("from", startState) + " ");
    desc = desc.concat(makeAttributeDescription("to", endState) + " ");

    desc = desc.concat("/>\n");


    return desc;
  }


}

