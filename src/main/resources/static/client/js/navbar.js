// document.addEventListener("DOMContentLoaded", function() {
//     const menuBtn = document.getElementById('menuBtn');
//     const navbar = document.getElementById('navbarNav');
//
// // Toggle navbar when clicking menu button
//     menuBtn.addEventListener('click', function() {
//         menuBtn.classList.toggle('active');
//         navbar.classList.toggle('active');
//     });
//
// // Close navbar when clicking outside
//     document.addEventListener('click', function(event) {
//         if (!navbar.contains(event.target) && !menuBtn.contains(event.target)) {
//             navbar.classList.remove('active');
//             menuBtn.classList.remove('active');
//         }
//     });
//
// // // Close navbar when clicking on a link
// //     const navLinks = navbar.getElementsByTagName('a');
// //     Array.from(navLinks).forEach(link => {
// //         link.addEventListener('click', function() {
// //             navbar.classList.remove('active');
// //             menuBtn.classList.remove('active');
// //         });
// //     });
// });



document.addEventListener("DOMContentLoaded", function() {
    const menuBtn = document.getElementById('menuBtn');
    const navbarNav = document.getElementById('navbarNav');
    const btnClose = document.getElementById("btnClose");

    if (menuBtn && navbarNav) {
        menuBtn.addEventListener('click', function() {
            navbarNav.classList.toggle('show'); // Thêm hoặc xóa class 'show'
        });
        btnClose.addEventListener('click', function() {
            navbarNav.classList.remove('show'); // Thêm hoặc xóa class 'show'
        });
    } else {
        console.warn("Không tìm thấy nút hoặc navbar.");
    }
});