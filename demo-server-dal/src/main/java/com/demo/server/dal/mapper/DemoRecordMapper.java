package com.demo.server.dal.mapper;

import com.demo.server.dal.model.DemoRecord;
import com.demo.server.dal.model.DemoRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemoRecordMapper {
    int countByExample(DemoRecordExample example);

    int deleteByExample(DemoRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DemoRecord record);

    int insertSelective(DemoRecord record);

    List<DemoRecord> selectByExample(DemoRecordExample example);

    DemoRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DemoRecord record, @Param("example") DemoRecordExample example);

    int updateByExample(@Param("record") DemoRecord record, @Param("example") DemoRecordExample example);

    int updateByPrimaryKeySelective(DemoRecord record);

    int updateByPrimaryKey(DemoRecord record);
}