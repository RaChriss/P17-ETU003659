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
  <title>Insertion de Credit</title>
</head>

<body>
  <div>
    <aside>
      <div>
        <nav>
          <ul>
            <li>
              <span>Home</span>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/">Dashboard</a>
            </li>
            <li>
              <span>UI COMPONENTS</span>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/credits">Insertion Credit</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/depenses">Insertion Depense</a>
            </li>
            <li>
              <span>AUTH</span>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/register">Register</a>
            </li>
          </ul>
        </nav>
      </div>
    </aside>

    <div>
      <div>
        <h2>Insertion de Credit</h2>
        <form action="${pageContext.request.contextPath}/credits" method="post">
          <div>
            <label for="inputlibelle">Libelle</label>
            <input type="text" id="inputlibelle" name="libelle" required>
            <div>Libelle du credit, ex: frais bus</div>
          </div>

          <div>
            <label for="inputMontant">Montant</label>
            <input type="number" id="inputMontant" name="montant" required>
          </div>

          <button type="submit">Submit</button>
          <p><%= message %></p>
        </form>
      </div>
    </div>
  </div>
</body>

</html>
