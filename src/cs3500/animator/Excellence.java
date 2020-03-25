package cs3500.animator;

import java.awt.*;

import cs3500.animator.model.Dimension;
import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.VisualView;

public final class Excellence {
  public static void main(String[] args) {

      ExcellenceOperations hwExample = new ShapeAnimationModel();

      Position p10x200 = new Position(10, 200);
      Position p200x200 = new Position(200, 200);
      Position p300x300 = new Position(300, 300);
      Position p440x70 = new Position(50, 70);
      Position p440x250 = new Position(50, 250);
      Position p440x370 = new Position(50, 370);

      Dimension d25x100 = new Dimension(25, 100);
      Dimension d50x100 = new Dimension(50, 100);
      Dimension d120x60 = new Dimension(120, 60);

      Color greenBlue = new Color(5, 60, 73); //Found online

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


    AnimatorView view = new VisualView(hwExample, 10);

    view.displayAnimation();
    /*// FILL IN HERE
    //ExcellenceOperations e ;
    //depending on what the command line arguments are, determine what kind of view to create
    //and create it, calling it v
    for (String s : args) {

    }

    String animationFile = "";
    Readable in;
    try {
      in = new BufferedReader(new FileReader(animationFile));
      ShapeAnimationModel.Builder builder = new ShapeAnimationModel.Builder();
      //return AnimationReader.parseFile(in, builder);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read file and create model");
    }
    //create a controller that takes in the view we created

    //play animation from controller*/
  }
}
