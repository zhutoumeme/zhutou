package cn.cm.union.cloneable;


public class Test<E> {

  static class Node<E> {

    E item;

    Node<E> next;

    Node(E x) {
      item = x;
    }
  }

  public String userData = null;

  public Test(String userData) {
    this.userData = userData;
  }

  transient Node<E> head;

  /**
   * Tail of linked list. Invariant: last.next == null
   */
  private transient Node<E> last;


  public static void main(String[] args) {
    Test test = new Test("1212");
    test.last = test.head = new Node<>(null);
    Node node = new Node("zhuyingye");
    test.last.next = node;
    test.last =  test.last.next;
    System.out.println(test.last.item);
    System.out.println(test.last.next);
  }
}
