function Firstnamevalidation() {
	var alpha = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var add = /^[a-zA-Z0-9]*$/;
	var username = myform.username.value;
	var password = myform.password.value;
	var con = myform.confirmpassword.value;

	alert("2")
	if ((password.match(add)) && (password.length > 5 && password.length < 20)) {
		alert("password is  valid");
	} else {
		alert("Enter valid password!!!!");
		return false;
	}
	alert("3");
	if(con.match(password))
		{
		alert("confirm password is  valid");
		}
	else
		{
		alert("Enter valid confirm password!!!!");
		}
}