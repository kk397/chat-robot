package dkw.demo.ai.resp;

import lombok.Data;

@Data
public class resp_data {
    private String id;

    private String object;

    private int created;

    private String result;

    private boolean is_truncated;

    private boolean need_clear_history;

    private Usage usage;
}