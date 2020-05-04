package medbooking.bookingrequest.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        
        return DateTimeFormatter.ISO_DATE.format(date);
    }
}