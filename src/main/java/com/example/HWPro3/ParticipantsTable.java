package com.example.HWPro3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ParticipantsTable extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListParticipant catalog =  WorkWithDataBace.loadCatalogFromXMLFile(new File("D://file.xml"));
       String ansver ="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
        "<html lang=\"en\">\n"+
        "<head>\n"+
        "<title> List Participant</title>\n"
       +"<meta charset=\"utf-8\">\n"+
       "<style>\n"+
        "box-sizing: border-box;\n"
        +"font-family: Arial, Helvetica, sans-serif;\n}"
        +"body { margin: 0;font-family: Arial, Helvetica, sans-serif;\n}"
             +  "\n +.topnav {\n" +
               "            overflow: hidden;\n" +
               "            background-color: skyblue;\n" +
               "        }\n" +
               "\n" +
               "        .topnav a {\n" +
               "            float: left;\n" +
               "            display: block;\n" +
               "            color: #f2f2f2;\n" +
               "            text-align: center;\n" +
               "            padding: 14px 16px;\n" +
               "            text-decoration: none;\n" +
               "        }\n" +
               "        .topnav a:hover {\n" +
               "            background-color: #ddd;\n" +
               "            color: black;\n" +
               "        }\n" +
               "        .content {\n" +
               "            background-color: lightcyan;\n" +
               "            padding: 10px;\n" +
               "            height: 500px;\n" +
               "        }\n" +
               "        .footer {\n" +
               "            background-color: #f1f1f1;\n" +
               "            padding: 10px;\n" +
               "        }\n" +
               "        h1{\n" +
               "            color: darkblue;\n" +
               "        }\n" +

               "       button{\n" +
               "           width: 15%;\n" +
               "           background-color: skyblue;\n" +
               "           padding: 14px 20px;\n" +
               "           margin: 8px 0;\n" +
               "           border: none;\n" +
               "           border-radius: 4px;\n" +
               "           cursor: pointer;\n" +
               "           text-align: center;\n" +
               "       }\n" +
               "        a:link, a:visited {\n" +
               "\n" +
               "            text-decoration: none;\n" +
               "            display: inline-block;\n" +
               "        }\n" +
               "    </style>\n" +
               "</head>\n" +
               "<body>\n" +
               "\n" +
               "<div class=\"topnav\">\n" +
               "    <h1>Registration of marathon participants</h1>\n" +
               "\n" +
               "</div>\n" +
               "\n" +
               "<div class=\"content\">\n" +
               "<table border='2' cellpadding='5' ><tr><th>Name</th><th>LastName</th><th>Country</th></tr>";
        for (int i = 0; i < catalog.getParticipants().size(); i++) {
            ansver += "<tr>";
            ansver += "<td>" + catalog.getParticipants().get(i).getName() + "</td>";
            ansver += "<td>" + catalog.getParticipants().get(i).getLastName() + "</td>";
            ansver += "<td>" + catalog.getParticipants().get(i).getCountry() + "</td>";
            ansver += "</tr>";
        }
        ansver += "</table></body></html>";
        ansver += "<button > <a href=\"http://localhost:8080\" >return to the questionnaire </a></button>\n" +
               "</div>\n" +
               "<div class=\"footer\">\n" +
               "    <p>Footer</p>\n" +
               "</div>\n" +
               "</body>\n" +
               "</html>";


        resp.getWriter().println(ansver);
    }
}
