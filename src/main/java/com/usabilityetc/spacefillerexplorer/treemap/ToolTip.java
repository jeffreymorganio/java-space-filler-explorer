package com.usabilityetc.spacefillerexplorer.treemap;

import java.awt.*;

/**
 * Holds the information about a file required to present a tool tip
 * for the rectangular region on a TreeMap panel that represent the file.
 * 
 * @author Jeffrey Morgan
 */
public class ToolTip {
	private static final String toolTipFormat = "<html><pre>%s\n\n%d bytes\n%s</pre></html>";
	private final Rectangle rectangle;
	private final String text;
	private final long size;
	private String toolTipText;
	
	public ToolTip(final String text, final long size, final Rectangle rectangle) {
		this.text = text;
		this.size = size;
		this.rectangle = rectangle;
	}
	
	public boolean contains(final Point p) {
		return rectangle.contains(p);
	}
	
	public String getToolTipText() {
		if (toolTipText == null) {
			toolTipText = createToolTipText();
		}
		return toolTipText;
	}
	
	private String createToolTipText() {
		final String[] pathComponents = text.split("/");
		final String filename = pathComponents[pathComponents.length - 1];
		final String pathHierarchy = createPathHierarchy(pathComponents);
		return String.format(
			toolTipFormat,
			filename,
			size,
			pathHierarchy
		);
	}
	
	private String createPathHierarchy(final String[] pathComponents) {
		final StringBuffer pathHierarchy = new StringBuffer();
		if (pathComponents.length > 1) {
			pathHierarchy.append("\n");
		}
		for (int index = 1; index < pathComponents.length; index += 1) {
			pathHierarchy.append(pathComponents[index]);
			if (index < pathComponents.length - 1) {
				pathHierarchy.append("\n");
				pathHierarchy.append(indentation(2 * index));
			}
		}
		return pathHierarchy.toString();
	}
	
	private String indentation(final int length) {
		final Character indentationCharacter = ' ';
		final StringBuffer indentation = new StringBuffer(length);
		for (int i = 0; i < length; i += 1) {
			indentation.append(indentationCharacter);
		}
		return indentation.toString();
	}
}
