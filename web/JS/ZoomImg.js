/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var imgClick = document.querySelector('.fa-magnifying-glass-plus');

imgClick.addEventListener("click", function() {
    document.querySelector('.popup_img').style.display = 'block';
})

document.querySelector('.popup_img span').onclick = () =>{
    document.querySelector('.popup_img').style.display = 'none';
}