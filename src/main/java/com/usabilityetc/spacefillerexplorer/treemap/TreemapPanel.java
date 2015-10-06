package com.usabilityetc.spacefillerexplorer.treemap;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.usabilityetc.spacefillerexplorer.tree.Tree;
import com.usabilityetc.spacefillerexplorer.color.ColorWheel;

/**
 * An implementation of Ben Shneiderman's treemap algorithm that
 * paints the size of a tree structure as the contents of a JPanel.
 * 
 * Tree visualization with tree-maps: 2-d space-filling approach, Ben Shneiderman,
 * ACM Transactions on Graphics (TOG), Volume 11, Issue 1, January 1992:92-99.
 * http://dl.acm.org/citation.cfm?doid=102377.115768
 * 
 * @author Jeffrey Morgan
 */
public class TreemapPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final ColorWheel colorWheel = new ColorWheel(20);
	private final Color borderColor = Color.BLACK;
	private final List<ToolTip> toolTips = new ArrayList<>();
	private int margin = 10;
	private Tree tree;
	
	public TreemapPanel() {
		setToolTipText("");
	}

	public void setTree(final Tree tree) {
		this.tree = tree;
		repaint();
	}

	public void paint(final Graphics g) {
		if (tree == null) {
			return;
		}
		final int[] P = new int[] { margin, margin };
		final int[] Q = new int[] { getWidth() - margin, getHeight() - margin };
		toolTips.clear();
		paintTreeMap(g, tree, P, Q, 0);
	}

	private void paintTreeMap(
			final Graphics g,
			final Tree tree,
			final int[] P,
			final int[] Q,
			final int axis) {
		// Shneiderman assumes that P and Q are passed by value 
		// so save their values here and restore them at the end
		final int[] tempP = { P[0], P[1] };
		final int[] tempQ = { Q[0], Q[1] };
		
		// Create the rectangle that represents the current tree node
		final Rectangle rectangle = new Rectangle(P[0], P[1], Q[0] - P[0], Q[1] - P[1]);

		// Draw the border and fill the rectangle for the current tree node
		final Color fillColour = getFillColor(tree);
		g.setColor(fillColour);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(borderColor);
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		
		// Add tool tip
		final ToolTip toolTip = new ToolTip(tree.getName(), tree.getSize(), rectangle);
		toolTips.add(0, toolTip);

		// Recursively draw each remaining tree
		final int width = Q[axis] - P[axis];
		for (Tree child : tree.getChildren()) {
			final double offset = (((double)child.getSize() / (double)tree.getSize()) * (double)width);
			Q[axis] = P[axis] + (int)offset;
			paintTreeMap(g, child, P, Q, 1 - axis);
			P[axis] = Q[axis];
		}
		
		// Restore the saved values of P and Q
		P[0] = tempP[0];
		P[1] = tempP[1];
		Q[0] = tempQ[0];
		Q[1] = tempQ[1];
	}
	
	private Color getFillColor(final Tree tree) {
		Color fillColour = tree.getColor();
		if (fillColour == null) {
			fillColour = colorWheel.nextColor();
			tree.setColor(fillColour);
		}
		return fillColour;
	}
	
	@Override
	public String getToolTipText(final MouseEvent event) {
		final Point mousePoint = event.getPoint();
		for (ToolTip toolTip : toolTips) {
			if (toolTip.contains(mousePoint)) {
				return toolTip.getToolTipText();
			}
		}
		return getToolTipText();
	}
}
