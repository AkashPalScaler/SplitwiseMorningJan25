package scaler.com.splitwise.strategies;

import org.springframework.stereotype.Component;
import scaler.com.splitwise.dtos.Transaction;
import scaler.com.splitwise.models.Group;
import scaler.com.splitwise.models.User;

import java.util.List;
import java.util.Map;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(Map<User, Integer> balanceMap) {
        //Home - work
        System.out.println("Heap settle up algorithm running");
        return List.of();
    }
}
