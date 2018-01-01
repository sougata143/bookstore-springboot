package com.sougata.bookstore.domain;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.MediaType;

public final class Constants {
    private Constants(){}

    public static final long RESERVED_FILESYSTEM_SPACE = 5242880L;
    public static final long MIN_FILESIZE = 1024L;
    public static final long MAX_FILESIZE = 1048576L;
    public static final String[] ACCEPTED_MIME = new String[]{MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE};
    public static final String UPLOAD_FILE_FORMAT = "PH%s_%d_%s";


    public static final String API_ROOT_URL = "http://localhost:8081";
    public static final String ACTIVATION_EMAIL_SUBJECT = "Phew Account Activation";
    public static final int ACTIVATION_KEY_LENGTH = 6;
    /**
     * JWT Constants
     */
    public static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;
    public static final String AUTH_HEADER = "X-Auth-Token";

    /**
     * Enumeration used while contructing a new JWT(Json Web Token). Audience denotes
     * the type of user holding the JSON token.
     */
    public static enum AudienceType {
        AUDIENCE_TYPE_USER,
        AUDIENCE_TYPE_ADMIN
    }

    /**
     * Pagination defaults
     */
    public static final int DEFAULT_PAGE_SIZE = 12;
    public static final int DEFAULT_PAGE = 0;
    public static final int MAXIMUM_PAGE_SIZE = 30;

    public static final int MAXIMUM_SUGGESTION_LIMIT = 8;
    public static final int SUGGESTION_PAGE = 1;

    public static final int DEFAULT_NEARBY_LOCATIONS_RADIUS = 10;

    /**
     * Regular Expression patterns for validation
     */
    public static final String ISO_PATTERN = "(?<![A-Z])[A-Z]{3}(?![A-Z])";
    public static final String COUNTRY_CODE_PATTERN = "(?<![A-Z])[A-Z]{2}(?![A-Z])";
    public static final String TIME_PATTERN_24 = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
    public static final String TIME_PATTERN_12 = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
    public static final String WORDS_PATTERN = "^[a-zA-Z ()]*$";
    public static final String WORDS_WITH_SPACES_PATTERN = "^[a-zA-Z ]*$";
    public static final String WORD_PATTERN = "^[a-zA-Z]*$";
    public static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    public static final String ALPHANUMERIC_WITH_SPACES_PATTERN = "^[a-zA-Z0-9 ]*$";
    public static final String USERNAME_PATTERN = "(?=^.{6,32}$)^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+$";
    public static final String EMAIL_PATTERN = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";

    public static class DatabaseQueries {

        private DatabaseQueries() {}

        //public static final String NEARBY_LOCATIONS_SQL = "SELECT loc FROM Location loc WHERE FUNCTION('acos',FUNCTION('sin',FUNCTION('radians',loc.latitude)) * FUNCTION('sin',FUNCTION('radians',loc.latitude)) + FUNCTION('cos',FUNCTION('radians',loc.latitude)) * FUNCTION('cos',FUNCTION('radians',:latitude)) * FUNCTION('cos',FUNCTION('radians',loc.longitude) - FUNCTION('radians',:longitude))) * 6380 <= :radius";
//        public static final String NEARBY_LOCATIONS_SQL = "SELECT location_id,city_id,short_address,locality,postal_code,latitude,longitude, ROUND(ACOS(SIN(RADIANS(`latitude`)) * SIN(RADIANS(:latitude)) + COS(RADIANS(`latitude`)) * COS(RADIANS(:latitude)) * COS(RADIANS(`longitude`) - RADIANS(:longitude))) * 6380) AS `distance` FROM `locations` WHERE ACOS(SIN(RADIANS(`latitude`)) * SIN(RADIANS(:latitude)) + COS(RADIANS(`latitude`)) * COS(RADIANS(:latitude)) * COS(RADIANS(`longitude`) - RADIANS(:longitude))) * 6380 <= :radius ORDER BY `distance`";
//        public static final String NEARBY_RESTAURANTS_SQL = "SELECT *, ROUND(ACOS(SIN(RADIANS(`latitude`)) * SIN(RADIANS(:latitude)) + COS(RADIANS(`latitude`)) * COS(RADIANS(:latitude)) * COS(RADIANS(`longitude`) - RADIANS(:longitude))) * 6380) AS `distance` FROM `locations` INNER JOIN restaurants USING(location_id) WHERE ACOS(SIN(RADIANS(`latitude`)) * SIN(RADIANS(:latitude)) + COS(RADIANS(`latitude`)) * COS(RADIANS(:latitude)) * COS(RADIANS(`longitude`) - RADIANS(:longitude))) * 6380 <= :radius ORDER BY `distance`";
//        public static final String NEARBY_LOCATIONS_COUNT_SQL = "SELECT COUNT(*) FROM `locations` WHERE ACOS(SIN(RADIANS(`latitude`)) * SIN(RADIANS(:latitude)) + COS(RADIANS(`latitude`)) * COS(RADIANS(:latitude)) * COS(RADIANS(`longitude`) - RADIANS(:longitude))) * 6380 < :radius";
//
//        public static final String RESTAURANT_SEARCH_QUERY = "SELECT DISTINCT res FROM Restaurant res "
//                + "LEFT JOIN res.restaurantCuisines cuisines "
//                + "LEFT JOIN cuisines.cuisine cuisine "
//                + "LEFT JOIN res.location loc "
//                + "LEFT JOIN loc.city city "
//                + "WHERE city.name LIKE :query% "
//                + "OR loc.locality LIKE :query% "
//                + "OR loc.shortAddress LIKE :query% "
//                + "OR res.restaurantName LIKE :query% "
//                + "OR res.streetAddress LIKE :query% "
//                + "OR cuisine.cuisineName LIKE :query% ";
        final String SELECT_ALL_USERS = "SELECT * from user";
        final String SELECT_ALL_BOOKS = "SELECT * from book";

    }
}
