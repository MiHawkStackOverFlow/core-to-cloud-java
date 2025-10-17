package corejava.oops;

// Abstraction: Account defines the "what", not the "how"
abstract class Account {
  // Encapsulation: private fields, accessible only through methods
  private double balance;

  public Account(double initialBalance) {
    this.balance = initialBalance;
  }

  // Encapsulated accessors
  public double getBalance() {
    return balance;
  }

  protected void setBalance(double balance) {
    this.balance = balance;
  }

  // Abstract method forces subclass to define withdrawal logic
  public abstract void withdraw(double amount);

  // Concrete method for common behavior
  public void deposit(double amount) {
    if (amount <= 0)
      throw new IllegalArgumentException("Deposit amount must be positive");
    this.balance += amount;
  }
}

// Inheritance: specific account types extend the abstract base
class SavingsAccount extends Account {
  private static final double MIN_BALANCE = 500;

  public SavingsAccount(double balance) {
    super(balance);
  }

  @Override
  public void withdraw(double amount) throws InsufficientBalanceException {
    if (getBalance() - amount < MIN_BALANCE) {
      throw new InsufficientBalanceException(
          "Withdrawal denied: minimum balance of ₹" + MIN_BALANCE + " must be maintained.");
    }
    setBalance(getBalance() - amount);
  }
}

class CurrentAccount extends Account {
  public CurrentAccount(double balance) {
    super(balance);
  }

  @Override
  public void withdraw(double amount) {
    setBalance(getBalance() - amount);
  }
}

// Polymorphism: same interface, different runtime behavior
public class BankDemo {
  public static void main(String[] args) {
    Account acc1 = new SavingsAccount(1000);
    Account acc2 = new CurrentAccount(1000);

    try {
      acc1.withdraw(600); // should throw exception
    } catch (InsufficientBalanceException e) {
      System.err.println("Transaction failed for SavingsAccount: " + e.getMessage());
    }

    try {
      acc2.withdraw(600); // succeeds. CurrentAccount has no min balance rule
    } catch (InsufficientBalanceException e) {
      System.err.println("Transaction failed for CurrentAccount: " + e.getMessage());
    }

    System.out.println("Savings Balance: " + acc1.getBalance());
    System.out.println("Current Balance: " + acc2.getBalance());
  }
}
