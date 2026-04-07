public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> list;

    public MyMinHeap() {
        list = new MyArrayList<>();
    }

    public void insert(T item) {
        list.add(item);
        siftUp(list.size() - 1);
    }

    public T getMin() {
        if (empty()) throw new RuntimeException("Heap is empty");
        return list.get(0);
    }

    public T extractMin() {
        if (empty()) throw new RuntimeException("Heap is empty");
        T min = list.get(0);
        T last = list.get(list.size() - 1);
        list.removeLast();
        if (!empty()) {
            list.set(0, last);
            siftDown(0);
        }
        return min;
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (list.get(i).compareTo(list.get(parent)) < 0) {
                swap(i, parent);
                i = parent;
            } else break;
        }
    }

    private void siftDown(int i) {
        int n = list.size();
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;
            if (left < n && list.get(left).compareTo(list.get(smallest)) < 0) smallest = left;
            if (right < n && list.get(right).compareTo(list.get(smallest)) < 0) smallest = right;
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else break;
        }
    }

    private void swap(int a, int b) {
        T tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }
}
