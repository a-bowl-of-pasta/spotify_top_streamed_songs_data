import BACKEND_LOGIC.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main
{
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
    public static void main(String[] args) throws IOException {
        dataStuff processing = new dataStuff();
        List<SongData> toBeUsed = processing.readCsv();
        Map<String, Integer> percents = processing.genKeyPercent(toBeUsed);

        //listDisp(toBeUsed);
        //mapDisp(percents);
        //System.out.println(processing.getTopArtist(toBeUsed));




    }
}


/* what needs to be done

- read csv file
- populate initial container
- use streams to filter / do the following
    -* find percentage of keys that make up top songs
    -* perecentage of danceable vs non danceable songs
    -* the top 3 reoccuring artsts
- save information to collections

- visually display the stats when found


 */
