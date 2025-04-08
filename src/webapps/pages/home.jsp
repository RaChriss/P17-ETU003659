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
  <title>Dashboard Credits</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #333;
      padding: 10px;
      text-align: center;
    }

    th {
      background-color: #f2f2f2;
    }

    h5 {
      text-align: center;
      margin-top: 20px;
    }
  </style>
</head>

<body>
  <div id="main-wrapper">
    <aside>
      <div>
        <nav>
          <ul>
            <li><span>Home</span></li>
            <li><a href="<%= request.getContextPath() %>/">Dashboard</a></li>
            <li><span>UI COMPONENTS</span></li>
            <li><a href="<%= request.getContextPath() %>/credits">Insertion Credit</a></li>
            <li><a href="<%= request.getContextPath() %>/depenses">Insertion Depense</a></li>
            <li><span>AUTH</span></li>
            <li><a href="<%= request.getContextPath() %>/login">Login</a></li>
            <li><a href="<%= request.getContextPath() %>/register">Register</a></li>
          </ul>
        </nav>
      </div>
    </aside>

    <div>
      <div>
        <div>
          <h1>Dashboard</h1>
          <h5>Unite en AR</h5>
          <div>
            <table>
              <thead>
                <tr>
                  <th>Libelle</th>
                  <th>Credit</th>
                  <th>Somme Depense</th>
                  <th>Reste Credit</th>
                </tr>
              </thead>
              <tbody>
                <% for(int i = 0; i < credits.length; i++) { %>
                <tr>
                  <td><%= credits[i].getLibelle() %></td>
                  <td><%= credits[i].getMontant() %></td>
                  <td><%= credits[i].getTotalDepenses() %></td>
                  <td><%= credits[i].getMontant() - credits[i].getTotalDepenses() %></td>
                </tr>
                <% } %>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
