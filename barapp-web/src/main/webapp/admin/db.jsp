<%@ page language="java" %>

<%@ page import="java.util.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.sql.*" %>

<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%
if ( request.getParameter("query") != null ) {
        String query = request.getParameter("query");
        query = query.trim().toLowerCase();
        
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
        
        DataSource ds = (DataSource)context.getBean("dataSource");
        Connection con = ds.getConnection();
        Statement stat = con.createStatement();

        if ( query.startsWith("select") ) {

                ResultSet rs = stat.executeQuery(request.getParameter("query"));
                ResultSetMetaData rsmd = rs.getMetaData();
                int n = rsmd.getColumnCount();
                %>
                <table><tr>
                <% for (int i = 1; i <= n; i++) { %>
                        <td><%= rsmd.getColumnName(i) %></td>
                <% } %>
                <% while ( rs.next() ) { %>
                <tr>
                        <% for (int i = 1; i <= n; i++) { %>
                        <td><% try { %>
                                <%= rs.getObject(i).toString() %>
                            <% } catch (Exception e) { System.out.println(e); } %>
                        </td>
                        <% } %>
                </tr>
                <% } %>
                </table>
        <% } else { %>
                <% stat.execute(query); %>
        <% } %>
                <%
                stat.close();
                con.close();
       %>
<% } %>

<form action="db.jsp" method="post">
<textarea name="query" cols=80 rows=8><% if ( request.getParameter("query") != null ) { %><%= request.getParameter("query") %><% } %>
</textarea>
<input type="submit" value="Execute">
</form>
                
