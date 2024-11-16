package cn.qxnvc.textbooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BJXXPO {
    private String xbmc; // 系部名称
    private String zymc; // 专业名称
    private String bh; // 班号
    private String bjmc; // 班级名称
    private int rs; // 人数
    private String fdy; // 辅导员
    private String fdydh; // 辅导员单号
}
