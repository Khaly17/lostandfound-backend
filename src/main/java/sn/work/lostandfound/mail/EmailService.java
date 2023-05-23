package sn.work.lostandfound.mail;

/**
 * @author DIENG Khaly (MS2E)
 */
public interface EmailService {
    /**
     *
     * @param to
     * @param subject
     * @param text
     */
    void sendEmail(String to, String subject, String text);
}
