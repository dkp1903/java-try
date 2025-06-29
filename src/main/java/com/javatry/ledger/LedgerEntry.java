package com.javatry.ledger;

public abstract class LedgerEntry {
    protected final String eventId;
    protected final String accountId;
    protected final Double amount;
    protected final String transactionId;

    public LedgerEntry(String eventId, String accountId, Double amount, String transactionId) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    // Optional: common utility method
    public String summary() {
        return String.format("Txn [%s]: %s → %.2f", transactionId, accountId, amount);
    }
}
