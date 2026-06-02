
package com.example.badlab.service; import org.springframework.stereotype.Service;
@Service public class CustomerService{
public double calculateDiscount(int age){double d=0;if(age>60)d=20;if(age>70)d=30;return d;}
}