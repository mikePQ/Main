package il.org.spartan.spartanizer.tippers;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.spartanizer.engine.nominal.*;
import il.org.spartan.spartanizer.java.*;
import il.org.spartan.spartanizer.tipping.*;
import il.org.spartan.spartanizer.tipping.categories.*;

/** Simplify while statements as much as possible (or remove them or parts of
 * them) if and only if it doesn't have any side-effect.
 * @author Dor Ma'ayan
 * @since 2016-09-26 */
public class WhileDeadRemove extends ReplaceCurrentNode<WhileStatement>//
    implements Category.EmptyCycles {
  private static final long serialVersionUID = -0x13FDE688F2015E55L;

  @Override protected boolean prerequisite(final WhileStatement ¢) {
    return sideEffects.free(¢);
  }
  @Override public String description(final WhileStatement ¢) {
    return "Remove :" + Trivia.gist(¢);
  }
  @Override public ASTNode replacement(final WhileStatement ¢) {
    return ¢.getAST().newBlock();
  }
}
