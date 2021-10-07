package mailSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainExec {
	public static void main(String argv[]) {
		MailSend ms = new MailSend();
		String subject = argv[0]; //�D��
	    //String exectime = argv[1]; //����ɶ�
	    String errorfile = argv[1]; //�����ɮ�
	    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date current = new Date();
	    String exectime=sdFormat.format(current);
	    String body= "=====================================================\r\n" + 
		  		"";
	    body +="����ɶ� :"+exectime+"\r\n";
	    body += "=====================================================\r\n" + 
	  		"";
	    if(!errorfile.equals("")) {
	          body +="�����ɮ�: "+errorfile+"\r\n";
	    }
	    String managerMail = argv[2];//�n�e�F���H�cguanzhou711@gmail.com
	    String from = argv[3];//�H��H��email
	    String user = argv[4];//user�b��
        String pwd = argv[5];//password
        String host = argv[6];
        String port = argv[7];//prot 465
        /*
         * host
         * yahoo:"smtp.mail.yahoo.com"
         * outlook:"smtp-mail.outlook.com"
         * google:"smtp.gmail.com"
         */
        FileWriter fw = null;
		checkfolder("log");
		try {
			Date now=new Date();
			SimpleDateFormat folderFmt=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fileFmt=new SimpleDateFormat("yyyy-MM-dd-HHmm");
			checkfolder("log/"+folderFmt.format(now));
			fw = new FileWriter("log/"+folderFmt.format(now)+"/"+fileFmt.format(now)+"-checkcsv-"+errorfile+".txt");
			fw.write("/r/n�D��:"+subject);
			fw.write("/r/n���e:"+body);
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // �i�H�۰ʫإ�
		
        String[] email_arr = managerMail.split(";");
		for(int i=0;i<email_arr.length;i++) {
			ms.sendMail(email_arr[i],subject,body,user,pwd,from,host,port);
		}
	}
	public static void checkfolder(String folderPosition){
		File file =new File(folderPosition);    
		//�p�G��Ƨ����s�b�h�إ�    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    System.out.println("��Ƨ����s�b");  
		    file .mkdir();
		} else   
		{  
		    System.out.println("��Ƨ��s�b");  
		}  
	}
}
