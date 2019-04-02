package com.arkumbra.raytracer.light;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.geometry.Point;

public class PointLight {

  private final Point position;
  private final Colour intensity;

  public PointLight(Point position, Colour intensity) {
    this.position = position;
    this.intensity = intensity;
  }

  public Point getPosition() {
    return position;
  }

  public Colour getIntensity() {
    return intensity;
  }
}
