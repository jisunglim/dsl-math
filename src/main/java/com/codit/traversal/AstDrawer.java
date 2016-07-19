package com.codit.traversal;

import com.codit.pojos.AddExpression;
import com.codit.pojos.AssignStatement;
import com.codit.pojos.DivExpression;
import com.codit.pojos.IdExpression;
import com.codit.pojos.MulExpression;
import com.codit.pojos.NegateExpression;
import com.codit.pojos.NumberExpression;
import com.codit.pojos.PrintStatement;
import com.codit.pojos.SimpleStatement;
import com.codit.pojos.SubExpression;

/**
 *
 * Created by Jisung on 7/18/2016.
 */
public class AstDrawer extends AstVisitor {

  @Override
  public void visit(AddExpression a) {
    System.out.printf("[%d:%d|%d:%d] %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "Add");

    visit(a.getRight());
    visit(a.getLeft());
  }

  @Override
  public void visit(SubExpression a) {
    System.out.printf("[%d:%d|%d:%d] %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "Sub");

    visit(a.getRight());
    visit(a.getLeft());
  }

  @Override
  public void visit(MulExpression a) {
    System.out.printf("[%d:%d|%d:%d] %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "Mul");

    visit(a.getRight());
    visit(a.getLeft());
  }

  @Override
  public void visit(DivExpression a) {
    System.out.printf("[%d:%d|%d:%d] %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "Div");

    visit(a.getRight());
    visit(a.getLeft());
  }

  @Override
  public void visit(NumberExpression a) {
    System.out.printf("[%d:%d|%d:%d] %s = %f\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "Num", a.value);
  }

  @Override
  public void visit(IdExpression a) {
    System.out.printf("[%d:%d|%d:%d] %s = %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "Id", a.id);
  }

  @Override
  public void visit(NegateExpression a) {
    System.out.printf("[%d:%d|%d:%d] %s\n", a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "Negation");

    visit(a.expression);
  }

  @Override
  public void visit(SimpleStatement a) {
    System.out.printf("[%d:%d|%d:%d] %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "SimpleExpression");

    visit(a.getExpression());
  }

  @Override
  public void visit(AssignStatement a) {
    System.out.printf("[%d:%d|%d:%d] %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "AssignExpression");

    visit(a.getExpression());
  }

  @Override
  public void visit(PrintStatement a) {
    System.out.printf("[%d:%d|%d:%d] %s\n",a.getStartRow(), a.getStartCol(), a.getEndRow(), a.getEndCol(), "PrintExpression");

    visit(a.getExpression());
  }


}
