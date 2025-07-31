package com.guru.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class CandidateController {

    @GetMapping("/add")
    public ModelAndView addCandidate(){
        ModelAndView mv = new ModelAndView("addCandidate");
        mv.setViewName("addCandidate");
        return mv;
    }
}
