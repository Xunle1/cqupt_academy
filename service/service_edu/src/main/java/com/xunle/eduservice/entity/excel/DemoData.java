package com.xunle.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xunle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoData {

    @ExcelProperty(value = "学生编号",index = 0)
    private String sno;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}
