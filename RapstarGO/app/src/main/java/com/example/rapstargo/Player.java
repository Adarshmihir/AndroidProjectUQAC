package com.example.rapstargo;

public class Player {
    private String m_name;
    private String m_level;
    private String m_status;

    Player(String p_name, String p_level, String p_status) {
        this.m_name = p_name;
        this.m_level = p_level;
        this.m_status = p_status;
    }

    public String getName() { return this.m_name; }

    public String getLevel() { return this.m_level; }

    public String getStatus() { return this.m_status; }
}
