package com.A.GA.Repository;

import com.A.GA.Model.ComBo;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ComBoRepository {
    @Autowired

//    tên địa chỉ foldel
    public static final String imageComBo = "C:/WebChickenOfficial/src/main/resources/templates/imageComBo";
    public static final List<String> tableImage = new ArrayList<>();
    public static final List<ComBo> tableComBo = new ArrayList<>();
    public static void arrayImage(String folderName){
        File fileImage= new File(folderName);
        if(fileImage.exists()){
            for (File file: fileImage.listFiles()){
                if(file.isFile() &&(file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) ){
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(file);
                        String base64Image = convertImageToBase64(image);
                        tableImage.add(base64Image);
                    } catch (IOException e) {
                        System.out.println("Không thể đọc ảnh từ " + file.getPath() + ": " + e.getMessage());
                    }
                }

            }
        }else {
            System.out.println("không thể tìm thấy folder");
        }
    }

    // chuyển đổi ảnh về dạng String để trình duyệt bên html có thể hiểu
    private static String convertImageToBase64(BufferedImage image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", outputStream); // Ghi ảnh dưới dạng PNG vào output stream
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes); // Chuyển đổi sang Base64
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
    public ComBoRepository() {
        arrayImage(imageComBo);
        tableComBo.add(new ComBo("Com Bô Nhóm 2",4007,tableImage.get(0),"3 miếng gà +1 lát phô mai","newMeal"));
        tableComBo.add(new ComBo("Com Bô Nhóm 2",4003,tableImage.get(1),"3 miếng gà +1 lát phô mai","group"));
        tableComBo.add(new ComBo("Com Bô Nhóm 3",4010,tableImage.get(2),"3 miếng gà +1 lát phô mai","one"));
        tableComBo.add(new ComBo("Com Bô Nhóm 1",4003,tableImage.get(3),"3 miếng gà +1 lát phô mai","group"));
        tableComBo.add(new ComBo("Com Bô Nhóm 4",4056,tableImage.get(4),"3 miếng gà +1 lát phô mai","newMeal"));

    }

    public List<ComBo> getAllComBo() {
        return tableComBo;
    }

    public void addComBo(String nameComBo, int price, String category, String describe, String file) {
        ComBo newComBo= new ComBo(nameComBo,price,file,describe,category);
        tableComBo.add(newComBo);
    }

    public List<ComBo> searchBoxComBo(String searchBox) {
        String lowerCaseKeyword = searchBox.toLowerCase();
         return tableComBo.stream()
                .filter(product -> product.getNameComBo().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toList());
    }
}
