package cn.suvue.discipline.core.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片压缩工具类
 *
 * @author suvue
 * @date 2020/2/19
 */
public class ImageZipUtil {
    public static void main(String[] args) {
        zipWidthHeightImageFile(new File("Z:\\tmp\\192239536527941.png"), new File("Z:\\tmp\\192239536527941" +
                "——copy.png"), 200, 200, 0.7f);
        System.out.println("ok");
    }


    /**
     * 按设置的宽度高度压缩图片文件<br> 先保存原文件，再压缩、上传
     *
     * @param oldFile 要进行压缩的文件全路径
     * @param newFile 新文件
     * @param width   宽度
     * @param height  高度
     * @param quality 质量
     * @return 返回压缩后的文件的全路径
     */
    public static void zipWidthHeightImageFile(File oldFile, File newFile, int width, int height, float quality) {
        if (oldFile == null) {
            return;
        }

        try {
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(oldFile);
            String srcImgPath = newFile.getAbsoluteFile().toString();
            System.out.println(srcImgPath);
            String subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".") + 1);
            BufferedImage buffImg;
            if (subfix.equals("png")) {
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            } else {
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            }
            Graphics2D graphics = buffImg.createGraphics();
            graphics.setBackground(new Color(255, 255, 255));
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(0, 0, width, height);
            graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(buffImg, subfix, new File(srcImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
