package BACKEND_LOGIC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class dataStuff {
    // ============= logic using file data =================
    // finds the percentage of each key in the song list
    public Map<String, Integer> genKeyPercent(List<SongData> songs)
    {
        int totalSongs = songs.size();

        Map<String, Integer> keyPercent = songs.stream()
                .filter(data -> data.getKey() != null ) // filter out null values
                .collect(Collectors.groupingBy // group by keys then convert to 'key : percent' map
                        (SongData::getKey,
                        Collectors.collectingAndThen // count occurrences then convert to percent
                                (Collectors.counting(), count -> ((int)((count / (double) totalSongs) * 100.0)))
                        ));
        // --- fixes output for key of 'C'
        int fixVals = keyPercent.get("");
        keyPercent.remove("");
        keyPercent.put("C", fixVals);

        return keyPercent;
    }
    // gets the top artist ; will be made to get top 3-5
    public String getTopArtists(List<SongData> songs)
    {
        // groups by artist then counts the occurrences
        Map<String, Long> artistTally = songs.stream()
                .collect(Collectors.groupingBy(SongData::getArtist,Collectors.counting()));

        String topArtist = "";
        int occurenceCount = 0;

        // --- finds the top artists
        for (Map.Entry<String, Long> entry : artistTally.entrySet()) {

            // current key's value > the last greatest key's value
            if (entry.getValue() > occurenceCount) {
                topArtist = entry.getKey();
                occurenceCount = entry.getValue().intValue();
            }

        }
        return (topArtist + " : " + occurenceCount + " occurrences");
    }
    public String calcDancability(List<SongData> songs)
    {
        // level of dancability : average streams by level of dancability
        Map<Integer, Double> streamsByPercent = songs.stream()
                .collect(Collectors.groupingBy(SongData::getDancability,
                        Collectors.averagingDouble(SongData::getStreamCount)));


        double mostStreamed = 0;
        int mvpercent = 0;

        // finds what level of dancability has the highest average streams
        for(Map.Entry<Integer, Double> entry : streamsByPercent.entrySet()){
            if(entry.getValue() > mostStreamed)
            {
                mostStreamed = entry.getValue();
                mvpercent = entry.getKey();
            }
        }
        // formats it from scientific notation to a mor readable decimal
        DecimalFormat df = new DecimalFormat("###,###.#");
        return ("(" + mvpercent +"% : " + df.format(mostStreamed) + " aveStreams)");
    }

    //========== data displays ===========
    public void listDisp(List<SongData> song)
    {
        System.out.println(song.toString());
    }
    public <fe, se> void mapDisp(Map <fe,se> percents)
    {
        for(Map.Entry<fe, se> entry : percents.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() );
        }
    }

    // =========== gen list with file data =========
    public List<SongData> readCsv() throws IOException {

        List<SongData> validSongs = Files.lines(Paths.get("src\\mostStreamedSongs.csv"))
                .skip(1) // skips csv file's initial header line
                .map(line -> line.split(",")) // converts splits and forms array
                .filter(data -> data.length >= 18)// ensures there is enough space for data
                .map(data -> new SongData // data of each line turned to song obj
                        (data[8],
                        data[0],
                        data[1],
                        data[15],
                        data[16],
                        data[17]))
                .filter(data -> !data.isEmpty()) // filters out empty song objs
                .toList(); // adds non-empty objs to list

            return validSongs;
    }
}
