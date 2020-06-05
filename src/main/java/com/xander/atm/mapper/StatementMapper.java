package com.xander.atm.mapper;

import com.xander.atm.pojo.Statement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatementMapper {
    @Select("SELECT * FROM statement WHERE accountId = #{id} OR toAccount = #{id}")
    List<Statement> getStatementById(String id);

    @Insert("INSERT INTO statement (accountId, time, type, money, toAccount) VALUES (#{accountId}, #{time}, #{type}, #{money}, #{toAccount})")
    void insertStatement(Statement statement);
}
