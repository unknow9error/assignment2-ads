public class Main {
    public static void main(String[] args) {
        System.out.println("=== MyArrayList ===");
        MyArrayList<Integer> al = new MyArrayList<>();
        al.add(3); al.add(1); al.add(4); al.add(1); al.add(5); al.add(9); al.add(2); al.add(6);
        al.addFirst(0);
        al.addLast(99);
        al.add(2, 100);
        System.out.print("List: ");
        for (int x : al) System.out.print(x + " ");
        System.out.println();
        System.out.println("size=" + al.size());
        System.out.println("get(0)=" + al.getFirst() + " get(last)=" + al.getLast());
        System.out.println("indexOf(1)=" + al.indexOf(1) + " lastIndexOf(1)=" + al.lastIndexOf(1));
        System.out.println("exists(100)=" + al.exists(100));
        al.set(0, -1);
        al.remove(2);
        System.out.print("After set/remove: ");
        for (int x : al) System.out.print(x + " ");
        System.out.println();
        al.sort();
        System.out.print("Sorted: ");
        for (int x : al) System.out.print(x + " ");
        System.out.println();
        Object[] arr = al.toArray();
        System.out.println("toArray length=" + arr.length);
        al.clear();
        System.out.println("After clear size=" + al.size());

        System.out.println("\n=== MyLinkedList ===");
        MyLinkedList<String> ll = new MyLinkedList<>();
        ll.add("b"); ll.add("a"); ll.add("d"); ll.add("c");
        ll.addFirst("first");
        ll.addLast("last");
        ll.add(3, "mid");
        System.out.print("List: ");
        for (String s : ll) System.out.print(s + " ");
        System.out.println();
        System.out.println("getFirst=" + ll.getFirst() + " getLast=" + ll.getLast());
        System.out.println("get(2)=" + ll.get(2));
        System.out.println("indexOf(d)=" + ll.indexOf("d"));
        ll.removeFirst();
        ll.removeLast();
        ll.remove(2);
        System.out.print("After removes: ");
        for (String s : ll) System.out.print(s + " ");
        System.out.println();
        ll.sort();
        System.out.print("Sorted: ");
        for (String s : ll) System.out.print(s + " ");
        System.out.println();
        System.out.println("size=" + ll.size());

        System.out.println("\n=== MyStack ===");
        MyStack<Integer> st = new MyStack<>();
        st.push(1); st.push(2); st.push(3);
        System.out.println("peek=" + st.peek());
        System.out.println("pop=" + st.pop());
        System.out.println("pop=" + st.pop());
        System.out.println("size=" + st.size() + " empty=" + st.empty());

        System.out.println("\n=== MyQueue ===");
        MyQueue<String> q = new MyQueue<>();
        q.enqueue("A"); q.enqueue("B"); q.enqueue("C");
        System.out.println("peek=" + q.peek());
        System.out.println("dequeue=" + q.dequeue());
        System.out.println("dequeue=" + q.dequeue());
        System.out.println("size=" + q.size() + " empty=" + q.empty());

        System.out.println("\n=== MyMinHeap ===");
        MyMinHeap<Integer> h = new MyMinHeap<>();
        int[] vals = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        for (int v : vals) h.insert(v);
        System.out.print("Extracted in order: ");
        while (!h.empty()) System.out.print(h.extractMin() + " ");
        System.out.println();
    }
}
