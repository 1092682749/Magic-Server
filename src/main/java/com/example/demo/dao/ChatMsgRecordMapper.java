package com.example.demo.dao;

import com.example.demo.model.ChatMsgRecord;
import com.example.demo.model.ChatMsgRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatMsgRecordMapper {
    long countByExample(ChatMsgRecordExample example);

    int deleteByExample(ChatMsgRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChatMsgRecord record);

    int insertSelective(ChatMsgRecord record);

    List<ChatMsgRecord> selectByExampleWithBLOBs(ChatMsgRecordExample example);

    List<ChatMsgRecord> selectByExample(ChatMsgRecordExample example);

    ChatMsgRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChatMsgRecord record, @Param("example") ChatMsgRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") ChatMsgRecord record, @Param("example") ChatMsgRecordExample example);

    int updateByExample(@Param("record") ChatMsgRecord record, @Param("example") ChatMsgRecordExample example);

    int updateByPrimaryKeySelective(ChatMsgRecord record);

    int updateByPrimaryKeyWithBLOBs(ChatMsgRecord record);

    int updateByPrimaryKey(ChatMsgRecord record);
    List<ChatMsgRecord> selectBySendName(String sendName, String receiveName);
    Integer selectCountBySendName(String sendName, String receiveName);
    void already(String sendName, String receiveName);
    int deleteRecord(String sendname,String receivename);
}