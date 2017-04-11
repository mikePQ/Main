package il.org.spartan.spartanizer.issues;

import static il.org.spartan.spartanizer.testing.TestsUtilsTrimmer.*;

import org.junit.*;

import il.org.spartan.spartanizer.testing.*;
import il.org.spartan.spartanizer.tippers.*;

/** This is a unit test for {@link InitializationListRemoveComma} The tests
 * issue74a-d are taken from {@link IgnoredTrimmerTest} and {@link Version230}
 * @author Yuval Simon
 * @since 2016-12-09 */
@Ignore
@SuppressWarnings("static-method")
public class Issue0074 {
  // TODO Yuval Simon unignore a test for this tipper in {@link Version 230}
  @Test public void issue74a() {
    trimminKof("int[] a = new int[] {,}")//
        .gives("int[] a = new int[] {}");
  }

  @Test public void issue74b() {
    trimminKof("int[] a = new int[] {2,3,}")//
        .gives("int[] a = new int[] {2,3}");
  }

  @Test public void issue74c() {
    trimminKof("a = new int[]{2,3,}")//
        .gives("a = new int[] {2,3}");
  }

  @Test public void issue74d() {
    trimminKof("int[] a = new int[] {2,3};")//
        .gives("");
  }
}