package il.org.spartan.spartanizer.issues;

import static il.org.spartan.spartanizer.testing.TestsUtilsTrimmer.*;

import org.junit.*;
import org.junit.runners.*;

/** TODO: Yossi Gil {@code Yossi.Gil@GMail.COM} please add a description
 * @author Yossi Gil {@code Yossi.Gil@GMail.COM}
 * @since 2016-12-23 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings({ "static-method", "javadoc" })
public class Issue0997 {
  @Test public void a0() {
    trimmingOf("@interface A{final int a=4;}")//
        .gives("@interface A{int a=4;}")//
        .stays();
  }

  @Test public void a1() {
    trimmingOf("@interface A{final int a=4;final char c;}")//
        .gives("@interface A{int a=4;char c;}")//
        .stays();
  }
}
