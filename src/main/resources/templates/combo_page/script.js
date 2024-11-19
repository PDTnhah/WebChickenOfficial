function showTab(category) {
    // Ẩn tất cả sản phẩm trước
    const allProducts = document.querySelectorAll('.space');
    allProducts.forEach(product => {
        product.style.display = 'none';
    });

    // Hiển thị các sản phẩm thuộc nhóm được chọn
    const selectedProducts = document.querySelectorAll(`.${category}`);
    selectedProducts.forEach(product => {
        product.style.display = 'block';
    });
}