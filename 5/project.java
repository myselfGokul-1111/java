import java.util.*;

// ===== CUSTOM EXCEPTIONS =====
class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) { super(message); }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) { super(message); }
}

class AccountFrozenException extends Exception {
    public AccountFrozenException(String message) { super(message); }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) { super(message); }
}

class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) { super(message); }
}

// ===== INTERFACES =====
interface Freezable {
    void freeze();
    void unfreeze();
    boolean isFrozen();
}

// ===== Transactable Interface =====
interface Transactable {
    void deposit(double amount) throws InvalidAmountException, AccountFrozenException;
    void withdraw(double amount) throws InvalidAmountException, AccountFrozenException, InsufficientFundsException;
    void transfer(Account target, double amount) throws InvalidAmountException, AccountFrozenException, InsufficientFundsException;
}

// Generic interface
interface AccountViewer<T extends Account> {
    List<T> viewAccounts() throws AccountNotFoundException;
}

// ===== Abstract Account Class =====
abstract class Account implements Freezable, Transactable {
    protected String accountNo;
    protected double balance;
    protected String accountType;
    protected boolean frozen;
    protected List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNo, double balance, String accountType) throws InvalidAmountException {
        if (balance < 0) throw new InvalidAmountException("Initial balance cannot be negative");
        this.accountNo = accountNo;
        this.balance = balance;
        this.accountType = accountType;
        this.frozen = false;
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException, AccountFrozenException {
        if (frozen) throw new AccountFrozenException("Account is frozen");
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive");
        balance += amount;
        transactions.add(new Transaction(UUID.randomUUID().toString(), amount, new Date(), "deposit", this));
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, AccountFrozenException, InsufficientFundsException {
        if (frozen) throw new AccountFrozenException("Account is frozen");
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive");
        if (balance < amount) throw new InsufficientFundsException("Insufficient funds");
        balance -= amount;
        transactions.add(new Transaction(UUID.randomUUID().toString(), amount, new Date(), "withdraw", this));
    }

    @Override
    public void transfer(Account target, double amount) throws InvalidAmountException, AccountFrozenException, InsufficientFundsException {
        if (target == null) throw new IllegalArgumentException("Target account cannot be null");
        this.withdraw(amount);
        target.deposit(amount);
    }

    public double getBalance() { return balance; }
    public String getAccountNo() { return accountNo; }
    public String getAccountType() { return accountType; }
    public List<Transaction> getTransactions() { return transactions; }

    public String getAccountInfo() {
        return "AccountNo: " + accountNo + ", Type: " + accountType + ", Balance: " + balance + ", Frozen: " + frozen;
    }

    @Override
    public void freeze() { frozen = true; }
    @Override
    public void unfreeze() { frozen = false; }
    @Override
    public boolean isFrozen() { return frozen; }
}

// ===== SavingsAccount Subclass =====
class SavingsAccount extends Account {
    private double interestRate;
    public SavingsAccount(String accountNo, double balance, double interestRate) throws InvalidAmountException {
        super(accountNo, balance, "Savings");
        this.interestRate = interestRate;
    }
    public void calculateInterest() throws InvalidAmountException, AccountFrozenException {
        double interest = balance * interestRate / 100;
        deposit(interest);
    }
}

// ===== CurrentAccount Subclass =====
class CurrentAccount extends Account {
    private double overdraftLimit;
    public CurrentAccount(String accountNo, double balance, double overdraftLimit) throws InvalidAmountException {
        super(accountNo, balance, "Current");
        this.overdraftLimit = overdraftLimit;
    }
    @Override
    public void withdraw(double amount) throws InvalidAmountException, AccountFrozenException, InsufficientFundsException {
        if (isFrozen()) throw new AccountFrozenException("Account is frozen");
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive");
        if (balance + overdraftLimit < amount) throw new InsufficientFundsException("Overdraft limit exceeded");
        balance -= amount;
        transactions.add(new Transaction(UUID.randomUUID().toString(), amount, new Date(), "withdraw", this));
    }
    public boolean checkOverdraft() { return balance < 0; }
}

// ===== Transaction Class =====
class Transaction {
    private String transactionId;
    private double amount;
    private Date date;
    private String type;
    private Account account;

    public Transaction(String transactionId, double amount, Date date, String type, Account account) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.account = account;
    }
    @Override
    public String toString() {
        return "TransactionID: " + transactionId + ", Type: " + type + ", Amount: " + amount + ", Date: " + date;
    }
}

