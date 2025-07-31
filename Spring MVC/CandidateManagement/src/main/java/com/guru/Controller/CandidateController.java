package com.guru.Controller;

import com.guru.Model.Candidate;
import com.guru.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class CandidateController {
    private CandidateService candidateService;
    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @PostMapping("/add")
    public ModelAndView addCandidate(@ModelAttribute Candidate candidate) {
        ModelAndView mv = new ModelAndView();
        candidateService.addCandidate(candidate);
        mv.setViewName("CandidateAddSuccess");
        return mv;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addCandidateForm");
        return mv;
    }

    @GetMapping("/getCandidate/{id}")
    public ModelAndView getCandidate( @PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        Candidate candidate=candidateService.getCandidateByID(id);
        mv.addObject("candidate",candidate);
        mv.setViewName("CandidateView");
        return mv;
    }
}
