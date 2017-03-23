package il.org.spartan.spartanizer.tippers;

import static org.eclipse.jdt.core.dom.Assignment.Operator.*;

import static il.org.spartan.lisp.*;

import static il.org.spartan.spartanizer.ast.navigate.step.*;

import static il.org.spartan.spartanizer.ast.navigate.wizard.*;

import java.util.*;

import org.eclipse.jdt.core.dom.*;
import org.jetbrains.annotations.*;

import il.org.spartan.spartanizer.ast.factory.*;
import il.org.spartan.spartanizer.ast.navigate.*;
import il.org.spartan.spartanizer.ast.safety.*;
import il.org.spartan.spartanizer.dispatch.*;
import il.org.spartan.spartanizer.java.*;
import il.org.spartan.spartanizer.tipping.*;

/** Replace {@code x = x # a } by {@code x #= a } where # can be any operator.
 * Tested in {@link Issue103}
 * @author Alex Kopzon
 * @since 2016 */
public final class AssignmentToFromInfixIncludingTo extends ReplaceCurrentNode<Assignment>//
    implements TipperCategory.SyntacticBaggage {
  private static final long serialVersionUID = -6131575687350461523L;

  private static List<Expression> dropAnyIfSame(@NotNull final List<Expression> xs, @NotNull final Expression left) {
    @NotNull final List<Expression> $ = new ArrayList<>(xs);
    for (@NotNull final Expression ¢ : xs)
      if (same(¢, left)) {
        $.remove(¢);
        return $;
      }
    return null;
  }

  private static List<Expression> dropFirstIfSame(@NotNull final Expression ¢, @NotNull final List<Expression> xs) {
    return !same(¢, first(xs)) ? null : chop(new ArrayList<>(xs));
  }

  private static Expression reduce(final InfixExpression x, @NotNull final Expression deleteMe) {
    @Nullable final List<Expression> es = hop.operands(x), $ = !nonAssociative(x) ? dropAnyIfSame(es, deleteMe) : dropFirstIfSame(deleteMe, es);
    return $ == null ? null : $.size() == 1 ? copy.of(first($)) : subject.operands($).to(operator(x));
  }

  private static ASTNode replacement(@NotNull final Expression to, final InfixExpression from) {
    if (iz.arrayAccess(to) || !sideEffects.free(to))
      return null;
    @Nullable final Expression $ = reduce(from, to);
    return $ == null ? null : subject.pair(to, $).to(infix2assign(operator(from)));
  }

  @Override @NotNull public String description(final Assignment ¢) {
    return "Replace x = x " + operator(¢) + "a; with x " + operator(¢) + "= a;";
  }

  @Override public ASTNode replacement(@NotNull final Assignment ¢) {
    return ¢.getOperator() != ASSIGN || az.infixExpression(from(¢)) == null ? null : replacement(to(¢), az.infixExpression(from(¢)));
  }
}
