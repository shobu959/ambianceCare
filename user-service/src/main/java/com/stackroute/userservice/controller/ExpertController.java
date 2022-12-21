package com.stackroute.userservice.controller;

import com.stackroute.userservice.dto.Expert;
import com.stackroute.userservice.exception.ExpertNotFoundException;
import com.stackroute.userservice.service.ExpertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ExpertController {
    @Autowired
     ExpertService expertService;
    @PostMapping("expert")
    public Expert createNewExpert(@RequestBody @Valid Expert expert){
        return this.expertService.createNewExpert(expert);
    }
    @PutMapping("expert")
    public Expert updateExpert(@RequestBody Expert expert){
        return this.expertService.updateExpert(expert);
    }

    @GetMapping("expert/{userEmail}")
    public Expert getExpertByuserEmail(@PathVariable("userEmail")String userEmail) throws ExpertNotFoundException {
        return this.expertService.getExpertByuserEmail(userEmail);
    }
}
