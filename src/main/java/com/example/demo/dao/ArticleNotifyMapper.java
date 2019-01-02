package com.example.demo.dao;

import com.example.demo.model.ArticleNotify;
import com.example.demo.model.ArticleNotifyExample;
import com.example.demo.model.ArticleNotifyWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleNotifyMapper {
    long countByExample(ArticleNotifyExample example);

    int deleteByExample(ArticleNotifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleNotifyWithBLOBs record);

    int insertSelective(ArticleNotifyWithBLOBs record);

    List<ArticleNotifyWithBLOBs> selectByExampleWithBLOBs(ArticleNotifyExample example);

    List<ArticleNotify> selectByExample(ArticleNotifyExample example);

    ArticleNotifyWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleNotifyWithBLOBs record, @Param("example") ArticleNotifyExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleNotifyWithBLOBs record, @Param("example") ArticleNotifyExample example);

    int updateByExample(@Param("record") ArticleNotify record, @Param("example") ArticleNotifyExample example);

    int updateByPrimaryKeySelective(ArticleNotifyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleNotifyWithBLOBs record);

    int updateByPrimaryKey(ArticleNotify record);
    List<ArticleNotify> findNotify();
    int save(ArticleNotify articleNotify);
    ArticleNotify findById(Integer id);
}