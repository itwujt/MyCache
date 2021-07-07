package com.bestwu.mycache.common.dto;

import lombok.*;

import java.util.StringJoiner;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/7 22:50 <br>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisterResponseDTO extends BasePacketDTO {

    private static final long serialVersionUID = -8719054176608047389L;

    private String code;

    private String msg;

    private Object data;
}
