package scaler.com.splitwise.strategies;

import scaler.com.splitwise.dtos.Transaction;
import scaler.com.splitwise.models.Group;
import scaler.com.splitwise.models.User;

import java.util.*;

public class GeneralSettleUpStrategy implements SettleUpStrategy{

    private class SettleUpTransactionsPair implements Comparable<SettleUpTransactionsPair> {
        User user;
        int amount;

        SettleUpTransactionsPair(User user, int amt){
            this.user = user;
            this.amount = amt;
        }

        @Override
        public int compareTo(SettleUpTransactionsPair o) {
            return Integer.compare(this.amount, o.amount);
        }
    }
    @Override
    public List<Transaction> settleUp(Map<User, Integer> balanceMap) {

        ArrayList<SettleUpTransactionsPair> inputTrxList = new ArrayList<>();

        List<Transaction> trxListResponse = new ArrayList<>();

        //Adding all exchanges into a list to be sorted by amount
        for(User user : balanceMap.keySet()){
            inputTrxList.add(new SettleUpTransactionsPair(user,balanceMap.get(user)));
        }

        //Sorting the input list based on amounts
        Collections.sort(inputTrxList);

        int trxAmount = 0;
        int currentAmount = 0;

        if(!inputTrxList.isEmpty()) {
            currentAmount = inputTrxList.get(0).amount;
        }

        //Transferring the amount from one person to next
        for(int i = 1;i<inputTrxList.size();i++){

            User hasToPayUser = inputTrxList.get(i-1).user;
            User getsPaidUser = inputTrxList.get(i).user;
            trxAmount = Math.abs(currentAmount);
            currentAmount = inputTrxList.get(i).amount;

            Transaction trx = new Transaction();
            trx.setHasToPayUserId(hasToPayUser.getId());
            trx.setGetsPaidUserId(getsPaidUser.getId());
            trx.setAmount(trxAmount);
            trxListResponse.add(trx);
        }

        return trxListResponse;
    }
}
