package il.org.spartan.spartanizer.tippers;

import static il.org.spartan.spartanizer.ast.navigate.extract.*;

import il.org.spartan.spartanizer.ast.factory.*;
import il.org.spartan.spartanizer.ast.navigate.*;
import il.org.spartan.spartanizer.ast.safety.*;
import il.org.spartan.spartanizer.dispatch.*;
import il.org.spartan.spartanizer.tipping.*;
import il.org.spartan.utils.*;

/** convert
 *
 * <pre>
 * a ? (f,g,h) : c(d,e)
 * </pre>
 *
 * into
 *
 * <pre>
 * a ? c(d, e) : f(g, h)
 * </pre>
 *
 * @author Yossi Gil
 * @since 2015-08-14 */
public final class TernaryShortestFirst extends ReplaceCurrentNode<ConditionalExpression>//
    implements TipperCategory.Sorting {
  private static double align(final Expression e1, final Expression e2) {
    return new LongestCommonSubsequence(e1 + "", e2 + "").similarity();
  }

  private static boolean compatible(final Expression e1, final Expression e2) {
    return e1.getNodeType() == e2.getNodeType()
        && (e1 instanceof InstanceofExpression || e1 instanceof InfixExpression || e1 instanceof MethodInvocation);
  }

  private static boolean compatibleCondition(final Expression e1, final Expression e2) {
    return compatible(e1, e2) || compatible(e1, make.notOf(e2));
  }

  @Override public String description(@SuppressWarnings("unused") final ConditionalExpression __) {
    return "Invert logical condition and exhange order of '?' and ':' operands to conditional expression";
  }

  @Override public ConditionalExpression replacement(final ConditionalExpression x) {
    final ConditionalExpression $ = subject.pair(core(x.getElseExpression()), core(x.getThenExpression())).toCondition(make.notOf(x.getExpression()));
    final Expression then = $.getElseExpression(), elze = $.getThenExpression();
    if (!iz.conditionalExpression(then) && iz.conditionalExpression(elze))
      return null;
    if (iz.conditionalExpression(then) && !iz.conditionalExpression(elze))
      return $;
    final ConditionalExpression parent = az.conditionalExpression(x.getParent());
    if (parent != null && parent.getElseExpression() == x && compatibleCondition(parent.getExpression(), x.getExpression())) {
      final Expression alignTo = parent.getThenExpression();
      final double a1 = align(elze, alignTo), a2 = align(then, alignTo);
      if (Math.abs(a1 - a2) > 0.1)
        return a1 > a2 ? $ : null;
    }
    final Expression condition = make.notOf($.getExpression());
    return metrics.length(condition, then) > metrics.length(make.notOf(condition), elze) ? $ : null;
  }
}
