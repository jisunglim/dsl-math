package com.codit.listner;

import com.codit.gencode.MathBaseListener;
import com.codit.gencode.MathParser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Jisung on 7/15/2016.
 */
public class LoggingListener extends MathBaseListener {

  private static final Logger logger = LoggerFactory.getLogger(LoggingListener.class);
  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    super.enterEveryRule(ctx);
    logger.info("[ENTER] " + ctx.getRuleIndex() + " ( getText : " + ctx.getText() + " | depth : "+ ctx.depth() + " | toString : "+ ctx.toString(Arrays.asList(MathParser.ruleNames)) + " | toStringTree : " + ctx.toStringTree(Arrays.asList(MathParser.ruleNames)) + " )");
  }

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {
    super.exitEveryRule(ctx);
    logger.info("[EXIT_] " + ctx.getRuleIndex() + " ( getText : " + ctx.getText() + " | depth : "+ ctx.depth() + " | toString : "+ ctx.toString(Arrays.asList(MathParser.ruleNames)) + " | toStringTree : " + ctx.toStringTree(Arrays.asList(MathParser.ruleNames)) + " )");
  }
}
