package il.org.spartan.spartanizer.issues;

import static il.org.spartan.spartanizer.testing.TestsUtilsTrimmer.*;

import org.junit.*;

import il.org.spartan.spartanizer.tippers.*;

/** Test class for {@link InfixMultiplicationDistributive}
 * @since 2016 */
@Ignore("Disabled: there is some bug in distributive rule - not in Toolbox.")
@SuppressWarnings("static-method")
public class Issue0076 {
  @Test public void issue076a() {
    topDownTrimming("a*b + a*c")//
        .gives("a*(b+c)");
  }

  @Test public void issue076b() {
    topDownTrimming("b*a + c*a")//
        .gives("a*(b+c)");
  }

  @Test public void issue076c() {
    topDownTrimming("b*a + c*a + d*a")//
        .gives("a*(b+c+d)");
  }
}
