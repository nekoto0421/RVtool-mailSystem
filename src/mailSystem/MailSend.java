package mailSystem;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
public class MailSend {
    /*
     * �ݭn
     * javamail.jar
     * session.jar
     * �p�Gimport�O�ϥ�Ctrl shift+O�A���o���import�n�ק令�o�ˡA���M���|import��O��
     * import javax.mail.Authenticator;
     * import javax.mail.PasswordAuthentication;
     */
    public int sendMail(String to,String subject,String body,final String user,final String pwd,String from,String host,String port){
    	/*
    	user = "johnny5610j67k601@gmail.com";//user�b��
        pwd = "z1x2c345";//password
        from = "johnny5610j67k601@gmail.com";//�H��H��email
        host ="smtp.gmail.com" ;
        */
        /*
         * host
         * yahoo:"smtp.mail.yahoo.com"
         * outlook:"smtp-mail.outlook.com"
         * google:"smtp.gmail.com"
         */

        // �إߤ@��Properties�ӳ]�wProperties
        Properties properties = System.getProperties();
        //�]�w�ǿ��w��smtp
        properties.setProperty("mail.transport.protocol", "smtp");
        //�]�wmail Server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);//outlook587
        
        properties.put("mail.smtp.auth", "false");//�ݭn���ұb���K�X
        //SSL authentication
        properties.put("mail.smtp.ssl.enable", "false");
        //properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.starttls.enable", "false");
        //System.setProperty("java.net.preferIPv4Stack" , "true");
       // �إߤ@��Session����A�ç�properties�Ƕi�h
        /*
        Session mailSession = Session.getInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                 return new PasswordAuthentication(user,pwd);
            }
        });  
        */
        Session mailSession = Session.getInstance(properties,null);
        try {
            //�إߤ@�� MimeMessage object.
            MimeMessage message = new MimeMessage(mailSession);
            //�]�w�l��
            message.setFrom(new InternetAddress(from)); // �]�w�H��H
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // �]�w����H
            message.setSubject(MimeUtility.encodeWord(subject, "UTF-8", "B")); // �]�w�D��

            // �ŧi�@��multipart , ���i�H�Ϥ��妳���P���q���A
            //�Ϩ�i�H�ΥΨӧ��a������ɮ�
            Multipart multipart = new MimeMultipart();
            //�Ĥ@�Ӭq��
            BodyPart messageBodyPart = new MimeBodyPart(); //�ŧi�@��BodyPart�ΥH���a����     
            messageBodyPart.setText(body);//�]�w����
            multipart.addBodyPart(messageBodyPart); //��BodyPart�[�JMultipart  
            
            message.setContent(multipart); //�]�weMultipart��messag��Content           
            Transport transport = mailSession.getTransport("smtp");
            //transport.connect(host ,user, pwd);
            transport.connect(host ,user, null);
            //�ǰe�H��         
            transport.sendMessage(message,message.getAllRecipients());
            
            System.out.println("�o�e���\");
            //�����s�u
            transport.close();
            return 1;
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return 0;
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    }
}