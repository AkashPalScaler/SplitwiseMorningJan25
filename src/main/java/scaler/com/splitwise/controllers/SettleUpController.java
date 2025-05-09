package scaler.com.splitwise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import scaler.com.splitwise.dtos.ResponseStatus;
import scaler.com.splitwise.dtos.SettleUpGroupRequestDTO;
import scaler.com.splitwise.dtos.SettleUpGroupResponseDTO;
import scaler.com.splitwise.dtos.Transaction;
import scaler.com.splitwise.services.SettleUpService;

import java.util.List;

@Controller
public class SettleUpController {
    @Autowired
    private SettleUpService settleUpService;
    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO settleUpRequestDTO){
        SettleUpGroupResponseDTO settleUpGroupResponseDTO = new SettleUpGroupResponseDTO();
        try{
            Long groupId = settleUpRequestDTO.getGroupId();
            List<Transaction> transactions = settleUpService.settleUpGroup(groupId);
            settleUpGroupResponseDTO.setTransactions(transactions);
            settleUpGroupResponseDTO.setResponseMessage("Successfully settled up");
            settleUpGroupResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);

        }catch(Exception e){
            settleUpGroupResponseDTO.setResponseMessage("Couldn't settled up due to " + e.getMessage());
            settleUpGroupResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return settleUpGroupResponseDTO;
    }
}
