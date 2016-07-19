package com.codit.pojos;

/**
 * Created by Jisung on 7/14/2016.
 */
public abstract class Expression extends AstNode {

  public Expression(int sR, int sC, int eR, int eC) {
    super(sR, sC, eR, eC);
  }


}
