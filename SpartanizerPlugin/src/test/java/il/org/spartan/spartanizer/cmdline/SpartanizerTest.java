package il.org.spartan.spartanizer.cmdline;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * @author Matteo Orru'
 * @since 2016 */
public class SpartanizerTest {
  
  @SuppressWarnings("static-method") @Test public void testFileName_01() {
    assertTrue("fooTest.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }

  @SuppressWarnings("static-method") @Test public void testFileName_02() {
    assertTrue("test.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_03() {
    assertTrue("Test.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_04() {
    assertTrue("Testfoo.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_05() {
    assertTrue("testfoo.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_06() {
    assertTrue("foo1Testfoo2.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_07() {
    assertTrue("foo1testfoo2.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_08() {
    assertTrue("test_foo.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_09() {
    assertTrue("foo1_Test_foo2.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_10() {
    assertTrue("foo1_test_foo2.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_11() {
    assertTrue("test-foo.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_12() {
    assertTrue("foo1-Test-foo2.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
  @SuppressWarnings("static-method") @Test public void testFileName_13() {
    assertTrue("foo1-test-foo2.java".matches("[A-Za-z0-9_-]*[Tt]est[A-Za-z0-9_-]*.java"));
  }
  
}
