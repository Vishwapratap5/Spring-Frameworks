package com.guru.Controller;

import com.guru.Model.Candidate;
import com.guru.Service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class CandidateController {
    private CandidateService candidateService;
    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView MAV = new ModelAndView();
        MAV.addObject("candidate", new Candidate());
        MAV.setViewName("AddCandidateForm");
        return MAV;
    }

    @PostMapping("/AddCandidate")
    public ModelAndView addCandidate(@Valid @ModelAttribute("candidate") Candidate candidate, BindingResult result) {
        ModelAndView MAV = new ModelAndView();

        if (result.hasErrors()) {
            MAV.setViewName("AddCandidateForm");
            return MAV; // ✅ Stop execution here
        }

        Candidate c = candidateService.addCandidate(candidate);
        MAV.addObject("candidate", c);
        MAV.setViewName("AddCandidateSuccess");
        return MAV;
    }

}
