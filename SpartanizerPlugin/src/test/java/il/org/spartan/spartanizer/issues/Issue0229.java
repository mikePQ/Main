package il.org.spartan.spartanizer.issues;

import static il.org.spartan.spartanizer.testing.TestsUtilsSpartanizer.*;

import org.junit.*;
import il.org.spartan.spartanizer.tippers.*;

/** Unit tests for {@link SafeVarargs} in {@link ModifierRedundant}
 * @author Yossi Gil
 * @since 2016 */

@SuppressWarnings({ "static-method", "javadoc" })
public final class Issue0229 {
  @Test public void vanilla() {
    trimminKof("final class X { @SafeVarargs public final void f(final int... __) {}}")//
        .stays();
  }
}
