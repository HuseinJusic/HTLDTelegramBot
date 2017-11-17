package at.htld.modules.handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class WebHandler {


    public String getdefaultData() throws IOException {
        final Document document = Jsoup.connect("http://abfahrtszeiten.at/index.cfm?bk=1,2&HstId=211_17410&job=dsp").get();
        String planAbfahrt = "";
        String ziel = "";

        for (Element row : document.select("table.cinfo")) {

            planAbfahrt += row.select("tr.li_odd td.bbottom.sc_zeit.bright").html();
            planAbfahrt += row.select("tr.li_even td.bbottom.sc_zeit.bright").html();
        }


        return ziel + planAbfahrt;
    }

    public ArrayList<String> getStationDataByLink(String url) throws IOException {
        final Document document = Jsoup.connect(url).get();
        ArrayList<String> planAbfahrt = new ArrayList<String>();
        String ziel = "";

        for (Element row : document.select("table.cinfo")) {
            planAbfahrt.add(row.selectFirst("tr.li_odd td.bbottom.sc_zeit.bright").text() + " " +  row.selectFirst("tr.li_odd td.bbottom.sc_zeit.bright.bold").text());
            planAbfahrt.add(row.selectFirst("tr.li_even td.bbottom.sc_zeit.bright").text() + " " + row.selectFirst("tr.li_even td.bbottom.sc_zeit.bright.bold").text());
        }

        return planAbfahrt;
    }
}
