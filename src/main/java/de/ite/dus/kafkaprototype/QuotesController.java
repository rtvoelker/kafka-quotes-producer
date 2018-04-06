package de.ite.dus.kafkaprototype;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class QuotesController {
    private Sender sender;

    public QuotesController(Sender sender) {
        this.sender = sender;
    }

    @RequestMapping(value = "/quotes", method = POST, consumes = APPLICATION_XML_VALUE)
    public HttpStatus importQuoteSet(@RequestBody String message) {
        if(StringUtils.isEmpty(message)) {
            return BAD_REQUEST;
        }
        sender.send("quotes", message);
        return OK;
    }
}
