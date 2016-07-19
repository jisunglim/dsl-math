package com.codit.pojos;

/**
 * Created by Jisung on 7/15/2016.
 */
public class NegateExpression extends Expression {

  public final Expression expression;

  public NegateExpression(int sR, int sC, int eR, int eC, Expression expression) {
    super(sR, sC, eR, eC);
    this.expression = expression;
  }

}
