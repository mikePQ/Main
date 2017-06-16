package il.org.spartan.java.cfg;

import static org.eclipse.jdt.core.dom.ASTNode.*;

import java.util.*;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.java.cfg.builders.*;

/** TODO Ori Roth: document class
 * @author Ori Roth
 * @since 2017-06-15 */
public class CFGBuilders {
  @SuppressWarnings("unchecked") private final List<CFGBuilder<? extends ASTNode>>[] implementation = (List<CFGBuilder<? extends ASTNode>>[]) new List<?>[2
      * ASTNode.TYPE_METHOD_REFERENCE];

  public void initialize(CFG<?> cfg) {
    add(cfg, METHOD_DECLARATION, new MethodBuilder());
  }
  public static CFGBuilders all(final CFG<?> cfg) {
    final CFGBuilders $ = new CFGBuilders();
    $.initialize(cfg);
    return $;
  }
  public boolean build(final ASTNode n) {
    return implementation[n.getNodeType()].stream().anyMatch(b -> b.accept(n));
  }
  public final boolean isRoot(final ASTNode n) {
    final List<CFGBuilder<? extends ASTNode>> bs = implementation[n.getNodeType()];
    return bs != null && !bs.isEmpty();
  }
  @SafeVarargs private final void add(CFG<?> cfg, int type, CFGBuilder<? extends ASTNode>... builders) {
    Arrays.stream(builders).forEach(b -> b.register(cfg));
    if (implementation[type] == null)
      implementation[type] = an.empty.list();
    Collections.addAll(implementation[type], builders);
  }
}
