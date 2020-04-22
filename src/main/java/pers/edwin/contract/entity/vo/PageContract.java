package pers.edwin.contract.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * @Author Haoyang Yin
 * Create Data: 2020/4/23 0:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageContract implements Serializable {
    private Integer id;

    private Integer typeId;

    private String title;

    private String content;
    /**
     * 甲方
     */
    private String partyA;
    /**
     * 甲方签名
     */
    private String partyAUrl;
    /**
     * 甲方签署日期
     */
    private Instant partyADate;
    /**
     * 乙方
     */
    private String partyB;
    /**
     * 乙方签名
     */
    private String partyBUrl;
    /**
     * 乙方签署日期
     */
    private Instant partyBDate;
    /**
     * 创建时间
     */
    private Instant createTime;
    /**
     * 合同状态
     */
    private String status;

    private Integer offset;

    private Integer limit;
}
