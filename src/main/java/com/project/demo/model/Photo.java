package com.project.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Lob;

@Table(name="PHOTOZ")
public class Photo {
  
  @Id
  private Integer id;

  @NotEmpty
  private String fileName;

  private String contentType;

  @Lob
  @JsonIgnore
  private byte[] data;

  public Photo(){

  }

  public byte [] getData(){
    return data;
  }

  public void setData(byte[] data){
    this.data = data;
  }

  public String getContentType() {
    return contentType;
  }

  public Integer getId(){
    return id;
  }

  public String getFileName(){
    return fileName;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setFileName(String originalFilename) {
    this.fileName = originalFilename;
  }

  public void setContentType(String contentType2) {
    this.contentType = contentType2;
  }
}
