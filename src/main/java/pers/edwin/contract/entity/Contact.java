package pers.edwin.contract.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.io.Serializable;

/**
 * (Contact)实体类
 *
 * @author makejava
 * @since 2020-04-19 17:27:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable {
    private static final long serialVersionUID = 368502254108432639L;
    
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


}