package com.mathartsys.demo.controller;

import com.mathartsys.demo.common.ServerResponse;
import com.mathartsys.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/max_price_bt")
    @ResponseBody
    public ServerResponse  selectMaxPriceBt(@RequestParam("productId") String productId,@RequestParam("start") String start, @RequestParam("end")String end){
        return orderService.findMaxPriceBt(productId,start,end);
    }

    @GetMapping("/min_price_bt")
    @ResponseBody
    public ServerResponse  selectMinPriceBt(@RequestParam("productId") String productId,@RequestParam("start") String start, @RequestParam("end")String end){
        return orderService.findMinPriceBt(productId, start, end);
    }

    @GetMapping("/avg_price_bt")
    @ResponseBody
    public ServerResponse  selectAvgPriceBt(@RequestParam("productId") String productId,@RequestParam("start") String start, @RequestParam("end")String end){
        return orderService.findAvgPriceBt(productId, start, end);
    }

    @GetMapping("/month_price")
    @ResponseBody
    public ServerResponse selectMonthPrice(@RequestParam("productId") String productId){
        return orderService.findMonthPrice(productId);
    }

    @GetMapping(value = "/max_month")
    @ResponseBody
    public ServerResponse selectMaxMonth(@RequestParam("productId") String productId){
        return orderService.findMaxMonth(productId);
    }

    @GetMapping("/min_month")
    @ResponseBody
    public ServerResponse  selectMinMonth(@RequestParam("productId") String productId){
        return orderService.findMinMonth(productId);
    }

    @GetMapping("/month_bounce")
    @ResponseBody
    public ServerResponse  selectMonth_BounceByYear(@RequestParam("productId") String productId){
        return orderService.findMonthBounce(productId);
    }
}
