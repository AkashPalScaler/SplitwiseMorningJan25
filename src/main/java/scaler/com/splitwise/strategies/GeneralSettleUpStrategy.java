package scaler.com.splitwise.strategies;

import scaler.com.splitwise.dtos.Transaction;
import scaler.com.splitwise.models.Group;
import scaler.com.splitwise.models.User;

import java.util.List;
import java.util.Map;

public class GeneralSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(Map<User, Integer> balanceMap) {
        return List.of();
    }
}
