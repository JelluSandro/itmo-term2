package queue;


import java.util.Arrays;
/*
Model:
    [a1, a2, ...an]
    n -- размер дека
Inv:
    n >= 0
    forall i = 1..n: a[i] != null

Immutable:
    n = n' && forall i a[i] == a'[i]
*/

import static java.lang.System.arraycopy;

public class ArrayQueue extends AbstractQueue {
    private int head;
    private Object[] elements = new Object[5];

    protected void enqueueImpl(Object element) {
        if (size == elements.length) {
            resize();
        }
        elements[(head + size) % elements.length] = element;
    }

    private void copy(Object[] temporary) {
        if (head + size < elements.length) {
            arraycopy(elements, head, temporary, 0, size);
        } else {
            arraycopy(elements, head, temporary, 0, elements.length - head);
            arraycopy(elements, 0, temporary, elements.length - head, (head + size) % elements.length);
        }
    }

    private void resize() {
        Object[] temporary = new Object[elements.length * 2 + 1];
        copy(temporary);
        head = 0;
        elements = temporary;
    }

    protected void removeF() {
        elements[head] = null;
        head = (head + 1) % elements.length;
    }

    protected ArrayQueue getQueue() {
        return new ArrayQueue();
    }

    protected Object elementImpl() {
        return elements[head];
    }

    public void clear() {
        Arrays.fill(elements, null);
        head = 0;
        size = 0;
    }

    // Pred: n > 0
    // Post:  Immutable && R = a[n]
    public Object peek() {
        assert size > 0;
        return elements[(head + size - 1) % elements.length];
    }

    // Pred: n > 0
    // Post: a[n'] delete && n = n' - 1 && forall i = 1..n' - 1 a[i] == a'[i] && R = a'[n']
    public Object remove() {
        assert size > 0;
        int tail = (head + size - 1) % elements.length;
        Object x = elements[tail];
        elements[tail] = null;
        size--;
        return x;
    }

    // Pred: true
    // Post: R = String([a[0], a[1], ... a[n]]) && Immutable
    public String toStr() {
        return Arrays.toString(toArray());
    }
    // Pred: true
    // Post: R = Array.of{a[0], a[1], ... a[n]} && Immutable
    public Object[] toArray() {
        Object[] temporary = new Object[size];
        copy(temporary);
        return temporary;
    }
    // Pred: element != null && not empty && integer
    // Post: a[0] = element && n = n' + 1 && forall i = 1..n a[i] == a'[i]
    public void push(Object element) {
        assert element != null;
        if (size == this.elements.length) {
            resize();
        }
        size++;
        head--;
        if (head < 0) {
            head = elements.length - 1;
        }
        elements[head] = element;
    }

}
