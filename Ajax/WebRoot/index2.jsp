<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  </head>
  
  <body>
     <form action="xml.do" method="post">
       <ul style="list-style-type: none;">
       <li>
        <select name="user" onchange="check(this)">
         <option value="">Please select a user</option>
         <option value="whlweb1">whlweb1</option>
         <option value="whlweb2">whlweb2</option>
        </select>
       </li>
       <li>Username:<input name="username" id="username" type="text"/></li>
       <li>Password:<input name="password" type="password"/></li>
       <li>RePassword:<input name="password" type="password"/></li>
       </ul>
     </form>
     <script type="text/javascript">
        function check(user){
        var un=user.value;
        xmlhttp=createXmlHttpRequest();
        var url="xml.do?user="+un;
        xmlhttp.onreadystatechange=onChange;
        xmlhttp.open("GET",url);
        xmlhttp.send(null);
        
        }
        
        function onChange(){
         if(xmlhttp.readyState==4&&xmlhttp.status==200){
          var xml=xmlhttp.responseXML;
          if(xml){
           var users=xml.getElementsByTagName("username");
           if(users.length>0){
            var username=users[0].firstChild.nodeValue;
            document.getElementById("username").value=username;
           }
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
