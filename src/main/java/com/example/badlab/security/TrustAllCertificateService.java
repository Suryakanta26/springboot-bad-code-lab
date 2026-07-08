package com.example.badlab.security;

import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class TrustAllCertificateService {
    public SSLContext insecureSslContext() throws Exception {
        TrustManager[] trustAllManagers = new TrustManager[] {
            new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }
        };
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, trustAllManagers, new java.security.SecureRandom());
        return context;
    }

    public HostnameVerifier allowAnyHostname() {
        return (hostname, session) -> true;
    }
}
