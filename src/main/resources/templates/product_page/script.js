function chooseFile(fileInput){
    if(fileInput.files && fileInput.files[0]){
        let reader = new FileReader();
        
        reader.onload = function(e){
            $('#image').attr('src', e.target.result);
        }
        reader.readAsDataURL(fileInput.files[0]); // Sửa file[0] thành files[0]
    }
}

        // Lấy đối tượng button và div chứa các liên kết
        const button = document.getElementById('toggleButton');
        const selectLinks = document.getElementById('selectLinks');

        // Thêm sự kiện click cho nút
        button.addEventListener('click', function() {
            // Kiểm tra trạng thái hiển thị của các liên kết
            if (selectLinks.style.display === 'block') {
                selectLinks.style.display = 'none'; // Ẩn liên kết
            } else {
                selectLinks.style.display = 'block'; // Hiển thị liên kết
            }
        });
    

