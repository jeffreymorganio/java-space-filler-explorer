package com.usabilityetc.spacefillerexplorer.tree;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A tree structure for representing the name and size of objects
 * that are visualised by the TreemapPanel class.
 * 
 * @author Jeffrey Morgan
 */
public class Tree {
  private final List<Tree> children = new ArrayList<>();
  private String name = null;
  private long size = 0;
  private Color color;

  public Tree(final String name, final long size) {
    this.name = name;
    this.size = size;
  }

  public void addChild(final Tree child) {
    children.add(child);
  }

  public String getName() {
    return name;
  }

  public long getSize() {
    long totalSize = 0;
    if (children.isEmpty()) {
      totalSize = size;
    } else {
      for (Tree child : children) {
        totalSize += child.getSize();
      }
    }
    size = totalSize;
    return size;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(final Color color) {
    this.color = color;
  }

  public List<Tree> getChildren() {
    return children;
  }
}
