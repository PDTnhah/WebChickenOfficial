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
        tableComBo.add(new ComBo("Com Bô Nhóm 2",4007,tableImage.get(0),"3 miếng gà +1 lát phô mai","newMeal",1));
        tableComBo.add(new ComBo("Com Bô Nhóm 2",4003,tableImage.get(1),"3 miếng gà +1 lát phô mai","group",1));
        tableComBo.add(new ComBo("Com Bô Nhóm 3",4010,tableImage.get(2),"3 miếng gà +1 lát phô mai","one",1));
        tableComBo.add(new ComBo("Com Bô Nhóm 1",4003,tableImage.get(3),"3 miếng gà +1 lát phô mai","group",1));
        tableComBo.add(new ComBo("Com Bô Nhóm 4",4056,tableImage.get(4),"3 miếng gà +1 lát phô mai","newMeal",1));

    }

    public List<ComBo> getAllComBo() {
        return tableComBo;
    }

    public void addComBo(String nameComBo, int price, String category, String describe, String file, int maStore) {
        ComBo newComBo= new ComBo(nameComBo,price,file,describe,category,maStore);
        tableComBo.add(newComBo);
    }

    public List<ComBo> searchBoxComBo(String searchBox) {
        String lowerCaseKeyword = searchBox.toLowerCase();
         return tableComBo.stream()
                .filter(product -> product.getNameComBo().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toList());
    }

    public List<ComBo> getAllComBoAdmin(int maStore) {
        List<ComBo> ListComBoOfOneProduct = new ArrayList<>();
        for (ComBo comBo:tableComBo){
            if (comBo.getMaStore() == maStore){
                ListComBoOfOneProduct.add(comBo);
            }
        }
        return ListComBoOfOneProduct;
    }

    public List<ComBo> searchBoxHomeAdmin(String searchBox, int maStore) {
        String lowerCaseKeyword = searchBox.toLowerCase();

        // Dùng stream để kết hợp cả 2 điều kiện
        return tableComBo.stream()
                .filter(comBo -> comBo.getMaStore() == maStore) // Lọc theo mã cửa hàng
                .filter(comBo -> comBo.getNameComBo().toLowerCase().contains(lowerCaseKeyword)) // Lọc theo từ khóa
                .collect(Collectors.toList());
    }

}
