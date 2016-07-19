package com.codit.pojos;

/**
 * Created by Jisung on 7/15/2016.
 */
public final class DivExpression extends InfixExpression {
  public DivExpression(int sR, int sC, int eR, int eC, Expression left, Expression right) {
    super(sR, sC, eR, eC, left, right);
  }

}
