package com.huaji.phonetester;


import org.litepal.crud.LitePalSupport;

public class History extends LitePalSupport {

    private long scors;
    private int type;
    private long id;
    private String BB;
    private String time;

    public long getScore()
    {
        return scors;
    }

    public long getId()
    {
        return id;
    }

    public int getType()
    {
        return type;
    }

    public String getBB()
    {
        return BB;
    }

    public String getTime()
    {
        return time;
    }

    public void setScore(long scors)
    {
        this.scors = scors;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setBB(String BB)
    {
        this.BB = BB;
    }

    public void setTime(String time)
    {
        this.time = time;
    }














}


