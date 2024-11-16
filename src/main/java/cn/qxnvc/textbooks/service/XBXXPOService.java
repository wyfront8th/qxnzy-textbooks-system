package cn.qxnvc.textbooks.service;

//XBXXPOService接口
import cn.qxnvc.textbooks.model.XBXXPO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface XBXXPOService {
    // 添加系部信息
    public Long saveXBXX(XBXXPO xbxxPO);

    // 显示所有系部信息
    public PageInfo<XBXXPO> getList(Integer pageSize, Integer pageNum);

    // 条件查询
    public PageInfo<XBXXPO> getList(String conditions, Integer pageSize, Integer pageNum);

    // 根据id显示系部信息
    public XBXXPO findById(String bmbm);

    // 更新一个系部信息
    public Long updateXBXX(XBXXPO xbxxPO);

    // 删除一个系部信息
    public int deleteXBXX(String bmbm);

    // 处理Excel文件上传和数据导入
    public void importExcelData(MultipartFile file, ResponseBodyEmitter emitter) throws Exception;

    // 批量删除
    public int deleteBatch(List<String> list);

    // 获取所有数据
    public List<XBXXPO> getAllList();
}
