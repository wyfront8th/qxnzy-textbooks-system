package cn.qxnvc.textbooks.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import cn.qxnvc.textbooks.model.XBXXPO;
import cn.qxnvc.textbooks.service.XBXXPOService;

public class ExcelImportListener extends AnalysisEventListener<XBXXPO> {

    // private final String importId;
    // private final Map<String, SseEmitter> emitters;
    private final SseEmitter emitter;
    private final XBXXPOService xbxxService;
    private int totalRows = 0;
    private int totalCount = 0;

    public ExcelImportListener(SseEmitter emitter, XBXXPOService xbxxService) {
        // this.importId = importId;
        // this.emitters = emitters;
        this.emitter = emitter;
        this.xbxxService = xbxxService;
    }

    @Override
    public void invoke(XBXXPO data, AnalysisContext context) {
        if (totalCount == 0) {
            totalCount = context.readSheetHolder().getApproximateTotalRowNumber();
        }
        totalRows++;
        // SseEmitter emitter = emitters.get(importId);
        try {
            // 保存数据
            xbxxService.saveXBXX(data);
            emitter.send(SseEmitter.event().data(Map.of("totalRows", totalRows, "totalCount", totalCount)));
        } catch (IOException e) {
            e.printStackTrace();
            emitter.completeWithError(e);
            // emitters.remove(importId);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
