<%
    String message = (String) request.getAttribute("message");
    if (message == null) {
        message = "";
    }
%>

<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Web Servlet | Login</title>
</head>

<body>
  <!--  Body Wrapper -->
  <div>
    <div>
      <div>
        <div>
          <div>
            <div>
              <div>

                <p>Gestion des Depenses et Credits</p>
                <form action="login" method="post">
                  <div>
                    <label for="exampleInputEmail1">Username</label>
                    <input type="text" id="exampleInputEmail1" name="username" placeholder="Ex: RaChriss" value="Christian" required>
                  </div>
                  <div>
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" id="exampleInputPassword1" name="password" placeholder="********" value="123" required>
                  </div>
                  <div>
                    <a href="./index.html">Forgot Password ?</a>
                  </div>
                  <input type="submit">
                  <div>
                    <p>New here?</p>
                    <a href="signIn">Create an account</a>
                  </div>
                  <p><%= message %></p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>
