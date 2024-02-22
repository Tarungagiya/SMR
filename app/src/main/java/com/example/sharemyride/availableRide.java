package com.example.sharemyride;

public class availableRide {
    String from,to,person,time,name;

    public availableRide() {
    }

    public availableRide(String from, String to, String person, String time, String name) {
        this.from = from;
        this.to = to;
        this.person = person;
        this.time = time;
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
