public class Node<T> {

    private Node<T> previousNode;
    private Node<T> nextNode;
    private T element;

    public Node() { }

    public Node(T element) {
        this.element = element;
    }

    public Node(T element, Node<T> previousNode, Node<T> nextNode) {
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getPrevious() {
        return previousNode;
    }

    public void setPrevious(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

    public Node<T> getNext() {
        return nextNode;
    }

    public void setNext(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
}
