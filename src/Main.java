import BACKEND_LOGIC.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main
{

    //=========== main meth ============
    public static void main(String[] args) throws IOException {
        // === getting the data ===
        dataStuff processing = new dataStuff();
        List<SongData> toBeUsed = processing.readCsv();
        Map<String, Integer> percents = processing.genKeyPercent(toBeUsed);

        // === displaying method calls ===
        System.out.println("""
                :: Menu ::
                0) print all the song objects
                1) print the percentage each key makes up the top streamed
                2) print the top artist and most popular song
                3) print most popular level of dancability
                """);
        Scanner inp = new Scanner(System.in);
        int choice = inp.nextInt();

        switch(choice)
        {
            case 0:
                processing.listDisp(toBeUsed);
                break;
            case 1:
                processing.mapDisp(percents);
                break;
            case 2:
                System.out.println("the artist with the most popular songs is :: " + processing.getTopArtists(toBeUsed));
                break;
            case 3:
                System.out.println("the average song in 'the most streamed' has a  dancability rate of :: " + processing.calcDancability(toBeUsed));
                break;
            default:
                System.out.println("ERROR :: Invalid choice :: enter value 0-3");
        }

    }
}