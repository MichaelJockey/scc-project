//package com.mj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.HashMaps;
import java.util.Set;

public class SocialNetwork
{
    //private Map<String, Set<String>> socialNetwork;
    private HashMap<String, Set<String>> socialNetwork;

    public SocialNetwork()
    {
        this.socialNetwork = new HashMap<String, Set<String>>();
    }
   private void loadSocialNetwork(String filePath)
    {
        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] people = line.split(" ");
                String name = people[0];
                //Set<String> followers = Arrays.asList(people).subList(1, people.length);
                Set<String> followers = new HashSet<>(Arrays.asList(people).subList(1, people.length));

                socialNetwork.put(name, followers);
            }

            scanner.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("file not founds");
        }
    }

    // Task 1: Calculate the density of the graph
    public double calculateGraphDensity()
    {
        int numNodes = socialNetwork.size();
        int numEdges = 0;
        for (Set<String> followers : socialNetwork.values())
        {
            numEdges += followers.size();
        }
        return (double) numEdges / (numNodes * (numNodes - 1));
    }

    // Task 2: Find the person with the most followers
    public String MostFollowedPerson()
    {
        int maxFollowers = 0;
        String MostFollowedPerson = "";
        for (Map.Entry<String, Set<String>> entry : socialNetwork.entrySet())
        {
            int numFollowers = entry.getValue().size();
            if (numFollowers > maxFollowers || (numFollowers == maxFollowers && entry.getKey().compareTo(MostFollowedPerson) < 0))
            {
                maxFollowers = numFollowers;
                MostFollowedPerson = entry.getKey();
            }
        }
        return MostFollowedPerson;
    }

    // Task 3: Find the person who follows the most people
    public String personWithMostFollowings()
    {
        int maxFollowings = 0;
        String personWithMostFollowings = "";
        for (Map.Entry<String, Set<String>> entry : socialNetwork.entrySet())
        {
            int numFollowings = entry.getValue().size();
            if (numFollowings > maxFollowings || (numFollowings == maxFollowings && entry.getKey().compareTo(personWithMostFollowings) < 0))
            {
                maxFollowings = numFollowings;
                personWithMostFollowings = entry.getKey();
            }
        }
        return personWithMostFollowings;
    }

    // Task 4: Calculate the number of people at two degrees of separation from the first person
    public int numPeopleAtTwoDegrees(String firstPerson)
    {
        Set<String> firstPersonFollowers = socialNetwork.get(firstPerson);
        Set<String> secondDegreePeople = new HashSet<>();
        for (String follower : firstPersonFollowers)
        {
            Set<String> followersOfFollower = socialNetwork.get(follower);
            for (String followerOfFollower : followersOfFollower)
            {
                if (!firstPersonFollowers.contains(followerOfFollower) && !followerOfFollower.equals(firstPerson))
                {
                    secondDegreePeople.add(followerOfFollower);
                }
            }
        }
        return secondDegreePeople.size();
    }

    // Task 5: Calculate the median number of followers
    public double calculateMedianFollowers()
    {
        List<Integer> numFollowersList = new ArrayList<>();
        for (Set<String> followers : socialNetwork.values())
        {
            numFollowersList.add(followers.size());
        }
        Collections.sort(numFollowersList);
        int size = numFollowersList.size();
        if (size % 2 == 0)
        {
            return (double) (numFollowersList.get(size / 2 - 1) + numFollowersList.get(size / 2)) / 2;
        } else
        {
            return numFollowersList.get(size / 2);
        }
    }

    // Task 6: get the most followers for partnership
    public String BrandAmbassador()
    {
        return MostFollowedPerson();
    }

}