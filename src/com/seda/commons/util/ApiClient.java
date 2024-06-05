package com.seda.commons.util;

import com.esed.log.req.ExecLogPap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.db2.jcc.am.he;
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerInterface;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.xml.XmlParserSupport;
import com.seda.payer.commons.utility.StringUtility;

import javax.net.ssl.*;
import javax.ws.rs.HttpMethod;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.util.Base64Util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ApiClient {

    /**
     * Se lo si desidera, ho creato una classe HttpRequester
     * Alla suddetta classe è stata assegnata solamente la responsabilità di inviare richieste HTTP,
     * eliminando ad esempio qualsiasi logica di conversione del body della response
     * o logiche d'autenticazione.
     * Se serve qualcosa di più semplice, è possibile quindi utilizzare HttpRequester.
     */

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ExecLogPap.class);	

    enum AuthenticationType {
        NoAuth, BasicAuth, BearerToken, SSLAuth
    }

    private final String endpoint;

    private String basicAuthHeader;
    private String bearerAuthHeader;

    public ApiClient(String endpoint, String keyStorePath, String keyStoreType, String keyStorePassword, String trustStorePath, String trustStoreType, String trustStorePassword) {
        this(endpoint);
        this.authenticationType = AuthenticationType.SSLAuth;
        this.sslParameters = new SSLParameters(keyStorePath, keyStoreType, keyStorePassword, trustStorePath, trustStoreType, trustStorePassword);
    }

    private AuthenticationType authenticationType;

    private SSLParameters sslParameters;

    private Integer timeout = 5000;

    private boolean logAttivo = true;

    public ApiClient(String endpoint) {
        this.endpoint = endpoint;
    }
    public ApiClient(String endpoint, Integer timeout) {
        this.endpoint = endpoint;
        if (timeout != null) {
            this.timeout = timeout;
        }
    }
    public ApiClient(String endpoint, boolean logAttivo) {
        this.endpoint = endpoint;
        this.logAttivo = logAttivo;
    }
    public ApiClient(String endpoint, boolean logAttivo, Integer timeout) {
        this.endpoint = endpoint;
        this.logAttivo = logAttivo;
        if (timeout != null) {
            this.timeout = timeout;
        }
    }
    public ApiClient(String endpoint, String tokenBearer) {
        this.endpoint = endpoint;
        this.authenticationType = AuthenticationType.BearerToken;
        this.bearerAuthHeader = "Bearer " + tokenBearer;
    }
    public ApiClient(String endpoint, String username, String password) {
        this(endpoint);
        this.authenticationType = AuthenticationType.BasicAuth;
        this.basicAuthHeader = "Basic " + Base64Util.encode(username + ":" + password);
    }
    
    public synchronized <T> T post(String json, String path, Map<String, String> mappaHeader, Class<T> classeTipoRitorno) throws Exception {
        T response;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(endpoint + path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setReadTimeout(this.timeout);
            conn.setConnectTimeout(this.timeout);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // aggiungo header dalla mappa
            for (String headerKey : mappaHeader.keySet()) {
                conn.setRequestProperty(headerKey, mappaHeader.get(headerKey));
            }

            if (authenticationType == AuthenticationType.BasicAuth) {
                conn.setRequestProperty("Authorization", basicAuthHeader);
            } else if (authenticationType == AuthenticationType.BearerToken) {
                conn.setRequestProperty("Authorization", bearerAuthHeader);
            }  else if (authenticationType == AuthenticationType.SSLAuth) {
                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(this.sslParameters);
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            }
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            StringBuilder output = new StringBuilder();
            try (InputStreamReader is = new InputStreamReader(conn.getInputStream())) {
                try (BufferedReader br = new BufferedReader(is)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        output.append(line);
                    }
                }
            }
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            response = mapper.readValue(output.toString(), classeTipoRitorno);

        } catch (MalformedURLException err) {
            throw new Exception("MalformedURLException - " + err.getMessage(), err);
        } catch (Exception err) {
            throw new Exception("Exception - " + err.getMessage(), err);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return response;
    }
    
    public synchronized <T> T post(String json, String path, Class<T> c) throws Exception {
        return post(json, path, c, null);
    }

    private Map<LoggerInterface.LOGGER_PROPERTY, Object> getProperties(String method, String url, String request, String response, int statusCode, long timeTaken) {
        return new HashMap<LoggerInterface.LOGGER_PROPERTY, Object>() {{
            put(LoggerInterface.LOGGER_PROPERTY.ApiMethod, method);
            put(LoggerInterface.LOGGER_PROPERTY.ApiUri, url);
            put(LoggerInterface.LOGGER_PROPERTY.ApiRequestBody, request);
            put(LoggerInterface.LOGGER_PROPERTY.ApiResponseBody, response);
            put(LoggerInterface.LOGGER_PROPERTY.ApiResponseStatus, statusCode);
            put(LoggerInterface.LOGGER_PROPERTY.ApiElaborationTime, timeTaken);
            put(LoggerInterface.LOGGER_PROPERTY.Direction, "OUTBOUND");
        }};
    }
    
    public synchronized <T> T post(String json, String path, Class<T> c, Map<String, String> header) throws Exception {
        StringBuilder output = new StringBuilder();
        T response = null;
        HttpURLConnection connection = null;
        long  startTime = System.currentTimeMillis();
        try {
            URL url = new URL(endpoint + path);

            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setConnectTimeout(this.timeout);
            connection.setReadTimeout(this.timeout);
            connection.setRequestMethod(HttpMethod.POST.toString());
            connection.setRequestProperty("Content-Type", "application/json");

            if (authenticationType == AuthenticationType.BasicAuth) {
                connection.setRequestProperty("Authorization", basicAuthHeader);
            } else if (authenticationType == AuthenticationType.BearerToken) {
                connection.setRequestProperty("Authorization", bearerAuthHeader);
            } else if (authenticationType == AuthenticationType.SSLAuth) {
                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(this.sslParameters);
                ((HttpsURLConnection) connection).setSSLSocketFactory(sslSocketFactory);
            }
            if (header!=null && !header.isEmpty()) {
                for (String key : header.keySet()) {
                    connection.setRequestProperty(key, header.get(key));
                }
            }
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes());
            os.flush();

            try (InputStreamReader is = new InputStreamReader(connection.getInputStream())) {
                try (BufferedReader br = new BufferedReader(is)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        output.append(line);
                    }
                }
            }
            catch (IOException e) {
                try (InputStreamReader is = new InputStreamReader(connection.getErrorStream())) {
                    try (BufferedReader br = new BufferedReader(is)) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                        }
                    }
                }
            }
            connection.getResponseCode();
            if(c != null) {
                response = new ObjectMapper().readValue(output.toString(), c);
            }
        } catch (MalformedURLException err) {
            throw new Exception("MalformedURLException - " + err.getMessage(), err);
        } catch (Exception err) {
            throw new Exception("Exception - " + err.getMessage(), err);
        } finally {
            if (connection != null) {
                connection.disconnect();
                long timeTaken = System.currentTimeMillis() - startTime;

                if (logAttivo) {
                    int statusCode;
                    try {
                        statusCode = connection.getResponseCode();
                    } catch (IOException e) {
                        statusCode = -1;
                    }
                    logger.info("api request", getProperties("POST", endpoint + path, json, output.toString(), statusCode, timeTaken));
                }
            }
        }
        return response;
    }
    
    public synchronized void delete(String path) throws Exception {
        HttpURLConnection connection = null;
        long  startTime = System.currentTimeMillis();
        try {
            URL url = new URL(endpoint + path);

            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(this.timeout);
            connection.setReadTimeout(this.timeout);
            connection.setRequestMethod(HttpMethod.DELETE.toString());
            connection.setRequestProperty("Content-Type", "application/json");

            if (authenticationType == AuthenticationType.BasicAuth) {
                connection.setRequestProperty("Authorization", basicAuthHeader);
            } else if (authenticationType == AuthenticationType.BearerToken) {
                connection.setRequestProperty("Authorization", bearerAuthHeader);
            } else if (authenticationType == AuthenticationType.SSLAuth) {
                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(this.sslParameters);
                ((HttpsURLConnection) connection).setSSLSocketFactory(sslSocketFactory);
            }

            if (connection.getResponseCode() >= 400) {
                throw new Exception("Errore HTTP " + connection.getResponseCode() + " durante la chiamata ");
            }
        } catch (MalformedURLException err) {
            throw new Exception("MalformedURLException - " + err.getMessage(), err);
        } catch (Exception err) {
            throw new Exception("Exception - " + err.getMessage(), err);
        } finally {
            if (connection != null) {
                connection.disconnect();
                long timeTaken = System.currentTimeMillis() - startTime;

                if(logAttivo) {
                    int statusCode;
                    try {
                        statusCode = connection.getResponseCode();
                    } catch (IOException e) {
                        statusCode = -1;
                    }
                    logger.info("api request", getProperties("DELETE", endpoint + path, null, null, statusCode, timeTaken));
                }
            }
        }
    }
    
    public synchronized <T> T put(String json, String path, Class<T> responseClass) throws Exception {
        HttpURLConnection connection = null;
        T response = null;
        StringBuilder output = new StringBuilder();
        long  startTime = System.currentTimeMillis();
        try {
            URL url = new URL(endpoint + path);

            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setConnectTimeout(this.timeout);
            connection.setReadTimeout(this.timeout);
            connection.setRequestMethod(HttpMethod.PUT.toString());
            connection.setRequestProperty("Content-Type", "application/json");

            if (authenticationType == AuthenticationType.BasicAuth) {
                connection.setRequestProperty("Authorization", basicAuthHeader);
            } else if (authenticationType == AuthenticationType.BearerToken) {
                connection.setRequestProperty("Authorization", bearerAuthHeader);
            } else if (authenticationType == AuthenticationType.SSLAuth) {
                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(this.sslParameters);
                ((HttpsURLConnection) connection).setSSLSocketFactory(sslSocketFactory);
            }

            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes());
            os.flush();

            if (connection.getResponseCode() >= 400) {
                throw new Exception("Errore HTTP " + connection.getResponseCode() + " durante la chiamata ");
            }

            if (responseClass != null) {

                try (InputStreamReader is = new InputStreamReader(connection.getInputStream())) {
                    try (BufferedReader br = new BufferedReader(is)) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                        }
                    }
                }
                if(!StringUtility.isEmpty(output.toString())) {
                    response = new ObjectMapper().readValue(output.toString(), responseClass);
                } else {
                    response = null;
                }
            }
        } catch (MalformedURLException err) {
            throw new Exception("MalformedURLException - " + err.getMessage(), err);
        } catch (Exception err) {
            throw new Exception("Exception - " + err.getMessage(), err);
        } finally {
            if (connection != null) {
                connection.disconnect();
                long timeTaken = System.currentTimeMillis() - startTime;

                if(logAttivo) {
                    int statusCode;
                    try {
                        statusCode = connection.getResponseCode();
                    } catch (IOException e) {
                        statusCode = -1;
                    }
                    logger.info("api request", getProperties("PUT", endpoint + path, json, output.toString(), statusCode, timeTaken));
                }
            }
        }
        return response;
    }
    
    public synchronized <T> T get(String path, Map<String, String> mappaHeader, Class<T> classeTipoRitorno) throws Exception {
        HttpURLConnection conn = null;
        T response;
        try {
            URL url = new URL(new StringBuffer(endpoint).append(path).toString());
            conn = (HttpURLConnection) url.openConnection();
            // conn.setDoOutput(true);
            conn.setConnectTimeout(this.timeout);
            conn.setReadTimeout(this.timeout);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            // aggiungo header dalla mappa
            for (String headerKey : mappaHeader.keySet()) {
                conn.setRequestProperty(headerKey, mappaHeader.get(headerKey));
            }

            if (authenticationType == AuthenticationType.BasicAuth) {
                conn.setRequestProperty("Authorization", basicAuthHeader);
            } else if (authenticationType == AuthenticationType.SSLAuth) {
                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(this.sslParameters);
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            }

            if (conn.getResponseCode() == 200) {
                StringBuilder output = new StringBuilder();
                try (InputStreamReader is = new InputStreamReader(conn.getInputStream())) {
                    try (BufferedReader br = new BufferedReader(is)) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                        }
                    }
                }
                response = new ObjectMapper().readValue(output.toString(), classeTipoRitorno);
            } else {
                throw new Exception(String.format("Http Response code %s", conn.getResponseCode()));
            }
        } catch (Exception e) {
            throw new Exception("Exception", e);
        } finally {
            if (conn != null)
                conn.disconnect();

        }
        return response;
    }
    
    public synchronized <T> T get(String path, Class<T> clazz) throws Exception {
        return get(path, "application/json", clazz);
    }
    
    public synchronized <T> T get(String path, TypeReference<T> type) throws Exception {
        return get(path, "application/json", type);
    }

    
    /**
     * Esegue chiamata GET
     *
     * @param path        path
     * @param contentType contentType "application/json";"application/xml";"text/csv"
     * @param clazzT      clazzT
     * @return {@link T} in caso di "text/csv" o di contentType non gestito verra ritornata una stringa
     * @throws Exception it.maggioli.informatica.jcitygov.pagopa.core.exception. a p i rest client exception
     * @see T
     */
    public synchronized <T> T get(String path, String contentType, Class<T> clazzT) throws Exception {

        StringBuilder response = get(path, contentType);

        try {
            T ret;
            switch (contentType) {
                case "application/json":

                    if(StringUtility.isEmpty(response)) {
                        return null;
                    }

                    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    ret = mapper.readValue(response.toString(), clazzT);
                    break;
                case "application/xml":
                    ret = (T) XmlParserSupport.convertXmlToObject(response.toString(), clazzT);
                    break;
                case "text/csv":
                    ret = (T) response.toString();
                    break;
                default:
                    logger.warn("Content Type non gestit: " + contentType);
                    ret = (T) response.toString();
            }
            return ret;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private synchronized <T> T get(String path, String contentType, TypeReference<T> typeReference) throws Exception {

        StringBuilder response = get(path, contentType);

        try {
            T ret;
            if (contentType.equals("application/json")) {
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                ret = mapper.readValue(response.toString(), typeReference);
            } else {
                logger.warn("Content Type non gestit: " + contentType);
                ret = (T) response.toString();
            }
            return ret;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Esegue chiamata GET con headers e ritorna un oggetto della classe specificata. Metodo pensato per REST e application/json
     * @param path path
     * @param clazz classe
     * @param headers headers
     * @return {@link T}
     * @throws Exception it.maggioli.informatica.jcitygov.pagopa.core.exception. api rest client exception
     * @see T
     */
    public synchronized <T> T get(String path, Class<T> clazz, Map<String, String> headers) throws Exception {
        HttpURLConnection  conn = null;

        long startTime = System.currentTimeMillis();
        try {
            URL url = new URL(endpoint + path);

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(this.timeout);
            conn.setReadTimeout(this.timeout);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            if (authenticationType == AuthenticationType.BasicAuth) {
                conn.setRequestProperty("Authorization", basicAuthHeader);
            } else if (authenticationType == AuthenticationType.BearerToken) {
                conn.setRequestProperty("Authorization", bearerAuthHeader);
            } else if (authenticationType == AuthenticationType.SSLAuth) {
                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(this.sslParameters);
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            }

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (conn.getResponseCode() == 200){
                return  new ObjectMapper().readValue(conn.getInputStream(), clazz);
            } else {
                throw new Exception(String.format("Http Response code %s", conn.getResponseCode()));
            }
        } catch (Exception e) {
            throw new Exception("Exception", e);
        } finally {
            if (conn != null) {
                conn.disconnect();

                long timeTaken = System.currentTimeMillis() - startTime;

                if(logAttivo) {
                    int statusCode;
                    T response = null;
                    try {
                        statusCode = conn.getResponseCode();
                        response = new ObjectMapper().readValue(conn.getInputStream(), clazz);
                    } catch (IOException e) {
                        statusCode = -1;
                    }
                    logger.info("api request", getProperties("GET", endpoint + path, null, response != null ? response.toString() : null, statusCode, timeTaken));
                }
            }
        }
    }
    
    private synchronized StringBuilder get(String path, String contentType) throws Exception{
        HttpURLConnection conn = null;
        StringBuilder output = new StringBuilder();
        long  startTime = System.currentTimeMillis();
        try {
            URL url = new URL(endpoint + path);
            conn = (HttpURLConnection) url.openConnection();
            // conn.setDoOutput(true);
            conn.setConnectTimeout(this.timeout);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", contentType);
            if (authenticationType == AuthenticationType.BasicAuth) {
                conn.setRequestProperty("Authorization", basicAuthHeader);
            } else if (authenticationType == AuthenticationType.BearerToken) {
                conn.setRequestProperty("Authorization", bearerAuthHeader);
            } else if (authenticationType == AuthenticationType.SSLAuth) {
                SSLSocketFactory sslSocketFactory = getSSLSocketFactory(this.sslParameters);
                ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            }

            String body;
            if (conn.getResponseCode() == 200) {
                body = readFromStream(contentType, conn.getInputStream());
                output = new StringBuilder(body);
                return output;
            } else {
                body = readFromStream(contentType, conn.getErrorStream());
                output = new StringBuilder(body);
                throw new Exception(String.format("Http Response code " + conn.getResponseCode()));
            }
        }
        catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
                long timeTaken = System.currentTimeMillis() - startTime;

                if(logAttivo) {
                    int statusCode;
                    try {
                        statusCode = conn.getResponseCode();
                    } catch (IOException e) {
                        statusCode = -1;
                    }
                    logger.info("api request", getProperties("GET", endpoint + path, null, output.toString(), statusCode, timeTaken));
                }
            }
        }
    }

    private static String readFromStream(String contentType, InputStream in) throws IOException {
        String body;
        try (InputStreamReader isr = new InputStreamReader(in)) {
            try (BufferedReader br = new BufferedReader(isr)) {
                String line;
                StringBuilder output = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    output.append(line);
                    if (contentType.equals("text/csv"))
                        output.append("\n");
                }
                body = output.toString();
            }
        }
        return body;
    }
    private SSLSocketFactory getSSLSocketFactory(SSLParameters sslParameters) throws Exception {
        TrustManager[] trustManagers = getTrustManagers(sslParameters.trustStorePath, sslParameters.trustStoreType, sslParameters.trustStorePassword);

        KeyManager[] keyManagers = getKeyManagers(sslParameters.keyStorePath, sslParameters.keyStoreType, sslParameters.keyStorePassword);
        // We build a SSLContext with both our trust/key managers
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagers, trustManagers, null);
            return sslContext.getSocketFactory();
        }
        catch (Exception e) {
            throw new Exception("Errore durante la creazione del Socket SSL: " + e.getMessage(), e);
        }
    }
    private KeyManager[] getKeyManagers(String keyStorePath, String keyStoreType, String keyStorePassword) {
        try {
            File file = new File(keyStorePath);
            if (file.exists()) {
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                try (FileInputStream fis = new FileInputStream(file)) {
                    keyStore.load(fis, keyStorePassword.toCharArray());
                }
                KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyFactory.init(keyStore, keyStorePassword.toCharArray());
                return keyFactory.getKeyManagers();
            }
        }
        catch (Exception e) {
            logger.error("Errore durante il caricamento dei KeyManager: " + e.getMessage(), e);
        }
        return null;
    }
    private TrustManager[] getTrustManagers(String trustStorePath, String trustStoreType, String trustStorePassword) {
        try {
            File file = new File(trustStorePath);
            if (file.exists()) {

                KeyStore trustStore = KeyStore.getInstance(trustStoreType);
                try(FileInputStream fis = new FileInputStream(file)) {
                    trustStore.load(fis, trustStorePassword.toCharArray());
                }
                TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustFactory.init(trustStore);
                return trustFactory.getTrustManagers();
            }
        }
        catch (Exception e) {
            logger.error("Errore durante il caricamento dei TrustManager: " + e.getMessage(), e);
        }
        return null;
    }
    static class SSLParameters {

        private final String keyStorePath;
        private final String keyStoreType;
        private final String keyStorePassword;
        private final String trustStorePath;
        private final String trustStoreType;
        private final String trustStorePassword;

        public SSLParameters(String keyStorePath, String keyStoreType, String keyStorePassword, String trustStorePath, String trustStoreType, String trustStorePassword) {
            this.keyStorePath = keyStorePath;
            this.keyStoreType = keyStoreType;
            this.keyStorePassword = keyStorePassword;
            this.trustStorePath = trustStorePath;
            this.trustStoreType = trustStoreType;
            this.trustStorePassword = trustStorePassword;
        }
    }
}
