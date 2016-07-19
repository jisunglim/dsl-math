package com.codit.traversal;

import com.codit.pojos.AddExpression;
import com.codit.pojos.AssignStatement;
import com.codit.pojos.AstNode;
import com.codit.pojos.DivExpression;
import com.codit.pojos.Expression;
import com.codit.pojos.IdExpression;
import com.codit.pojos.InfixExpression;
import com.codit.pojos.MulExpression;
import com.codit.pojos.NegateExpression;
import com.codit.pojos.NumberExpression;
import com.codit.pojos.PrintStatement;
import com.codit.pojos.SimpleStatement;
import com.codit.pojos.Statement;
import com.codit.pojos.Statements;
import com.codit.pojos.SubExpression;

/**
 * Created by Jisung on 7/18/2016.
 */
public abstract class AstVisitor {

  protected abstract void visit(AddExpression addExpression);

  protected abstract void visit(SubExpression subExpression);

  protected abstract void visit(MulExpression mulExpression);

  protected abstract void visit(DivExpression divExpression);

  protected abstract void visit(NumberExpression numberExpression);

  protected abstract void visit(IdExpression idExpression);

  protected abstract void visit(NegateExpression negateExpression);

  protected abstract void visit(SimpleStatement simpleStatement);

  protected abstract void visit(AssignStatement assignStatement);

  protected abstract void visit(PrintStatement printStatement);

  public void visit(InfixExpression infixExpression) {

    if (infixExpression instanceof AddExpression) {
      visit((AddExpression) infixExpression);
    } else if (infixExpression instanceof SubExpression) {
      visit((SubExpression) infixExpression);
    } else if (infixExpression instanceof MulExpression) {
      visit((MulExpression) infixExpression);
    } else if (infixExpression instanceof DivExpression) {
      visit((DivExpression) infixExpression);
    }
  }

  public void visit(Expression expression) {
    if (expression instanceof InfixExpression) {
      visit((InfixExpression) expression);
    } else if (expression instanceof NumberExpression) {
      visit((NumberExpression) expression);
    } else if (expression instanceof IdExpression) {
      visit((IdExpression) expression);
    } else if (expression instanceof NegateExpression) {
      visit((NegateExpression) expression);
    }
  }

  public void visit(Statements statements) {
    for(Statement statement : statements.getStatementList()) {
      visit(statement);
    }
  }

  public void visit(Statement statement) {
    if (statement instanceof SimpleStatement) {
      visit((SimpleStatement) statement);
    } else if (statement instanceof PrintStatement) {
      visit((PrintStatement) statement);
    } else if (statement instanceof AssignStatement) {
      visit((AssignStatement) statement);
    }
  }

  public void visit(AstNode astNode) {
    visit((Statements) astNode);
  }
}
