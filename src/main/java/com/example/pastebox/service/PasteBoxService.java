package com.example.pastebox.service;

import com.example.pastebox.api.request.PasteBoxRequest;
import com.example.pastebox.api.respponse.PasteBoxResponse;
import com.example.pastebox.api.respponse.PasteBoxUrlResponse;

import java.util.List;

public interface PasteBoxService {
    PasteBoxResponse getByHash(String hash);
    List<PasteBoxResponse> getFirstPublicPaste();
    PasteBoxUrlResponse create(PasteBoxRequest request);
}
