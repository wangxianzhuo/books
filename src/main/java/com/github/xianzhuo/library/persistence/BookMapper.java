package com.github.xianzhuo.library.persistence;

import com.github.xianzhuo.library.model.Book;
import com.github.xianzhuo.library.model.BookFact;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shangjie on 2017/5/10.
 */
@Repository
public interface BookMapper {
    @Insert("insert into books (id, isbn, name, publisher, authors, created_time)" +
            " values (#{id}, #{isbn}, #{name}, #{publisher}, #{authors}, #{createdTime})")
    void insert(Book book);

    @Update("update books set isbn = #{isbn}, name = #{name}, publisher = #{publisher}, authors = #{authors}," +
            " updated_time = #{updatedTime} where id = #{id}")
    void update(Book book);

    @Delete("delete from books where id = #{id}")
    void delete(@Param("id") String id);

    @Select("select id, isbn, name, publisher, authors, created_time, updated_time from books where id = #{id}")
    @ResultMap("BookResult")
    Book find(@Param("id") String id);

    @Select("select id, isbn, name, publisher, authors, created_time, updated_time from books order by created_time desc")
    @ResultMap("BookResult")
    List<Book> all();

    List<Book> page(long limit, long offset, String q);

    Long count(String q);

    @Insert("insert into book_facts (id, book_id, name, value, created_time) values (#{id}, #{bookId}, #{name}, #{value}, #{createdTime})")
    void insertFact(BookFact fact);

    @Update("update book_facts set value = #{value}, updated_time = now() where id = #{id}")
    void updateFact(BookFact fact);

    @Delete("delete from book_facts where id = #{id}")
    void deleteFact(@Param("id") String id);

    @Delete("delete from book_facts where book_id = #{bookId}")
    void deleteFactsByBookId(@Param("bookId") String bookId);

    @Select("select id, book_id, name, value, created_time, updated_time from book_facts where id = #{id}")
    BookFact findFact(@Param("id") String id);

    @Select("select id, book_id, name, value, created_time, updated_time from book_facts book_id = #{bookId} order by created_time desc")
    List<BookFact> findFactsByBookId(@Param("bookId") String bookId);
}
