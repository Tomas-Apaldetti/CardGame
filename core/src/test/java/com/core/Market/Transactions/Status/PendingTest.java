package com.core.Market.Transactions.Status;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PendingTest {

    @Test
    public void pendingCanBeApplied() {
        ITransactionStatus status = new Pending();

        assertDoesNotThrow(() -> status.assertCanApply());
    }

    @Test
    public void pendingHasPendingStatus() {
        ITransactionStatus status = new Pending();

        assertEquals(TransactionStatus.PENDING, status.type());
    }

    @Test
    public void pendingNextIsApplied() {
        ITransactionStatus status = new Pending();

        assertEquals(status.next().getClass(), Applied.class);
    }
}
