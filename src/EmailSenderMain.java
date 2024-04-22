import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Properties;




public class EmailSenderMain {
    public static void main(String[] args) {
        EmailScheduler testSpamSchedule = new EmailScheduler();
        testSpamSchedule.dailyEmailRun(14, 0);
    }

    public static void sendEmails(){

                //email used to send the test spam
                String senderEmail = "s1mplescripttestf0raproject@outlook.com"; //test email
                String password = "Wif#4TYbf7Uh$IOn9"; //test email password

                //set the properties for the email sending service
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.outlook.com"); //using outlook
                properties.put("mail.smtp.port", "587");

                //create a session for the email service with the specified sender account
                Session emailSession = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, password);
                    }
                });

                //get receivers email list
                List<String> emails = FileInput.loadEmails();

                for(String email: emails) {
                    try {

                        String emailBodyContents = FileInput.loadEmailMessage();

                        //create the MimeMessage email object
                        Message emailMessage = new MimeMessage(emailSession);
                        emailMessage.setFrom(new InternetAddress(senderEmail));
                        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                        emailMessage.setSubject("URGENT! PLEASE RESPOND!");
                        emailMessage.setText(emailBodyContents);

                        //send current email
                        Transport.send(emailMessage);
                        System.out.print("\nSuccess! Email sent to " + email);

                    } catch (MessagingException e) {
                        System.out.println("Failed to send message to " + email + " : " + e);
                    }
                }
    }
}
