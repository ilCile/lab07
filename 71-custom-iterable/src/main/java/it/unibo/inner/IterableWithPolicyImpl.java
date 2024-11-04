package it.unibo.inner;

import java.util.Arrays;
import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final T[] array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] array){
        this(array, null);
    }

    public IterableWithPolicyImpl(T[] array, Predicate<T> filter){
        this.array =  Arrays.copyOf(array, array.length);
        this.filter = filter;    
    }


    @Override
    public Iterator<T> iterator() {
        return new IteratorWithPolicy(array);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    public class IteratorWithPolicy implements Iterator<T>{

        private final T[] elements;
        private int current;

        public IteratorWithPolicy(T[] array){
            this.elements = array;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return elements.length > current;
        }

        @Override
        public T next() { 
            if(filter.test(this.elements[this.current++])){
                return this.elements[this.current++];
            }  
            return null;
        }

    }

}
