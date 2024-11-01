package FRONTEND_GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

import BACKEND_LOGIC.*;
import org.jfree.chart.JFreeChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class statsVisuals extends JPanel
{
    JFreeChart pieChart;
    dataStuff values = new dataStuff();
    List<SongData> songList;
    Map<String, Integer> percents;

    JPanel piePanel;
    JPanel artPanel;
    JPanel listPanel;
    JPanel dancePanel;

    public statsVisuals() throws IOException {
        songList = values.readCsv();
    }

    public JPanel dispPie()
    {


        return piePanel;
    }
    public JPanel dispArt()
    {


        return artPanel;
    }
    public JPanel dispList()
    {
        String[] columnHeads = {"artist", "song", "song key", "song mode", "level of dancability %", "stream count"};

        List<String[]> elementData = new ArrayList<>();

        for(SongData data : songList)
        {
            String[] elmData = {data.getArtist(), data.getSongName(), data.getKey(), data.getMode()
                    , ("" + data.getDancability()), (""+data.getStreamCount())};

            if(!elmData[0].isBlank() && !elmData[1].isBlank() && !elmData[2].isBlank()
                    && !elmData[3].isBlank() && !elmData[4].isBlank() && !elmData[5].isBlank()) {
                elementData.add(elmData);
            }
        }

        DefaultTableModel dataInfo = new DefaultTableModel(0, columnHeads.length);
        dataInfo.setColumnIdentifiers(columnHeads);

        for(String[] data : elementData)
        {
            dataInfo.addRow(data);
        }

        JTable listTable = new JTable(dataInfo);

        JScrollPane dataViewing = new JScrollPane(listTable);
        listPanel = new JPanel();
        listPanel.add(dataViewing);

        return listPanel;
    }
    public JPanel dispDance()
    {


        return dancePanel;
    }

}
