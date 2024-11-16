package cn.qxnvc.textbooks.controller;

import cn.qxnvc.textbooks.dto.ResponseResult;
import cn.qxnvc.textbooks.dto.ResultCodeEnum;
import cn.qxnvc.textbooks.model.XBXXPO;
import cn.qxnvc.textbooks.service.XBXXPOService;
import cn.qxnvc.textbooks.utils.ExcelImportListener;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/xbxx")
@CrossOrigin(origins = "http://localhost:5173")
public class XBXXController {
    @Resource
    private XBXXPOService xbxxService;

    @GetMapping("/list")
    // @CrossOrigin(origins = "*")
    public ResponseResult<PageInfo<XBXXPO>> list(
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfo<XBXXPO> data = xbxxService.getList(pageSize, pageNum);
        return ResponseResult.success(ResultCodeEnum.SUCCESS, data);
    }

    @GetMapping("/list/query")
    public ResponseResult<PageInfo<XBXXPO>> listWithConditions(
            @RequestParam String condition,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfo<XBXXPO> data = xbxxService.getList(condition, pageSize, pageNum);
        return ResponseResult.success(ResultCodeEnum.SUCCESS, data);
    }

    @PostMapping("/save-xbxx")
    public ResponseResult<String> save(@RequestBody XBXXPO xbxxPO) {
        Long id = xbxxService.saveXBXX(xbxxPO);
        if (id > 0) {
            return ResponseResult.success(ResultCodeEnum.SUCCESS);
        } else {
            return ResponseResult.fail(ResultCodeEnum.ERROR);
        }
    }

    @PostMapping("/update-xbxx")
    public ResponseResult<String> update(@RequestBody XBXXPO xbxxPO) {
        Long result = xbxxService.updateXBXX(xbxxPO);
        if (result == 0L) {
            return ResponseResult.fail(ResultCodeEnum.ERROR);
        } else {
            return ResponseResult.success(ResultCodeEnum.SUCCESS);
        }
    }

    @DeleteMapping("/delete/{bmbm}")
    public ResponseResult<String> delete(@PathVariable String bmbm) {
        int result = xbxxService.deleteXBXX(bmbm);
        if (result == 0) {
            return ResponseResult.fail(ResultCodeEnum.ERROR);
        } else {
            return ResponseResult.success(ResultCodeEnum.SUCCESS);
        }
    }

    @GetMapping("/find/{bmbm}")
    public ResponseResult<XBXXPO> find(@PathVariable String bmbm) {
        XBXXPO data = xbxxService.findById(bmbm);
        return ResponseResult.success(ResultCodeEnum.SUCCESS, data);
    }

    @PostMapping("/import")
    public ResponseResult<String> importExcel(@RequestParam("file") MultipartFile file) {
        // ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        // 在新线程中处理Excel导入
        String importId = String.valueOf(System.currentTimeMillis());
        // 保存文件
        try {
            String projeString = System.getProperty("user.dir");
            String uploadDir = projeString + "/file_upload";
            System.out.println(uploadDir);
            // 按时间戳创建文件
            String fileName = importId + ".xlsx";
            File desfile = new File(uploadDir, fileName);
            file.transferTo(desfile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseResult.success(ResultCodeEnum.SUCCESS, importId);
    }

    @GetMapping("/import/progress/{importId}")
    public SseEmitter importProgress(@PathVariable String importId) {
        SseEmitter emitter = new SseEmitter(0L);
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + File.separator + "file_upload" + File.separator + importId + ".xlsx";
        File exceFile = new File(filePath);
        if (!exceFile.exists()) {
            return emitter;
        }
        try {
            ExcelImportListener listener = new ExcelImportListener(emitter, xbxxService);
            EasyExcel.read(exceFile, XBXXPO.class, listener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            emitter.completeWithError(e);
        }
        return emitter;
    }

    @DeleteMapping("/delete/batch")
    public ResponseResult<String> deleteBatch(@RequestBody List<String> list) {
        int result = xbxxService.deleteBatch(list);
        if (result == 0) {
            return ResponseResult.fail(ResultCodeEnum.ERROR);
        } else {
            return ResponseResult.success(ResultCodeEnum.SUCCESS);
        }
    }

    // 导出数据为电子表格
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        try {
            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("系部信息", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            // 获取所有数据
            List<XBXXPO> dataList = xbxxService.getAllList();

            // 写入Excel
            EasyExcel.write(response.getOutputStream(), XBXXPO.class)
                    .sheet("系部信息")
                    .doWrite(dataList);

        } catch (Exception e) {
            e.printStackTrace();
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            try {
                response.getWriter().println(JSON.toJSONString(ResponseResult.fail(
                        ResultCodeEnum.ERROR.getCode(),
                        "导出失败：" + e.getMessage(),
                        null)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
