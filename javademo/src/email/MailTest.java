package email;

import java.util.Date;

public class MailTest {
    public static void main(String[] args) {

    	MessageVo mv = new MessageVo();
    	
    	mv.setSubject("很遗憾，您的程序出bug了"+new Date());
    	
    	mv.setText("bug的具体内容为："+"空指针异常");
    	
    	mv.setToMailAddress("2456220221@qq.com");
    	
    	SendMail.sendMail(mv);
    }
}
