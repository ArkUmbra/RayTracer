package com.arkumbra.raytracer;

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
    jframe.setVisible(true);
    jframe.setSize(WIDTH,HEIGHT);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Canvas c = new PaintCanvas();
    c.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    JPanel jpanel = new JPanel();
    jframe.getContentPane().add(jpanel);
    jpanel.add(c);
  }

}
