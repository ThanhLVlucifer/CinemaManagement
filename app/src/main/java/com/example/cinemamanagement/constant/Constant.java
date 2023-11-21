package com.example.cinemamanagement.constant;

public interface Constant {
    String FIREBASE_URL = "https://cinema-management-dfd3b-default-rtdb.firebaseio.com";
    String FIREBASE_MOVIE_TABLE = "movies";
    String FIREBASE_GENRE_TABLE = "genres";
    String FIREBASE_ROOM_TABLE = "rooms";
    String FIREBASE_SCREENING_TABLE = "screenings";
    String FIREBASE_SEAT_TABLE = "seats";
    String FIREBASE_LOOKUP_TABLE = "lookups";
    String FIREBASE_PRODUCT_TABLE = "products";
    String FIREBASE_BOOKING_TABLE = "bookings";

    String NAME_COLUMN = "name";
    String START_TIME_COLUMN = "startTime";
    String GENREID_COLUMN = "genreIds";

    String AVAILABLE_STATUS = "available";
    String UNAVAILABLE_STATUS = "unavailable";

    String CHILD_ID = "id";

    String CURRENCY_VND = " 000đ";
    String CURRENCY_USD = " USD";
    String PER_ONE_TICKET = " /1 vé";
    int TYPE_PAYMENT_CASH = 1;
    int TYPE_PAYMENT_OTHER = 2;
    String PAYMENT_METHOD_CASH = "Tiền mặt";
    String PAYMENT_METHOD_PAYPAL = "PayPal";

    String KEY_INTENT_MOVIE_OBJECT = "movie_object";
    String KEY_INTENT_URI = "uri";
    String KEY_INTENT_GENRE_OBJECT = "genre_object";
    String KEY_INTENT_PRODUCT_OBJECT = "product_object";

    String UID_ADMIN = "OOz9J2eGk6dLoAQNPb4yZc7nx913";

    int FLAG = -1;
}
