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
public interface Queue {

    // Pred: element != null && not empty && integer
    // Post: element in tail && n = n' + 1 && R[n] = element && forall i = 1..n' a[i] == a'[i]

    void enqueue(Object element);

    // Pred: n > 0
    // Post: a[0] delete && n = n' - 1 && forall i = 2..n' a[i] == a'[i] && R = a'[0]

    Object dequeue();

    // Pred: size > 0
    // Post: R = a[0] && Immutable

    Object element();

    // Pred: true
    // Post: R = n && Immutable

    int size();

    // Pred: true
    // Post: if n == 0 then R = true else R = false && Immutable

    boolean isEmpty();

    // Pred: true
    // Post: n = 0

    void clear();

    // Pred: n > 0
    // Post: Immutable
    // R = b : b.size = size / n && forall  1 <= i <= b.size b[i] == a[i * n]
    // b.type same a.type (b.type is implements Queue)

    Queue getNth(int n);

    // Pred: n > 0
    // Post: size == size' - size' / n && forall 0 < i <= size' && i % n != 0 a'[i] = a[i - i / n]
    void dropNth(int n);

    // Pred: n > 0
    // Post: size == size' - size' / n && forall 0 < i <= size' && i % n != 0 a'[i] = a[i - i / n]
    // R = b : b.size = size' / n && forall  0 < i <= b.size a'[i * n] = b[i]
    // b.type same a.type (b.type is implements Queue)
    Queue removeNth(int n);
}