<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>
   <body>

      <h2>Submitted Student Information${User}</h2>
      <table>
         <tr>
            <td>ID</td>
            <td>${User.id}</td>
         </tr>
         <tr>
            <td>Email</td>
            <td>${User.email}</td>
         </tr>
         <tr>
            <td>Name</td>
            <td>${User.name}</td>
         </tr>
      </table>  
   </body>
</html>