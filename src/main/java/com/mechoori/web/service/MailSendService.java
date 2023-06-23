package com.mechoori.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@Component
public class MailSendService {
    @Autowired
    private JavaMailSender mailSender;
    private int authNumber;
    // 난수 발생(여러분들 맘대러)

    public void makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
        authNumber = checkNum;
    }


    //이메일 보낼 양식!
    public String joinEmail(String email) {
        makeRandomNumber();
        String setFrom = ".com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "홈페이지를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 !
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
        mailSend(setFrom, email, title, content);
        return Integer.toString(authNumber);
    }

    // 이메일 전송 메소드
// MailSendService 클래스의 mailSend 메서드 내부

    public void mailSend(String setFrom, String toMail, String title, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText(content, true);

            // 암호화 알고리즘 및 프로토콜 설정
            JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) mailSender;
            mailSenderImpl.setProtocol("smtps"); // or "smtp"
            Properties javaMailProperties = mailSenderImpl.getJavaMailProperties();
            javaMailProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2"); // 사용 가능한 암호화 알고리즘을 지정합니다.

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // 예외가 발생한 경우, 적절한 오류 메시지를 사용자에게 표시할 수 있습니다.
            System.out.println("이메일 전송 중 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }



}