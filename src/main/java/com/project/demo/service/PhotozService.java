package com.project.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.model.Photo;
import com.project.demo.repository.PhotozRepository;

// @Component
@Service
public class PhotozService {
  
  //   private Map<String, Photo> db = new HashMap<>(){{
  //   put("1", new Photo("1", "hello.jpg"));
  // }};

  private final PhotozRepository photozRepository;

  public PhotozService(PhotozRepository photozRepository) {
    this.photozRepository = photozRepository;
  }

  public Iterable<Photo> get() {
    return photozRepository.findAll();
  }

  public Photo get(Integer id) {
    return photozRepository.findById(id).orElse(null);
  }

  public Photo save(String fileName, String contentType, byte[] data) {
    Photo photo = new Photo();

    photo.setContentType(contentType);
    photo.setFileName(fileName);
    photo.setData(data);
    photozRepository.save(photo);
    return photo;
  }

  public void remove(Integer id) {
    photozRepository.deleteById(id);
  }
}
