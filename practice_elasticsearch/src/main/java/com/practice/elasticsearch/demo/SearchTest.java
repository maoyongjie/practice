package com.practice.elasticsearch.demo;

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
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("UploadTime", 0));
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("VerificationTime", 0));
        boolQueryBuilder.must(QueryBuilders.existsQuery("UploadTime"));
        boolQueryBuilder.must(QueryBuilders.existsQuery("VerificationTime"));
        String script = StrUtil.format("doc['{}'].value - doc['{}'].value", "UploadTime", "VerificationTime");
        StatsAggregationBuilder stats = AggregationBuilders.stats("statics").script(new Script(script));
        String equalScript = StrUtil.format("doc['{}'].value - doc['{}'].value == 0", "UploadTime", "VerificationTime");
        boolQueryBuilder.mustNot(QueryBuilders.scriptQuery(new Script(equalScript)));
        boolQueryBuilder.must(QueryBuilders.rangeQuery("VPCCreateTime")
                .gt("20220512090000").lt("20220512095959"));
//                    System.out.println(req.getSpiltVIIDStartTime(finalK, finalI));
//                    System.out.println(req.getSpiltVIIDEndTime(finalK, finalI));
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        ConstantScoreQueryBuilder constantScoreQuery = QueryBuilders.constantScoreQuery(boolQueryBuilder);
        ssb.query(constantScoreQuery).aggregation(stats).size(0);


        SearchRequest request = new SearchRequest("index_viidplus_tracelog_nodes_*");
        request.source(ssb);
        SearchResponse response = null;
        try {
            long time1 = System.currentTimeMillis();
            response = client.search(request, RequestOptions.DEFAULT);
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
