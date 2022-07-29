package com.practice.elasticsearch.demo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
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

    @Test
    public void test_03(){
        String url = "http://viid-plus-dev.devdolphin.com/ga-viid-moc-server/pointDevice/exportHugeList?type=ipc";
        HttpResponse httpResponse = HttpRequest.post(url).
                timeout(5000000).keepAlive(true).body("[]","application/json")
                .execute();
        System.out.println(httpResponse.body());
    }

    @Test
    public void test_04(){
        throw new IllegalArgumentException("KFC Crazy Thursday need $50.");
    }
}
