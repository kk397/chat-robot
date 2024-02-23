package dkw.demo.res;

import dkw.demo.vo.Topics;

import java.util.ArrayList;
import java.util.List;
public class Resp_data
{
    private List<Topics> topics;

    public void setTopics(List<Topics> topics){
        this.topics = topics;
    }
    public List<Topics> getTopics(){
        return this.topics;
    }
}