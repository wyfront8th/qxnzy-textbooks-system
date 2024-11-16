package cn.qxnvc.textbooks.mapper;

import cn.qxnvc.textbooks.model.XBXXPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XBXXPOMapper {
    int deleteByPrimaryKey(String bmbm);

    int insert(XBXXPO record);

    int insertSelective(XBXXPO record);

    XBXXPO selectByPrimaryKey(String bmbm);

    int updateByPrimaryKeySelective(XBXXPO record);

    int updateByPrimaryKey(XBXXPO record);

    int insertBatch(List<XBXXPO> list);

    List<XBXXPO> selectAll();

    List<XBXXPO> selectByCondition(String conditions);

    // 批量删除
    int deleteBatch(List<String> list);
}
