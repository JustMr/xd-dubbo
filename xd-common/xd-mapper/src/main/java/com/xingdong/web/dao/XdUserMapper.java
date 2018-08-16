package com.xingdong.web.dao;

import com.xingdong.entity.XdUser;

import java.util.List;

public interface XdUserMapper {
    int deleteByPrimaryKey(String xid);

    int insert(XdUser record);

    int insertSelective(XdUser record);

    XdUser selectByPrimaryKey(String xid);

    int updateByPrimaryKeySelective(XdUser record);

    int updateByPrimaryKey(XdUser record);

    List<XdUser> selectByFilter(XdUser xdUser);

}