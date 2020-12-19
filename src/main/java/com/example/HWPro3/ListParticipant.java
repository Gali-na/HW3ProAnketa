package com.example.HWPro3;

import java.util.ArrayList;

public class ListParticipant {

    private  ArrayList <Participant> participants;

    public ListParticipant(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    public ListParticipant() {

        this.participants = new ArrayList<>();
    }

    public ArrayList<Participant> getParticipants() {

        return participants;
    }

    public void addParticipant (Participant participant){
        this.participants.add(participant);
    }

    @Override
    public String toString() {
        StringBuilder rezult = new StringBuilder();
        for(Participant p:this.participants) {
            rezult.append(p.toString());
        }
        return rezult.toString();
    }
}
