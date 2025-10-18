/*

List<E> is an ordered, indexed, and duplicate-allowed collection. Think of it as a resizable array with richer operations.

public interface List<E> extends Collection<E>

| Feature                | Description                             |
| ---------------------- | --------------------------------------- |
| Ordering               | Maintains insertion order               |
| Duplicates             | Allowed                                 |
| Index-based access     | Yes (`get(index)`, `set(index, value)`) |
| Null elements          | Allowed (depends on implementation)     |
| Thread-safe            | Only `Vector` is (legacy)               |


| Implementation | Underlying Structure       | Performance                         | Best Use Case                         |
| -------------- | -------------------------- | ----------------------------------- | ------------------------------------- |
| `ArrayList`    | Dynamic array              | O(1) get, O(n) add/remove in middle | Random access, frequent reads         |
| `LinkedList`   | Doubly linked nodes        | O(n) get, O(1) add/remove at ends   | Queues, frequent insert/delete        |


| Operation                    | Average Time Complexity | Worst Case | Explanation                                 |
| ---------------------------- | ----------------------- | ---------- | ------------------------------------------- |
| **Access (get)**             | O(1)                    | O(1)       | Direct index access (backed by array).      |
| **Update (set)**             | O(1)                    | O(1)       | Index-based replacement.                    |
| **Add at end**               | O(1) amortized          | O(n)       | Occasionally resizes internal array.        |
| **Add at index**             | O(n)                    | O(n)       | Shifts elements to the right.               |
| **Remove at index / object** | O(n)                    | O(n)       | Shifts elements left after removal.         |
| **Contains / search**        | O(n)                    | O(n)       | Linear search unless sorted + binarySearch. |
| **Iteration**                | O(n)                    | O(n)       | Sequential traversal.                       |
| **Clear**                    | O(n)                    | O(n)       | Nullifies references for GC.                |


| Metric              | Space Complexity | Explanation                                                               |
| ------------------- | ---------------- | ------------------------------------------------------------------------- |
| **Memory usage**    | O(n)             | Stores object references + some unused capacity.                          |
| **Resize overhead** | O(n)             | When full, creates a new array 1.5x the old size and copies all elements. |


| Scenario                                           | Recommended                    | Reason                                        |
| -------------------------------------------------- | ------------------------------ | --------------------------------------------- |
| **You know size at compile time**                  | `Array`                        | Fixed length; slightly lower overhead.        |
| **You need fast random access (get/set)**          | `ArrayList`                    | Index lookup O(1).                            |
| **You frequently add/remove at the end**           | `ArrayList`                    | Append amortized O(1).                        |
| **You frequently insert/remove in the middle**     | `LinkedList`                   | No shifting cost (O(1) if node known).        |
| **You rarely modify but iterate often**            | `ArrayList`                    | Better cache locality → faster iteration.     |
| **You need queue/deque behavior**                  | `LinkedList` / `ArrayDeque`    | Head/tail operations O(1).                    |
| **You need primitive type efficiency (no boxing)** | `Array`                        | Stores raw primitives, no `Integer` wrappers. |


| Feature                     | Array           | ArrayList      | LinkedList                    |
| --------------------------- | --------------- | -------------- | ----------------------------- |
| **Size**                    | Fixed           | Dynamic        | Dynamic                       |
| **Index Access**            | O(1)            | O(1)           | O(n)                          |
| **Insert/Remove at end**    | O(1)            | O(1) amortized | O(1)                          |
| **Insert/Remove at middle** | O(n)            | O(n)           | O(1)* (if you have reference) |
| **Memory Overhead**         | Low             | Moderate       | High (extra node pointers)    |
| **Cache Locality**          | Excellent       | Good           | Poor                          |
| **Thread-Safe**             | No              | No             | No                            |
| **Implementation Base**     | Primitive array | Dynamic array  | Doubly-linked nodes           |


| Use Case                                                                      | Ideal Choice                 | Why                                 |
| ----------------------------------------------------------------------------- | ---------------------------- | ----------------------------------- |
| Banking transactions displayed in dashboard (frequent reads, occasional adds) | `ArrayList`                  | Fast random access and iteration.   |
| Transaction log queue (FIFO)                                                  | `LinkedList` or `ArrayDeque` | Fast insert/remove from ends.       |
| Fixed-size 12-month interest rates                                            | `Array`                      | Simple, no dynamic resizing needed. |
| Real-time feed updates with frequent middle insertions                        | `LinkedList`                 | Avoids shifting cost of ArrayList.  |
| Large dataset read-only view (analytics snapshot)                             | `ArrayList`                  | Efficient memory and cache use.     |


“ArrayList offers O(1) random access and better cache locality, making it ideal for read-heavy workloads. 
LinkedList only benefits when frequent insertions/removals occur at known positions — otherwise traversal makes it slower and more memory intensive.”

| Operation   | Method Used                 | Purpose              |
| ----------- | --------------------------- | -------------------- |
| **Create**  | `add()`                     | Add new customers    |
| **Read**    | `get(index)`, `contains()`  | Access and search    |
| **Update**  | Iterate + `setBalance()`    | Modify data          |
| **Delete**  | `removeIf()`                | Conditional deletion |
| **Iterate** | For-each loop / `forEach()` | Process elements     |
| **Size**    | `size()`                    | Total customers      |
| **Clear**   | `clear()` + `isEmpty()`     | Remove all           |

 */
package corejava.collections;

import java.util.ArrayList;
import java.util.List;

class Customer {

    private int id;
    private String name;
    private double balance;

    public Customer(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Customer{id=%d, name='%s', balance=%.2f}", id, name, balance);
    }
}

public class ArrayListBankDemo {

    public static void main(String[] args) {
        // ------------------ CREATE ------------------
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer(101, "Alice", 1200.50));
        customers.add(new Customer(102, "Bob", 2500.75));
        customers.add(new Customer(103, "Charlie", 1800.00));

        System.out.println("Initial customer list:");
        System.out.println(customers);

        // ------------------ READ ------------------
        System.out.println("\nFirst customer: " + customers.get(0));
        System.out.println("Contains Bob? " + customers.stream().anyMatch(c -> c.getName().equals("Bob")));

        // ------------------ UPDATE ------------------
        for (Customer c : customers) {
            if (c.getId() == 102) {  // Update Bob's balance
                c.setBalance(c.getBalance() + 500.00);
            }
        }
        System.out.println("\nAfter updating Bob’s balance:");
        customers.forEach(System.out::println);

        // ------------------ DELETE ------------------
        customers.removeIf(c -> c.getName().equals("Charlie"));
        System.out.println("\nAfter deleting Charlie:");
        customers.forEach(System.out::println);

        // ------------------ ITERATE ------------------
        System.out.println("\nIterating using for-each:");
        for (Customer c : customers) {
            System.out.println("Customer Name: " + c.getName() + ", Balance: " + c.getBalance());
        }

        System.out.println("\nIterating using forEach() method reference:");
        customers.forEach(System.out::println);

        // ------------------ SIZE ------------------
        System.out.println("\nTotal customers: " + customers.size());

        // ------------------ CLEAR ------------------
        customers.clear();
        System.out.println("\nAfter clearing list: " + customers);
        System.out.println("Is list empty? " + customers.isEmpty());
    }
}
