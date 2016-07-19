package com.codit.visitor;

import com.codit.gencode.MathBaseVisitor;
import com.codit.gencode.MathLexer;
import com.codit.gencode.MathParser;
import com.codit.pojos.AddExpression;
import com.codit.pojos.AssignStatement;
import com.codit.pojos.AstNode;
import com.codit.pojos.DivExpression;
import com.codit.pojos.Expression;
import com.codit.pojos.IdExpression;
import com.codit.pojos.MulExpression;
import com.codit.pojos.NegateExpression;
import com.codit.pojos.NumberExpression;
import com.codit.pojos.PrintStatement;
import com.codit.pojos.SimpleStatement;
import com.codit.pojos.Statement;
import com.codit.pojos.Statements;
import com.codit.pojos.SubExpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jisung on 7/15/2016.
 */
public class AstBuilder extends MathBaseVisitor<AstNode> {

  private static final Logger logger = LoggerFactory.getLogger(AstBuilder.class);

  @Override
  public AstNode visitCompileUnit(MathParser.CompileUnitContext ctx) {

    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;


    List<Statement> statementList = new ArrayList<>();
    for(MathParser.StmContext stmContext : ctx.stm()) {
      Statement tempStm = (Statement) visit(stmContext);
      statementList.add(tempStm);
    }
    Statements statements = new Statements( startRow, startCol, endRow, endCol, statementList );

    logger.info("Compile Unit has " + ctx.stm().size() + " statements.");
    return statements;
  }

  @Override
  public AstNode visitSimpleStm(MathParser.SimpleStmContext ctx) {
    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;

    return new SimpleStatement( startRow, startCol, endRow, endCol,  (Expression) visit(ctx.expr()) );
  }

  @Override
  public AstNode visitAssignStm(MathParser.AssignStmContext ctx) {
    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;

    return new AssignStatement( startRow, startCol, endRow, endCol,  (Expression) visit(ctx.expr()) );
  }

  @Override
  public AstNode visitPrintStm(MathParser.PrintStmContext ctx) {
    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;

    return new PrintStatement( startRow, startCol, endRow, endCol,  (Expression) visit(ctx.expr()) );
  }

  @Override
  public AstNode visitInfixExpr(MathParser.InfixExprContext ctx) {
    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;

    // Visit expression context left and right and get expression pojo.
    Expression left = (Expression) visit(ctx.left);
    Expression right = (Expression) visit(ctx.right);

    // Decide which class to be constructed as a instance of infix expression based on the operator
    logger.info("visitInfixExpr " + ctx.op.getText());
    switch(ctx.op.getType()) {
      case MathLexer.ADD :
        return new AddExpression( startRow, startCol, endRow, endCol, left, right);
      case MathLexer.SUB :
        return new SubExpression( startRow, startCol, endRow, endCol, left, right);
      case MathLexer.MUL :
        return new MulExpression( startRow, startCol, endRow, endCol, left, right);
      case MathLexer.DIV :
        return new DivExpression( startRow, startCol, endRow, endCol, left, right);
      default :
        return null;
    }

  }

  @Override
  public AstNode visitUnaryExpr(MathParser.UnaryExprContext ctx) {
    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;

    Expression innerExpression = (Expression) visit(ctx.expr());

    switch(ctx.op.getType()) {
      case MathLexer.ADD :
        return innerExpression;
      case MathLexer.SUB :
        return new NegateExpression( startRow, startCol, endRow, endCol, innerExpression);
    }

    logger.info("visitUnaryExpr " + ctx.op.getText());
    return null;
  }

  @Override
  public AstNode visitNumberExpr(MathParser.NumberExprContext ctx) {
    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;

    String val = ctx.value.getText();

    // Pattern and symbol
    String pattern = "#0.0#";
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setDecimalSeparator('.');

    // Decimal format
    DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
    decimalFormat.setParseBigDecimal(true);

    BigDecimal value;

    try {
      value = (BigDecimal) decimalFormat.parse(val);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    double num = value.doubleValue();

    logger.info("visitNumberExpr " + val + " : " + value + " : " + num);
    return new NumberExpression( startRow, startCol, endRow, endCol, num);
  }

  @Override
  public AstNode visitIdExpr(MathParser.IdExprContext ctx) {
    //Position
    int startRow = ctx.getStart().getLine();
    int startCol = ctx.getStart().getCharPositionInLine();
    int endRow = (ctx.stop != null)? ctx.getStop().getLine() : startRow;
    int endCol = (ctx.stop != null)? ctx.getStop().getCharPositionInLine() : startCol;

    logger.info("visitIdExpr");

    String var = ctx.value.getText();

    return new IdExpression( startRow, startCol, endRow, endCol, var);
  }
}