// ===== Customer Class =====
class Customer implements AccountViewer<Account> {
    private String customerId;
    private String name;
    private String address;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String customerId, String name, String address) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
    }

    public void openAccount(Account account) { accounts.add(account); }
    @Override
    public List<Account> viewAccounts() throws AccountNotFoundException {
        if (accounts.isEmpty()) throw new AccountNotFoundException("No accounts found for customer: " + name);
        return accounts;
    }
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }

    public List<Transaction> viewTransactionHistory(Account account) throws AccountNotFoundException {
        if (!accounts.contains(account)) throw new AccountNotFoundException("Account not owned by this customer.");
        return account.getTransactions();
    }
    public String getAccountDetails(Account account) throws AccountNotFoundException {
        if (!accounts.contains(account)) throw new AccountNotFoundException("Account not owned by this customer.");
        return account.getAccountInfo();
    }
}

// ===== Branch Class =====
class Branch implements AccountViewer<Account> {
    private String branchName;
    private String location;
    private List<Account> accounts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public Branch(String branchName, String location) {
        this.branchName = branchName;
        this.location = location;
    }

    public String getBranchName() { return branchName; }
    public void addAccount(Account account) { accounts.add(account); }
    public void addCustomer(Customer customer) { customers.add(customer); }

    public List<Customer> getCustomers() { return customers; }
    @Override
    public List<Account> viewAccounts() throws AccountNotFoundException {
        if (accounts.isEmpty()) throw new AccountNotFoundException("No accounts found in branch: " + branchName);
        return accounts;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        for (Account acc : accounts) allTransactions.addAll(acc.getTransactions());
        return allTransactions;
    }
}

// ===== Bank Class =====
class Bank {
    private String bankName;
    private String bankAddress;
    private List<Branch> branches = new ArrayList<>();

    public Bank(String bankName, String bankAddress) {
        this.bankName = bankName;
        this.bankAddress = bankAddress;
    }

    public void addBranch(Branch branch) { branches.add(branch); }
    public List<Branch> getBranches() { return branches; }

    public Branch selectBranch(String branchName) throws AccountNotFoundException {
        for (Branch branch : branches)
            if (branch.getBranchName().equalsIgnoreCase(branchName)) return branch;
        throw new AccountNotFoundException("Branch not found: " + branchName);
    }

    public Customer selectCustomer(Branch branch, String customerId) throws CustomerNotFoundException {
        for (Customer customer : branch.getCustomers())
            if (customer.getCustomerId().equals(customerId)) return customer;
        throw new CustomerNotFoundException("Customer not found: " + customerId);
    }

    public List<Transaction> viewBranchTransactions(Branch branch) { return branch.getAllTransactions(); }
}

// ===== Clerk Class =====
class Clerk {
    private String clerkId;
    private String name;
    public Clerk(String clerkId, String name) {
        this.clerkId = clerkId;
        this.name = name;
    }
    public Customer createCustomer(String customerId, String customerName, String address, Branch branch) {
        Customer newCustomer = new Customer(customerId, customerName, address);
        branch.addCustomer(newCustomer);
        return newCustomer;
    }
}

// ===== Manager Class =====
class Manager implements AccountViewer<Account> {
    private String managerId;
    private String name;
    private Bank bank;
    public Manager(String managerId, String name, Bank bank) {
        this.managerId = managerId;
        this.name = name;
        this.bank = bank;
    }
    @Override
    public List<Account> viewAccounts() throws AccountNotFoundException {
        List<Account> all = new ArrayList<>();
        for (Branch branch : bank.getBranches()) all.addAll(branch.viewAccounts());
        if (all.isEmpty()) throw new AccountNotFoundException("No accounts found in the bank.");
        return all;
    }
}