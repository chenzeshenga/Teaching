package com.gs.poi;

import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/30.
 */
public class XLSTest {

    public static void main(String... args) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
                    new File("src/main/resources/poi_1.xls")
            ));
            HSSFSheet sheet = workbook.getSheet("Sheet1");
            HSSFRow row = sheet.getRow(0);
            HSSFCell cell = row.getCell(0);
            System.out.println(cell.getStringCellValue());
            HSSFCellStyle cellStyle = cell.getCellStyle();
            System.out.println(cellStyle.getDataFormat());
            HSSFFont cellFont = cellStyle.getFont(workbook);
            System.out.println(cellFont.getFontName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
