package scaler.com.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import scaler.com.splitwise.models.User;

@Getter
@Setter
public class Transaction {
    Long hasToPayUserId;
    Long getsPaidUserId;
    Integer amount;

    public Long getHasToPayUserId() {
        return hasToPayUserId;
    }

    public void setHasToPayUserId(Long hasToPayUserId) {
        this.hasToPayUserId = hasToPayUserId;
    }

    public Long getGetsPaidUserId() {
        return getsPaidUserId;
    }

    public void setGetsPaidUserId(Long getsPaidUserId) {
        this.getsPaidUserId = getsPaidUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

