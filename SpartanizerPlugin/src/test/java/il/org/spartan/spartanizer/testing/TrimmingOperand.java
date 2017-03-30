package il.org.spartan.spartanizer.testing;

import static il.org.spartan.azzert.*;
import static il.org.spartan.spartanizer.testing.TestUtilsAll.*;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.*;
import il.org.spartan.spartanizer.cmdline.*;
import il.org.spartan.spartanizer.dispatch.*;
import il.org.spartan.spartanizer.engine.nominal.*;
import il.org.spartan.spartanizer.tipping.*;
import il.org.spartan.spartanizer.utils.*;
import il.org.spartan.utils.*;

/** An operand of a testing case, generated by
 * {@link TestsUtilsTrimmer#trimmingOf(String)} which can then be subjected to
 * {@link #gives(String)}, {@link #stays()}, or {@link #doesNotCrash()}. Prior
 * to that, it can be restricted to certain {@link Tipper}s, by using
 * {@link #using(Class, Tipper)} or {@link #using(Class, Tipper...)}.
 * @author Yossi Gil {@code Yossi.Gil@GMail.COM}
 * @since 2017-03-12 */
public class TrimmingOperand extends Wrapper<String> {
  protected static final String QUICK = "Quick fix: MARK, COPY, PASTE, and REFORMAT is:\n";
  protected static final String NEW_UNIT_TEST = "Quick fix: COPY & PASTE Junit @Test method:\n\n";
  private final Trimmer trimmer;
  private static int rerunsLeft = 5;

  public TrimmingOperand(final String inner) {
    super(inner);
    trimmer = new Trimmer();
  }

  void checkExpected(final String expected) {
    final WrapIntoComilationUnit w = WrapIntoComilationUnit.find(get());
    final String wrap = w.on(get()), unpeeled = trim.apply(new Trimmer(), wrap);
    if (wrap.equals(unpeeled))
      azzert.fail("Nothing done on " + get());
    final String peeled = w.off(unpeeled);
    if (peeled.equals(get()))
      azzert.that("No trimming of " + get(), peeled, is(not(get())));
    if (tide.clean(peeled).equals(tide.clean(get())))
      azzert.that("Trimming of " + get() + "is just reformatting", tide.clean(get()), is(not(tide.clean(peeled))));
    assertSimilar(expected, peeled);
  }

  public void doesNotCrash() {
    try {
      apply();
    } catch (final Throwable ¢) {
      System.err.println("*** Test crashed with " + ¢.getClass().getSimpleName());
      System.err.println("*** Test crashed with " + ¢.getMessage());
      System.err.println("*** Test crashed rerunning ");
      monitor.set(monitor.INTERACTIVE_TDD);
      apply();
      monitor.unset();
    }
  }

  String apply() {
    return trim.apply(trimmer, WrapIntoComilationUnit.find(get()).on(get()));
  }

  public TrimmingOperand gives(final String $) {
    final WrapIntoComilationUnit w = WrapIntoComilationUnit.find(get());
    final String wrap = w.on(get()), unpeeled = trim.apply(trimmer, wrap);
    if (wrap.equals(unpeeled)) {
      copyPasteReformat("  .stays()//\n  ;\n");
      azzert.fail("Nothing done on " + get());
    }
    final String peeled = w.off(unpeeled);
    if (peeled.equals(get()))
      azzert.that("No trimming of " + get(), peeled, is(not(get())));
    if (tide.clean(peeled).equals(tide.clean(get())))
      azzert.that("Trimming of " + get() + "is just reformatting", tide.clean(get()), is(not(tide.clean(peeled))));
    if ($.equals(peeled) || trivia.essence(peeled).equals(trivia.essence($)))
      return new TrimmingOperand($);
    copyPasteReformat("  .gives(\"%s\") //\nCompare with\n  .gives(\"%s\") //\n", trivia.escapeQuotes(trivia.essence(peeled)),
        trivia.escapeQuotes(trivia.essence($)));
    azzert.that(trivia.essence(peeled), is(trivia.essence($)));
    return new TrimmingOperand($);
  }

  protected void copyPasteReformat(final String format, final Object... os) {
    rerun();
    System.err.printf(QUICK + format, os);
    System.err.println(NEW_UNIT_TEST + JUnitTestMethodFacotry.makeTipperUnitTest(get()));
  }

  /** Check whether one of the code options is correct
   * @param options
   * @return Operand
   * @author Dor Ma'ayan
   * @since 09-12-2016 */
  public TrimmingOperand givesEither(final String... options) {
    assert options != null;
    final WrapIntoComilationUnit w = WrapIntoComilationUnit.find(get());
    final String wrap = w.on(get()), unpeeled = trim.apply(trimmer, wrap);
    if (wrap.equals(unpeeled))
      azzert.fail("Nothing done on " + get());
    final String peeled = w.off(unpeeled);
    if (peeled.equals(get()))
      azzert.that("No trimming of " + get(), peeled, is(not(get())));
    if (tide.clean(peeled).equals(tide.clean(get())))
      azzert.that("Trimming of " + get() + "is just reformatting", tide.clean(get()), is(not(tide.clean(peeled))));
    for (final String $ : options)
      if (trivia.essence($).equals(trivia.essence(peeled)))
        return new TrimmingOperand($);
    azzert.fail("Expects: " + peeled + " But none of the given options match");
    return null;
  }

  public void stays() {
    final WrapIntoComilationUnit w = WrapIntoComilationUnit.find(get());
    final String wrap = w.on(get()), unpeeled = trim.apply(trimmer.setExceptionListener(λ -> fail(λ.getClass() + " was thrown")), wrap);
    if (wrap.equals(unpeeled))
      return;
    final String peeled = w.off(unpeeled);
    if (peeled.equals(get()) || tide.clean(peeled).equals(tide.clean(get())))
      return;
    final String expected = get();
    if (expected.equals(peeled) || trivia.essence(peeled).equals(trivia.essence(expected)))
      return;
    copyPasteReformat("\n .gives(\"%s\") //\nCompare with\n  .gives(\"%s\") //\n", //
        trivia.escapeQuotes(trivia.essence(peeled)), //
        trivia.escapeQuotes(trivia.essence(expected)));
    azzert.that(trivia.essence(peeled), is(trivia.essence(expected)));
  }

  public void rerun() {
    if (rerunsLeft < 1)
      return;
    System.err.println("*** Test failed (rerunning to collect more information)");
    TrimmerLog.on();
    monitor.set(monitor.INTERACTIVE_TDD);
    apply();
    TrimmerLog.off();
    monitor.unset();
    System.err.printf("*** Rerun done. (scroll back to find logging infromation)\n");
    System.err.printf("*** %d reruns left \n ", box.it(--rerunsLeft));
  }

  public <N extends ASTNode> TrimmingOperand using(final Class<N> c, final Tipper<N> ¢) {
    trimmer.fix(c, ¢);
    return this;
  }

  @SafeVarargs public final <N extends ASTNode> TrimmingOperand using(final Class<N> c, final Tipper<N>... ts) {
    as.list(ts).forEach(λ -> trimmer.addSingleTipper(c, λ));
    return this;
  }

  @SafeVarargs public final TrimmingOperand usingTipper(final Tipper<?>... ¢) {
    trimmer.fixTipper(¢);
    return this;
  }

  @SafeVarargs public final TrimmingOperand usingBloater(final Tipper<?>... ¢) {
    trimmer.fixBloater(¢);
    return this;
  }
}