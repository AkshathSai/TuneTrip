package com.tunetrip.controller;

import com.tunetrip.service.YouTube;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final YouTube youTube;

    public WebController(YouTube youTube) {
        this.youTube = youTube;
    }

    @GetMapping
    public String index(Model model) {
        //System.out.println(youTube.channelVideos("UCVVgeban0nTK3mZW5LhINEw", 10, YouTube.Sort.VIEW_COUNT));
        model.addAttribute("channelVideos", youTube.channelVideos("UCVVgeban0nTK3mZW5LhINEw", 10, YouTube.Sort.VIEW_COUNT));
        return "index";
    }

}
