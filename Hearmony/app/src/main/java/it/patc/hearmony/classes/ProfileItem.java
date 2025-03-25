package it.patc.hearmony.classes;

public class ProfileItem
{
    private String name;
    private int propic;

    public ProfileItem(String name, int propic)
    {
        this.name = name;
        this.propic = propic;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPropic()
    {
        return propic;
    }

    public void setPropic(int propic)
    {
        this.propic = propic;
    }
}
