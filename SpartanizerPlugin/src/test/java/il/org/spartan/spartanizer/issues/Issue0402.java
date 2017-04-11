package il.org.spartan.spartanizer.issues;

import static il.org.spartan.spartanizer.testing.TestsUtilsTrimmer.*;

import org.junit.*;

import il.org.spartan.spartanizer.tippers.*;

/** A test class regarding a bug with
 * {@link LocalVariableIntializedStatementToForInitializers}. Desired behavior
 * is not to allow inlining if the modifiers are different.
 * @author Dan Greenstein
 * @since 2016 */
@SuppressWarnings("static-method")
public class Issue0402 {
  @Test public void a() {
    topDownTrimming("final L<O> list = new A<>();final int len = Array.getLength(def);for (int ¢ = 0; ¢ <len; ++¢)list.add(Array.get(def, ¢));$.p(list);")//
        .stays();
  }

  @Test public void b() {
    topDownTrimming("final L<O> list = new A<>();final int len = o();for (int ¢ = 0; ¢ <len; ++¢)list.add(Array.get(def, ¢));$.p(list);")//
        .stays();
  }

  @Test public void c() {
    topDownTrimming("final L<O> list = new A<>();volatile int len = o();for (int ¢ = 0; ¢ <len; ++¢)list.add(Array.get(def, ¢));$.p(list);")//
        .stays();
  }

  @Test public void d() {
    topDownTrimming("final L<O> list = new A<>();final int len = o();for (final int ¢ = 0; ¢ <len; ++¢)list.add(Array.get(def, ¢));$.p(list);")
        .gives("final L<O> list = new A<>();for (final int len = o(), ¢ = 0; ¢ <len; ++¢)list.add(Array.get(def, ¢));$.p(list);").stays();
  }

  @Test public void e() {
    topDownTrimming("final L<O> list = new A<>();final int len = o();for (int ¢ = 0; ¢ <len; ++¢)list.add(Array.get(def, ¢));$.p(list);")//
        .stays();
  }

  @Test public void f() {
    topDownTrimming("final L<O> list = new A<>();int len = o();for (final int ¢ = 0; ¢ <len; ++¢)list.add(Array.get(def, ¢));$.p(list);")//
        .stays();
  }
}
