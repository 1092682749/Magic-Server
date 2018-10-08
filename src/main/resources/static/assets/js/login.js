
function cambiar_login() {
  document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";  
document.querySelector('.cont_form_login').style.display = "block";
document.querySelector('.cont_form_sign_up').style.opacity = "0";               

setTimeout(function(){  document.querySelector('.cont_form_login').style.opacity = "1"; },400);  
  
setTimeout(function(){    
document.querySelector('.cont_form_sign_up').style.display = "none";
},200);

  }

function cambiar_sign_up(at) {
  document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
  document.querySelector('.cont_form_sign_up').style.display = "block";
document.querySelector('.cont_form_login').style.opacity = "0";
  
setTimeout(function(){  document.querySelector('.cont_form_sign_up').style.opacity = "1";
},100);  

setTimeout(function(){   document.querySelector('.cont_form_login').style.display = "none";
},400);
if (at == 2) {
    var username = document.getElementById('signinusername').value;
    var password = document.getElementById('signinpassword').value;
    var nickname = document.getElementById('nickName').value;
    if (username == '' || password == '') {
        alert("用户名和密码不能为空！");
        return;
    }
    // var form = new FormData();
    // form.append("username",username);
    // form.append("password",password);
    var user = {
        username: username,
        password: password,
        nickName: nickname
    };
    $.ajax({
        url: '/ok/save',
        type: 'post',
        contentType:'application/json',
        data: JSON.stringify(user),
        dataType: 'json',
        success: function (e) {
            alert(e.msg);
        }
    })

}

}    



function ocultar_login_sign_up() {

document.querySelector('.cont_forms').className = "cont_forms";  
document.querySelector('.cont_form_sign_up').style.opacity = "0";               
document.querySelector('.cont_form_login').style.opacity = "0"; 

setTimeout(function(){
document.querySelector('.cont_form_sign_up').style.display = "none";
document.querySelector('.cont_form_login').style.display = "none";
},500);  
  
  }