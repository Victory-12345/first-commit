import java.io.PrintWriter;


public class DoublyLinkedList<T> implements List<T> {
    class DNode {
        T data;
        DNode next;
        DNode prev;

        DNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private DNode head;
    private DNode tail;
    private DNode cursor;
    private int length;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        length = 0;
    }

    @Override
    public void insert(T newElement) throws ListException {
        if (newElement == null) {
            throw new ListException("Cannot insert null.");
        }
        DNode newNode = new DNode(newElement);
        if (head == null) { // 如果列表为空
            head = newNode;
            tail = newNode;
            cursor = head;
        } else {

            if (cursor == tail) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                cursor = tail;
            } else {
                newNode.next = cursor.next;
                if (cursor.next != null) {
                    cursor.next.prev = newNode;
                }
                cursor.next = newNode;
                newNode.prev = cursor;
                cursor = newNode;
            }

        }
        length++;
    }

    @Override
    public void remove()  {
        if (cursor==null) {
            throw new IllegalStateException("List is empty.");
        }
        if (cursor == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            cursor = (head != null) ? head : null;
        } else if(cursor == tail){
            tail.prev.next = null;
            tail = tail.prev;
            cursor = head;
        }else {
            if(cursor.next!=null) {
                cursor.next.prev = cursor.prev;
                cursor.prev.next = cursor.next;
                cursor = cursor.next;
            }
        }
        length--;
    }
    @Override
    public void replace(T newElement)  {
        if (isEmpty() || newElement == null) {
            throw new IllegalStateException("List is empty or newElement is null.");
        }
        if(cursor!=null)
            cursor.data = newElement;

    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        cursor = null;
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean isFull() {
        return false;
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
        cursor = tail;
        return true;
    }

    @Override
    public boolean gotoNext() {
        if (cursor != null && cursor.next != null) {
            cursor = cursor.next;
            return true;
        }
        return false;
    }

    @Override
    public boolean gotoPrev()  {
        if (cursor != null && cursor.prev != null) {
            cursor = cursor.prev;
            return true;
        }
        return false;
    }

    @Override
    public T getCursor()  {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        return cursor.data;
    }

    @Override
    public void showStructure(PrintWriter pw) {
        DNode temp = cursor;
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
    public boolean find(T searchElement)  {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
        DNode current = cursor;
        do {
            if (current.data.equals(searchElement)) {
                cursor = current;
                return true;
            }
            current = current.next;
        } while (current != null);
        return false;
    }
}


