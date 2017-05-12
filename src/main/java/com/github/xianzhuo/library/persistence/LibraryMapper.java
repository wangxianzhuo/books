package com.github.xianzhuo.library.persistence;

import com.github.xianzhuo.library.model.Library;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shangjie on 2017/5/10.
 */
@Repository
public interface LibraryMapper {
    @Insert("insert into libraries (id, name, parent_id, weight, capacity, size, created_time)" +
            " values (#{id}, #{name}, #{parentId}, #{weight}, #{capacity}, #{size}, #{createdTime})")
    void insert(Library library);

    @Update("update libraries set name = #{name}, parent_id = #{parentId}, weight = #{weight}, capacity = #{capacity}, size = #{size}," +
            " updated_time = #{updatedTime} where id = #{id}")
    void update(Library library);

    @Delete("delete from libraries where id = #{id}")
    void delete(@Param("id") String id);

    @Select("select id, name, parent_id, weight, capacity, size, created_time, updated_time from libraries where id = #{id}")
    @ResultMap("LibraryResult")
    Library find(@Param("id") String id);

    @Select("select id, name, parent_id, weight, capacity, size, created_time, updated_time from libraries order by created_time desc")
    @ResultMap("LibraryResult")
    List<Library> all();
}
