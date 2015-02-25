package ru.ifmo.ctddev.gordon.arrayset;

import java.util.*;

/**
 * Created by Freemahn on 25.02.2015.
 */
public class ArraySortedSet<E> extends AbstractSet<E> implements SortedSet<E>, Collection<E> {

    ArrayList<E> m = new ArrayList<E>();
    Comparator<? super E> comparator;

    public ArraySortedSet(Collection<E> c) {


        m = new ArrayList<E>();
        for (E item : c)
            if (!contains(item))
                m.add(item);
        m.sort(null);


    }

    public ArraySortedSet() {


    }

    public ArraySortedSet(Collection<E> c, Comparator<? super E> comp) {
        comparator = comp;
        for (E item : c)
            if (!contains(item))
                m.add(item);
        m.sort(comparator);

    }

    public ArraySortedSet(Collection<E> c, Comparator<? super E> comp, boolean isSorted) {
        if (isSorted) {
            comparator = comp;
            for (E item : c)
                m.add(item);
            //m.addAll(c);
        } else {
            comparator = comp;
            for (E item : c)
                if (!contains(item))
                    m.add(item);
            m.sort(comparator);

        }

    }


    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return new ArraySortedSet<>(headSet(toElement).tailSet(fromElement), comparator);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {

        int i = Collections.binarySearch(m, toElement, comparator);
        if (i >= 0)
            return new ArraySortedSet<>(m.subList(0, i), comparator, true);
        else
            return new ArraySortedSet<>(m.subList(0, -i - 1), comparator, true);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        int i = Collections.binarySearch(m, fromElement, comparator);
        List<E> temp;
        if (i >= 0)
            temp = m.subList(i, m.size());
        else
            temp = m.subList(-i - 1, m.size());

        return new ArraySortedSet<E>(temp, comparator, true);


    }

    /*
        @Override
        public SortedSet<E> tailSet1(E fromElement) {
            int i = Collections.binarySearch(m, fromElement, comparator);
            List<E> temp;
            if (i >= 0)
                temp = m.subList(i, m.size());
            else
                temp = m.subList(-i - 1, m.size());

           return new ArraySortedSet<E>(temp,comparator,true);


        }
    */
    @Override
    public boolean contains(Object o) {
        return Collections.binarySearch(m, (E) o, comparator) >= 0;
    }


    @Override
    public Iterator<E> iterator() {
        return new MyIterator();

    }

    class MyIterator implements Iterator<E> {
        Iterator<E> currentIterator;

        MyIterator() {
            currentIterator = m.iterator();

        }

        @Override
        public boolean hasNext() {
            return currentIterator.hasNext();
        }

        @Override
        public E next() {
            return currentIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    ///////////done

    @Override
    public E first() {
        if (m.isEmpty()) throw new NoSuchElementException();
        return m.get(0);
    }

    @Override
    public E last() {
        if (m.isEmpty()) throw new NoSuchElementException();
        return m.get(m.size() - 1);
    }

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public boolean isEmpty() {
        return m.isEmpty();
    }


    @Override
    public Object[] toArray() {
        return m.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return m.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (m.isEmpty()) return m.containsAll(c);
        for (Object e : c) {
            if (contains(e)) return true;
        }
        return false;
    }


    //unusued because of
    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(14);

        ArraySortedSet<Integer> arraySortedSet = new ArraySortedSet<Integer>(a);
        System.out.println();
        for (Integer i : arraySortedSet) {
            System.out.print(i + " ");
        }
        System.out.println();

      /*  TreeSet<Integer> treeSet = new TreeSet<Integer>(a);
        for (Integer i : treeSet) {
            System.out.print(i + " ");
        }*/
    }
}
