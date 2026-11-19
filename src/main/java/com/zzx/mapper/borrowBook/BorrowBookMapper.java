package com.zzx.mapper.borrowBook;



/**
 * 借书接口：传入读者 ID 和图书 ID。需要校验：图书库存是否 > 0、读者是否存在、该读者是否已经借过这本书且未还。
 * 校验通过后，库存减 1，生成一条借阅记录。
 */

public interface BorrowBookMapper {


}
