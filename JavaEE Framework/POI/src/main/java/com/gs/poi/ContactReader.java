package com.gs.poi;

import com.gs.bean.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */
public class ContactReader {

    @Test
    public void testReadContact() {
        List<Student> studentList = readContact("src/main/resources/poi_1.xls");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public List<Student> readContact(String xlsPath) {
        List<Student> studentList = new ArrayList<Student>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(xlsPath)));
            HSSFSheet sheet = workbook.getSheet("Sheet1");
            System.out.println(sheet.getLastRowNum());
            for (int row = 2; row <= sheet.getLastRowNum(); row++) {
                HSSFRow hssfRow = sheet.getRow(row);
                Student student = new Student();
                for (int cell = 0; cell <= hssfRow.getLastCellNum(); cell++) {
                    HSSFCell hssfCell = hssfRow.getCell(cell);
                    if (cell == 0) {
                        student.setNo((int) hssfCell.getNumericCellValue());
                    } else if (cell == 1) {
                        student.setName(hssfCell.getStringCellValue());
                    } else if (cell == 2) {
                        student.setGender(hssfCell.getStringCellValue());
                    } else if (cell == 3) {
                        student.setPhone(hssfCell.getStringCellValue());
                    }
                }
                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

}
