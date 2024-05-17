//package com.mj;

import java.util.HashSet;
import java.util.Set;

public class Person     
{
    private String name;
    private Set<Person> followers;

    public Person(String name) 
    {
        this.name = name;
        this.followers = new HashSet<>();
    }

    public String getName() 
    {
        return name;
    }

    public void addFollower(Person follower)
    {
        followers.add(follower);
    }

    public Set<Person> getFollowers()
    {
        return followers;
    }

    public int AmountOfFollowers() 
    {
        return followers.size();
    }
}