package il.org.spatan.iteration;

import java.util.*;
import java.util.BitSet;

import il.org.spartan.collections.*;
import il.org.spartan.collections.Contains;
import il.org.spartan.iterables.*;

/** An abstract encoding of an object (drawn from a given, predetermined, set of
 * objects) as an <code><b>int</b></code> value, and a representation of a set
 * of objects (which must be a subset of this given set) as a {@link BitSet}.
 * @author Yossi Gil
 * @param <T> Type of encoded objects
 * @param <U> An upper bound on the type of encoded objects */
public abstract class Codex<T> implements Contains<T> {
  public final Set<T> decode(final BitSet s) {
    final Set<T> $ = new HashSet<>();
    for (int ¢ = s.nextSetBit(0); ¢ >= 0; ¢ = s.nextSetBit(¢ + 1))
      $.add(decode(¢));
    return $;
  }
  /** Returns the object represented by <code>i</code> as determined by this
   * translator.
   * @param i the <code><b>int</b></code> whose translation shall be returned.
   * @return the translation of <code>i</code>. */
  public abstract T decode(int i);
  public final BitSet encode(final Iterable<? extends U> us) {
    assert us != null;
    final BitSet $ = new BitSet(size());
    for (final U t : us)
      $.set(encode(t));
    return $;
  }
  /** Returns the <code><b>int</b></code> value of <code>c</code> as determined
   * by this translator.
   * @param t the whose <code><b>int</b></code> translation shall be returned.
   * @return the <code><b>int</b></code> translation of <code>c</code>. */
  public abstract int encode(U t);

  public abstract static class Anchored<T> extends Codex<T, T> {
    //
  }
}
