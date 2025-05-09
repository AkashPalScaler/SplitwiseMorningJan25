package scaler.com.splitwise.strategies;

import org.springframework.stereotype.Component;
import scaler.com.splitwise.dtos.Transaction;
import scaler.com.splitwise.models.User;

import java.util.*;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy{

    private class SettleUpTransactionsPair implements Comparator<SettleUpTransactionsPair> {
        User user;
        int amount;

        SettleUpTransactionsPair(User user, int amt){
            this.user = user;
            this.amount = amt;
        }

        @Override
        public int compare(SettleUpTransactionsPair o1, SettleUpTransactionsPair o2) {
            return Integer.compare(o2.amount, o1.amount);
        }
    }



    @Override
    public List<Transaction> settleUp(Map<User, Integer> balanceMap) {
        //Home - work
        System.out.println("Heap settle up algorithm running");

        PriorityQueue<SettleUpTransactionsPair> giver = new PriorityQueue<>();
        PriorityQueue<SettleUpTransactionsPair> receiver = new PriorityQueue<>();

        for(User user : balanceMap.keySet()){
            int balance = balanceMap.get(user);

            if(balance < 0){
                giver.add(new SettleUpTransactionsPair(user,-1 * balance));
            }
            else{
                receiver.add(new SettleUpTransactionsPair(user,balance));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        //Converting the balance map into List of transactions by checking who pays who and how much.
        while(!receiver.isEmpty() && !giver.isEmpty()){
            SettleUpTransactionsPair rec = receiver.poll();
            SettleUpTransactionsPair giv = giver.poll();

            Transaction trx = new Transaction();

            if(rec.amount < giv.amount){
                giver.add(new SettleUpTransactionsPair(giv.user,giv.amount - rec.amount));
                trx.setAmount(rec.amount);
                trx.setHasToPayUserId(giv.user.getId());
                trx.setGetsPaidUserId(rec.user.getId());
                transactions.add(trx);
            }
            else if(rec.amount > giv.amount) {
                receiver.add(new SettleUpTransactionsPair(rec.user, rec.amount - giv.amount));
                trx.setAmount(giv.amount);
                trx.setHasToPayUserId(giv.user.getId());
                trx.setGetsPaidUserId(rec.user.getId());
                transactions.add(trx);
            }
            else{
                trx.setAmount(rec.amount);
                trx.setHasToPayUserId(giv.user.getId());
                trx.setGetsPaidUserId(rec.user.getId());
                transactions.add(trx);
            }

        }


        return transactions;
    }
}
