package ex1;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VectorGeneric<T> {
    private T[] vec;
    private int nElem;
    private final static int ALLOC = 50;
    private int dimVec = ALLOC;

    @SuppressWarnings("unchecked")
    public VectorGeneric() {
        vec = (T[]) new Object[dimVec];
        nElem = 0;
    }

    public boolean addElem(T elem) {
        if (elem == null)
            return false;
        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }

    private void ensureSpace() {
        if(nElem>=dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
                    T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem);
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for(int i = 0; i < nElem; i++) {
            if(vec[i].equals(elem)) {
                if(nElem-i-1 > 0) // not last element
                    System.arraycopy(vec, i+1, vec, i, nElem-i-1);
                vec[--nElem] = null; // libertar último objecto para o GC
                return true;
            }
        }
        return false;
    }

    public int totalElem() {
        return nElem;
    }

    public T getElem(int i) {
        return (T) vec[i];
    }

    public java.util.Iterator<T> Iterator() {
        return (this).new VectorIterator<T>();
    }

    private class VectorIterator<K> implements Iterator<K> {
        private int indice;
        VectorIterator() {
            indice = 0;
        }

        @Override
        public boolean hasNext() {
            return (indice < nElem);
        }

        @Override
        public K next() {

            if(hasNext()) return (K)VectorGeneric.this.vec[indice++];
            throw new NoSuchElementException("only "+nElem+" elements");
        }
    }

    public java.util.ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public java.util.ListIterator<T> listIterator(int index) {
        return (this).new VectorListIterator<T>(index);

    }

    private class VectorListIterator<K> implements ListIterator<K> {
        private int prev,next;

        VectorListIterator(int idx) {

            prev = idx-1;
            next = idx;
        }

        @Override
        public boolean hasNext() {
            return (next < nElem);
        }

        @Override
        public K next() {
            if(hasNext()) return (K)VectorGeneric.this.vec[next++];
            throw new NoSuchElementException("only "+nElem+" elements");
        }

        @Override
        public boolean hasPrevious() {
            return (prev > 0);
        }

        @Override
        public K previous() {
            if(hasPrevious()) return (K)VectorGeneric.this.vec[prev--];
            throw new NoSuchElementException("only "+nElem+" elements");
        }

        @Override
        public int nextIndex() {
            return next+1;
        }


        @Override
        public int previousIndex() {
            return prev-1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

        @Override
        public void set(K k) {
            throw new UnsupportedOperationException();

        }

        @Override
        public void add(K k) {
            throw new UnsupportedOperationException();

        }
    }
}

