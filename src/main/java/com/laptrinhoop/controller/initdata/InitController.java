package com.laptrinhoop.controller.initdata;


import com.laptrinhoop.common.ProductSE;
import com.laptrinhoop.service.CodeTimer;
import com.laptrinhoop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
    @Autowired
    IProductService productService;
    @Autowired
    ProductSE productSE;

    @GetMapping("/api/initproduct")
    public String initProduct(int id, int dataCenter){
        var status = HttpStatus.OK;
        var message = "";
        try {

            message = productSE.refresh(id,productService,dataCenter);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message="CAN'T INIT, CHECK !!!";

        }
        return message;
    }

}
