package com.example.badlab.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;

@RestController
@CrossOrigin(origins = "*")
public class VulnerableExamplesController {
    private static final String DATABASE_PASSWORD = "root:toor";

    @GetMapping("/bad/command")
    public String commandInjection(@RequestParam String cmd) throws Exception {
        Process process = Runtime.getRuntime().exec(cmd);
        return new String(process.getInputStream().readAllBytes());
    }

    @GetMapping("/bad/expression")
    public String expressionInjection(@RequestParam String expression) {
        ExpressionParser parser = new SpelExpressionParser();
        Object value = parser.parseExpression(expression).getValue();
        return String.valueOf(value);
    }

    @GetMapping("/bad/sql")
    public String sqlInjection(@RequestParam String username) {
        return "select * from users where username = '" + username + "' and active = true";
    }

    @GetMapping("/bad/file")
    public String pathTraversal(@RequestParam String path) throws Exception {
        return Files.readString(Paths.get(path));
    }

    @GetMapping("/bad/ssrf")
    public String ssrf(@RequestParam String url) throws Exception {
        return new String(new URL(url).openStream().readAllBytes());
    }

    @GetMapping("/bad/redirect")
    public ResponseEntity<Void> openRedirect(@RequestParam String next) {
        return ResponseEntity.status(302).header(HttpHeaders.LOCATION, URI.create(next).toString()).build();
    }

    @PostMapping("/bad/deserialize")
    public String unsafeDeserialize(@RequestBody String base64Payload) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(base64Payload);
        ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object object = stream.readObject();
        return object.getClass().getName();
    }

    @PostMapping("/bad/xml")
    public String xxe(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml))).getDocumentElement().getNodeName();
    }

    @GetMapping("/bad/token")
    public String predictableToken(@RequestParam(defaultValue = "guest") String user) {
        Random random = new Random(12345);
        return user + "-" + random.nextInt(999999);
    }

    @GetMapping("/bad/hash")
    public String weakHash(@RequestParam String value) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        return Base64.getEncoder().encodeToString(digest.digest(value.getBytes()));
    }

    @GetMapping("/bad/cookie")
    public String insecureCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("session", DATABASE_PASSWORD);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        response.addCookie(cookie);
        return "cookie set";
    }
}
