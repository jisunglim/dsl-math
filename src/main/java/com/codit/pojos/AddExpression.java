package com.codit.pojos;

/**
 * Created by Jisung on 7/15/2016.
 */
public class AddExpression extends InfixExpression {

  public AddExpression(int sR, int sC, int eR, int eC, Expression left, Expression right) {
    super(sR, sC, eR, eC, left, right);
  }

}
