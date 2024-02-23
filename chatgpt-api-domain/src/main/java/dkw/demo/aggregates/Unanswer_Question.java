package dkw.demo.aggregates;

import dkw.demo.res.Resp_data;

public class Unanswer_Question {
    private boolean succeeded;
    private Resp_data resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public Resp_data getResp_data() {
        return resp_data;
    }

    public void setResp_data(Resp_data resp_data) {
        this.resp_data = resp_data;
    }
}
