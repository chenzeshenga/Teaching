package com.gs.poi;

import com.gs.bean.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */
public class ContactWriter {

    @Test
    public void testWriteContact() {
        writeContact("d:/a.xls");
    }

    public void writeContact(String path) {
        HSSFWorkbook workbook = new HSSFWorkbook(); // 保存在内存中的一个工作薄
        HSSFSheet hssfSheet = workbook.createSheet("所有同学的通讯录");
        createTitleRow(workbook, hssfSheet);
        createHeadRow(workbook, hssfSheet);
        // service去调用数据库查找数据
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student(1, "张三", "男", "18888888888"));
        studentList.add(new Student(2, "张三", "男", "18888888888"));
        studentList.add(new Student(3, "张三", "男", "18888888888"));
        studentList.add(new Student(4, "张三", "男", "18888888888"));
        createContentRows(workbook, hssfSheet, studentList);
        try {
            workbook.write(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTitleRow(Workbook workbook, Sheet sheet) {
        Row hssfRow = sheet.createRow(0);
        Cell titleCell = hssfRow.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        titleCell.setCellStyle(createTitleCellStyle(workbook));
        titleCell.setCellValue("同学通讯录");
    }

    private CellStyle createTitleCellStyle(Workbook workbook) {
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setItalic(true);
        titleFont.setFontHeight((short) 280);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    private void createHeadRow(Workbook workbook, Sheet sheet) {
        Row headRow = sheet.createRow(1);
        Cell noCell = headRow.createCell(0);
        noCell.setCellStyle(createHeadCellStyle(workbook));
        noCell.setCellValue("编号");
        Cell nameCell = headRow.createCell(1);
        nameCell.setCellStyle(createHeadCellStyle(workbook));
        nameCell.setCellValue("姓名");
        Cell genderCell = headRow.createCell(2);
        genderCell.setCellStyle(createHeadCellStyle(workbook));
        genderCell.setCellValue("性别");
        Cell phoneCell = headRow.createCell(3);
        phoneCell.setCellStyle(createHeadCellStyle(workbook));
        phoneCell.setCellValue("手机号");
        sheet.setColumnWidth(3, 6000);
        Cell iconCell = headRow.createCell(4);
        iconCell.setCellStyle(createHeadCellStyle(workbook));
        iconCell.setCellValue("头像");
    }

    private CellStyle createHeadCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }

    private void createContentRows(Workbook workbook, Sheet sheet, List<Student> studentList) {
        int i = 2;
        for (Student student : studentList) {
            Row row = sheet.createRow(i);
            row.setHeight((short) 600);
            for (int j = 0; j <= 3; j++) {
                Cell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(student.getNo());
                } else if (j == 1) {
                    cell.setCellValue(student.getName());
                } else if (j == 2) {
                    cell.setCellValue(student.getGender());
                } else if (j == 3) {
                    cell.setCellValue(student.getPhone());
                }
            }
            HSSFPatriarch patriarch =  (HSSFPatriarch) sheet.createDrawingPatriarch(); // 创建图片的绘制对象
            HSSFPicture picture = patriarch.createPicture(
                    // 第一个参数：左顶点的距离
                    // 第二个参数：左顶点的y
                    // 第三个参数：宽
                    // 第四个参数：高
                    // 第5个参数：图片左边开始于哪个列
                    // 第6个参数：图片上边开始于哪个行
                    // 第7个参数：图片右边结束列
                    // 第8个参数 图片下边结束行
                    new HSSFClientAnchor(0, 0, 100, 100, (short) 4, i, (short) 5, i + 1),
                    workbook.addPicture(getImageData("src/main/resources/icon.png"), Workbook.PICTURE_TYPE_PNG));
            picture.resize();
            i++;
        }
    }

    private byte[] getImageData(String imagePath) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 字节输出流
            BufferedImage image = ImageIO.read(new FileInputStream(new File(imagePath))); // ImageIO是专门处理图片的io流
            ImageIO.write(image, "png", baos); // 把指定图片写出到字节输出流
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
