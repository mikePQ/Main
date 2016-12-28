package il.org.spartan.athenizer.inflate.expanders;

import org.junit.*;

import static il.org.spartan.athenizer.inflate.expanders.ExpanderTestUtils.*;

/** Test class for issue #965
 * @author Dor Ma'ayan <tt>dor.d.ma@gmail.com</tt>
 * @since 2016-12-20 */
@SuppressWarnings("static-method")
public class Issue965 {
  @Test public void test0() {
    expansionOf("a+\"\"").stays(); //no binding for now...
  }

  @Test public void test1() {
    expansionOf("\"\"+t").stays(); //no binding for now...
  }

  @Test public void test2() {
    expansionOf("\"abcd\"+t()").stays();
  }

  @Test public void test4() {
    expansionOf("true+\"\"").stays();
  }
}
