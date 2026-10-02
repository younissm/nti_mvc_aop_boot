public class NotificationServiceImpl implements NotificationService{

    @Override
    public void sendEmail(String to, String message) {
        System.out.println("An email was sent to "+ to + ".");
        System.out.println("Email content: " + message);
    }

    @Override
    public void sendSms(String to, String message) {
        System.out.println("An SMS was sent to "+ to + ".");
        System.out.println("SMS content: " + message);
    }
}
