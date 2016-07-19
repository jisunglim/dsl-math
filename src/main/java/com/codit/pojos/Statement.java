package com.codit.pojos;

/**
 * Created by Jisung on 7/14/2016.
 */
public abstract class Statement extends AstNode {

  private final Expression expression;


  /** Constructor */
  public Statement(int sR, int sC, int eR, int eC, Expression expression) {
    super(sR, sC, eR, eC);
    this.expression = expression;
  }

  /** return the expression */
  public Expression getExpression() {
    return this.expression;
  }

}
