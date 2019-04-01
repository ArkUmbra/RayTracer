package com.arkumbra.raytracer.shapes;

import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Intersections {

//  private TreeSet<Intersection> sortedIntersections = new TreeSet<>();
  private PriorityQueue<Intersection> sortedIntersections = new PriorityQueue<>();

  public Intersections(Intersection... intersections) {
    for (Intersection in : intersections) {
      sortedIntersections.add(in);
    }
  }

  public Intersection get(int pos) {
    Iterator<Intersection> iter = sortedIntersections.iterator();

    int i = 0;
    while (iter.hasNext()) {
      Intersection intersection = iter.next();
      if (i == pos) {
        return intersection;
      } else {
        i++;
      }
    }

    return null;
  }

  public Intersection getHit() {
    for (Intersection in : sortedIntersections) {
      if (in.getT() >= 0) {
        return in;
      }
    }

    return null;
  }

  public int length() {
    return sortedIntersections.size();
  }

}
