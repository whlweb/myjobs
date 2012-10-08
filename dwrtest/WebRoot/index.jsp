    <%@ page language="java" contentType="text/html; charset=utf-8"  
      
      pageEncoding="utf-8"%>  
      
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
      
    <html>  
      
    <head>  
      
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
      
    <title>DWR - Test Home</title>  
      
    <script type='text/javascript' src='dwr/engine.js'></script>  
      
    <script type='text/javascript' src='dwr/util.js'></script>  
      
    <script type='text/javascript' src='dwr/interface/service.js'></script>  
      

      
    </head>  
      
    <body>  
      
        <h1>Ajax Test Page</h1>      
      
        <input type="button" value="returnArray" onclick="returnArray()">  <hr>   
        <input type="button" value="returnList" onclick="returnList()">    <hr> 
        <input type="button" value="returnMap" onclick="returnMap()">     <hr>
        <input type="button" value="returnUserList" onclick="userList()">     <hr>
        USERNAME<input type="text" id="username" value="Please input your username"/><br>
        PASSWORD:<input type="password" id="password" /><br>
        <input type="button" value="varify" onclick="varify()">     <hr>
   
      
    <script language="javascript">   
      function returnArray(){
       service.returnArray(function(array){
        for(var i=0;i<array.length;i++){
          alert(array[i]);
         }
       });
      }
         function returnList(){
       service.returnArray(function(list){
        for(var i=0;i<list.length;i++){
          alert(list[i]);
         }
       });
      }
         function returnMap(){
       service.returnMap(function(map){
        for(var key in map){
          alert(key+" "+map[key]);
         }
       });
      }
      
             function userList(){
       service.userList(function(list){
        for(var i=0;i<list.length;i++){
          alert(list[i].username);
          alert(list[i].password);
         }
       });
      }

      function varify(){
      var username=$("username").value;
      var password=$("password").value;
      var user={
         username:username,
         password:password
      }
      service.varify(user,
       function(result){
        alert(result);
       }
      );
      }
      
    </script>         
      
    </body>  
      
    </html>  