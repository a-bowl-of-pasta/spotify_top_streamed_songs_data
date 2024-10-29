import BACKEND_LOGIC.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main
{
    //========== data displays ===========
    public static void listDisp(List<SongData> song)
    {
        System.out.println(song.toString());
    }
    public static void mapDisp(Map<String,Integer> percents)
    {

        for(Map.Entry<String, Integer> entry : percents.entrySet()) {
         System.out.println(entry.getKey() + ": " + entry.getValue() +"%");
        }
    }
    //=========== main meth ============
    public static void main(String[] args) throws IOException {
        // === getting the data ===
        dataStuff processing = new dataStuff();
        List<SongData> toBeUsed = processing.readCsv();
        Map<String, Integer> percents = processing.genKeyPercent(toBeUsed);

        // === displaying method calls ===

        //listDisp(toBeUsed);
        //mapDisp(percents);
        System.out.println("the artist with the most popular songs is :: " + processing.getTopArtists(toBeUsed));

    }
}