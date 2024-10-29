package BACKEND_LOGIC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class dataStuff {
    // ============= logic using file data =================
    public Map<String, Integer> genKeyPercent(List<SongData> songs)
    {
        int totalSongs = songs.size();
        Map<String, Integer> keyPercent = songs.stream()
                .filter(data -> data.getKey() != null )
                .collect(Collectors.groupingBy
                        (SongData::getKey,
                        Collectors.collectingAndThen
                                (Collectors.counting(), count -> ((int)((count / (double) totalSongs) * 100.0)))
                        ));
        int fixVals = keyPercent.get("");
        keyPercent.remove("");
        keyPercent.put("C", fixVals);
        return keyPercent;
    }


    // =========== gen list with file data =========
    public List<SongData> readCsv() throws IOException {
        List<SongData> validSongs = Files.lines(Paths.get("C:\\Users\\antoh\\java_intellijFiles\\my_labs\\lab3\\src\\mostStreamedSongs.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .filter(data -> data.length >= 18)
                .map(data -> new SongData(
                        data[8],
                        data[0],
                        data[1],
                        data[15],
                        data[16],
                        data[17]))
                .filter(data -> !data.isEmpty())
                .toList();

            return validSongs;
    }


}
