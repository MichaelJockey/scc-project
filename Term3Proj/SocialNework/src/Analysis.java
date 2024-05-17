import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Analysis
{
    private static HashMap<String, Set<String>> socialNetwork;


    public static void main(String[] args) throws FileNotFoundException
    {
         socialNetwork =  new HashMap<>();
        if (args.length == 0)
        {
            System.out.println("Usage: java Analysis <file>");
            return;
        }
        String filePath= args[0];
        loadSocialNetwork(filePath);
        SocialNetwork Dapper = new SocialNetwork(socialNetwork);

        System.out.println("Graph Density: " + Dapper.GraphDensity());

        System.out.println("Most followed person: " + Dapper.MostFollowedPerson());

        System.out.println("Person who follows the most people: " + Dapper.personWithMostFollowings());

        System.out.println("Number of people at two degrees of separation: " + Dapper.numPeopleAtTwoDegrees());

        System.out.println("Median number: " + Dapper.calculateMedianFollowers());

        System.out.println("Brand Ambassador: " + Dapper.personForPartnership());
    }


    private static void loadSocialNetwork(String filePath)
    {
        try
        {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] people = line.split(" ");
                String name = people[0];
                Set<String> followers = new HashSet<>(Arrays.asList(people).subList(1, people.length));

                socialNetwork.put(name, followers);
            }

            scanner.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("file not found: " + filePath);
        }
    }
}


