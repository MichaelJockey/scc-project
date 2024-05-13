//package com.mj;

import java.util.HashSet;
import java.util.Set;

public class Person     
{
    private String name;
    private Set<String> followers;

    public Person(String name) 
    {
        this.name = name;
        this.followers = new HashSet<>();
    }

    public String getName() 
    {
        return name;
    }

    public void addFollower(String follower) 
    {
        followers.add(follower);
    }

    public Set<String> getFollowers() 
    {
        return followers;
    }

    public int AmountOfFollowers() 
    {
        return followers.size();
    }
}