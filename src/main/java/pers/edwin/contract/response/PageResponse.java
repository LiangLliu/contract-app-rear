package pers.edwin.contract.response;

import lombok.Data;

import java.util.List;

/**
 * @Author Jinzi Yuan
 * Create Data: 2020/4/6 19:22
 * @Description :
 */
@Data
public class PageResponse<V> {
    private Integer count;
    private Integer page;
    private Integer size;
    private List<V> list;
}
