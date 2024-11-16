package cn.qxnvc.textbooks.model;

import org.apache.poi.ss.usermodel.IndexedColors;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadFontStyle(fontHeightInPoints = 11, bold = BooleanEnum.FALSE, color = 1)
@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 48)
public class XBXXPO {
    @ExcelProperty("部门编码")
    private String bmbm;

    @ExcelProperty("部门名称")
    private String bmmc;

    @ExcelProperty("学校名称")
    private String xxmc;

    @ExcelProperty("单位职能")
    private String dwzn;

    @ExcelProperty("是否开课单位")
    private Boolean sfkkdw;
}