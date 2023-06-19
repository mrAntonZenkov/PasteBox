package com.example.pastebox.repository;

import java.util.List;

public interface PasteBoxRepository {
    PasteBoxEntity getHash(String hash);

    List<PasteBoxEntity> getListOfPublicAndLive(int amount);

    void add(PasteBoxEntity entity);
}
