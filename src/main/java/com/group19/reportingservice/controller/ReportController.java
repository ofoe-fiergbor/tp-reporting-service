package com.group19.reportingservice.controller;

import com.group19.reportingservice.domain.dto.ResponseDto;
import com.group19.reportingservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.Param;

@Controller
@RequestMapping(value ="/reporting-service")
public class ReportController {

    @Autowired
    private ReportService reportingService;


    @GetMapping("/all")
    public ResponseEntity<ResponseDto> fetchOrders(){
        return new ResponseEntity<>(reportingService.fetchAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orderbook/product/")
    public ResponseEntity<ResponseDto>  fetchAllOrders(@RequestParam("status") String status){
        return new ResponseEntity<>(reportingService.fetchAllOrdersByCustomField(status),HttpStatus.FOUND);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<ResponseDto>  fetchAllOrders(Long orderId){
        return new ResponseEntity<>(reportingService.fetchOrderStatus(String.valueOf(orderId)),HttpStatus.FOUND);
    }

    @GetMapping("/portfolios/")
    public ResponseEntity<ResponseDto>  fetchAllPortfolios(){
        return new ResponseEntity<>(reportingService.fetchPortfolios(),HttpStatus.FOUND);
    }

    @GetMapping("/portfolios/{id}")
    public ResponseEntity<ResponseDto> fetchPortfoliosByUserId(Long userId){
            return new ResponseEntity<>(reportingService.fetchPortfoliosByUserId(userId),HttpStatus.FOUND);
    }

}
