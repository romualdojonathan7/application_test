package br.com.jonathan.challenge.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateDeserializer implements JsonDeserializer<Date> {

    private static final String[] DATE_FORMATS = new String[]{
            "EEE MMM dd HH:mm:ss zzz yyyy",
            "MMM dd, yyyy HH:mm:ss",
            "MMM dd, yyyy",
            "dd/MM/yyyy",
            "mmm dd, yyyy",
            "yyyy/MM/dd",
            "yyyy-MM-dd",
            "dd-MM-yyyy"
    };

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        for (String format : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(format, Locale.ENGLISH).parse(json.getAsString());
            } catch (ParseException e) {
            }
        }
        throw new JsonParseException("Unparseable date: \"" + json.getAsString()
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }
}
