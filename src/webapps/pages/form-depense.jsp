<%@ page import="Models.Credits" %>

<%
    String message = (String) request.getAttribute("message");
    if (message == null) {
        message = "";
    }

    Credits[] credits = (Credits[]) request.getAttribute("listeCredits");
%>

<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Page Depense</title>
</head>

<body>
  <div id="main-wrapper">
    <aside>
      <div>
        <div>
          <a href="./index.html">
            <img src="../assets/images/logos/logo-light.svg" alt="" />
          </a>
        </div>
        <nav>
          <ul>
            <li>
              <span>Home</span>
            </li>
            <li>
              <a href="<%= request.getContextPath() %>/">Dashboard</a>
            </li>
            <li>
              <span>UI COMPONENTS</span>
            </li>
            <li>
              <a href="<%= request.getContextPath() %>/credits">Insertion Credit</a>
            </li>
            <li>
              <a href="<%= request.getContextPath() %>/depenses">Insertion Depense</a>
            </li>
            <li>
              <span>AUTH</span>
            </li>
            <li>
              <a href="<%= request.getContextPath() %>/login">Login</a>
            </li>
            <li>
              <a href="<%= request.getContextPath() %>/register">Register</a>
            </li>
          </ul>
        </nav>
      </div>
    </aside>

    <div>
      <div>
        <div>
          <div>
            <h5>Insertion de Depense</h5>
            <div>
              <div>
                <form action="<%= request.getContextPath() %>/depenses" method="post">
                    <div>
                      <label for="select">Selection du Credit</label>
                      <select id="select" name="id_credit">
                        <option>Ex: frais bus</option>
                        <% for(int i=0;i<credits.length ; i++) { %>
                            <option value="<%= credits[i].getId() %>"><%= credits[i].getLibelle() %></option>
                        <% } %>
                      </select>
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
      </div>
    </div>
  </div>
</body>

</html>
