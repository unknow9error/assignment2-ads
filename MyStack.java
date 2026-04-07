public class MyStack<T> {
    private MyArrayList<T> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    public void push(T item) {
        list.addLast(item);
    }

    public T pop() {
        T val = list.getLast();
        list.removeLast();
        return val;
    }

    public T peek() {
        return list.getLast();
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}
