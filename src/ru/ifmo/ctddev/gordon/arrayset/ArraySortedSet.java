package ru.ifmo.ctddev.gordon.arrayset;

import java.util.*;

/**
 * Created by Freemahn on 25.02.2015.
 */
public class ArraySortedSet<E> extends AbstractSet<E> implements SortedSet<E>, Collection<E> {

    private List<E> data = new ArrayList<>();
    private Comparator<? super E> comparator;

    public ArraySortedSet(Collection<E> c) {
        for (E item : c)
            if (!contains(item))
                data.add(item);
        data.sort(null);
    }

    public ArraySortedSet() {

    }

    public ArraySortedSet(Collection<E> c, Comparator<? super E> comp) {
        comparator = comp;
        for (E item : c)
            if (!contains(item))
                data.add(item);
        data.sort(comparator);

    }

    private ArraySortedSet(List<E> list, Comparator<? super E> comp) {
        data = list;
        comparator = comp;

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

        int i = Collections.binarySearch(data, toElement, comparator);
        if (i >= 0)
            return new ArraySortedSet<>(data.subList(0, i), comparator);
        else
            return new ArraySortedSet<>(data.subList(0, -i - 1), comparator);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        int i = Collections.binarySearch(data, fromElement, comparator);
        List<E> temp;
        if (i >= 0) {
            temp = data.subList(i, data.size());
        } else {
            temp = data.subList(-i - 1, data.size());
        }

        return new ArraySortedSet<E>(temp, comparator);
    }

    @Override
    public boolean contains(Object o) {
        return Collections.binarySearch(data, (E) o, comparator) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (data.isEmpty()) {
            return data.containsAll(c);
        }
        for (Object e : c) {
            if (contains(e)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterator<E> iterator() {
        return new MyIterator();

    }

    class MyIterator implements Iterator<E> {
        Iterator<E> currentIterator;

        MyIterator() {
            currentIterator = data.iterator();

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


    @Override
    public E first() {
        if (data.isEmpty()) throw new NoSuchElementException();
        return data.get(0);
    }

    @Override
    public E last() {
        if (data.isEmpty()) throw new NoSuchElementException();
        return data.get(data.size() - 1);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return data.toArray(a);
    }

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