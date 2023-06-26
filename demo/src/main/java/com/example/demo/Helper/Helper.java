package com.example.demo.Helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
// import javax.mail.MessagingException;
// import javax.mail.internet.MimeMessage;
import java.io.File;
import com.example.demo.Entity.Product;

public class Helper {

    // check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    // convert excel to list of products

    public static List<Product> convertExcelToListOfProduct(InputStream is) {
        List<Product> list = new ArrayList<>();

        try {

            try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
                XSSFSheet sheet = workbook.getSheet("Alwar");

                int rowNumber = 0;
                Iterator<Row> iterator = sheet.iterator();

                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    int flag = 0;
                    if (rowNumber == 0) {
                        rowNumber++;
                        continue;
                    }

                    Iterator<Cell> cells = row.iterator();

                    int cid = 0;

                    Product p = new Product();

                    while (cells.hasNext()) {
                        Cell cell = cells.next();

                        switch (cid) {
                            case 0:
                                try {
                                    p.setStation(cell.getStringCellValue());
                                } catch (Exception e) {
                                    flag = 1;
                                }
                                break;
                            case 1:
                                try {
                                    p.setWeight((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    flag = 1;
                                }
                                break;
                            case 2:
                                try {
                                    p.setRate((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    flag = 1;
                                }
                                break;
                            case 3:
                                try {
                                    p.setTransporterId((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    flag = 1;
                                }
                                break;
                            case 4:
                                try {
                                    p.setEmail(cell.getStringCellValue());
                                } catch (Exception e) {
                                    flag = 1;
                                }
                                break;
                            default:
                                break;
                        }
                        cid++;
                    }

                    if (flag == 1) {
                        continue;
                    } else {
                        // System.out.println(p.getEmail());
                        // System.out.print(1);
                        list.add(p);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.print(list.size());
        return list;
    }
    
    //Helps to send emails.
    public static  void sendSimpleEmail(JavaMailSender mailSender,String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rocktheway.2akash@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

}
