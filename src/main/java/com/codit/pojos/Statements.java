package com.codit.pojos;

import java.util.Collections;
import java.util.List;

/**
 *
 * Created by Jisung on 7/14/2016.
 */
public class Statements extends AstNode {

  private final List<Statement> statementList;

  /** Constructor */
  public Statements(int sR, int sC, int eR, int eC, List<Statement> statementList) {
    super(sR, sC, eR, eC);
    this.statementList = statementList;
  }

  public List<Statement> getStatementList() {
    return Collections.unmodifiableList(statementList);
  }

}
