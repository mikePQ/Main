package il.org.spartan.bloater.bloaters;

import static il.org.spartan.spartanizer.ast.navigate.step.*;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.spartanizer.ast.factory.*;
import il.org.spartan.spartanizer.ast.safety.*;
import il.org.spartan.spartanizer.dispatch.*;
import il.org.spartan.spartanizer.tipping.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Same as ReturnTernaryExpander just for "throw" {@link Issue #998}
 * @author Doron Meshulam <tt>doronmmm@hotmail.com</tt>
 * @since 2016-12-26 */
public class ThrowTernaryBloater extends ReplaceCurrentNode<ThrowStatement>//
    implements TipperCategory.Bloater {
  private static final long serialVersionUID = 8014414856762598558L;

  private static ASTNode innerThrowReplacement(final Expression x, @NotNull final Statement s) {
    @Nullable final ConditionalExpression ¢;
    if (!(x instanceof ParenthesizedExpression))
      ¢ = az.conditionalExpression(x);
    else {
      // TODO Doron Meshulam: Use extract.core --yg
      @NotNull final Expression unpar = expression(az.parenthesizedExpression(x));
      if (!(unpar instanceof ConditionalExpression))
        return null;
      ¢ = az.conditionalExpression(unpar);
      if (¢ == null)
        return null;
    }
    final IfStatement $ = s.getAST().newIfStatement();
    $.setExpression(copy.of(expression(¢)));
    final ThrowStatement then = ¢.getAST().newThrowStatement();
    then.setExpression(copy.of(¢.getThenExpression()));
    $.setThenStatement(copy.of(az.statement(then)));
    final ThrowStatement elze = ¢.getAST().newThrowStatement();
    elze.setExpression(copy.of(¢.getElseExpression()));
    $.setElseStatement(copy.of(az.statement(elze)));
    return $;
  }

  private static ASTNode replaceReturn(@NotNull final Statement ¢) {
    @Nullable final ThrowStatement $ = az.throwStatement(¢);
    return $ == null || !(expression($) instanceof ConditionalExpression) && !(expression($) instanceof ParenthesizedExpression) ? null
        : innerThrowReplacement(expression($), ¢);
  }

  @Nullable @Override public ASTNode replacement(@NotNull final ThrowStatement ¢) {
    return replaceReturn(¢);
  }

  @NotNull @Override public String description(@SuppressWarnings("unused") final ThrowStatement __) {
    return "expanding a ternary operator to a full if-else statement";
  }
}
