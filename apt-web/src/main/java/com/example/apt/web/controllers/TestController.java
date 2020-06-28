package com.example.apt.web.controllers;

import com.example.apt.web.models.CarQo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author myou
 * @Date 2020/6/28  4:12 下午
 */
@RestController
@RequestMapping("/car")
public class TestController {

    @PostMapping
    public void testPostValid(@Valid @RequestBody CarQo carQo) {
        System.out.println(carQo.toString());
    }

    @PatchMapping
    public void testPatchValid(@RequestBody CarQo carQo) {
        System.out.println(carQo.toString());
    }
}
