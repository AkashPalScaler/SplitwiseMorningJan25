package scaler.com.splitwise.strategies;

import scaler.com.splitwise.dtos.Transaction;
import scaler.com.splitwise.models.Group;
import scaler.com.splitwise.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {
    public List<Transaction> settleUp(Map<User, Integer> balanceMap);
}
