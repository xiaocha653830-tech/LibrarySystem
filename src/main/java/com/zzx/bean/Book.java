package com.zzx.bean;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {


    private Integer id;

    @NotBlank(message = "ISBN不能为空")
    private String isbn;

    @NotBlank(message = "图书名称不能为空")
    private String title;

    private String author;
    private String publisher;

    @NotNull(message = "馆藏数量不能为空")
    @Min(value = 0, message = "馆藏数量不能小于0")
    private Integer totalCount;

    private Integer availableCount;


}
