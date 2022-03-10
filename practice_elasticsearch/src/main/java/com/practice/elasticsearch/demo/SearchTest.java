package com.practice.elasticsearch.demo;

import com.practice.elasticsearch.connect.RestHighLevelClientUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @author MaoYongjie
 * @date 2022/3/9 18:39
 * @Description:
 */
public class SearchTest {

    @Test
    public void test_01() throws IOException {
        RestHighLevelClient client = RestHighLevelClientUtil.getRestHighLevelClient();
        SearchRequest request = new SearchRequest("index_viidplus_subscribes");
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchAllQuery());
        SearchSourceBuilder ssb = new SearchSourceBuilder().size(10);
        request.source(ssb.query(queryBuilder));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getId());
        }
    }

    @Test
    public void test_02(){
        String str = "";
        for (int i = 0; i < 3; i++) {
            str+=i+",";
        }
        String[] split = str.split(",");
        System.out.println(split.length);
    }

}
