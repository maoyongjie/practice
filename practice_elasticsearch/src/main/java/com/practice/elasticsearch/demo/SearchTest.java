package com.practice.elasticsearch.demo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.practice.elasticsearch.connect.RestHighLevelClientUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ConstantScoreQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
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
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.rangeQuery("VPCCreateTime")
                .gte("20220522120000").lt("20220522130000"));
        boolQueryBuilder.must(QueryBuilders.termQuery("KafkaTopic","gvp.ods.MotorVehicles"));
        SearchSourceBuilder ssb = new SearchSourceBuilder();

        ssb.query(boolQueryBuilder).aggregation(AggregationBuilders.sum("sum").field("RecordCnt")).size(0);


        SearchRequest request = new SearchRequest("index_viidplus_tracelog_nodes_20220522");
        request.source(ssb);
        SearchResponse response = null;
        try {
            response = client.search(request, RequestOptions.DEFAULT);
            ParsedSum sum = response.getAggregations().get("sum");
            System.out.println(Convert.toLong(sum.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
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
