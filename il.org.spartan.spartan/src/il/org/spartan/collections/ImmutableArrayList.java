/* Copyright (c) 1997, 2007, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER. This code is
 * free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License version 2 only, as published by the Free
 * Software Foundation. Oracle designates this particular file as subject to the
 * "Classpath" exception as provided by Oracle in the LICENSE file that
 * accompanied this code. This code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License version 2 for more details (a copy is included in the LICENSE
 * file that accompanied this code). You should have received a copy of the GNU
 * General Public License version 2 along with this work; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA. Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA
 * 94065 USA or visit www.oracle.com if you need additional information or have
 * any questions. */
package il.org.spartan.collections;

import java.util.*;

/** Resizable-array implementation of the <tt>List</tt> interface. Implements
 * all optional list operations, and permits all elements, including
 * <tt>null</tt>. In addition to implementing the <tt>List</tt> interface, this
 * class provides methods to manipulate the size of the array that is used
 * internally to store the list. (This class is roughly equivalent to
 * <tt>Vector</tt>, except that it is unsynchronized.)
 * <p>
 * The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>,
 * <tt>iterator</tt>, and <tt>listIterator</tt> operations run in constant time.
 * The <tt>add</tt> operation runs in <i>amortized constant time</i>, that is,
 * adding n elements requires O(n) time. All of the other operations run in
 * linear time (roughly speaking). The constant factor is low compared to that
 * for the <tt>ArrayList</tt> implementation.
 * <p>
 * Each <tt>ArrayList</tt> instance has a <i>capacity</i>. The capacity is the
 * size of the array used to store the elements in the list. It is always at
 * least as large as the list size. As elements are added to an ArrayList, its
 * capacity grows automatically. The details of the growth policy are not
 * specified beyond the fact that adding an element has constant amortized time
 * cost.
 * <p>
 * An application can increase the capacity of an <tt>ArrayList</tt> instance
 * before adding a large number of elements using the <tt>ensureCapacity</tt>
 * operation. This may reduce the amount of incremental reallocation.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an <tt>ArrayList</tt> instance concurrently, and at
 * least one of the threads modifies the list structurally, it <i>must</i> be
 * synchronized externally. (A structural modification is any operation that
 * adds or deletes one or more elements, or explicitly resizes the backing
 * array; merely setting the value of an element is not a structural
 * modification.) This is typically accomplished by synchronizing on some object
 * that naturally encapsulates the list. If no such object exists, the list
 * should be "wrapped" using the {@link Collections#synchronizedList
 * Collections.synchronizedList} method. This is best done at creation time, to
 * prevent accidental unsynchronized access to the list:
 *
 * <pre>
 *   List list = Collections.synchronizedList(new ArrayList(...));
 * </pre>
 * <p>
 * <a name="fail-fast"/> The iterators returned by this class's
 * {@link #iterator() iterator} and {@link #listIterator(int) listIterator}
 * methods are <em>fail-fast</em>: if the list is structurally modified at any
 * time after the iterator is created, in any way except through the iterator's
 * own {@link ListIterator#remove() remove} or {@link ListIterator#add(Object)
 * add} methods, the iterator will throw a
 * {@link ConcurrentModificationException}. Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 * <p>
 * Note that the fail-fast behavior of an iterator cannot be guaranteed as it
 * is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification. Fail-fast iterators throw
 * {@code ConcurrentModificationException} on a best-effort basis. Therefore, it
 * would be wrong to write a program that depended on this exception for its
 * correctness: <i>the fail-fast behavior of iterators should be used only to
 * detect bugs.</i>
 * <p>
 * This class is a member of the
 * <a href="{@docRoot} /../technotes/guides/collections/index.html"> Java
 * Collections Framework</a>.
 * @param <E> type of elements
 * @author Yoss Gil
 * @author Josh Bloch
 * @author Neal Gafter
 * @see Collection
 * @see ArrayList
 * @see ArrayList
 * @see Vector
 * @since 1.2 */
