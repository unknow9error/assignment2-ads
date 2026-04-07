import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {
    private class MyNode {
        T element;
        MyNode next;
        MyNode prev;

        MyNode(T element) {
            this.element = element;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private MyNode nodeAt(int index) {
        if (index < size / 2) {
            MyNode cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
            return cur;
        } else {
            MyNode cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
            return cur;
        }
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        nodeAt(index).element = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            MyNode cur = nodeAt(index);
            MyNode node = new MyNode(item);
            node.prev = cur.prev;
            node.next = cur;
            cur.prev.next = node;
            cur.prev = node;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        MyNode node = new MyNode(item);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode node = new MyNode(item);
        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return nodeAt(index).element;
    }

    @Override
    public T getFirst() {
        if (head == null) throw new NoSuchElementException();
        return head.element;
    }

    @Override
    public T getLast() {
        if (tail == null) throw new NoSuchElementException();
        return tail.element;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            MyNode cur = nodeAt(index);
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            cur.prev = null;
            cur.next = null;
            size--;
        }
    }

    @Override
    public void removeFirst() {
        if (head == null) throw new NoSuchElementException();
        MyNode old = head;
        head = head.next;
        if (head == null) tail = null;
        else head.prev = null;
        old.next = null;
        size--;
    }

    @Override
    public void removeLast() {
        if (tail == null) throw new NoSuchElementException();
        MyNode old = tail;
        tail = tail.prev;
        if (tail == null) head = null;
        else tail.next = null;
        old.prev = null;
        size--;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size < 2) return;
        for (int i = 1; i < size; i++) {
            MyNode cur = nodeAt(i);
            T key = cur.element;
            MyNode j = cur.prev;
            while (j != null && ((Comparable<T>) j.element).compareTo(key) > 0) {
                j.next.element = j.element;
                j = j.prev;
            }
            if (j == null) head.element = key;
            else j.next.element = key;
        }
    }

    @Override
    public int indexOf(Object object) {
        int idx = 0;
        for (MyNode cur = head; cur != null; cur = cur.next, idx++) {
            if (object == null ? cur.element == null : object.equals(cur.element)) return idx;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int idx = size - 1;
        for (MyNode cur = tail; cur != null; cur = cur.prev, idx--) {
            if (object == null ? cur.element == null : object.equals(cur.element)) return idx;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (MyNode cur = head; cur != null; cur = cur.next) arr[i++] = cur.element;
        return arr;
    }

    @Override
    public void clear() {
        MyNode cur = head;
        while (cur != null) {
            MyNode nxt = cur.next;
            cur.prev = null;
            cur.next = null;
            cur.element = null;
            cur = nxt;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode cursor = head;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public T next() {
                if (cursor == null) throw new NoSuchElementException();
                T val = cursor.element;
                cursor = cursor.next;
                return val;
            }
        };
    }
}
