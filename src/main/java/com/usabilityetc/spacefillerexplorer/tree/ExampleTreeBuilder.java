package com.usabilityetc.spacefillerexplorer.tree;

/**
 * Build a simple example tree structure
 * to demonstrate the TreepmapPanel class.
 * 
 * @author Jeffrey Morgan
 */
public class ExampleTreeBuilder {
  public static Tree createTree() {
    final Tree root = new Tree("a", 0);

    final Tree b = new Tree("b", 12);
    root.addChild(b);
    final Tree c = new Tree("c", 8);
    root.addChild(c);

    final Tree d = new Tree("d", 0);
    root.addChild(d);
    final Tree f = new Tree("f", 2);
    d.addChild(f);
    final Tree g = new Tree("g", 2);
    d.addChild(g);
    final Tree h = new Tree("h", 2);
    d.addChild(h);
    final Tree i = new Tree("i", 2);
    d.addChild(i);
    final Tree j = new Tree("j", 2);
    d.addChild(j);

    final Tree e = new Tree("e", 0);
    root.addChild(e);

    final Tree k = new Tree("k", 0);
    e.addChild(k);
    final Tree n = new Tree("n", 5);
    k.addChild(n);
    final Tree o = new Tree("o", 20);
    k.addChild(o);

    final Tree l = new Tree("l", 0);
    e.addChild(l);
    final Tree p = new Tree("p", 5);
    l.addChild(p);

    final Tree m = new Tree("m", 40);
    e.addChild(m);

    return root;
  }
}
