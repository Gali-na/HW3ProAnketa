package com.example.HWPro3;

import com.example.HWPro3.ListParticipant;
import com.example.HWPro3.Participant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteServlet extends HttpServlet {
    private static ListParticipant listParticipant = new ListParticipant();
    @Override
    public synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String country = req.getParameter("country");
        Participant participant = new Participant();
        participant.setCountry(country);
        participant.setName(firstname);
        participant.setLastName(lastname);
        listParticipant.addParticipant(participant);
        WorkWithDataBace.saveToXML(listParticipant, "D://file.xml");
    }
}
