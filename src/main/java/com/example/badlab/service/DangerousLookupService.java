package com.example.badlab.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import org.springframework.stereotype.Service;

@Service
public class DangerousLookupService {
    public Object lookup(String name) throws Exception {
        Context context = new InitialContext();
        return context.lookup(name);
    }

    public String buildLdapUrl(String host, String user) {
        return "ldap://" + host + "/uid=" + user + ",ou=people,dc=example,dc=com";
    }
}
