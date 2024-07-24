package com.project.demo.web;

// import java.net.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.apache.catalina.connector.Response;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.MediaType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.project.demo.model.Photo;
import com.project.demo.service.PhotozService;

@RestController
public class DownloadController {
  private final PhotozService photozService;

  public DownloadController(PhotozService photozService){
    this.photozService = photozService;
  }

  @GetMapping("/download/{id}")
  public ResponseEntity<byte[]> download(@PathVariable Integer id){
    Photo photo = photozService.get(id);
    if(photo == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    byte[] data = photo.getData();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.valueOf(photo.getContentType()));
    ContentDisposition build = ContentDisposition
      .builder("attachment")
      .filename(photo.getFileName())
      .build();
    headers.setContentDisposition(build);


    return new ResponseEntity<>(data, headers, HttpStatus.OK);
  }
}
