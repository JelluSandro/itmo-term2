package queue;


public class LinkedQueue extends AbstractQueue {

    private Node tail;
    private Node head;

    protected void enqueueImpl(final Object element) {
        final Node t = new Node(element, null);
        if (size == 0) {
            head = t;
        } else {
            tail.next = t;
        }
        tail = t;
    }

    protected void removeF() {
        head = head.next;
    }

    protected Object elementImpl() {
        return head.value;
    }

    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    protected LinkedQueue getQueue() {
        return new LinkedQueue();
    }

    private static class Node {
        private final Object value;
        private Node next;

        public Node(final Object value, final Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }
}
