
package com.example.badlab.security;
public class InsecureSecurityConfig{
public boolean authenticate(String u,String p){
return "admin".equals(u)&&"password".equals(p);
}
}