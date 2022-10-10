package com.example.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/*
* pdf转txt
* */
public class PdfUtil {
    public static void main(String[] args) {
        PdfUtil pdfUtil = new PdfUtil();
        pdfUtil.pdfToTxt();
    }

    /**
     * @param
     */
    public void pdfToTxt() {
        try {
            // 是否排序
            boolean sort = false;
            // 开始提取页数
            int startPage = 1;
            // 结束提取页数
            int endPage = Integer.MAX_VALUE;
            String content = null;
            PrintWriter writer = null;
            // pdf文本路径
            String path = "E:/数据文件/2019_PDF.pdf";
            // 输出txt文本路径
            String target = "E:/数据文件/2019_PDF.txt";
            PDDocument document = PDDocument.load(new File(path));
            PDFTextStripper pts = new PDFTextStripper();
            endPage = document.getNumberOfPages();
            System.out.println("Total Page: " + endPage);
            pts.setStartPage(startPage);
            pts.setEndPage(endPage);
            try {
                // content就是从pdf中解析出来的文本
                content = pts.getText(document);
                writer = new PrintWriter(new FileOutputStream(target));
                writer.write(content);// 写入文件内容
                writer.flush();
                writer.close();
            } catch (Exception e) {
                throw e;
            } finally {
                if (null != document)
                    document.close();
            }
            System.out.println("Get PDF Content ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
