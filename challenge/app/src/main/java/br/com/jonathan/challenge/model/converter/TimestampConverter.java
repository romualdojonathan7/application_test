package br.com.jonathan.challenge.model.converter;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;

import androidx.room.TypeConverter;

public class TimestampConverter {

    @TypeConverter
    public static Timestamp toDate(Long timestamp) {
        return timestamp == null ? null : new Timestamp(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    }

//    @TypeConverter
//    public static Date fromTimestamp(Long value) {
//        return value == null ? null : new Date(value);
//    }
//
//    @TypeConverter
//    public static Long dateToTimestamp(Date date) {
//        return date == null ? null : date.getTime();
//    }

}
