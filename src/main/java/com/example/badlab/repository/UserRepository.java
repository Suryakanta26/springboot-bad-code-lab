
package com.example.badlab.repository;
import java.io.*;
public class UserRepository{
public String read(String f)throws Exception{
FileInputStream fis=new FileInputStream(f);
return new String(fis.readAllBytes());
}
}