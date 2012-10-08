<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  </head>
  
  <body>
     <form action="reg.do" method="post">
       <ul style="list-style-type: none;">
       <li>Username:<input name="username" type="text" onblur="check(this)"/></li>
       <li>Password:<input name="password" type="password"/></li>
       <li>RePassword:<input name="password" type="password"/></li>
       </ul>
     </form>
     <script type="text/javascript">
        function check(username){
        var un=username.value;
        if(!un){
          alert("用户名为空！");
          username.focus();
          return false;
        }
        xmlhttp=createXmlHttpRequest();
        var url="reg.do";
        var userinfo="username="+un;
        xmlhttp.onreadystatechange=onChange;
        xmlhttp.open("POST",url);
        xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlhttp.send(userinfo);
        
        }
        
        function onChange(){
         if(xmlhttp.readyState==4&&xmlhttp.status==200){
          var xml=xmlhttp.responseXml;
          result=result.replace(/(^\s*)|(\s*)/g,"");
          if(result=="true"){
           alert("用户名已经存在！");
          }else{
           alert("恭喜！此用户名可以注册！");
          }
         }
         
        }
        function createXmlHttpRequest(){
         if(window.XMLHttpRequest){
          return new XMLHttpRequest();
         }else{
          return new ActiveXObject("Microsoft.XMLHTTP");
         }
        }
     </script>
  </body>
</html>
