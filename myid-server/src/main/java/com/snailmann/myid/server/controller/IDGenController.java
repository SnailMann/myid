package com.snailmann.myid.server.controller;

import com.snailmann.myid.common.model.dto.IDGenDTO;
import com.snailmann.myid.server.entity.IDGen;
import com.snailmann.myid.server.service.IDGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * for local client
 *
 * @author liwenjie
 */
@Slf4j
@RestController
@RequestMapping("/myid-server/v1/id-gens")
public class IDGenController {

    private final IDGenService idGenService;

    public IDGenController(IDGenService idGenService) {
        this.idGenService = idGenService;
    }

    @PostMapping("/alloc")
    public IDGenDTO alloc(@RequestParam String tag) {
        IDGen idGen = idGenService.alloc(tag);
        return toDTO(idGen);
    }

    @GetMapping
    public IDGenDTO get(@RequestParam String tag) {
        IDGen idGen = idGenService.get(tag);
        return toDTO(idGen);
    }

    private IDGenDTO toDTO(IDGen idGen) {
        if (Objects.isNull(idGen)) {
            return null;
        }

        return IDGenDTO.builder()
                .id(idGen.getId())
                .tag(idGen.getTag())
                .sid(idGen.getSid())
                .mid(idGen.getMid())
                .step(idGen.getStep())
                .version(idGen.getVersion())
                .editor(idGen.getEditor())
                .creation(idGen.getCreation().getTime())
                .modification(idGen.getModification().getTime())
                .build();
    }

}
