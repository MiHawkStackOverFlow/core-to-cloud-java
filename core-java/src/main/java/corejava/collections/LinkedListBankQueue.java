/*
 
When to Use LinkedList

Use LinkedList when:
You need fast insertions/removals at beginning or end.
You’ll use it as a queue (FIFO) or stack (LIFO).
Data size is highly dynamic, and frequent element shifting happens.

Avoid LinkedList when:
You need frequent random access (get by index).
You need memory efficiency — it has extra pointer overhead.
You want cache-friendly performance (it’s pointer-based, scattered in memory).

| Feature                        | ArrayList      | LinkedList               |
| ------------------------------ | -------------- | ------------------------ |
| **Structure**                  | Dynamic array  | Doubly linked nodes      |
| **Access by index**            | O(1)           | O(n)                     |
| **Insert/remove at end**       | O(1) amortized | O(1)                     |
| **Insert/remove at beginning** | O(n)           | O(1)                     |
| **Insert/remove in middle**    | O(n)           | O(n)                     |
| **Memory overhead**            | Low            | High                     |
| **Cache performance**          | Good           | Poor                     |
| **Best for**                   | Frequent reads | Frequent inserts/deletes |

Think of:

ArrayList as a hotel corridor with fixed numbered rooms — great for direct access (Room 101, 102, …).
LinkedList as a chain of houses, each knowing only its neighbor — easy to add/remove a house, but slow to find “the 10th house.”

 */
package corejava.collections;

import java.time.Instant;
import java.util.LinkedList;
import java.util.ListIterator;

class BankTxn {

    private final String id;
    private final String account;
    private final double amount;
    private final Instant ts;

    BankTxn(String id, String account, double amount, Instant ts) {
      this.id = id;
      this.account = account;
      this.amount = amount;
      this.ts = ts;
    }

    public String id() {
      return id;
    }

    public String account() {
      return account;
    }

    public double amount() {
      return amount;
    }

    public Instant ts() {
      return ts;
    }

    @Override
    public String toString() {
      return "BankTxn{id='%s', acct='%s', amt=%.2f, ts=%s}".formatted(id, account, amount, ts);
    }
}

public class LinkedListBankQueue {

    public static void main(String[] args) {
      // ---------------- CREATE (as a queue/deque) ----------------
      LinkedList<BankTxn> queue = new LinkedList<>();
      queue.addLast(new BankTxn("t1", "ACC1001", 500.00, Instant.now()));
      queue.addLast(new BankTxn("t2", "ACC1002", -120.00, Instant.now()));
      queue.addLast(new BankTxn("t3", "ACC1003", 2000.00, Instant.now()));

      // High-priority correction at the head
      queue.addFirst(new BankTxn("t0", "ACC9999", 50.00, Instant.now()));

      System.out.println("Initial queue (head → tail):");
      printQueue(queue);

      // ---------------- READ (peek without removing) --------------
      BankTxn next = queue.peekFirst(); // same as peek()
      System.out.println("\nNext to process (peekFirst): " + next);

      BankTxn lastIncoming = queue.peekLast();
      System.out.println("Most recent incoming (peekLast): " + lastIncoming);

      // ---------------- UPDATE (replace/modify element) -----------
      // Example: fee waiver—change a specific txn amount by id
      ListIterator<BankTxn> it = queue.listIterator();
      while (it.hasNext()) {
        BankTxn tx = it.next();
        if (tx.id().equals("t2")) {
          // Replace with a new object (records would be cleaner,
          // but here we’re using a simple class)
          it.set(new BankTxn(tx.id(), tx.account(), 0.00, tx.ts()));
        }
      }
      System.out.println("\nAfter updating t2 amount to 0.00:");
      printQueue(queue);

      // ---------------- DELETE (poll/remove) ----------------------
      BankTxn processed1 = queue.pollFirst();   // FIFO: remove head
      System.out.println("\nProcessed (pollFirst): " + processed1);
      System.out.println("Queue after one process:");
      printQueue(queue);

      // Remove last (e.g., cancel the most recent unprocessed)
      BankTxn canceled = queue.pollLast();
      System.out.println("\nCanceled most recent (pollLast): " + canceled);
      System.out.println("Queue after cancel:");
      printQueue(queue);

      // Conditional removal (e.g., remove zero-amount no-ops)
      boolean anyRemoved = queue.removeIf(tx -> tx.amount() == 0.0);
      System.out.println("\nRemoved zero-amount txns? " + anyRemoved);
      printQueue(queue);

      // ---------------- ITERATE (for-each & iterator) -------------
      System.out.println("\nIterating with for-each:");
      for (BankTxn tx : queue) {
        System.out.println(tx);
      }

      System.out.println("\nIterating with backward traversal (ListIterator):");
      it = queue.listIterator(queue.size());
      while (it.hasPrevious()) {
        System.out.println(it.previous());
      }

      // ---------------- SIZE & CLEAR ------------------------------
      System.out.println("\nCurrent size: " + queue.size());
      queue.clear();
      System.out.println("Cleared. Is empty? " + queue.isEmpty());
    }

    private static void printQueue(LinkedList<BankTxn> queue) {
      System.out.println(queue.isEmpty() ? "[]" : String.join("\n", queue.stream().map(Object::toString).toList()));
    }
}
