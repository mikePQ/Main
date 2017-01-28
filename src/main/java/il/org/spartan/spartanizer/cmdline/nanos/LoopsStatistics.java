/** TODO: orimarco <marcovitch.ori@gmail.com> please add a description
 * @author orimarco <marcovitch.ori@gmail.com>
 * @since Jan 21, 2017 */
package il.org.spartan.spartanizer.cmdline.nanos;

import java.util.*;
import static il.org.spartan.spartanizer.ast.navigate.step.*;

import il.org.spartan.spartanizer.ast.safety.*;

import il.org.spartan.spartanizer.utils.*;

public class LoopsStatistics extends HashMap<Integer, Int> {
  private static final long serialVersionUID = 1L;
  private int total;
  private int definites;

  @Override public void clear() {
    total = definites = 0;
    super.clear();
  }

  public LoopsStatistics log(final ASTNode ¢) {
    ++total;
    if (iz.definiteLoop(¢))
      ++definites;
    return log(Integer.valueOf(nodeType(¢)));
  }

  public int whileLoops() {
    return nodeStatistics(ASTNode.WHILE_STATEMENT);
  }

  public int forLoops() {
    return nodeStatistics(ASTNode.FOR_STATEMENT);
  }

  public int enhancedForLoops() {
    return nodeStatistics(ASTNode.ENHANCED_FOR_STATEMENT);
  }

  private int nodeStatistics(final int nodeType) {
    return !containsKey(Integer.valueOf(nodeType)) ? 0 : get(Integer.valueOf(nodeType)).inner;
  }

  public int totalLoops() {
    return total;
  }

  public int doWhileLoops() {
    return nodeStatistics(ASTNode.DO_STATEMENT);
  }

  public int definites() {
    return definites;
  }
}
