package scaler.com.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private String description;
    private Integer amount;
    // Expense 1:M UserExpense
    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<UserExpense> userExpenses;
    // Ex M:1. G
    @ManyToOne
    private Group group;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<UserExpense> getUserExpenses() {
        return userExpenses;
    }

    public void setUserExpenses(List<UserExpense> userExpenses) {
        this.userExpenses = userExpenses;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
// Fetchtype :  LAZY | EAGER

// Expense e = ExpRepo.findById(1213);
// e.getGroup();
// e.getExpenseUsers(); // Incase of lazy it will fetch the expense users now