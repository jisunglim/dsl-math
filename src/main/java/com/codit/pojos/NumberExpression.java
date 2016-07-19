package com.codit.pojos;

/**
 * Created by Jisung on 7/15/2016.
 */
public class NumberExpression extends Expression {

  public final double value;

  public NumberExpression(int sR, int sC, int eR, int eC, double value) {
    super(sR, sC, eR, eC);
    this.value = value;
  }

}
