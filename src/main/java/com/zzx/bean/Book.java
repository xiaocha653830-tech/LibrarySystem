package com.zzx.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer totalCount;
    private Integer availableCount;


}
