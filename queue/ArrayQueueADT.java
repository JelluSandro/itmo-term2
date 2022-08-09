package queue;
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

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class ArrayQueueADT {
    private int size;
    private int head;
    private Object[] elements = new Object[5];

    // Pred: element != null && not empty && integer
    // Post: a[0] = element && n = n' + 1 && forall i = 1..n a[i] == a'[i]
    public static void push(ArrayQueueADT queue, Object element) {
        assert element != null;
        if (queue.size == queue.elements.length) {
            resize(queue);
        }
        queue.size++;
        queue.head--;
        if (queue.head < 0) {
            queue.head = queue.elements.length - 1;
        }
        queue.elements[queue.head] = element;
    }

    // Pred: n > 0
    // Post: Immutable && R = a[n]
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[(queue.head + queue.size - 1) % queue.elements.length];
    }

    // Pred: n > 0
    // Post: a[n'] delete && n = n' - 1 && forall i = 1..n' - 1 a[i] == a'[i] && R = a'[n']
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0;
        int tail = (queue.head + queue.size - 1) % queue.elements.length;
        Object x = queue.elements[tail];
        queue.elements[tail] = null;
        queue.size--;
        return x;
    }

    // Pred: true
    // Post: R = String([a[0], a[1], ... a[n]]) && Immutable
    public static String toStr(ArrayQueueADT queue) {
        return Arrays.toString(toArray(queue));
    }

    // Pred: element != null && not empty && integer
    // Post: element in tail && n = n' + 1 && R[n] = element && forall i = 1..n' a[i] == a'[i]
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        if (queue.size == queue.elements.length) {
            resize(queue);
        }
        queue.size++;
        queue.elements[(queue.head + queue.size - 1) % queue.elements.length] = element;
    }

    // Pred: true
    // Post: R = Array.of{a[0], a[1], ... a[n]} && Immutable
    public static Object[] toArray(ArrayQueueADT queue) {
        Object[] temporary = new Object[queue.size];
        copy(queue, temporary);
        return temporary;
    }

    private static void resize(ArrayQueueADT queue) {
        Object[] temporary = new Object[queue.elements.length * 2 + 1];
        copy(queue, temporary);
        queue.head = 0;
        queue.elements = temporary;
    }

    private static void copy(ArrayQueueADT queue, Object[] temporary) {
        if (queue.head + queue.size < queue.elements.length) {
            arraycopy(queue.elements, queue.head, temporary, 0, queue.size);
        }   else {
            arraycopy(queue.elements, queue.head, temporary, 0, queue.elements.length - queue.head);
            arraycopy(queue.elements, 0, temporary, queue.elements.length - queue.head,
                    (queue.head + queue.size) % queue.elements.length);
        }
    }

    // Pred: true
    // Post: n = 0
    public static void clear(ArrayQueueADT queue) {
        Arrays.fill(queue.elements, null);
        queue.head = 0;
        queue.size = 0;
    }

    // Pred: n > 0
    // Post: a[0] delete && n = n' - 1 && forall i = 2..n' a[i] == a'[i] && R = a'[0]
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object value = ArrayQueueADT.element(queue);
        queue.size--;
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        return value;
    }

    // Pred: size > 0
    // Post: R = a[0] && Immutable
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;

        return queue.elements[queue.head];
    }

    // Pred: true
    // Post: R = n && Immutable
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    // Pred: true
    // Post: if n == 0 then R = true else R = false && Immutable
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
}
