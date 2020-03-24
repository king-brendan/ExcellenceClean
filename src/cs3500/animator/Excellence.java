package cs3500.animator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import cs3500.animator.model.ExcellenceOperations;
import cs3500.animator.model.ShapeAnimationModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorView;

public final class Excellence {
  public static void main(String[] args) {
    // FILL IN HERE
    //ExcellenceOperations e ;
    //depending on what the command line arguments are, determine what kind of view to create
    //and create it, calling it v

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

    //play animation from controller
  }
}
