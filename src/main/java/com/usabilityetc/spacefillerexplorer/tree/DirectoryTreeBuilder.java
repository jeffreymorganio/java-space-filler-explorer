package com.usabilityetc.spacefillerexplorer.tree;

import java.io.File;

/**
 * Build a tree structure representing the size of the files
 * in the hierarchical file structure starting at the specified
 * root directory to demonstrate the TreepmapPanel class.
 * 
 * @author Jeffrey Morgan
 */
public class DirectoryTreeBuilder {
  public static Tree createDirectoryTree(final File rootDirectory) {
    Tree root = null;
    if (rootDirectory.exists() && rootDirectory.isDirectory()) {
      root = new Tree(rootDirectory.getAbsolutePath(), rootDirectory.length());
      populateDirectoryTree(root);
    }
    return root;
  }

  private static void populateDirectoryTree(final Tree root) {
    final File dir = new File(root.getName());
    final File[] files = dir.listFiles();
    for (File file : files) {
      final Tree tree = new Tree(file.getAbsolutePath(), file.length());
      root.addChild(tree);
      if (file.isDirectory()) {
        populateDirectoryTree(tree);
      }
    }
  }
}
