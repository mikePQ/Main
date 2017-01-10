package il.org.spartan.zoomer.zoomin.expanders;

import il.org.spartan.spartanizer.ast.navigate.*;

/** Example for testing with binding
 * @author YuvalSimon <tt>yuvaltechnion@gmail.com</tt>
 * @since 2017-01-07 [[SuppressWarningsSpartan]] */
@SuppressWarnings({ "static-method", "unused" })
public class Issue1040Aux2 extends ReflectiveTester {
  int total;
  int total2;
  @SuppressWarnings("boxing")
  Integer[] arr = {1,2,3,4,5};
  
  double total(int x) {
    return 5.0;
  }
  int total2(int x) {
    return 5;
  }
  void toTest() {
    total = 0;
    for(final Integer k : arr) {
      total += total(1);
    }
  }
    
  void toTest2() {
    total2 = 0;
    for(final Integer k : arr) {
      total2 += total2(1);
    }
  }
}