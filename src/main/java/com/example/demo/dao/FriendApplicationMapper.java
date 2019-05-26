package com.example.demo.dao;

import com.example.demo.model.FriendApplication;
import com.example.demo.model.FriendApplicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendApplicationMapper {
    long countByExample(FriendApplicationExample example);

    int deleteByExample(FriendApplicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FriendApplication record);

    int insertSelective(FriendApplication record);

    List<FriendApplication> selectByExample(FriendApplicationExample example);

    FriendApplication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FriendApplication record, @Param("example") FriendApplicationExample example);

    int updateByExample(@Param("record") FriendApplication record, @Param("example") FriendApplicationExample example);

    int updateByPrimaryKeySelective(FriendApplication record);

    int updateByPrimaryKey(FriendApplication record);
    void insertApplicationSingle(FriendApplication application);
}
