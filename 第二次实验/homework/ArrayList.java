import java.io.PrintWriter;
public class ArrayList implements List<Character> {
    private Character[] elements;
    private int size;
    private int cursor;

    public ArrayList(int capacity) {
        elements = new Character[capacity];
        size = 0;
        cursor = -1;
    }

    @Override
    public void insert(Character newElement) throws ListException {
        if (isFull()) {
            throw new ListException("List is full");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        if (size == 0) {
            elements[0] = newElement;
            cursor = 0;
        } else {
            for (int i = size; i > cursor + 1; i--) {
                elements[i] = elements[i - 1];
            }
            elements[cursor + 1] = newElement;
            cursor++;
        }
        size++;
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        for (int i = cursor; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;

        if (cursor >= size) {
            cursor = (size == 0) ? -1 : 0;
        }
    }

    @Override
    public void replace(Character newElement) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        if (newElement == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        elements[cursor] = newElement;
    }

    @Override
    public void clear() {
        elements = new Character[elements.length];
        size = 0;
        cursor = -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == elements.length;
    }

    @Override
    public boolean gotoBeginning() {
        if (isEmpty()) {
            return false;
        }
        cursor = 0;
        return true;
    }

    @Override
    public boolean gotoEnd() {
        if (isEmpty()) {
            return false;
        }
        cursor = size - 1;
        return true;
    }

    @Override
    public boolean gotoNext() {
        if (cursor < size - 1) {
            cursor++;
            return true;
        }
        return false;
    }

    @Override
    public boolean gotoPrev() {
        if (cursor > 0) {
            cursor--;
            return true;
        }
        return false;
    }

    @Override
    public Character getCursor() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return elements[cursor];
    }

    @Override
    public void showStructure(PrintWriter pw) {
        // 输出列表元素
        for (int i = 0; i < size; i++) {
            pw.print(elements[i] + " ");
        }

        pw.printf("{capacity = %d, length = %d, cursor = %d}\n", elements.length, size, cursor);
    }


    @Override
    public void moveToNth(int n) {
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Character element = elements[cursor];
        remove();
        cursor = n - 1;
        try {
            insert(element);
        } catch (ListException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean find(Character searchElement) {
        if (isEmpty()) {
            return false;
        }
        int start = cursor;
        do {
            if (elements[cursor].equals(searchElement)) {
                return true;
            }
        } while (gotoNext());
        cursor = start;
        return false;
    }
}
