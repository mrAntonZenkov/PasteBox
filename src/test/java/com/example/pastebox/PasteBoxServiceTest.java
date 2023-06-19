package com.example.pastebox;

import com.example.pastebox.api.respponse.PasteBoxResponse;
import com.example.pastebox.exeption.NotFoundEntityException;
import com.example.pastebox.repository.PasteBoxEntity;
import com.example.pastebox.repository.PasteBoxRepository;
import com.example.pastebox.service.PasteBoxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteBoxServiceTest {

    @Autowired
    private PasteBoxService pasteBoxService;

    @MockBean
    private PasteBoxRepository pasteBoxRepository;

    @Test
    public void notExistHash(){
        when(pasteBoxRepository.getHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pasteBoxService.getByHash("fghfg"));
    }

    @Test
    public void getExistHash(){
        PasteBoxEntity entity = new PasteBoxEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(pasteBoxRepository.getHash("1")).thenReturn(entity);

        PasteBoxResponse expected = new PasteBoxResponse("11", true);
        PasteBoxResponse actual = pasteBoxService.getByHash("1");

        assertEquals(expected, actual);
    }
}
