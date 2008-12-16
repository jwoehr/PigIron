<%-- 
    Document   : index
    Created on : Sep 2, 2008, 11:15:49 PM
    Author     : jax
--%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="generator" content="HTML Tidy, see www.w3.org">
<meta http-equiv="Content-Type" content=
"text/html; charset=UTF-8">
<title>PigLet the PigIron Servlet</title>
</head>
<body>
<h1>PigLet <img alt="" src="images/pig_18.gif"> the PigIron <img
alt="" src="images/pig_15.gif"> Servlet</h1>

<a href=
"/piglet/PigIronServlet/topview">{"service":"topview","uri":"/piglet/PigIronServlet/topview"}</a>

<hr>
<form method=POST action="/piglet/PigIronServlet/engine">
<br>
<textarea name="requestor" wrap="soft" cols="120" rows="20"></textarea> 
<p><input type=SUBMIT value="Submit a Requestor in JSON or a JSON Array of Requestors in JSON"></p>
</form>
<!-- <br/><br/>
<FORM METHOD=POST ENCTYPE="multipart/form-data" ACTION="/piglet/PigIronServlet/engine">
File containing a JSON array of Requestors to upload: <INPUT TYPE=FILE NAME="requestors"><BR>
<INPUT TYPE=SUBMIT VALUE="Submit a Requestor in JSON or a JSON Array of Requestors in JSON">
</FORM> -->
<hr>
<p><a href="http://pigiron.sourceforge.net">The PigIron Project
http://pigiron.sourceforge.net</a></p>
</body>
</html>


