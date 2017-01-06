package il.org.spartan.spartanizer.ast.engine.nominal;

import static il.org.spartan.azzert.*;
import static il.org.spartan.spartanizer.engine.into.*;

import org.junit.*;
import org.junit.runners.*;

import il.org.spartan.*;
import il.org.spartan.spartanizer.ast.navigate.*;
import il.org.spartan.spartanizer.engine.nominal.*;

/** A test suite for class {@link namer}
 * @author Yossi Gil
 * @since 2015-07-18
 * @see step */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings({ "static-method", "javadoc" })
public final class namerTest {
  @Test public void arrayOfInts() {
    azzert.that(namer.shorten(t("int[][] __;")), equalTo("iss"));
  }

  @Test public void components01() {
    azzert.that(namer.components("ConfusingASTNode"), equalTo(new String[] { "Confusing", "AST", "Node" }));
  }

  @Test public void components02() {
    azzert.that(namer.components("camelCaseXML"), equalTo(new String[] { "camel", "Case", "XML" }));
  }

  @Test public void components03() {
    azzert.that(namer.components("with_bTable"), equalTo(new String[] { "with", "b", "Table" }));
  }

  @Test public void test2() {
    final String[] components = namer.components("Table_NanosByCategories");
    azzert.that(components, is(new String[] { "Table", "Nanos", "By", "Categories" }));
    final Iterable<String> os = lisp.rest(as.iterable(components));
    azzert.that(os + "", is(""));
    azzert.that(separate.these(os).by('-').toLowerCase(), is("nanons-by-categories"));
  }

  @Test public void listOfInts() {
    azzert.that(namer.shorten(t("List<Set<Integer>> __;")), equalTo("iss"));
  }

  @Test public void shortNameASTRewriter() {
    azzert.that(namer.shorten(t("ASTRewriter __;")), equalTo("r"));
  }

  @Test public void shortNameChar() {
    azzert.that(namer.shorten(t("char __;")), equalTo("c"));
  }

  @Test public void shortNameDouble() {
    azzert.that(namer.shorten(t("double __;")), equalTo("d"));
  }

  @Test public void shortNameExpression() {
    azzert.that(namer.shorten(t("Expression __;")), equalTo("x"));
  }

  @Test public void shortNameExpressions() {
    azzert.that(namer.shorten(t("Expression[] __;")), equalTo("xs"));
  }

  @Test public void shortNameExpressionsIterable() {
    azzert.that(namer.shorten(t("Iterable<Iterable<Expression>> __;")), equalTo("xss"));
  }

  @Test public void shortNameExpressionsList() {
    azzert.that(namer.shorten(t("List<Expression> __;")), equalTo("xs"));
  }

  @Test public void shortNameInt() {
    azzert.that(namer.shorten(t("int __;")), equalTo("i"));
  }

  @Test public void shortNameQualifiedType() {
    azzert.that(namer.shorten(t("org.eclipse.jdt.core.dom.InfixExpression __;")), equalTo("x"));
  }
}
