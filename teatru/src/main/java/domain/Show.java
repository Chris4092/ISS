package domain;

import javax.persistence.Tuple;
import java.util.ArrayList;

public class Show extends Entity<Long> {
    private String date;
    private String time;
    private Integer ticketPrice;
    private String title;
    private String description;
    private ArrayList<ArrayList<Integer>> freeSeatList;
    private String freeSeatListString;

    public ArrayList<ArrayList<Integer>> getFreeSeatList() {
        return freeSeatList;
    }

    public void setFreeSeatList(ArrayList<ArrayList<Integer>> freeSeatList) {
        this.freeSeatList = freeSeatList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFreeSeatListString() {
        return freeSeatListString;
    }

    public void setFreeSeatListString(String freeSeatListString) {
        this.freeSeatListString = freeSeatListString;
    }

    public Show(String date, String time, Integer ticketPrice, String title, String description, String freeSeatListString) {
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
        this.title = title;
        this.description = description;
        this.freeSeatListString = freeSeatListString;
    }

    public void encode(){
        String s = "";
        String a = "111213141516171819212223242526272829";
        for(int i = 0; i < freeSeatList.size(); i++)
        {
            s+=freeSeatList.get(i).get(0).toString();
            s+=freeSeatList.get(i).get(1).toString();
        }
        freeSeatListString = s;
    }

    public void decode(String s){
        ArrayList<ArrayList<Integer>> constructor = new ArrayList<>();
        for(int i = 0; i < s.length(); i+=2)
        {
            Integer rand = Character.getNumericValue(s.charAt(i));
            Integer loc = Character.getNumericValue(s.charAt(i+1));
            ArrayList<Integer> listuta = new ArrayList<>();
            listuta.add(rand);
            listuta.add(loc);
            constructor.add(listuta);
        }
        freeSeatList = constructor;
    }

    public Show() {
    }
}
