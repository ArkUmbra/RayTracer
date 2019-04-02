package com.arkumbra.raytracer.light;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.geometry.Calc;
import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Tuple;
import com.arkumbra.raytracer.geometry.Vector;

public class Material {

  private static final Colour DEFAULT_COLOUR = new Colour(1, 1, 1);
  private static final double DEFAULT_AMBIENT = 0.1d;
  private static final double DEFAULT_DIFFUSE = 0.9;
  private static final double DEFAULT_SPECULAR = 0.9;
  private static final double DEFAULT_SHININESS = 200.0;

  private Colour colour;
  private double ambient;
  private double diffuse;
  private double specular;
  private double shininess;

  public Material() {
    setColour(DEFAULT_COLOUR);
    setAmbient(DEFAULT_AMBIENT);
    setDiffuse(DEFAULT_DIFFUSE);
    setSpecular(DEFAULT_SPECULAR);
    setShininess(DEFAULT_SHININESS);
  }

  public Material(Colour colour, double ambient, double diffuse, double specular, double shininess) {
    setColour(colour);
    setAmbient(ambient);
    setDiffuse(diffuse);
    setSpecular(specular);
    setShininess(shininess);
  }

  public Colour lighting(PointLight light, Point posToBeLit, Vector eye, Vector normal) {
    // combine the surface color with the light's color/intensity
    Colour effectiveColour = (Colour) this.colour.multiply(light.getIntensity());
    // find the direction to the light source
    Vector lightVector = (Vector) light.getPosition().minus(posToBeLit).normalize();
    // compute the ambient contribution
    Colour ambientEffect = (Colour) effectiveColour.multiply(this.ambient);

    // light_dot_normal represents the cosine of the angle between the
    // light vector and the normal vector. A negative number means the
    // light is on the other side of the surface.
    double lightDotNormal = lightVector.dotProd(normal);

    Colour diffuseEffect;
    Colour specularEffect;

    if (lightDotNormal < 0) {
      diffuseEffect = Colour.BLACK;
      specularEffect = Colour.BLACK;

    } else {
      diffuseEffect = (Colour) effectiveColour.multiply(this.diffuse).multiply(lightDotNormal);

      // reflect_dot_eye represents the cosine of the angle between the
      // reflection vector and the eye vector. A negative number means the
      // light reflects away from the eye.
      Vector reflectV = ((Vector)lightVector.negate()).reflect(normal);
      double reflectDotEye = reflectV.dotProd(eye);

      if (reflectDotEye <= 0) {
        specularEffect = Colour.BLACK;
      } else {
        double factor = Math.pow(reflectDotEye, this.shininess);
        specularEffect = (Colour) light.getIntensity().multiply(this.specular).multiply(factor);
      }
    }

    return (Colour) ambientEffect.add(diffuseEffect).add(specularEffect);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Material) {
      Material other = (Material) obj;

      return colour.equals(other.colour) &&
          Calc.approxEqual(ambient, other.ambient) &&
          Calc.approxEqual(diffuse, other.diffuse) &&
          Calc.approxEqual(shininess, other.shininess) &&
          Calc.approxEqual(specular, other.specular);
    }

    return false;
  }

  public void setColour(Colour colour) {
    this.colour = colour;
  }

  public void setAmbient(double ambient) {
    throwExceptionIfNegative(ambient);

    this.ambient = ambient;
  }

  public void setDiffuse(double diffuse) {
    throwExceptionIfNegative(diffuse);

    this.diffuse = diffuse;
  }

  public void setSpecular(double specular) {
    throwExceptionIfNegative(specular);
    this.specular = specular;
  }

  public void setShininess(double shininess) {
    throwExceptionIfNegative(shininess);
    this.shininess = shininess;
  }

  private void throwExceptionIfNegative(double val) {
    if (ambient < 0) {
      throw new RuntimeException("Value should not be negative");
    }
  }

  public Colour getColour() {
    return colour;
  }

  public double getAmbient() {
    return ambient;
  }

  public double getDiffuse() {
    return diffuse;
  }

  public double getShininess() {
    return shininess;
  }

  public double getSpecular() {
    return specular;
  }

}
