package cn.qxnvc.textbooks.service.impl;

import cn.qxnvc.textbooks.mapper.XBXXPOMapper;
import cn.qxnvc.textbooks.model.XBXXPO;
import cn.qxnvc.textbooks.service.XBXXPOService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.EasyExcel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class XBXXPOServiceImpl implements XBXXPOService {
    @Resource
    private XBXXPOMapper xbxxpoMapper;

    @Override
    public Long saveXBXX(XBXXPO xbxxPO) {
        // 保存系部信息
        Long result = 0L;
        try {
            result = (long) xbxxpoMapper.insert(xbxxPO);
        } catch (Exception e) {
            // 打印错误信息
            System.out.println("mybatis数据错误" + e.getMessage());
        }
        return result;
    }

    @Override
    public PageInfo<XBXXPO> getList(Integer pageSize, Integer pageNum) {
        // mybatis分页显示
        PageHelper.startPage(pageNum, pageSize);
        List<XBXXPO> list = xbxxpoMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<XBXXPO> getList(String conditions, Integer pageSize, Integer pageNum) {
        // 按部门名称和部门编码查询
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(xbxxpoMapper.selectByCondition(conditions));
    }

    @Override
    public XBXXPO findById(String bmbm) {
        // 根据id查询
        return xbxxpoMapper.selectByPrimaryKey(bmbm);
    }

    @Override
    public Long updateXBXX(XBXXPO xbxxPO) {
        // 更新系部信息
        Long result = 0L;
        try {
            result = (long) xbxxpoMapper.updateByPrimaryKey(xbxxPO);
        } catch (Exception e) {
            // 打印错误信息
            System.out.println("mybatis数据错误" + e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteXBXX(String bmbm) {
        // 删除系部信息
        return xbxxpoMapper.deleteByPrimaryKey(bmbm);
    }

    @Override
    public void importExcelData(MultipartFile file, ResponseBodyEmitter emitter) throws Exception {
    }

    @Override
    public int deleteBatch(List<String> list) {
        return xbxxpoMapper.deleteBatch(list);
    }

    @Override
    public List<XBXXPO> getAllList() {
        return xbxxpoMapper.selectAll();
    }
}
