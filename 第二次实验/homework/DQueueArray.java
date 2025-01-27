public class DQueueArray implements DQueue<Integer> {
    private Integer[] deque;
    private int front;
    private int rear;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public DQueueArray() {
        deque = (Integer[]) new Object[DEFAULT_CAPACITY];
        front = -1;
        rear = -1;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == deque.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueueToRear(Integer element) throws ListException {
        if (isFull()) {
            throw new ListException("Queue is full.");
        }
        if (rear == deque.length - 1) {
            // Rear is at the end of the array, we need to wrap it around
            rear = -1;
        }
        deque[++rear] = element;
        size++;
    }

    @Override
    public void enqueueToFront(Integer element) throws ListException {
        if (isFull()) {
            throw new ListException("Queue is full.");
        }
        if (front == 0) {
            front = deque.length;
        }
        deque[--front] = element;
        size++;
    }

    @Override
    public Integer dequeueFromFront() {
        if (isEmpty()) {
            return null;
        }
        Integer element = deque[front++];
        if (front == deque.length) {
            front = 0;
        }
        size--;
        return element;
    }

    @Override
    public Integer dequeueFromRear() {
        if (isEmpty()) {
            return null;
        }
        Integer element = deque[rear--];
        if (rear == -1) {
            rear = deque.length - 1;
        }
        size--;
        return element;
    }

    @Override
    public Integer getFront() {
        if (isEmpty()) {
            return null;
        }
        return deque[front];
    }

    @Override
    public Integer getRear() {
        if (isEmpty()) {
            return null;
        }
        return deque[rear];
    }
}
