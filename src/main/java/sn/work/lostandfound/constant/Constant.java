package sn.work.lostandfound.constant;

public class Constant {
    public static String SCORE_URL = "http://localhost:5000/match";
    public static String PAYMENT_URL = "https://paydunya.com/sandbox-checkout/invoice/";
    public static String OBJET_FOUND_MOTIF = "OBJET_FOUND";
    public static String OBJET_LOST_MOTIF = "OBJET_LOST";
    public static String OBJET_FOUND_DESCRIPTION = "Vous avez déclaré avoir trouvé un objet qui correspond à un objet perdu: ";
    public static String OBJET_LOST_DESCRIPTION = "Quelqu'un a déclaré avoir trouvé un objet similaire à votre objet perdu: ";
    public static String CORRESPONDENCE_STATUS_PENDING = "PENDING";
    public static String CORRESPONDENCE_STATUS_SUCCESS = "SUCCESS";
    public static double PAYMENT_AMOUNT = 1000;
    public static String INVOICE_DESCRIPTION = "Paiement";
    public static String STORE_NAME = "Objet perdu";
    public static String PAYMENT_STATUS_PENDING = "Pending";
    public static String PAYMENT_STATUS_SUCCESS = "Completed";
    public static String PAYMENT_STATUS_CANCELLED = "Cancelled";
    public static String ACTION_CANCEL_URL = "http://senwork.com/cancel_url";
    public static String ACTION_CALLBACK_URL = "http://senwork.com/callback_url";
    public static String ACTION_RETURN_URL = "http://senwork.com/return_url";

    public static String CATEGORY_STORAGE_PATH = "/app/images/category-images/";
    public static String OBJECT_STORAGE_PATH = "/app/images/object-images/";
//    public static String CATEGORY_STORAGE_PATH = "src/main/resources/images/category-images/";
//    public static String OBJECT_STORAGE_PATH = "src/main/resources/images/object-images/";

}
