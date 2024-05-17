//package com.mj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Set;

public class SocialNetwork
{

    private HashMap<String, Set<String>> socialNetwork;

    public SocialNetwork(HashMap<String, Set<String>> socialNetwork)
    {
        this.socialNetwork = socialNetwork;
    }

    // Task 1: Calculate the density of the graph
    public double GraphDensity()
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
    { // Method to find the person whose name appears the most times in the map
        Map<String, Integer> nameCount = new HashMap<>(); // Map to store the count of occurrences of each person's name
        for (Map.Entry<String, Set<String>> entry : socialNetwork.entrySet())
        {// Looping through each entry in the social network map
            String person = entry.getKey(); // Getting the person's name
            nameCount.put(person, nameCount.getOrDefault(person, 0) + 1); // Incrementing the count for the person's name
            for (String follower : entry.getValue())
            { // Looping through each follower of the person
                nameCount.put(follower, nameCount.getOrDefault(follower, 0) + 1); // Incrementing the count for the follower's name
            }
        }
        int maxCount = 0; // Initializing the maximum count
        String MostFollowedPerson = ""; // Initializing the person with the most followers
        for (Map.Entry<String, Integer> entry : nameCount.entrySet())
        {
            // Checking if the current person has more occurrences or if they have the same number of occurrences but come earlier alphabetically
            if (entry.getValue() > maxCount || (entry.getValue() == maxCount && entry.getKey().compareTo(MostFollowedPerson) < 0))
            {
                maxCount = entry.getValue(); // Updating the maximum count
                MostFollowedPerson = entry.getKey(); // Updating the person with the most occurrences
            }
        }
        return MostFollowedPerson; // Returning the person with the most occurrences
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
    public int numPeopleAtTwoDegrees()
    {  // Find the alphabetically first person in the network
        String firstPerson = socialNetwork.keySet().stream().min(String::compareTo).orElse(null);

        if (firstPerson == null)
        {
            return 0; // Return 0 if the network is empty
        }
        Set<String> firstPersonFollowers = socialNetwork.get(firstPerson); // Gets the followers of the first person
        Set<String> PeopleAtSecondDegree = new HashSet<>(); // intializes set "PeopleAtSecondDegree"
        for (String follower : firstPersonFollowers)
        {
            Set<String> followersOfFollower = socialNetwork.get(follower); // Gcd ets the folllowers of a follower of the firts person
            for (String followerOfFollower : followersOfFollower)
            {
                if (!firstPersonFollowers.contains(followerOfFollower) && !followerOfFollower.equals(firstPerson))
                {
                    PeopleAtSecondDegree.add(followerOfFollower);
                }
            }
        }
        return PeopleAtSecondDegree.size();
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
    public String personForPartnership()
    { // Method to find the brand ambassador for spreading information
        String brandAmbassador = ""; // Initialize the brand ambassador
        int maxUniqueRecipients = 0; // Initialize the maximum number of unique recipients
        for (String person : socialNetwork.keySet())
        { // Iterate through each person in the social network
            Set<String> visited = new HashSet<>(); // Set to track visited individuals to avoid message redundancy
            Queue<String> queue = new LinkedList<>(); // Queue for breadth-first traversal
            queue.offer(person); // Add the current person to the queue
            visited.add(person); // Mark the current person as visited
            int uniqueRecipients = 0; // Initialize the number of unique recipients for the current person
            while (!queue.isEmpty()) { // Continue traversal while the queue is not empty
                String currentPerson = queue.poll(); // Retrieve the current person from the queue
                Set<String> followers = socialNetwork.get(currentPerson); // Get the followers of the current person
                for (String follower : followers)
                { // Iterate through each follower of the current person
                    if (!visited.contains(follower))
                    { // Check if the follower has not been visited before
                        queue.offer(follower); // Add the follower to the queue for traversal
                        visited.add(follower); // Mark the follower as visited
                        uniqueRecipients++; // Increment the number of unique recipients
                    }
                }
            }
            int currentRecipients = maxUniqueRecipients-1;
            if (uniqueRecipients > currentRecipients) { // Check if the current person has more unique recipients than the current maximum
                currentRecipients = uniqueRecipients; // Update the maximum number of unique recipients
                brandAmbassador = person; // Update the brand ambassador
            }
        }
        return brandAmbassador; // Return the brand ambassador for spreading information
    }
}