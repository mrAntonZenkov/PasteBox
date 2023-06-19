package com.example.pastebox.api.respponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteBoxUrlResponse {
    private final String url;
}
