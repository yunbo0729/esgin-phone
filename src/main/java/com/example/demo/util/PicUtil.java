package com.example.demo.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
* 下载图片
* */
public class PicUtil {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        PicUtil picUtil = new PicUtil();
        picUtil.getImgWidthAndHeight();
        picUtil.getImgWidthAndHeight1();
        picUtil.getLocalImgWidthAndHeight();
        // picUtil.downloadPicture("https://mmbiz.qpic.cn/mmbiz_jpg/Z6bicxIx5naJibicqgjczUbqnBPkVz1S9cicyUWR5fg2Ku1DjfXUNlC33ZLR4NI6xHn2ib5HicwbpmZcq2fSNnhuazyw/640?wx_fmt=jpeg",
        // "E:/test.jpg");
    }

    /**
     * @param 链接url下载图片
     * @param urlStr图片网络地址
     * @param 图片本地存放路径
     */
    public void downloadPicture(String urlStr, String imagePathName) {
        URL url = null;
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            url = new URL(urlStr);
            dataInputStream = new DataInputStream(url.openStream());
            fileOutputStream = new FileOutputStream(new File(imagePathName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            // byte[] context = output.toByteArray();
            fileOutputStream.write(output.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param 获取服务器上的图片宽高
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getImgWidthAndHeight() throws FileNotFoundException,
            IOException {

        URL url = new URL(
                "http://img.mall.tcl.com/dev1/0/000/148/0000148235.fid");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        BufferedImage image = ImageIO.read(connection.getInputStream());
        int srcWidth = image.getWidth(); // 源图宽度
        int srcHeight = image.getHeight(); // 源图高度

        System.out.println("srcWidth = " + srcWidth);
        System.out.println("srcHeight = " + srcHeight);

    }

    /**
     * @param 获取服务器上的图片宽高
     * @param http
     *            ://img.mall.tcl.com/dev1/0/000/148/0000148235.fid
     * @throws IOException
     */
    public void getImgWidthAndHeight1() throws IOException {
        InputStream murl = new URL(
                "http://img.mall.tcl.com/dev1/0/000/148/0000148235.fid")
                .openStream();
        BufferedImage sourceImg = ImageIO.read(murl);
        System.out.println(sourceImg.getWidth()); // 源图宽度
        System.out.println(sourceImg.getHeight()); // 源图高度
    }

    /**
     * @param 本地图片宽高
     * */
    public void getLocalImgWidthAndHeight() throws IOException {
        File picture = new File("E:/test.jpg");
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
        System.out.println(String.format("%.1f", picture.length() / 1024.0));// 源图大小
        System.out.println(sourceImg.getWidth()); // 源图宽度
        System.out.println(sourceImg.getHeight()); // 源图高度

    }

}
