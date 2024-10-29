package BACKEND_LOGIC;

public class SongData {
    String songKey = null;
    String modeType = null;
    String artistName = null;
    String songName = null;
    int dancabilityRating =0;
    double streamCount = 0.0;

    public SongData(String listens, String songName, String artist, String key, String mode, String dancability)
    {
        try {
            streamCount = Double.parseDouble(listens);
            this.songName = songName;
            artistName = artist;
            songKey = key;
            modeType = mode;
            dancabilityRating = Integer.parseInt(dancability);
        }catch (Exception e)
        {
            songKey = null;
            modeType = null;
            this.songName = null;
            artistName = null;
            dancabilityRating = 0;
            streamCount = 0.0;
        }
    }

    public String getKey(){return songKey;}
    public String getMode(){return modeType;}
    public String getArtist(){return artistName;}
    public int getDancability(){return dancabilityRating;}
    public double getStreamCount(){return streamCount;}
    public boolean isEmpty(){
        if(songKey == null || artistName == null || modeType == null)
            return true;
        else if(dancabilityRating == 0 || streamCount == 0.0)
            return true;

        return false;
    }

    @Override
    public String toString() {
        String readableFormat= "artistName :: "+ artistName;
        readableFormat += "\nsongName :: "+ songName;
        readableFormat += "\nmodeType :: "+ modeType;
        readableFormat += "\ndancabilityRating :: "+ dancabilityRating;
        readableFormat += "\nstreamCount :: "+ streamCount;
        readableFormat+= "\n\n";
        return readableFormat;
    }
}