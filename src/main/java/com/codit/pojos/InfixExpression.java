package com.codit.pojos;

/**
 * Created by Jisung on 7/14/2016.
 */
public abstract class InfixExpression extends Expression {

  public final Expression left;
  public final Expression right;

  public InfixExpression(int sR, int sC, int eR, int eC, Expression left, Expression right) {
    super(sR, sC, eR, eC);
    this.left = left;
    this.right = right;
  }

  public Expression getLeft() {
    return this.left;
  }

  public Expression getRight() {
    return this.right;
  }
}
