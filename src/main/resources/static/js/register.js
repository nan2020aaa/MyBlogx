function checkPassword(){
    let pwd1=$("#password").attr("value");
    let pwd2=$("#repeat-password").attr("value");
    if(pwd1!=pwd2){
        window.alert('Passwords typed twice do not match.');
    }
}
function checkAccount(){
    if(true){
        window.alert('Account has already existed.');
    }
}
function checkEmailCheckCode(){
    if(true){
        window.alert('Check code is wrong.')
    }

}