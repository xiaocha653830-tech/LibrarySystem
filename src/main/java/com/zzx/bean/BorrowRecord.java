package com.zzx.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowRecord {

    private Integer id;
    private Integer bookId;
    private Integer readerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}
