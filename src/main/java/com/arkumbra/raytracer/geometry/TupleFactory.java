package com.arkumbra.raytracer.geometry;

import static com.arkumbra.raytracer.geometry.Calc.approxEqual;

import com.arkumbra.raytracer.canvas.Colour;

public class TupleFactory {
  private static final float POINT_W = 1;
  private static final float VECTOR_W = 0;


  public static Tuple create(double x, double y, double z, double w) {
    if (approxEqual(w, VECTOR_W)) {
      return new Vector(x, y, z);

    } else if (approxEqual(w, POINT_W)) {
      return new Point(x, y, z);

    } else {
      return new Point(x, y, z, w);
    }

//    } else if (approxEqual(w, POINT_W)) {
//      return new Point(x, y, z);
//
//    } else {
//      throw new RuntimeException("W must be either 0 or 1!");
//    }
  }

  public static Tuple create(Class<? extends Tuple> clazz, double x, double y, double z, double w) {
    if (clazz.equals(Colour.class)) {
      return new Colour(x, y, z);

    } else if (clazz.equals(Vector.class)) {
      return new Vector(x, y, z, w);

    } else if (clazz.equals(Point.class)) {
      return new Point(x, y, z, w);

    } else {
      return new Point(x, y, z, w);
    }
  }


}
