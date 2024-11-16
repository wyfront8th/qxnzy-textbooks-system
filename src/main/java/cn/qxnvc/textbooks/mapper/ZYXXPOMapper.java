package cn.qxnvc.textbooks.mapper;

import cn.qxnvc.textbooks.model.ZYXXPO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 专业信息Mapper接口
 */
@Mapper
public interface ZYXXPOMapper {
    
    /**
     * 查询所有专业信息
     */
    List<ZYXXPO> selectAll();
    
    /**
     * 根据专业代码查询专业信息
     */
    ZYXXPO selectByZydm(String zydm);
    
    /**
     * 插入专业信息
     */
    int insert(ZYXXPO zyxxpo);
    
    /**
     * 更新专业信息
     */
    int update(ZYXXPO zyxxpo);
    
    /**
     * 根据专业代码删除专业信息
     */
    int deleteByZydm(String zydm);
} 