package com.A.GA.Repository;

import com.A.GA.Model.Login;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
public class LoginRepository {
    public static final List<Login> tableLogin = new ArrayList<>();

    static {
        tableLogin.add(new Login(1,"tuyen", "123", "1"));
        tableLogin.add(new Login(2,"tuyen1", "1234", "2"));
        tableLogin.add(new Login(3,"tuyen2", "1236", "2"));
    }

    public Login checkLogin(String TKUser, String password, String role) {
        for (Login login : tableLogin) {
            if (login.getTKUser() != null && login.getPasswork() != null && login.getRole() != null) {
                if (login.getTKUser().equals(TKUser) && login.getPasswork().equals(password) && login.getRole().equals(role)) {
                    return login;
                }
            }

        }
        return null;
    }

    // đưa ảnh về dạng base64 để chính
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

    //   tải ảnh từ thiết bị của máy tính ( cần nâng cấp lên là người dùng có thể nhập khi nhấn 1 nút từ màn hình)
    public static String loadImage() {
        String nameFile = "D:\\WebChickenChaun\\src\\main\\resources\\templates\\imageLogin\\webchicken.jpeg";
        File fileImage = new File(nameFile);
        BufferedImage image = null;
        String base64Image = "";
        try {
            // Đọc ảnh từ đường dẫn được cung cấp
            image = ImageIO.read(fileImage);
            base64Image = convertImageToBase64(image);
            System.out.println("Đã tải ảnh thành công từ: ");
        } catch (IOException e) {
            System.out.println("Không thể đọc ảnh: " + e.getMessage());
        }

        return base64Image;
    }
}
