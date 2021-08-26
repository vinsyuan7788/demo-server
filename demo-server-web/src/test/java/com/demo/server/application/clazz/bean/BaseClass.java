package com.demo.server.application.clazz.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Vince Yuan
 * @date 08/26/2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseClass implements Serializable {
    
    private Long id;
}
