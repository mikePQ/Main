package il.org.spartan.spartanizer.research.nanos;

import static il.org.spartan.spartanizer.tippers.TrimmerTestsUtils.*;

import org.eclipse.jdt.core.dom.*;
import org.junit.*;
import org.junit.runners.*;

import il.org.spartan.spartanizer.research.nanos.deprecated.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Tests {@link Exhaust}
 * @author orimarco <tt>marcovitch.ori@gmail.com</tt>
 * @since 2017-01-01 */
@SuppressWarnings("static-method")
public class ExhaustTest {
  @Test public void a() {
    trimmingOf("while (keyReferenceQueue.poll() != null) {}")//
        .using(WhileStatement.class, new Exhaust())//
        .gives("exhaust(()->keyReferenceQueue.poll()!=null);")//
        .stays();
  }

  @Test public void b() {
    trimmingOf("while (keyReferenceQueue.poll() != null) {something(); andAnother();}")//
        .using(WhileStatement.class, new Exhaust())//
        .stays();
  }
}