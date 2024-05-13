import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
// greg helped me
public class Analysis
{
    private static HashMap<String, Set<String>> socialNetwork;


    public static void main(String[] args) throws FileNotFoundException
    {
        //for debugging
        String filename;
        if (args.length == 0)
        {
            filename = "social-network1.txt";
        }
        else
        {
            filename = args[0];
        }
        socialNetwork = new HashMap<>();
        System.out.println(filename);
        loadSocialNetwork(filename);

        System.out.println(socialNetwork.get("Abu"));

        //Do task 1 stuff here

    }


    private static void loadSocialNetwork(String filePath)
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
            System.out.println(new File(".").getAbsolutePath());
        }
    }
    SocialNetwork socialNetwork = new SocialNetwork();

    // Load social network from file
        socialNetwork.loadSocialNetwork("social_network.txt");

    // Task 1: Calculate the density of the graph
    double density = socialNetwork.calculateGraphDensity();
        System.out.println("Graph Density: " + density);

    // Task 2: Find the person with the most followers
    String mostFollowedPerson = socialNetwork.MostFollowedPerson();
        System.out.println("Person with the most followers: " + mostFollowedPerson);

    // Task 3: Find the person who follows the most people
    String personWithMostFollowings = socialNetwork.personWithMostFollowings();
        System.out.println("Person who follows the most people: " + personWithMostFollowings);

    // Task 4: Calculate the number of people at two degrees of separation from the first person
    String firstPerson = "";
    int numPeopleAtTwoDegrees = socialNetwork.numPeopleAtTwoDegrees(firstPerson);
        System.out.println("Number of people at two degrees of separation from " + firstPerson + ": " + numPeopleAtTwoDegrees);

    // Task 5: Calculate the median number of followers
    double medianFollowers = socialNetwork.calculateMedianFollowers();
        System.out.println("Median number of followers: " + medianFollowers);

    // Task 6: Get the brand ambassador
    String brandAmbassador = socialNetwork.BrandAmbassador();
        System.out.println("Brand Ambassador: " + brandAmbassador);
}


