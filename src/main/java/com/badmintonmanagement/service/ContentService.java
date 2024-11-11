package com.badmintonmanagement.service;

import com.badmintonmanagement.entity.Content;
import com.badmintonmanagement.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    public List<Content> getListContents() {
        return contentRepository.findAll();
    }
}