public class ImmutableArrayList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
  private static final long serialVersionUID = 1;

  public static <E> ImmutableArrayList<E> make(final Collection<? extends E> ¢) {
    return new ImmutableArrayList<>(¢);
  }
  public static <E> ImmutableArrayList<E> make(final E[] ¢) {
    return new ImmutableArrayList<>(¢);
  }
  static boolean fail() {
    throw new UnsupportedOperationException();
  }
  private static <E> E[] asArray(final Collection<? extends E> ¢) {
    @SuppressWarnings("unchecked") final E[] $ = (E[]) ¢.toArray();
    return $.getClass() == Object[].class ? $ : recopy($);
  }
  private static <E> E[] recopy(final E[] a) {
    @SuppressWarnings("unchecked") final E[] $ = (E[]) Arrays.copyOf(a, a.length, Object[].class);
    return $;
  }

  /** The array buffer into which the elements of the ArrayList are stored. The
   * capacity of the ArrayList is the length of this array buffer. */
  private final E[] data;

  /** Constructs a list containing the elements of the specified collection, in
   * the order they are returned by the collection's iterator.
   * @param c the collection whose elements are to be placed into this list
   * @throws NullPointerException if the specified collection is null */
  public ImmutableArrayList(final Collection<? extends E> c) {
    data = asArray(c);
  }
  /** Instantiate this class from a given array
   * @param data an arbitrary array */
  public ImmutableArrayList(final E[] data) {
    this.data = data;
  }
  /** Appends the specified element to the end of this list.
   * @param __ to be appended to this list
   * @return never returns */
  @Override public boolean add(final E __) {
    return fail();
  }
  /** Inserts the specified element at the specified position in this list.
   * Shifts the element currently at that position (if any) and any subsequent
   * elements to the right (adds one to their indices).
   * @param index index at which the specified element is to be inserted
   * @param element element to be inserted
   * @throws IndexOutOfBoundsException {@inheritDoc} */
  @Override public void add(final int index, final E element) {
    fail();
  }
  /** Appends all of the elements in the specified collection to the end of this
   * list, in the order that they are returned by the specified collection's
   * Iterator. The behavior of this operation is undefined if the specified
   * collection is modified while the operation is in progress. (This implies
   * that the behavior of this call is undefined if the specified collection is
   * this list, and this list is nonempty.)
   * @param __ collection containing elements to be added to this list
   * @return <tt>true</tt> if this list changed as a result of the call
   * @throws NullPointerException if the specified collection is null */
  @Override public boolean addAll(final Collection<? extends E> __) {
    return fail();
  }
  /** Inserts all of the elements in the specified collection into this list,
   * starting at the specified position. Shifts the element currently at that
   * position (if any) and any subsequent elements to the right (increases their
   * indices). The new elements will appear in the list in the order that they
   * are returned by the specified collection's iterator.
   * @param index index at which to insert the first element from the specified
   *        collection
   * @param __ collection containing elements to be added to this list
   * @return <tt>true</tt> if this list changed as a result of the call
   * @throws IndexOutOfBoundsException {@inheritDoc}
   * @throws NullPointerException if the specified collection is null */
  @Override public boolean addAll(final int index, final Collection<? extends E> __) {
    return fail();
  }
  /** Removes all of the elements from this list. The list will be empty after
   * this call returns. */
  @Override public void clear() {
    fail();
  }
  /** Returns a shallow copy of this <tt>ArrayList</tt> instance. (The elements
   * themselves are not copied.)
   * @return a clone of this <tt>ArrayList</tt> instance */
  @Override public ImmutableArrayList<E> clone() {
    return this;
  }
  /** Returns <tt>true</tt> if this list contains the specified element. More
   * formally, returns <tt>true</tt> if and only if this list contains at least
   * one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   * @param ¢ element whose presence in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element */
  @Override public boolean contains(final Object ¢) {
    return indexOf(¢) >= 0;
  }
  /* (non-Javadoc)
   *
   * @see java.util.List#containsAll(java.util.Collection) */
  @Override public boolean containsAll(final Collection<?> os) {
    for (final Object ¢ : os)
      if (!contains(¢))
        return false;
    return true;
  }
  /** Increases the capacity of this <tt>ArrayList</tt> instance, if necessary,
   * to ensure that it can hold at least the number of elements specified by the
   * minimum capacity argument.
   * @param minCapacity the desired minimum capacity */
  @SuppressWarnings({ "static-method", "unused" }) //
  public void ensureCapacity(final int minCapacity) {
    fail();
  }
  /** Returns the element at the specified position in this list.
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException {@inheritDoc} */
  @Override public E get(final int index) {
    if (index >= size())
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    return elementData(index);
  }
  /** Returns the index of the first occurrence of the specified element in this
   * list, or -1 if this list does not contain the element. More formally,
   * returns the lowest index <tt>i</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
   * or -1 if there is no such index. [[SuppressSpartanizationTips]] */
  @Override public int indexOf(final Object o) {
    if (o == null)
      for (int $ = 0; $ < size(); ++$) {
        // Spartanization bug here.Cannot remove the curly brackets.
        assert o == null;
        if (data[$] == null)
          return $;
      }
    else
      for (int ¢ = 0; ¢ < size(); ++¢) {
        // Spartanization bug here.Cannot remove the curly brackets.
        assert o != null;
        if (o.equals(data[¢]))
          return ¢;
      }
    return -1;
  }
  /** Returns <tt>true</tt> if this list contains no elements.
   * @return <tt>true</tt> if this list contains no elements */
  @Override public boolean isEmpty() {
    return size() == 0;
  }
  /** Returns an iterator over the elements in this list in proper sequence.
   * <p>
   * The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
   * @return an iterator over the elements in this list in proper sequence */
  @Override @SuppressWarnings("synthetic-access") public Iterator<E> iterator() {
    return new InternalIterator();
  }
  /** Returns the index of the last occurrence of the specified element in this
   * list, or -1 if this list does not contain the element. More formally,
   * returns the highest index <tt>i</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
   * or -1 if there is no such index. */
  @Override public int lastIndexOf(final Object o) {
    if (o == null)
      for (int $ = size() - 1; $ >= 0; --$) {
        assert o == null : "Do not remove; spartanization bug";
        if (data[$] == null)
          return $;
      }
    else {
      assert o != null : "Do not remove; spartanization bug";
      for (int ¢ = size() - 1; ¢ >= 0; --¢)
        if (o.equals(data[¢]))
          return ¢;
    }
    return -1;
  }
  /** Returns a list iterator over the elements in this list (in proper
   * sequence).
   * <p>
   * The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
   * @see #listIterator(int) */
  @Override public ListIterator<E> listIterator() {
    return new InternalListIterator(0);
  }
  /** Returns a list iterator over the elements in this list (in proper
   * sequence), starting at the specified position in the list. The specified
   * index indicates the first element that would be returned by an initial call
   * to {@link ListIterator#next next} . An initial call to
   * {@link ListIterator#previous previous} would return the element with the
   * specified index minus one.
   * <p>
   * The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
   * @throws IndexOutOfBoundsException {@inheritDoc} */
  @Override public ListIterator<E> listIterator(final int index) {
    if (index < 0 || index > size())
      throw new IndexOutOfBoundsException("Index: " + index);
    return new InternalListIterator(index);
  }
  /** Removes the element at the specified position in this list. Shifts any
   * subsequent elements to the left (subtracts one from their indices).
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {@inheritDoc} */
  @Override public E remove(final int index) {
    fail();
    return null;
  }
  /** Removes the first occurrence of the specified element from this list, if
   * it is present. If the list does not contain the element, it is unchanged.
   * More formally, removes the element with the lowest index <tt>i</tt> such
   * that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
   * (if such an element exists). Returns <tt>true</tt> if this list contained
   * the specified element (or equivalently, if this list changed as a result of
   * the call).
   * @param __ to be removed from this list, if present
   * @return <tt>true</tt> if this list contained the specified element */
  @Override public boolean remove(final Object __) {
    return fail();
  }
  /** Removes from this list all of its elements that are contained in the
   * specified collection.
   * @param __ containing elements to be removed from this list
   * @return {@code true} if this list changed as a result of the call
   * @throws ClassCastException if the class of an element of this list is
   *         incompatible with the specified collection (optional)
   * @throws NullPointerException if this list contains a null element and the
   *         specified collection does not permit null elements (optional), or
   *         if the specified collection is null
   * @see Collection#contains(Object) */
  @Override public boolean removeAll(final Collection<?> __) {
    return fail();
  }
  /** Retains only the elements in this list that are contained in the specified
   * collection. In other words, removes from this list all of its elements that
   * are not contained in the specified collection.
   * @param __ containing elements to be retained in this list
   * @return {@code true} if this list changed as a result of the call
   * @throws ClassCastException if the class of an element of this list is
   *         incompatible with the specified collection (optional)
   * @throws NullPointerException if this list contains a null element and the
   *         specified collection does not permit null elements (optional), or
   *         if the specified collection is null
   * @see Collection#contains(Object) */
  @Override public boolean retainAll(final Collection<?> __) {
    return fail();
  }
  /** Replaces the element at the specified position in this list with the
   * specified element.
   * @param index index of the element to replace
   * @param element element to be stored at the specified position
   * @return the element previously at the specified position
   * @throws IndexOutOfBoundsException {@inheritDoc} */
  @Override public E set(final int index, final E element) {
    fail();
    return null;
  }
  /** Returns the number of elements in this list.
   * @return the number of elements in this list */
  @Override public int size() {
    return data.length;
  }
  @Override public ImmutableArrayList<E> subList(final int fromIndex, final int toIndex) {
    fail();
    return null;
  }
  /** Returns an array containing all of the elements in this list in proper
   * sequence (from first to last element).
   * <p>
   * The returned array will be "safe" in that no references to it are
   * maintained by this list. (In other words, this method must allocate a new
   * array). The caller is thus free to modify the returned array.
   * <p>
   * This method acts as bridge between array-based and collection-based APIs.
   * @return an array containing all of the elements in this list in proper
   *         sequence */
  @Override public E[] toArray() {
    return Arrays.copyOf(data, size());
  }
  /** Returns an array containing all of the elements in this list in proper
   * sequence (from first to last element); the runtime type of the returned
   * array is that of the specified array. If the list fits in the specified
   * array, it is returned therein. Otherwise, a new array is allocated with the
   * runtime type of the specified array and the size of this list.
   * <p>
   * If the list fits in the specified array with room to spare (i.e., the array
   * has more elements than the list), the element in the array immediately
   * following the end of the collection is set to <tt>null</tt>. (This is
   * useful in determining the length of the list <i>only</i> if the caller
   * knows that the list does not contain any null elements.)
   * @param a the array into which the elements of the list are to be stored, if
   *        it is big enough; otherwise, a new array of the same runtime type is
   *        allocated for this purpose.
   * @return an array containing the elements of the list
   * @throws ArrayStoreException if the runtime type of the specified array is
   *         not a supertype of the runtime type of every element in this list
   * @throws NullPointerException if the specified array is null */
  @Override @SuppressWarnings("unchecked") public <T> T[] toArray(final T[] a) {
    if (a.length < size())
      return (T[]) Arrays.copyOf(data, size());
    System.arraycopy(data, 0, a, 0, size());
    return a;
  }
  /** Trims the capacity of this <tt>ArrayList</tt> instance to be the list's
   * current size. An application can use this operation to minimize the storage
   * of an <tt>ArrayList</tt> instance. */
  @SuppressWarnings("static-method") //
  public void trimToSize() {
    fail();
  }
  // Positional Access Operations
  E elementData(final int index) {
    return data[index];
  }

  private class InternalIterator implements Iterator<E> {
    protected int next; // index of next element to return

    @Override public boolean hasNext() {
      return next != size();
    }
    @Override public E next() {
      final int $ = next;
      if ($ >= size())
        throw new NoSuchElementException();
      next = $ + 1;
      return elementData($);
    }
    @Override public void remove() {
      fail();
    }
  }

  /** An optimized version of AbstractList.ListItr */
  private class InternalListIterator extends InternalIterator implements ListIterator<E> {
    InternalListIterator(final int index) {
      next = index;
    }
    @Override public void add(final E __) {
      fail();
    }
    @Override public boolean hasPrevious() {
      return next != 0;
    }
    @Override public int nextIndex() {
      return next;
    }
    @Override public E previous() {
      final int $ = next - 1;
      if ($ < 0 || $ >= size())
        throw new NoSuchElementException();
      next = $;
      return elementData($);
    }
    @Override public int previousIndex() {
      return next - 1;
    }
    @Override public void set(final E __) {
      fail();
    }
  }
}