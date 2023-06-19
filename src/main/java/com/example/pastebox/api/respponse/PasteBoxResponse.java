package com.example.pastebox.api.respponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteBoxResponse {
    private final String data;
    private final boolean isPublic;
}
