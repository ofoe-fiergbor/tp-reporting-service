package com.group19.reportingservice.service;

import com.group19.reportingservice.domain.dto.ResponseDto;
import com.group19.reportingservice.domain.model.Order;
import com.group19.reportingservice.domain.model.Portfolio;
import com.group19.reportingservice.domain.model.User;
import com.group19.reportingservice.domain.repository.OrderRepo;
import com.group19.reportingservice.domain.repository.PortfolioRepo;
import com.group19.reportingservice.domain.repository.UserRepo;
import com.group19.reportingservice.enums.ResponseDTOStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Service
public class ReportService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseDto fetchAllOrders(){
        if (orderRepo.findAll().isEmpty()){
            return new ResponseDto(ResponseDTOStatus.FAILED,"No orders available");
        }
        return new ResponseDto(ResponseDTOStatus.SUCCESS,"all orders", orderRepo.findAll());
    }

    public ResponseDto fetchOrderStatus(String orderId){
        return orderRepo.findById(orderId).
                map(order -> new ResponseDto(ResponseDTOStatus.SUCCESS,
                            String.format("Order %s present",orderId), order.getStatus()))
                .orElseGet(() -> new ResponseDto(ResponseDTOStatus.FAILED, String.format("Order %s missing",orderId)));
    }

//    public ResponseDto fetchAllOrdersByStatus(String status){
//        return new ResponseDto(ResponseDTOStatus.SUCCESS,
//                String.format("Order with status -  %s",status), orderRepo.findAll().stream()
//                        .filter(o-> o.getStatus().name().equalsIgnoreCase(status)).toList());
//    }

    public ResponseDto fetchPortfolios(){
    return new ResponseDto(ResponseDTOStatus.SUCCESS,
            "Portfolio available", portfolioRepo.findAll());
    }

    public ResponseDto fetchPortfoliosByUserId(Long userId) {
        return  new ResponseDto(ResponseDTOStatus.SUCCESS,
                String.format("Portfolios available for the user with id : %s",userId),
                portfolioRepo.findAll().stream().filter(
                        p->p.getUser().getId().equals(userRepo.findById(userId).get().getId()))
                        .toList()
        );

    }

    public ResponseDto fetchAllOrdersByCustomField(String custom){
        return switch (custom) {
            case "buy", "sell" -> new ResponseDto(ResponseDTOStatus.SUCCESS,
                    String.format("Orders with side %s:", custom),
                    orderRepo.findAll().stream().filter(o -> o.getSide().name().equalsIgnoreCase(custom)));
            case "open", "closed", "cancelled" -> new ResponseDto(ResponseDTOStatus.SUCCESS,
                    String.format("Orders with side %s:", custom),
                    orderRepo.findAll().stream().filter(o -> o.getStatus().name().equalsIgnoreCase(custom)));
            default -> fetchAllOrders();
        };
    }



}
