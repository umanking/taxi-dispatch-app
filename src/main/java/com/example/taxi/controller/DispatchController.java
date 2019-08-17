package com.example.taxi.controller;

import com.example.taxi.domain.dispatch.Dispatch;
import com.example.taxi.domain.dispatch.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/dispatch")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @GetMapping
    public List<Dispatch> findDispatchList() {
        return dispatchService.findDispatchList();
    }

    @PostMapping
    public Dispatch saveDispatch(@RequestBody String currentAddress) {
        // 주소 validation 100자를 넘기지 않아야한다.
        // TODO 성공일때 담아서 보내야함
        return dispatchService.saveDispatch(currentAddress);
    }

    @PutMapping
    public Dispatch takeTheDispatch(@RequestParam Long id) {
        // TODO 성공일때 담아서 보내야함
        return dispatchService.takeTheDispatch(id);
    }


}
