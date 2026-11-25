package com.zzx.mapper.borrowBook;


import com.zzx.bean.Book;
import com.zzx.bean.BorrowRecord;
import com.zzx.bean.Reader;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 借书接口：传入读者 ID 和图书 ID。需要校验：图书库存是否 > 0、读者是否存在、该读者是否已经借过这本书且未还。
 * 校验通过后，库存减 1，生成一条借阅记录。
 */

public interface BorrowBookMapper {

    // 新增借阅记录
    @Insert("""
    INSERT INTO borrow_record
    (book_id, reader_id, borrow_date, due_date, return_date)
    VALUES
    (#{bookId}, #{readerId}, #{borrowDate}, #{dueDate}, NULL)
    """)
    int insertBorrowRecord(BorrowRecord borrowRecord);

    // 查询读者尚未归还的借阅记录
    @Select("""
        SELECT *
        FROM borrow_record
        WHERE reader_id = #{readerId}
          AND return_date IS NULL
        """)
    List<BorrowRecord> getUnreturnedRecords(Integer readerId);

    // 更新归还日期
    @Update("""
        UPDATE borrow_record
        SET return_date = #{returnDate}
        WHERE id = #{id}
          AND return_date IS NULL
        """)
    int updateReturnDate(
            @Param("id") Integer id,
            @Param("returnDate") String returnDate
    );


    // 查询某个读者的借阅记录
    @Select("""
        SELECT *
        FROM borrow_record
        WHERE reader_id = #{readerId}
        """)
    List<BorrowRecord> getByReaderId(Integer readerId);

    // 查询某本书的借阅记录
    @Select("""
        SELECT *
        FROM borrow_record
        WHERE book_id = #{bookId}
        """)
    List<BorrowRecord> getByBookId(Integer bookId);

    @Select("""
    SELECT COUNT(*)
    FROM borrow_record
    WHERE book_id = #{bookId}
      AND reader_id = #{readerId}
      AND return_date IS NULL
    """)
    int countUnreturnedRecord(
            @Param("bookId") Integer bookId,
            @Param("readerId") Integer readerId
    );
}
