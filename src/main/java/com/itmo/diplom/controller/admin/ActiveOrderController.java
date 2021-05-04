package com.itmo.diplom.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ActiveOrderController {

    @GetMapping("/active")
    public String showAllActiveOrders(){

    }
}
