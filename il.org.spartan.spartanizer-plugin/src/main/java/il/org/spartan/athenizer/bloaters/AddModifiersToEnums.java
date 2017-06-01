package il.org.spartan.athenizer.bloaters;

import java.util.*;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.spartanizer.ast.factory.*;
import il.org.spartan.spartanizer.ast.navigate.*;
import il.org.spartan.spartanizer.tipping.*;

/** Add all the unecessary but legal modifiers to enums
 * @author Dor Ma'ayan
 * @since 2017-05-28 */
public class AddModifiersToEnums extends ReplaceCurrentNode<EnumDeclaration> implements TipperCategory.Bloater {
  private static final long serialVersionUID = 1;

  @Override public String description(@SuppressWarnings("unused") EnumDeclaration __) {
    return "add all the unecessary modifiers to the enum";
  }
  @Override @SuppressWarnings("unchecked") public ASTNode replacement(EnumDeclaration d) {
    EnumDeclaration $ = copy.of(d);
    List<Modifier> lst = extract.modifiers(d);
    if (!lst.contains(d.getAST().newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD)))
      $.modifiers().add(d.getAST().newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));
    return $;
  }
}