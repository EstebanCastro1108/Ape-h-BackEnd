package co.edu.unbosque.apeh.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordChangeNotification(String to, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Cambio de Contraseña Exitoso");
        message.setText("Tu contraseña temporal es: " + newPassword+"\nTe Recomendamos que ingreses y la cambies lo más pronto posible");
        emailSender.send(message);
    }
}
