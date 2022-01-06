package com.example.hotel.controller;

import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.Response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/search")
    public SimpleResponse search(@RequestParam Long timeStart,
                                 @RequestParam Long timeEnd,
                                 @RequestParam(required = false) ArrayList<String> star,
                                 @RequestParam(required = false) Integer lowerPrice,
                                 @RequestParam(required = false) Integer highPrice,
                                 @RequestParam(required = false) ArrayList<String> roomType,
                                 @RequestParam(required = false) Double rate,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) ArrayList<String> serves) throws ServiceException {
        System.out.println("POST./api/hotel/search?timeStart=" + timeStart + "&timeEnd=" + timeEnd + "&star=" + star
                + "&lowerPrice=" + lowerPrice + "&highPrice=" + highPrice + "&roomType=" + roomType + "&rate=" + rate
                + "&name=" + name + "&serves=" + serves);

        return SimpleResponse.ok(hotelService.findHotelsInCondition(timeStart, timeEnd, star, lowerPrice, highPrice,
                roomType, rate, name ,serves));
    }

    @GetMapping("/detail")
    public SimpleResponse findDetails(@RequestParam Integer hotelId,
                                      @RequestParam(required = false) Integer userId) throws ServiceException{
        System.out.println("GET./api/hotel/detail?hotelId=" + hotelId);
        return SimpleResponse.ok(hotelService.findDetails(hotelId, userId));
    }
    @GetMapping("/all")
    public SimpleResponse findAllHotels(@RequestParam(required = false) Integer userId) throws ServiceException{
        System.out.println("GET./api/hotel/all");
        return SimpleResponse.ok(hotelService.findAllHotels(userId));
    }
}
