package com.laptrinhoop.client;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ElasticClientWrite {
    private static String SERVER_ELASTICSEARCH_WRITE_HOST = "localhost";
    private static int SERVER_ELASTICSEARCH_WRITE_PORT = 9200;
    static ObjectMapper mapper = null;
    private static ElasticClientWrite instance;

    private RestHighLevelClient client = null;
    public ElasticClientWrite(String host,int port)
    {

        SERVER_ELASTICSEARCH_WRITE_HOST = host;
        SERVER_ELASTICSEARCH_WRITE_PORT = port;

        mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        mapper.setDateFormat(df);


        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        RestClientBuilder builder = RestClient.builder(new HttpHost(SERVER_ELASTICSEARCH_WRITE_HOST, SERVER_ELASTICSEARCH_WRITE_PORT))
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {

                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000);
                    }
//					public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//						return requestConfigBuilder.setConnectTimeout(200)
//								.setSocketTimeout(200);
//					}
                });
        // client = new RestHighLevelClient(
        // RestClient.builder(new HttpHost(SERVER_ELASTICSEARCH_WRITE_HOST, ElasticPort,
        // "http")));
        client = new RestHighLevelClient(builder);
    }

    public ElasticClientWrite(String host) {
        String connString = System.getenv(host);
        URI connUri = URI.create(host);
        String[] auth = connUri.getUserInfo().split(":");

        CredentialsProvider cp = new BasicCredentialsProvider();
        cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(auth[0], auth[1]));

        RestHighLevelClient rhlc = new RestHighLevelClient(
                RestClient.builder(new HttpHost(connUri.getHost(), connUri.getPort(), connUri.getScheme()))
                        .setHttpClientConfigCallback(
                                httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(cp)
                                        .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())));
        client = rhlc;
    }

    public RestHighLevelClient getClient() {
        return client;
    }

    public static synchronized ElasticClientWrite getInstance(int dataCenter) {
        // synchronized (ElasticClient.class) {
        if (instance == null) {
            String host ="";
            switch (dataCenter){
                case 1:
                     host = "localhost";
                    int port = 9200;
                    instance = new ElasticClientWrite(host, port);
                    break;
                case 2:
                     host = "https://mobileshop-6222481158.us-east-1.bonsaisearch.net/";
                    instance = new ElasticClientWrite(host);
                    break;
            }


        }
        // }
        return instance;
    }
    public <T> boolean IndexObject(String index, T data, String id) throws Exception {
        // build json
        String json = mapper.writeValueAsString(data);
        // String json = gson.toJson(data);
        IndexRequest indexRequest = new IndexRequest(index).id(id).source(json, XContentType.JSON);

        try {
            IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
            if (response != null) {
                if (response.status() == RestStatus.OK || response.status() == RestStatus.CREATED
                        || response.status() == RestStatus.ACCEPTED) {
                    return true;
                } else {
                    throw new Exception("ElasticIndexer::AddUpdateObject::" + response.status().toString() + " "
                            + response.getResult().toString());
                }
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }
}
