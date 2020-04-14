package cs3500.animator.provider.view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Visual View class for drawing an animation in a window.
 */
public class VisualView extends JFrame implements IView {
  protected AnimationPanel animationPanel;

  /**
   * Constructs a VisualView.
   */
  public VisualView() {
    super();
    this.setTitle("Animation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.animationPanel = null;
    this.setBackground(Color.WHITE);
  }

  @Override
  public void render(Animator model, int ticksPerSecond) {
    this.animationPanel = new AnimationPanel(model);
    this.modifyAnimationSpeed(ticksPerSecond);
    this.add(new JScrollPane(animationPanel));
    this.setPreferredSize(this.animationPanel.getPreferredSize());
    this.pack();
    this.animationPanel.play();
    setVisible(true);
  }

  /**
   * Modifies the speed of the animation.
   * @param speed  new Speed to be set.
   */
  public void modifyAnimationSpeed(int speed) {
    if (this.animationPanel == null) {
      throw new NullPointerException("Cannot modify animation speed while not rendering an " +
              "animation.");
    }
    this.animationPanel.setTicksPerSecond(speed);
    this.animationPanel.refreshTimer();
  }

  @Override
  public void setOutput(Appendable w) {
    // I think you guys said this was allowed to fail silently?
  }

  @Override
  public void setListener(ActionListener listener) {
    // doing nothing isn't the best option but we didn't have time to implement the strategy
    // pattern or anything that would've made this unnecessary while allowing the controller to
    // work on any views
  }
}
