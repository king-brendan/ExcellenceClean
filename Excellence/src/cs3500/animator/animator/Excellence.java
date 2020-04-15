package cs3500.animator;


import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.model.ReadOnlyExcellenceOperations;

import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.ViewFactory;


/**
 * The main class for running the Excellence model application. In order for the program to be run,
 * the input file, view type, output destination, and animation speed must be entered as command-
 * line arguments with the prefixes -in, -view, -out, and -speed respectively. Note that the input
 * file name must be the absolute file path of the desired file; that is, it must contain every
 * directory that the file is nested in. For example: /users/your_name/Desktop/Excellence
 * /src/cs3500/animator/big-bang-big-crunch.txt
 */
public final class Excellence {
  /**
   * The main method for running our views according to the javadoc above.
   */
  public static void main(String[] args) {
    if (args.length % 2 != 0) {
      throw new IllegalArgumentException("Command-Line Arguments need to be even");
    }

    String animationFile = "";
    String viewType = "";
    String output = "";
    int speed = 1;

    try {
      for (int i = 0; i < args.length; i++) {
        switch (args[i]) {
          case "-in":
            animationFile = args[i + 1];
            break;
          case "-view":
            viewType = args[i + 1];
            break;
          case "-out":
            output = args[i + 1];
            break;
          case "-speed":
            speed = Integer.parseInt(args[i + 1]);
            break;
        }
      }
    } catch (Exception exc) {
      throw new IllegalArgumentException("Illegal command-line arguments: " + exc.getMessage());
    }


    ReadOnlyExcellenceOperations m;
    Readable in;

    try {
      in = new BufferedReader(new FileReader(animationFile));
      ShapeAnimationModel.Builder builder = new ShapeAnimationModel.Builder();
      m = AnimationReader.parseFile(in, builder);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not create model from file: " + e.getMessage());
    }

    AnimatorView view;

    if (!output.equals("")) {
      try {
        FileWriter fw = new FileWriter(output);
        view = ViewFactory.makeView(viewType, fw, speed, m);
      } catch (Exception ioe) {
        throw new IllegalArgumentException("Output field is not valid");
      }
    } else {
      try {
        view = ViewFactory.makeView(viewType, System.out, speed, m);
      } catch (IllegalArgumentException iae) {
        throw new IllegalArgumentException(iae.getMessage());
      }
    }


    view.displayAnimation();
  }
}
