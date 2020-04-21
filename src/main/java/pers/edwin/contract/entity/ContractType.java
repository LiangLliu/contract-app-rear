package pers.edwin.contract.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (ContractType)实体类
 *
 * @author makejava
 * @since 2020-04-19 17:27:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractType implements Serializable {
    private static final long serialVersionUID = -44778146388979064L;
    
    private Integer id;
    
    private String type;

}