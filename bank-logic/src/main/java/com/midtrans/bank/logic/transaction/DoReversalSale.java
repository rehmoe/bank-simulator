package com.midtrans.bank.logic.transaction;

import com.midtrans.bank.core.model.Transaction;
import com.midtrans.bank.core.transaction.BankTxnSupport;
import org.jpos.transaction.Context;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: shaddiqa
 * Date: 9/9/13
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoReversalSale extends BankTxnSupport {
    @Override
    protected int doPrepare(long id, Context ctx) throws Exception {
        Transaction txn = (Transaction) ctx.get(TXN);

        Date now = new Date();
        String authId = "";

        ctx.put(AUTHORIZATION_ID, authId);
        ctx.put(TXN_TIME, now);

        txn.setReversal(true);

        ctx.put(VALAFTER, txn.calcSettleAmount());

        ctx.put(TXN, txn);

        return PREPARED | NO_JOIN;
    }
}
