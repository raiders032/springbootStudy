package org.example.springbootstudy.web;

import org.example.springbootstudy.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto hellDto(@RequestParam String name,@RequestParam int amount){
        return new HelloResponseDto(name, amount);
    }
}