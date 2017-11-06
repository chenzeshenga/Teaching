package com.gs.poi;

import com.gs.bean.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */
public class ContactReader1 {

    @Test
    public void testReadContact() {
        String path = "src/main/resources/poi_2.xlsx";
        List<Student> studentList = null;
        if (path.endsWith(".xls")) {
            studentList = readXLSContact(path);
        } else if (path.endsWith(".xlsx")) {
            studentList = readXLSXContact(path);
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public List<Student> readXLSContact(String xlsPath) {
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

    public List<Student> readXLSXContact(String xlsxPath) {
        List<Student> studentList = new ArrayList<Student>();
       try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(xlsxPath)));
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            System.out.println(sheet.getLastRowNum());
            for (int row = 2; row <= sheet.getLastRowNum(); row++) {
                XSSFRow xssfRow = sheet.getRow(row);
                Student student = new Student();
                for (int cell = 0; cell <= xssfRow.getLastCellNum(); cell++) {
                    XSSFCell xssfCell = xssfRow.getCell(cell);
                    if (cell == 0) {
                        student.setNo((int) xssfCell.getNumericCellValue());
                    } else if (cell == 1) {
                        student.setName(xssfCell.getStringCellValue());
                    } else if (cell == 2) {
                        student.setGender(xssfCell.getStringCellValue());
                    } else if (cell == 3) {
                        student.setPhone(xssfCell.getStringCellValue());
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
