import java.util.*;

public class SinglyLinkedList<T> implements MyList<T> {
  static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private Node<T> head;
  private int size;

  public SinglyLinkedList() {
    this.head = null;
    this.size = 0;
  }

  // O(1)
  @Override
  public void pushFront(T elem) {
    if (this.head == null) {
      this.head = new Node<>(elem);
    } else {
      Node<T> node = new Node<>(elem);
      node.next = this.head;
      this.head = node;
    }

    this.size++;
  }

  // O(n)
  @Override
  public void pushBack(T elem) {
    if (this.head == null) {
      this.head = new Node<>(elem);
    } else {
      Node<T> currNode = this.head;
      while (currNode.next != null) {
        currNode = currNode.next;
      }

      currNode.next = new Node<>(elem);
    }

    this.size++;
  }

  // O(1)
  @Override
  public T popFront() {
    if (isEmpty()) {
      throw new IllegalStateException("empty list");
    }

    T val = this.head.data;
    this.head = this.head.next;
    this.size--;

    return val;
  }

  // O(n)
  @Override
  public T popBack() {
    if (isEmpty()) {
      throw new IllegalStateException("empty list");
    }

    Node<T> prevNode = null;
    Node<T> currNode = this.head;
    while (currNode.next != null) {
      prevNode = currNode;
      currNode = currNode.next;
    }

    T val = currNode.data;
    if (prevNode == null) {
      this.head = null;
    } else {
      prevNode.next = null;
    }
    this.size--;

    return val;
  }

  @Override
  public T get(int idx) {
    throw new UnsupportedOperationException("get at index");
  }

  // O(n)
  @Override
  public void removeElem(T elem) {
    if (isEmpty()) {
      throw new IllegalStateException("empty list");
    }

    if (this.head.data.equals(elem)) {
      this.head = this.head.next;
    } else {

      Node<T> prevNode = null;
      Node<T> currNode = this.head;
      while (currNode != null && !currNode.data.equals(elem)) {
        prevNode = currNode;
        currNode = currNode.next;
      }

      if (currNode == null) {
        return;
      }

      prevNode.next = currNode.next;
    }
    this.size--;
  }

  // O(1)
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  // O(1)
  @Override
  public int size() {
    return this.size;
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "<EMPTY LIST>";
    }

    StringBuilder sb = new StringBuilder();
    Node<T> currNode = this.head;
    while (currNode != null) {
      sb.append(currNode.data).append(" ");
      currNode = currNode.next;
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      int nq = in.nextInt();
      in.nextLine();

      MyList<Integer> sll = new SinglyLinkedList<>();

      while (nq-- > 0) {
        String[] cmd = in.nextLine().trim().split(" ");

        switch (cmd[0]) {
        case "pushfront": {
          int elem = Integer.parseInt(cmd[1]);
          sll.pushFront(elem);
        } break;

        case "pushback": {
          int elem = Integer.parseInt(cmd[1]);
          sll.pushBack(elem);
        } break;

        case "popfront":
          System.out.printf("%d ", sll.popFront());
          break;

        case "popback":
          System.out.printf("%d ", sll.popBack());
          break;

        case "removeelem": {
          int elem = Integer.parseInt(cmd[1]);
          sll.removeElem(elem);
        } break;

        case "print":
          System.out.println(sll);
          break;

        case "newline":
          System.out.println();
          break;

        case "tillemptypopfront":
          while (!sll.isEmpty()) {
            System.out.printf("%d ", sll.popFront());
          }
          break;

        case "tillemptypopback":
          while (!sll.isEmpty()) {
            System.out.printf("%d ", sll.popBack());
          }
          break;

        default:
          throw new UnsupportedOperationException("invalid command: " + cmd[0]);
        }
      }
    }
  }
}