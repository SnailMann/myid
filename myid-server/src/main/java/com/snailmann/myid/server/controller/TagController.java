package com.snailmann.myid.server.controller;

import com.snailmann.myid.server.entity.IDGen;
import com.snailmann.myid.server.service.IDGenService;
import com.snailmann.myid.server.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author liwenjie
 */
@Slf4j
@RestController
@RequestMapping("/ms-id-server/v1/tags")
public class TagController {

    private static final int STEP_MIN = 100;

    private final TagService tagService;

    private final IDGenService idGenService;

    public TagController(TagService tagService,
                         IDGenService idGenService) {
        this.tagService = tagService;
        this.idGenService = idGenService;
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String tag,
            @RequestParam(required = false, defaultValue = "1") long sid,
            @RequestParam int step) {
        check(tag, sid, step);
        tagService.register(tag, sid, step);
        return "success";
    }

    @PostMapping("un-register")
    public String unregister(
            @RequestParam String tag,
            @RequestParam String editor) {
        if (StringUtils.isAllBlank(tag, editor)) {
            throw new IllegalArgumentException("illegal params");
        }
        tagService.unregister(tag, editor);
        return "success";
    }

    private void check(String tag, long sid, int step) {

        if (StringUtils.isBlank(tag)) {
            throw new IllegalArgumentException("tag is blank");
        }

        if (containsSpecChar(tag)) {
            throw new IllegalArgumentException(String.format("illegal char [0-9a-zA-Z_], tag: %s", tag));
        }

        if (tag.length() > 50) {
            throw new IllegalArgumentException("tag over 50 char");
        }

        if (sid <= 0) {
            throw new IllegalArgumentException("sid illegal");
        }

        if (step < STEP_MIN || step >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("step too small or big");
        }

        IDGen gen = idGenService.get(tag);
        if (!Objects.isNull(gen)) {
            throw new IllegalArgumentException("tag has been exist");
        }
    }

    private boolean containsSpecChar(String str) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        return !str.matches("[0-9a-zA-z_]+");
    }

}
