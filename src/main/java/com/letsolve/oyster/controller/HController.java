package com.letsolve.oyster.controller;

import com.letsolve.oyster.entity.MiniPainting;
import com.letsolve.oyster.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xavier.qiu
 * 9/16/18 7:34 AM
 */
@Controller
@RequestMapping("second")
public class HController {

    @Autowired
    RedisHelper redisHelper;

    @GetMapping("like")
    public String like(String key, String ipAddr) {
        String value = redisHelper.get(key);
        MiniPainting m = new MiniPainting(value);

        m.setLike(m.getLike() + 1);

        redisHelper.save(key, m.toString());

        return "redirect:/second";
    }

    @GetMapping("dislike")
    public String dislike(String key, String ipAddr) {
        String value = redisHelper.get(key);
        MiniPainting m = new MiniPainting(value);
        m.setDislike(m.getDislike() + 1);

        redisHelper.save(key, m.toString());

        return "redirect:/second";
    }

    @RequestMapping("")
    public String sec(Model model){
        model.addAttribute("list",fileController.gallery());
        return "html2";
    }

    @Autowired
    FileController fileController;
}
