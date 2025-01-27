import java.io.PrintWriter;
import java.util.Timer;


public class SinglyLinkedList<T> implements List<T> {

    class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node cursor;
    private int length;
    private int capacity;

    public SinglyLinkedList() {
        head = null;
        cursor = null;
        length = 0;
    }

    @Override
    public void insert(T newElement) throws ListException {
        if (isFull()) {
            throw new ListException("List is full.");
        }
        if (newElement == null) {
            throw new ListException("Cannot insert null.");
        }
        Node newNode = new Node(newElement);
        try {
            if (head == null) {
                head = newNode;
                cursor = head;
            } else {
                newNode.next = cursor.next;
                cursor.next = newNode;
                cursor = newNode;
            }
        } catch (NullPointerException e) {

        }
        length++;
    }

    @Override
    public void remove()  {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.Cannot remove");
        }
        if (cursor == head) {
            head = head.next;
            cursor = head; // 如果链表变为空，游标也置为空
        } else {
            Node temp = head;
            while (temp != null && temp.next != cursor) {
                temp = temp.next;
            }
            try {
                if (temp != null) {
                    temp.next = cursor.next;
                    cursor = (temp.next != null) ? temp.next : head; // 如果删除了最后一个元素，游标指向头部
                }
            }catch (NullPointerException e){

            }
        }
        length--;
    }

    @Override
    public void replace(T newElement)  {
        if (isEmpty() || newElement == null) {
            throw new IllegalStateException("List is empty or newElement is null.");
        }
        try {
            cursor.data = newElement;
        }catch (NullPointerException e){

        }
    }


    @Override
    public void clear() {
        head = null;
        cursor = null;
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean isFull() {
        return false; // 单向链表在逻辑上是无限的
    }

    @Override
    public boolean gotoBeginning() {
        if (isEmpty()) return false;
        cursor = head;
        return true;
    }

    @Override
    public boolean gotoEnd() {
        if (isEmpty()) return false;
        cursor = head;
        while (cursor.next != null) {
            cursor = cursor.next;
        }
        return true;
    }

    @Override
    public boolean gotoNext()  {
        if (cursor != null && cursor.next != null) {
            cursor = cursor.next;
            return true;
        }
        return false;
    }

    @Override
    public boolean gotoPrev()  {
        if (head == null || cursor == head) return false;
        Node temp = head;
        while (temp != null && temp.next != cursor) {
            temp = temp.next;
        }
        if (temp != null) {
            cursor = temp;
            return true;
        }
        return false;
    }

    @Override
    public T getCursor() {
        if (cursor == null) {
            throw new IllegalStateException("List is empty.");
        }
        return cursor.data;
    }

    @Override
    public void showStructure(PrintWriter pw) {
        Node temp = cursor;
        if (isEmpty()) {
            pw.println("Empty list");
        } else {
            cursor = head;
            while (cursor != null) {
                pw.print(cursor.data + " ");
                cursor = cursor.next;
            }
            cursor = temp;
            String cursorString = (cursor == null) ? "null" : cursor.data.toString();
            pw.printf(" {capacity=%d, length=%d, cursor=%s}", length, length, cursorString);
            pw.println();
        }
        pw.flush();
    }

    @Override
    public void moveToNth(int n)  {
        if (n < 0 || n >= length) {
            throw new IllegalStateException("Index out of bounds.");
        }
        Node temp = head;
        T element = getCursor();
        remove();
        cursor = head;
        for (int i = 0; i < n; i++) {
            gotoNext();
        }
        try {
            insert(element);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean find(Object searchElement)  {
        return false;
    }
}
