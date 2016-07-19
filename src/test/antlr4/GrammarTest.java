import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.codit.gencode.MathLexer;
import com.codit.gencode.MathParser;
import com.codit.listner.LoggingListener;
import com.codit.pojos.AstNode;
import com.codit.traversal.AstDrawer;
import com.codit.visitor.AstBuilder;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * Created by Jisung on 7/12/2016.
 */

@RunWith(Parameterized.class)
public class GrammarTest {
  private final boolean testValid;
  private final String testString;

  public GrammarTest(boolean testValid, String testString) {
    this.testValid = testValid;
    this.testString = testString;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
                /* Valid rules. */
        {true, "Math Qw{ ( 234 ); }"},
        {true, "Math Eaq{ +2; }"},
        {true, "Math text{-iam;}"},
        {true, "Math text{j + 2;}"},
        {true, "Math text{1 - i;}"},
        {true, "Math text{s * 3;}"},
        {true, "Math text{4 / ung;}"},
        {true, "Math text{lim;}"},
        {true, "Math text{22;}"},

        {true, "Math text{3 + 4 - 21;}"},
        {true, "Math text{3 - 2 + 4;}"},

        {true, "Math text{3 * 2; - 4;}"},
        {true, "Math text{2 + 6 / 3;}"},

        {true, "Math text{3 / 2.0 + 44 - 2;}"},
        {true, "Math text{17 - 3 + 2 / 33;}"},

        {true, "Math awdq{ }"}, // empty rule file
        {true, "Math text{//  comment with new line\n}"},
        {true, "Math text{}//  comment with new line\n"},
        {true, "//  comment with new line\nMath text{}"},

        {false, "3+3"},
        {false, "// comment with no initial block\n"}
    });
  }
  private static final Logger logger = LoggerFactory.getLogger(LoggingListener.class);
  @Test
  public void testRule() {

    logger.info( this.testString );

    ANTLRInputStream input = new ANTLRInputStream( this.testString);
    MathLexer lexer = new MathLexer(input);
    TokenStream tokens = new CommonTokenStream(lexer);

    MathParser parser = new MathParser(tokens);

    parser.setBuildParseTree(true);
    parser.addParseListener(new LoggingListener());

    ParserRuleContext ruleContext = parser.compileUnit();

    if (this.testValid) {
      assertThat(ruleContext.exception, is( nullValue() ) );
    } else {
      assertThat(ruleContext.exception, is( notNullValue() ) );
    }

    AstNode astTree = new AstBuilder().visitCompileUnit((MathParser.CompileUnitContext) ruleContext);

    astTree.accept(new AstDrawer());



//    logger.info( ruleContext.toStringTree((Arrays.asList(MathParser.ruleNames))) + " : " + ruleContext.toInfoString(parser) + " ::: " + ruleContext.children );
    System.out.println("==================================================================================================================================");
  }
}