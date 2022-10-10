package com.example.demo.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PicToStr {
    /**
     * 将图片文件转为字符串
     *
     * @param imgFile
     * @return
     */
    public static String getImageStr(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        // String imgFile = "d:\\111.jpg";// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    /**
     * 将图片文件转为byte数字
     *
     * @param imgFile
     * @return
     */
    public static byte[] getImageByte(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        // String imgFile = "d:\\111.jpg";// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回Base64编码过的字节数组字符串
        return data;
    }

    /**
     * 将字符串转为图片
     *
     * @param imgStr
     * @return
     */
    public static boolean generateImage(String imgStr, String imgFile)
            throws Exception {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            String imgFilePath = imgFile;// 新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 图片是否符合 jpg gjf png格式
     *
     * @param imgStr
     * @return
     */
    public static boolean isRightFormat(String format) {

        return (format.equals("jpg") || format.equals("gif") || format
                .equals("png")) ? true : false;
    }

    /**
     * 对图片进行放大
     *
     * @param originalImage
     *            原始图片
     * @param times
     *            放大倍数
     * @return
     */

    public static BufferedImage zoomInImage(BufferedImage originalImage,
                                            Integer times) {

        int width = originalImage.getWidth() * times;
        int height = originalImage.getHeight() * times;

        BufferedImage newImage = new BufferedImage(width, height,
                originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    /**
     * 对图片进行放大
     *
     * @param srcPath
     *            原始图片路径(绝对路径)
     * @param newPath
     *            放大后图片路径（绝对路径）
     * @param times
     *            放大倍数
     * @return 是否放大成功
     */

    public static boolean zoomInImage(String srcPath, String newPath,
                                      Integer times) {

        BufferedImage bufferedImage = null;
        try {

            File of = new File(srcPath);
            if (of.canRead()) {

                bufferedImage = ImageIO.read(of);

            }
        } catch (Exception e) {
            // TODO: 打印日志
            return false;
        }
        if (bufferedImage != null) {

            bufferedImage = zoomInImage(bufferedImage, times);
            try {
                // TODO: 这个保存路径需要配置下子好一点
                // 保存修改后的图像,全部保存为JPG格式
                ImageIO.write(bufferedImage, "JPG", new File(newPath));
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;

    }

    /**
     * 对图片进行缩小
     *
     * @param originalImage
     *            原始图片
     * @param times
     *            缩小倍数
     * @return 缩小后的Image
     */
    public static BufferedImage zoomOutImage(BufferedImage originalImage,
                                             Integer times) {
        int width = originalImage.getWidth() / times;
        int height = originalImage.getHeight() / times;
        BufferedImage newImage = new BufferedImage(width, height,
                originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    /**
     * 对图片进行放大
     *
     * @param srcPath
     *            原始图片路径(绝对路径)
     * @param newPath
     *            放大后图片路径（绝对路径）
     * @param times
     *            放大倍数
     * @return 是否放大成功
     */

    public static boolean zoomOutImage(String srcPath, String newPath,
                                       Integer times) {
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if (of.canRead()) {
                bufferedImage = ImageIO.read(of);
            }
        } catch (IOException e) {
            // TODO: 打印日志
            return false;
        }
        if (bufferedImage != null) {
            bufferedImage = zoomOutImage(bufferedImage, times);
            try {
                // TODO: 这个保存路径需要配置下子好一点
                // 保存修改后的图像,全部保存为JPG格式
                ImageIO.write(bufferedImage, "JPG", new File(newPath));
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String imageStr = getImageStr("C:/Users/##/Desktop/img/2f738bd4b31c870183e862c22b7f9e2f0708ffbc.jpg");
        System.out.println(imageStr);
        try {
            generateImage(imageStr, "C:/Users/##/Desktop/xingxing.jpg");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.exit(-1);
        boolean testIn = zoomInImage("E:/11.jpg", "E:\\in.jpg", 4);
        if (testIn) {
            System.out.println("in ok");
        }
        boolean testOut = zoomOutImage("E:/11.jpg", "E:\\out.jpg", 4);
        if (testOut) {
            System.out.println("out ok");
        }
    }

}
