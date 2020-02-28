package ru.textanalysis.common.util;

import ru.textanalysis.common.exception.RuTextanalysisException;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class ConnectionHelper {
    public static URL createURL(String url) throws RuTextanalysisException {
        try {
            return new URL(url);
        } catch (MalformedURLException ex) {
            throw new RuTextanalysisException("Не верный URL", ex);
        }
    }

    public static URLConnection createConnection(String urlString, boolean isCheckCertificate) throws RuTextanalysisException {
        URL url = createURL(urlString);
        if (urlString.matches(".*(https).*")) {
            return createHttpsURLConnection(url, isCheckCertificate);
        } else {
            return createHttpURLConnection(url);
        }
    }

    public static HttpsURLConnection createHttpsURLConnection(URL url, boolean isCheckCertificate) throws RuTextanalysisException {
        try {
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            if (!isCheckCertificate) {
                con.setSSLSocketFactory(createEmptySSLContext().getSocketFactory());
                con.setHostnameVerifier(createEmptyHostnameVerifier());
            }
            return con;
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            throw new RuTextanalysisException(ex.getMessage(), ex);
        }
    }

    public static SSLContext createEmptySSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, createEmptyTrustManager(), null);
        return sslContext;
    }

    public static HttpURLConnection createHttpURLConnection(URL url) throws RuTextanalysisException {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            throw new RuTextanalysisException(ex.getMessage(), ex);
        }
    }

    public static TrustManager[] createEmptyTrustManager() {
        return new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
    }

    public static HostnameVerifier createEmptyHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return s.equals(sslSession.getPeerHost());
            }
        };
    }
}
