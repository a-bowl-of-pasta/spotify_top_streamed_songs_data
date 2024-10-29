package BACKEND_LOGIC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
