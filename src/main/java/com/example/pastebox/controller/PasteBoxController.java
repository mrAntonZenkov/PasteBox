package com.example.pastebox.controller;

import com.example.pastebox.api.request.PasteBoxRequest;
import com.example.pastebox.api.respponse.PasteBoxResponse;
import com.example.pastebox.api.respponse.PasteBoxUrlResponse;
import com.example.pastebox.service.PasteBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {

    private final PasteBoxService pasteBoxService;

    @GetMapping("/")
    public Collection<PasteBoxResponse> getPublicPasteList() {
        return pasteBoxService.getFirstPublicPaste();
    }

    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {
        return pasteBoxService.getByHash(hash);
    }
    @PostMapping("/")
    public PasteBoxUrlResponse add(@RequestBody PasteBoxRequest request){
        return pasteBoxService.create(request);
    }
}
