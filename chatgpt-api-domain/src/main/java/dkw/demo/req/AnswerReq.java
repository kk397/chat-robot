package dkw.demo.req;

public class AnswerReq {
    ReqData Req_data;

    public AnswerReq(ReqData req_data) {
        Req_data = req_data;
    }

    public ReqData getReq_data() {
        return Req_data;
    }

    public void setReq_data(ReqData req_data) {
        Req_data = req_data;
    }
}
