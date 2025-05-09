package scaler.com.splitwise.commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scaler.com.splitwise.controllers.SettleUpController;
import scaler.com.splitwise.dtos.ResponseStatus;
import scaler.com.splitwise.dtos.SettleUpGroupRequestDTO;
import scaler.com.splitwise.dtos.SettleUpGroupResponseDTO;
import scaler.com.splitwise.dtos.Transaction;

import java.util.List;

@Component
public class SettleUpGroupCommand implements Command{
    @Autowired
    private SettleUpController settleUpController;
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        return (words.size() == 2 && words.get(0).equalsIgnoreCase("settleupgroup"));
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        Long groupId = Long.parseLong(words.get(1));

        SettleUpGroupRequestDTO settleUpGroupRequestDTO = new SettleUpGroupRequestDTO();
        settleUpGroupRequestDTO.setGroupId(groupId);

        SettleUpGroupResponseDTO responseDTO = settleUpController.settleUpGroup(settleUpGroupRequestDTO);
        if(responseDTO.getResponseStatus().equals(ResponseStatus.SUCCESS)){
            for(Transaction transaction : responseDTO.getTransactions()){
                System.out.println(transaction.getHasToPayUserId() + " has to pay " + transaction.getAmount() + " to " + transaction.getGetsPaidUserId());
            }
        }else {
            System.out.println(responseDTO.getResponseMessage());
        }


    }
}
