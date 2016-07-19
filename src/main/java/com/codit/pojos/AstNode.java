package com.codit.pojos;

import com.codit.traversal.AstVisitor;

/**
 * Created by Jisung on 7/15/2016.
 */
public abstract class AstNode {
  private final int startRow;
  private final int startCol;
  private final int endRow;
  private final int endCol;

  public AstNode(int sR, int sC, int eR, int eC) {
    this.startRow = sR;
    this.startCol = sC;
    this.endRow = eR;
    this.endCol = eC;
  }

  public int getStartRow() {
    return startRow;
  }

  public int getStartCol() {
    return startCol;
  }

  public int getEndRow() {
    return endRow;
  }

  public int getEndCol() {
    return endCol;
  }

  public void accept(AstVisitor astVisitor) {
    astVisitor.visit(this);
  }
}
