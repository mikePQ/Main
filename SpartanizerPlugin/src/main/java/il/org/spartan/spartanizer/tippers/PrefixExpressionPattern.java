package il.org.spartan.spartanizer.tippers;

import org.eclipse.jdt.core.dom.*;

/** Abstract Pattern of {@link PrefixExpression}
 * @author dormaayn <tt>dor.d.ma@gmail.com</tt>
 * @since 2017-03-31 */
public abstract class PrefixExpressionPattern extends NodePattern<PrefixExpression> {
  private static final long serialVersionUID = -0x4D043452F68C22F8L;
  protected Expression operand;
  protected PrefixExpression.Operator operator;

  public PrefixExpressionPattern() {
    andAlso("Must be infix expression", () -> {
      operand = current.getOperand();
      operator = current.getOperator();
      return operand != null && operator != null;
    });
  }
}