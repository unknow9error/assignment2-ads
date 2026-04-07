# Assignment 2 — ArrayList and LinkedList

Custom implementations of `ArrayList`, `LinkedList`, `Stack`, `Queue` and `MinHeap` in Java, without using anything from `java.util.*` except `Iterator`.

## Structure

| File | Description |
|------|-------------|
| `MyList.java` | Generic interface `MyList<T> extends Iterable<T>` defining the contract for list-like structures. |
| `MyArrayList.java` | Dynamic array implementation backed by `Object[]`. Auto-resizes when capacity is exceeded. |
| `MyLinkedList.java` | Doubly linked list with `head`/`tail` pointers and an inner `MyNode` class. |
| `MyStack.java` | LIFO stack built on top of `MyArrayList`. |
| `MyQueue.java` | FIFO queue built on top of `MyLinkedList`. |
| `MyMinHeap.java` | Binary min-heap built on top of `MyArrayList`. |
| `Main.java` | Test driver exercising every method of every class. |

## Choice of underlying structures

- **`MyStack` → `MyArrayList`** — push/pop happen at the end of the array, both are amortized O(1). No need for prev/next pointer overhead.
- **`MyQueue` → `MyLinkedList`** — needs O(1) insertion at the tail and O(1) removal from the head. A doubly linked list gives both for free; an array would require shifting elements on `dequeue`.
- **`MyMinHeap` → `MyArrayList`** — heap operations rely on index arithmetic (`parent = (i-1)/2`, `left = 2i+1`, `right = 2i+2`), which requires O(1) random access. A linked list would make sift-up/sift-down O(n) per step.

## `MyList<T>` interface

```java
void add(T item);
void set(int index, T item);
void add(int index, T item);
void addFirst(T item);
void addLast(T item);
T get(int index);
T getFirst();
T getLast();
void remove(int index);
void removeFirst();
void removeLast();
void sort();
int indexOf(Object object);
int lastIndexOf(Object object);
boolean exists(Object object);
Object[] toArray();
void clear();
int size();
```

`sort()` uses insertion sort and assumes elements implement `Comparable`.

## Complexity

### MyArrayList
| Operation | Complexity |
|-----------|------------|
| `add(item)` / `addLast` | amortized O(1) |
| `addFirst` / `add(index, item)` | O(n) |
| `get` / `set` | O(1) |
| `remove(index)` | O(n) |
| `indexOf` / `lastIndexOf` / `exists` | O(n) |
| `sort` | O(n²) |

### MyLinkedList
| Operation | Complexity |
|-----------|------------|
| `addFirst` / `addLast` | O(1) |
| `removeFirst` / `removeLast` | O(1) |
| `get(index)` / `set(index, ...)` | O(n) (optimized: traverses from the closer end) |
| `add(index, ...)` / `remove(index)` | O(n) |
| `indexOf` / `lastIndexOf` / `exists` | O(n) |
| `sort` | O(n²) |

### MyStack (on MyArrayList)
| Operation | Complexity |
|-----------|------------|
| `push` / `pop` / `peek` | amortized O(1) |

### MyQueue (on MyLinkedList)
| Operation | Complexity |
|-----------|------------|
| `enqueue` / `dequeue` / `peek` | O(1) |

### MyMinHeap (on MyArrayList)
| Operation | Complexity |
|-----------|------------|
| `insert` | O(log n) |
| `extractMin` | O(log n) |
| `getMin` | O(1) |

## Build & run

Requires a JDK (any version ≥ 8).

```bash
cd assignment2-ads
javac *.java
java Main
```

`Main` runs a test suite that exercises every method of every class and prints the results.

## Notes

- No imports from `java.util.*` are used apart from `Iterator` and `NoSuchElementException`.
- Both `MyArrayList` and `MyLinkedList` implement `Iterable<T>`, so they support enhanced `for` loops.
- `MyLinkedList` clears `prev`/`next` references on removal to help garbage collection and prevent accidental cycles.
