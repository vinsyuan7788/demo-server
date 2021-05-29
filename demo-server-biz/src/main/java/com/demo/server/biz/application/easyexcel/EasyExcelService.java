package com.demo.server.biz.application.easyexcel;

import java.util.List;

/**
 * @author Vince Yuan
 * @date 05/14/2021
 */
public interface EasyExcelService {

    /**
     *  Write Excel file
     */
    void writeExcelFile();

    /**
     *  Read Excel file
     *
     * @return
     */
    List<Object> readExcelFile();
}
