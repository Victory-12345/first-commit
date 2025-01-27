public class DQueueLinkedList implements DQueue<Integer> {
    private class Node {
        Integer data;
        Node prev;
        Node next;

        Node(Integer data) {
            this.data = data;
            prev = null;
            next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public DQueueLinkedList() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false; // Linked list implementation is never full unless memory is exhausted
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueueToRear(Integer element) throws ListException {
        if (element == null) {
            throw new ListException("Element cannot be null.");
        }
        Node newNode = new Node(element);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }

    @Override
    public void enqueueToFront(Integer element) throws ListException {
        if (element == null) {
            throw new ListException("Element cannot be null.");
        }
        Node newNode = new Node(element);
        if (front == null) {
            front = rear = newNode;
        } else {
            front.prev = newNode;
            newNode.next = front;
            front = newNode;
        }
        size++;
    }

    @Override
    public Integer dequeueFromFront() {
        if (isEmpty()) {
            return null;
        }
        Integer element = front.data;
        front = front.next;
        if (front != null) {
            front.prev = null;
        } else {
            rear = null; // List becomes empty
        }
        size--;
        return element;
    }

    @Override
    public Integer dequeueFromRear() {
        if (isEmpty()) {
            return null;
        }
        Integer element = rear.data;
        rear = rear.prev;
        if (rear != null) {
            rear.next = null;
        } else {
            front = null; // List becomes empty
        }
        size--;
        return element;
    }

    @Override
    public Integer getFront() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    @Override
    public Integer getRear() {
        if (isEmpty()) {
            return null;
        }
        return rear.data;
    }
}
