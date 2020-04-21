package pers.edwin.contract.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Company)实体类
 *
 * @author makejava
 * @since 2020-04-19 17:27:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    private static final long serialVersionUID = 756696827267975681L;

    private Integer id;

    private String companyName;

    private String address;

    private String telephone;

    private String signatureUrl;

    private String logo;

}