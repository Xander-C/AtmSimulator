package com.xander.atm.mapper;


import com.xander.atm.pojo.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {
    @Select("SELECT * FROM account WHERE id = #{id}")
    Account getAccountById(String id);

    @Update("UPDATE account SET name = #{name}, password = #{password}, money = #{money} WHERE id = #{id}")
    void updateAccount(Account account);

    @Insert("INSERT INTO account VALUES (#{id}, #{name}, #{password}, #{money});")
    void insertAccount(Account account);
}
