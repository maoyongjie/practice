package com.practice.elasticsearch.connect;

import com.practice.elasticsearch.constant.Constants;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author MaoYongjie
 * @date 2022/3/9 18:36
 * @Description:
 */
public class RestHighLevelClientUtil {

    public static RestHighLevelClient getRestHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(Constants.ES_HOST, Constants.ES_PORT, Constants.ES_SCHEME)));

    }

}
