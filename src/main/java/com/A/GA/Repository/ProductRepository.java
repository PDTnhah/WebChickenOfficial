    package com.A.GA.Repository;

    import com.A.GA.Model.ProductChicken;
    import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Repository;

    import javax.imageio.ImageIO;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Base64;
    import java.util.List;
    import java.util.stream.Collectors;

    @Repository
    public class ProductRepository {


        //        Đường dẫn đến folder Image
//        public static final  String test ="D:/image";
        private static final String folderImage = "D:/WebChickenChaun/src/main/resources/templates/image";
    // mảng các sản phẩm
        public static final List<ProductChicken> tableProduct = new ArrayList<>();

    //    mảng các sản phẩm người đã đặt
        public static final List<ProductChicken> tableOrder = new ArrayList<>();

//         mảng dùng để lưu giữ tất cả ảnh, ảnh sẽ được map theo index của từng sản phẩm ví dụ sản phẩm ở vị trí 1 sẽ map đến mảng ảnh ở vị trí 1
        public static final List<String> tableImage = new ArrayList<>();
        @Autowired
        public ProductRepository() {
            arrayImage(folderImage  );
            // tạo sẵn các sản phẩm
            tableProduct.add( new ProductChicken(0,"Burger Zinger" , "chiên" , 1000,tableImage.get(0)));
            tableProduct.add( new ProductChicken(1,"Burger Tôm" , "chiên" , 2000,tableImage.get(1)));
            tableProduct.add( new ProductChicken(2,"Burger Gà Quay Flava" , "hấp" , 2000,tableImage.get(2)));
            tableProduct.add( new ProductChicken(3,"Cơm Phi-lê Gà Quay" , "hấp" , 2000,tableImage.get(3)));
            tableProduct.add( new ProductChicken(4,"Com Gà Teriyaki" , "hấp" , 2000,tableImage.get(4)));
            tableProduct.add( new ProductChicken(5,"Mì Ý Gà Viên" , "hấp" , 2000,tableImage.get(5)));

//            tạo sẳn ở order
            tableOrder.add(new ProductChicken(1,"gà chiên", "chiên",1000,tableImage.get(1)));
        }
//        loát toàn bộ ảnh từ 1 folder chuyển về dạng strign Base86 để gửi thêm vào bảng data
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


    //   tải ảnh từ thiết bị của máy tính ( cần nâng cấp lên là người dùng có thể nhập khi nhấn 1 nút từ màn hình)
//    public static BufferedImage loadImage(File fileImage) {
//        BufferedImage image = null;
//        try {
//            // Đọc ảnh từ đường dẫn được cung cấp
//            image = ImageIO.read(fileImage);
//            System.out.println("Đã tải ảnh thành công từ: ");
//        } catch (IOException e) {
//            System.out.println("Không thể đọc ảnh: " + e.getMessage());
//        }
//
//        return image;
//    }

    }


        //    tạo ra các sản phẩm mẫu
//        static {
////            arrayImage(folderImage);
////            String imageBase64 = convertImageToBase64(loadImage());
////            tableProduct.add( new ProductChicken(0,"Burger Zinger" , "chiên" , 1000,tableImage.get(0)));
////            tableProduct.add( new ProductChicken(1,"Burger Tôm" , "chiên" , 2000,tableImage.get(1)));
////            tableProduct.add( new ProductChicken(2,"Burger Gà Quay Flava" , "hấp" , 2000,tableImage.get(2)));
////            tableProduct.add( new ProductChicken(3,"Cơm Phi-lê Gà Quay" , "hấp" , 2000,tableImage.get(3)));
////            tableProduct.add( new ProductChicken(4,"Com Gà Teriyaki" , "hấp" , 2000,tableImage.get(4)));
////            tableProduct.add( new ProductChicken(5,"Mì Ý Gà Viên" , "hấp" , 2000,tableImage.get(5)));
//        }

         // bảng các sản phẩm mà người dùng đã chọn
//        static {
////             String imageBase64 = convertImageToBase64(loadImage());
//            tableOrder.add(new ProductChicken(1,"gà chiên", "chiên",1000,tableImage.get(1)));
//        }
    // tìm kiếm sản phẩm bằng id
        public ProductChicken getByIdProduct(int id) {
            for (ProductChicken productChicken : tableProduct){
                if (productChicken.getId() == id){
                    return productChicken;
                }
            }
            return null;
        }

        // lấy ra toàn bộ sản phẩm
        public List<ProductChicken> getProduct() {
            return tableProduct;
        }

        // Lấy ra toàn bộ sản phẩm người dùng đã chọn
        public List<ProductChicken> getOrderProduct() {
            return tableOrder;
        }

        // thêm sản phẩm người dùng đã chọn vào mảng
        public void addOrderProduct(int id) {
            ProductChicken productChicken = getByIdProduct(id);
            tableOrder.add(productChicken);
        }

        // tìm kiếm sản phẩm khi người dùng nhấn chọn sản phẩm

        public ProductChicken getByIdOrder(int id){
            for (ProductChicken order : tableOrder){
                if (order.getId() == id){
                    return order;
                }
            }
            return null;
        }

        // Sóa 1 sản phẩm đã order
        public void deleteOrder(int id) {
            ProductChicken productOrder = getByIdOrder(id);
            tableOrder.remove(productOrder);
        }

        //lấy ra các sản phẩm khi người dùng gõ vào thanh tìm kiếm
        public List<ProductChicken> listSearchBox(String searchBox) {
            String lowerCaseKeyword = searchBox.toLowerCase();
            return tableProduct.stream()
                    .filter(product -> product.getNameProduct().toLowerCase().contains(lowerCaseKeyword))
                    .collect(Collectors.toList());
        }

        // tính tổng số tiền mà khách hàng đã chọn
        public double sumProduct() {
            double sum = 0 ;
            for (ProductChicken productChicken : tableOrder){
                sum = sum +productChicken.getPrice();
            }
            return sum;
        }
    }

