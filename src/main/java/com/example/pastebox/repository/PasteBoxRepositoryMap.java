package com.example.pastebox.repository;

import com.example.pastebox.exeption.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PasteBoxRepositoryMap implements PasteBoxRepository {

    private final Map<String, PasteBoxEntity> vault = new ConcurrentHashMap<>();

    @Override
    public PasteBoxEntity getHash(String hash) {
        PasteBoxEntity pasteBoxEntity = vault.get(hash);
        if (pasteBoxEntity == null) {
            throw new NotFoundEntityException("PasteBox not found with hash = " + hash);
        }
        return pasteBoxEntity;
    }

    @Override
    public List<PasteBoxEntity> getListOfPublicAndLive(int amount) {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(PasteBoxEntity::isPublic)
                .filter(paste -> paste.getLifeTime().isAfter(now))
                .sorted(Comparator.comparing(PasteBoxEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteBoxEntity entity) {
        vault.put(entity.getHash(), entity);
    }
}
