import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        clear();
    }

    public int getSize() {
        return size;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return head.getElement();
    }

    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.getElement();
    }

    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    public void addFirst(T element) {

        Node<T> node = new Node<T>(element, null, head);
        if (isEmpty()) {
            tail = node;
        } else {
            head.setPrevious(node);
        }
        head = node;
        size++;
    }

    public void addLast(T element) {
        Node<T> node = new Node<T>(element, tail, null);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    public T setFirst(T element) {
        if (isEmpty()) throw new NoSuchElementException();

        T oldElement = head.getElement();
        head.setElement(element);

        return oldElement;
    }

    public T setLast(T element) {
        if (isEmpty()) throw new NoSuchElementException();

        T oldElement = tail.getElement();
        tail.setElement(element);

        return oldElement;
    }

    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        T oldElement = head.getElement();
        if (size > 1) {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        } else {
            clear();
        }
        return oldElement;
    }

    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        T oldElement = tail.getElement();
        if (size > 1) {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        } else {
            clear();
        }
        return oldElement;
    }

    public Node<T> getNode(int position) {
        Node<T> currentNode = head;
        for (int i = position - 1; i > 0; i--) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    public Node<T> getNode(T element) {
        int i = 1;
        Node<T> currentNode = head;
        while (element != currentNode.getElement() && i < size) {
            currentNode = currentNode.getNext();
            i++;
        }
        return currentNode;
    }

    public T get(int position) {
        if (isEmpty()) throw new NoSuchElementException();
        if (position <= 0 || position > size) throw new NoSuchElementException();

        Node<T> node = getNode(position);

        return node.getElement();
    }

    public T get(T element) {
        if (isEmpty()) throw new NoSuchElementException();

        if (element != head.getElement() || element != tail.getElement()) {
            Node<T> node = getNode(element);

            if (element != node.getElement()) {
                throw new NoSuchElementException();
            }
        }
        return element;
    }

    public void removeNodeBetweenNodes(Node<T> nodeToRemove) {
        nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
        nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());
        size--;
    }

    public T remove(int position) {
        if (isEmpty()) throw new NoSuchElementException();
        if (position <= 0 || position > size) throw new NoSuchElementException();

        T oldElement;

        if (position == 1) {
            oldElement = removeFirst();
        } else if (position == size) {
            oldElement = removeLast();
        } else {
            Node<T> nodeToRemove = getNode(position);

            removeNodeBetweenNodes(nodeToRemove);

            oldElement = nodeToRemove.getElement();
        }

        return oldElement;
    }

    public T remove(T element) {
        if (isEmpty()) throw new NoSuchElementException();

        Node<T> nodeToRemove = getNode(element);
        if (element != nodeToRemove.getElement()) {
            throw new NoSuchElementException();
        }

        if (nodeToRemove == head) {
            if (head == tail) {
                clear();
            } else {
                element = removeFirst();
            }
        } else if (nodeToRemove == tail) {
            element = removeLast();
        } else {
            removeNodeBetweenNodes(nodeToRemove);
        }

        return element;
    }

    public T set(T element, int position) {
        if (position <= 0 || position > size) throw new NoSuchElementException();

        Node<T> nodeToAlter = getNode(position);
        T oldElement = nodeToAlter.getElement();
        nodeToAlter.setElement(element);

        return oldElement;
    }

    public T set(T element, T oldElement) {
        if (isEmpty()) throw new NoSuchElementException();
        if (element == null || oldElement == null) throw new NullPointerException();

        Node<T> nodeToAlter = getNode(oldElement);

        if (nodeToAlter.getElement() != oldElement) {
            throw new NoSuchElementException();
        }

        nodeToAlter.setElement(element);

        return oldElement;
    }

    public void addNodeAfterCurrentNode(T element, Node<T> currentNode) {
        Node newNode = new Node(element, currentNode, currentNode.getNext());
        currentNode.getNext().setPrevious(newNode);
        currentNode.setNext(newNode);
        size++;
    }

    public void addNodeBeforeCurrentNode(T element, Node<T> currentNode) {
        Node<T> newNode = new Node(element, currentNode.getPrevious(), currentNode);
        currentNode.getPrevious().setNext(newNode);
        currentNode.setPrevious(newNode);
        size++;
    }

    public void addAfter(T element, int position) {
        if (position <= 0 || position > size) throw new NoSuchElementException();

        if (position == size) {
            addLast(element);
        } else {
            Node currentNode = getNode(position);
            addNodeAfterCurrentNode(element, currentNode);
        }
    }

    public void addAfter(T element, T oldElement) {
        if (isEmpty()) throw new NoSuchElementException();
        if (element == null || oldElement == null) throw new NullPointerException();

        Node<T> currentNode = getNode(oldElement);

        if (currentNode.getElement() != oldElement) {
            throw new NoSuchElementException();
        }

        if (currentNode == tail) {
            addLast(element);
        } else {
            addNodeAfterCurrentNode(element, currentNode);
        }
    }

    public void addBefore(T element, int position) {
        if (position <= 0 || position > size) throw new NoSuchElementException();

        if (position == 1) {
            addFirst(element);
        } else {
            Node currentNode = getNode(position);
            addNodeBeforeCurrentNode(element, currentNode);
        }
    }

    public void addBefore(T element, T oldElement) {
        if (isEmpty()) throw new NoSuchElementException();
        if (element == null || oldElement == null) throw new NullPointerException();

        Node<T> currentNode = getNode(oldElement);

        if (currentNode.getElement() != oldElement) {
            throw new NoSuchElementException();
        }

        if (currentNode == head) {
            addFirst(element);
        } else {
            addNodeBeforeCurrentNode(element, currentNode);
        }
    }

    public void insert(T element) {
        if (isEmpty()) {
            addFirst(element);
        } else {
            Node<T> currentNode = head;
            int i = 1;
            while (i < size && element.compareTo(currentNode.getElement()) > 0) {
                currentNode = currentNode.getNext();
                i++;
            }
            if (currentNode == tail && element.compareTo(currentNode.getElement()) > 0) {
                addLast(element);
            } else if (currentNode == head) {
                addFirst(element);
            } else {
                addNodeBeforeCurrentNode(element, currentNode);
            }
        }
    }

    public void sortAscending() {

        if(!isEmpty() || head != tail) {
            boolean isSorted;
            Node<T> currentNode;
            Node<T> nextNode;
            T currentElement;

            do {
                isSorted = true;
                currentNode = head;
                nextNode = currentNode.getNext();

                while (nextNode != null) {
                    currentElement = currentNode.getElement();

                    if (currentElement.compareTo(nextNode.getElement()) > 0) {
                        currentNode.setElement(nextNode.getElement());
                        nextNode.setElement(currentElement);
                        isSorted = false;
                    }
                    currentNode = nextNode;
                    nextNode = currentNode.getNext();
                }
            } while (!isSorted);
        }
    }
}
