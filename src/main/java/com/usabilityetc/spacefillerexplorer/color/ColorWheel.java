package com.usabilityetc.spacefillerexplorer.color;

import java.awt.Color;

/**
 * The ColorWheel class generates a sequence of colors. When the last
 * color in the sequence has been returned by the nextColor() method,
 * the sequence resets to the first color.
 * 
 * @author Jeffrey Morgan
 */
public class ColorWheel {
  private final float saturation = 0.65f;
  private final float brightness = 0.90f;
  private int nextColourNumber = 0;
  private int maxColourNumber;

  public ColorWheel() {
    this(10);
  }
  
  public ColorWheel(final int numberOfColors) {
    maxColourNumber = numberOfColors;
  }
  
  public Color nextColor() {
    incrementNextColor();
    final float hue = (float)nextColourNumber / maxColourNumber;
    return Color.getHSBColor(hue, saturation, brightness);
  }
  
  private void incrementNextColor() {
    nextColourNumber += 1;
    if (nextColourNumber > maxColourNumber) {
      nextColourNumber = 0;
    }
  }
}
