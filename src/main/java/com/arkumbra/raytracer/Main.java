package com.arkumbra.raytracer;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.canvas.SceneCanvas;
import com.arkumbra.raytracer.ui.PaintCanvas;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

  private static final int WIDTH = 800, HEIGHT = 600;


  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    JFrame jframe = new JFrame("RayTracer");
    jframe.setSize(WIDTH,HEIGHT);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    PaintCanvas c = new PaintCanvas();
    c.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    writeScene(c);

    JPanel jpanel = new JPanel();
    jframe.getContentPane().add(jpanel);
    jpanel.add(c);

    jframe.setVisible(true);
  }

  private void writeScene(PaintCanvas paintCanvas) {
    SceneCanvas sceneCanvas = new SceneCanvas(WIDTH, HEIGHT);

    Colour colour = new Colour(1,0,0);
    for (int x = 200; x < 600; x++) {
      for (int y = 500; y < 550; y++) {
        sceneCanvas.write(x, y, colour);
      }
    }

    paintCanvas.setSceneCanvas(sceneCanvas);
    paintCanvas.invalidate();
  }

}
