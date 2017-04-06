package il.org.spartan.spartanizer.issues;

import static il.org.spartan.spartanizer.testing.TestsUtilsTrimmer.*;

import org.junit.*;
import org.junit.runners.*;

/** Unit tests for the GitHub issue thus numbered
 * @author Doron Meshulam
 * @since 06-Dec-16 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings({ "static-method", "javadoc" })
public class Issue0750 {
  @Test public void testDidntSpoiledAnything() {
    trimmingOf("void f (int x) {for(Object o : x) { System.out.println(o);}}")//
        .gives("void f (int x) {for(Object ¢ : x) { System.out.println(¢);}}");
  }

  @Test public void testNoChange() {
    trimmingOf("void f (int ¢) {for(Object o : ¢) System.out.println(o);}")//
        .stays();
  }
}