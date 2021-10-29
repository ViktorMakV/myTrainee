package com.broker.controller;

import com.broker.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Viktor Makarov
 */
@Controller
@RequiredArgsConstructor
public class MainController {
    private final SenderService senderService;

    @RequestMapping("/emit")
    @ResponseBody
    public String queue() {
        senderService.emit();
        return "Emit to queue";
    }
}
