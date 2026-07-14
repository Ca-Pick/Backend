package com.swyp.BE.domain.save.controller;


import com.swyp.BE.global.documention.SaveApiDocumentation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/save")
@RequiredArgsConstructor
public class SaveController {



    @SaveApiDocumentation.SavePostDoc
    @PostMapping("/{referenceId}/like")
    public void createsave(@PathVariable int referenceId) {

    }
}
