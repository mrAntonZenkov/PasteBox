package com.example.pastebox.service;

import com.example.pastebox.api.request.PasteBoxRequest;
import com.example.pastebox.api.request.PublicStatus;
import com.example.pastebox.api.respponse.PasteBoxResponse;
import com.example.pastebox.api.respponse.PasteBoxUrlResponse;
import com.example.pastebox.repository.PasteBoxEntity;
import com.example.pastebox.repository.PasteBoxRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteBoxServiceImpl implements PasteBoxService {

    private String host;
    private int publicListSize;
    private final PasteBoxRepository repository;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntity entity = repository.getHash(hash);
        return new PasteBoxResponse(entity.getData(), entity.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPaste() {
        List<PasteBoxEntity> list = repository.getListOfPublicAndLive(publicListSize);
        return list.stream().map(entity ->
            new PasteBoxResponse(entity.getData(), entity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteBoxUrlResponse create(PasteBoxRequest request) {
        int id = generateId();
        PasteBoxEntity entity = new PasteBoxEntity();
        entity.setData(request.getData());
        entity.setId(id);
        entity.setHash(Integer.toHexString(id));
        entity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        entity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(entity);
        return new PasteBoxUrlResponse(host + "/" + entity.getHash());
    }

    private int generateId() {
        return atomicInteger.getAndIncrement();
    }
}
