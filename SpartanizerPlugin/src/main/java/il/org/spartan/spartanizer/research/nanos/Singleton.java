package il.org.spartan.spartanizer.research.nanos;

import static il.org.spartan.spartanizer.research.TipperFactory.*;

import java.util.*;

import org.eclipse.jdt.core.dom.*;
import org.jetbrains.annotations.*;

import il.org.spartan.*;
import il.org.spartan.spartanizer.engine.*;
import il.org.spartan.spartanizer.research.*;
import il.org.spartan.spartanizer.research.nanos.common.*;

/** collection.size() == 1
 * @author orimarco <tt>marcovitch.ori@gmail.com</tt>
 * @since 2017-01-28 */
public final class Singleton extends NanoPatternTipper<InfixExpression> {
  private static final long serialVersionUID = 6833387526348076529L;
  private static final List<UserDefinedTipper<InfixExpression>> tippers = as.list(patternTipper("$X.size() == 1", "singleton($X)", "is singleton"),
      patternTipper("1 == $X.size()", "singleton($X)", "is singleton"));

  @Override public boolean canTip(final InfixExpression ¢) {
    return anyTips(tippers, ¢);
  }

  @Nullable @Override public Fragment pattern(@NotNull final InfixExpression ¢) {
    return firstTip(tippers, ¢);
  }

  @Override public String description() {
    return "Boolean expression that checks whether a collection is of size 1";
  }

  @Override public String technicalName() {
    return "singleton(X)";
  }

  @Override public String example() {
    return firstPattern(tippers);
  }

  @Override public String symbolycReplacement() {
    return firstReplacement(tippers);
  }

  @NotNull @Override public NanoPatternTipper.Category category() {
    return Category.Iterative;
  }
}
