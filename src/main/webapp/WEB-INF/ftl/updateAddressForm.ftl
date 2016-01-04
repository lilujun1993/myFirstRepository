<html>
<body>
<div> update address</div>  
  
<form method="POST" action="${rc.contextPath}/user/updateAddress">  
  <p><br>old adress:</br>${user.address}  
  <p>Your new address:<br>  
  <input type="text" name="newAddress" size=60>  
  <p><input type="submit" value="Submit"> 
   
</form>
</body>
</html>