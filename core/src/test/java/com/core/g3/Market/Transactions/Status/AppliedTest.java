package com.core.g3.Market.Transactions.Status;

import com.core.g3.Market.Exception.TransactionAlreadyAppliedException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppliedTest {

    @Test
    public void appliedCanNotBeAppliedAgain() {
        ITransactionStatus status = new Applied();

        assertThrows(TransactionAlreadyAppliedException.class, () -> status.assertCanApply());
    }

    @Test
    public void appliedNextIsItself() {
        ITransactionStatus status = new Applied();

        assertEquals(status.next().getClass(), Applied.class);
    }

    @Test
    public void appliedTypeIsTransactionApplied() {
        ITransactionStatus status = new Applied();

        assertEquals(status.type(), TransactionStatus.TRANSACTION_APPLIED);
    }
}
