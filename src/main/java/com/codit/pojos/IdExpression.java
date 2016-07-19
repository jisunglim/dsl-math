package com.codit.pojos;

/**
 * Created by Jisung on 7/15/2016.
 */
public final class IdExpression extends Expression {

  public final String id;

  public IdExpression(int sR, int sC, int eR, int eC, String id) {
    super(sR, sC, eR, eC);
    this.id = id;
  }

}
