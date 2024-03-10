package dkw.demo.ai.req;

import com.alibaba.fastjson2.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JSONType(orders={"role", "content"})
public class single_Message
{
    private String role;

    private String content;
}

