package queue;




public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(final Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }


    protected abstract void enqueueImpl(Object element);

    public Object element() {
        assert size > 0;

        return elementImpl();
    }

    protected abstract Object elementImpl();


    public Object dequeue() {
        assert size > 0;

        final Object result = element();
        size--;
        removeF();
        return result;
    }

    protected abstract void removeF();

    private AbstractQueue commonNth(final int n, final boolean deleteN, final AbstractQueue ans) {
        final int size = this.size;
        for (int i = 1; i <= size; i++) {
            final Object e = dequeue();
            if (i % n != 0) {
                enqueue(e);
            } else {
                if (!deleteN) {
                    enqueue(e);
                }
                if (ans != null) {
                    ans.enqueue(e);
                }
            }
        }
        return ans;
    }

    @Override
    public void dropNth(final int n) {
        commonNth(n, true, null);
    }

    public AbstractQueue getNth(final int n) {
        return commonNth(n, false, getQueue());
    }

    @Override
    public Queue removeNth(final int n) {
        return commonNth(n, true, getQueue());
    }

    protected abstract AbstractQueue getQueue();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
