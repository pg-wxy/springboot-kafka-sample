package hyron.kafka.demo.SpringBootDemo.control;

import hyron.kafka.demo.SpringBootDemo.domain.MyObject;
import hyron.kafka.demo.SpringBootDemo.producer.MyProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class MyProducerConctroller {
    @Autowired
    MyProducer producer;

    @RequestMapping(value = "/sender.action", method = RequestMethod.POST)
    public void exec(HttpServletRequest request, HttpServletResponse response, int user_id, String fname, String lname) throws IOException {
        MyObject user = new MyObject();
        user.setUser_id(user_id);
        user.setFname(fname);
        user.setLname(lname);
        this.producer.send("user-topic", user);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        response.getWriter().write("success");
        response.getWriter().flush();
        response.getWriter().close();
    }
}
