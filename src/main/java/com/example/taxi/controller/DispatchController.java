package com.example.taxi.controller;

import com.example.taxi.domain.dispatch.DispatchService;
import com.example.taxi.dto.DispatchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/dispatch")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @GetMapping
    public List<DispatchDto.DispatchResponse> findDispatchList() {
        return dispatchService.findDispatchList();

    }

    @PostMapping
    public DispatchDto.DispatchResponse saveDispatch(@RequestBody String currentAddress) {
        return dispatchService.saveDispatch(currentAddress);
    }

    @PutMapping
    public DispatchDto.DispatchResponse takeTheDispatch(@RequestParam Long id) {
        return dispatchService.takeTheDispatch(id);
    }


}
