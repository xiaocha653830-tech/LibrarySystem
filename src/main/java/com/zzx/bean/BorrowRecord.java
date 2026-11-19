package com.zzx.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowRecord {

    private int id;
    private int bookId;
    private int readerId;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
}
