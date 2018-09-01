package com.example.demo.dao;

import com.example.demo.model.ClientInfo;
import com.example.demo.model.ClientInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInfoMapper {
    long countByExample(ClientInfoExample example);

    int deleteByExample(ClientInfoExample example);

    int insert(ClientInfo record);

    int insertSelective(ClientInfo record);

    List<ClientInfo> selectByExample(ClientInfoExample example);

    int updateByExampleSelective(@Param("record") ClientInfo record, @Param("example") ClientInfoExample example);

    int updateByExample(@Param("record") ClientInfo record, @Param("example") ClientInfoExample example);
    public ClientInfo findClientByclientid(String clientId);
    public void save(ClientInfo clientInfo);
}