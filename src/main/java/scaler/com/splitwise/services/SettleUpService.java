package scaler.com.splitwise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.stereotype.Service;
import scaler.com.splitwise.dtos.Transaction;
import scaler.com.splitwise.models.*;
import scaler.com.splitwise.repositories.GroupRepository;
import scaler.com.splitwise.strategies.HeapSettleUpStrategy;
import scaler.com.splitwise.strategies.SettleUpStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SettleUpService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private HeapSettleUpStrategy heapSettleUpStrategy;
    public List<Transaction> settleUpGroup(Long groupId){
        //Get the group from groupId
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        //Validate the group
        if(optionalGroup.isEmpty()){
            throw new RuntimeException("Group doesn't exist");
        }
        // Fetch the expenses
        List<Expense> expenses = optionalGroup.get().getExpenses();

        // Sending Expenses to the algorithm directly? FinancialMap/BalanceMap
        Map<User, Integer> balanceMap = new HashMap<>();

        //Generate balanceMap
        for(Expense expense : expenses){
            for(UserExpense userExpense : expense.getUserExpenses()){
                User user = userExpense.getUser();;
                balanceMap.putIfAbsent(user, 0);
                if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID_BY)){
                    balanceMap.put(user, balanceMap.get(user) + userExpense.getAmount());
                }else if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID_FOR)){ // PAID_FOR
                    balanceMap.put(user, balanceMap.get(user) - userExpense.getAmount());
                }
            }
        }


        //Call the heap settle up algorithm with balanceMap
        return heapSettleUpStrategy.settleUp(balanceMap);
    }
}
