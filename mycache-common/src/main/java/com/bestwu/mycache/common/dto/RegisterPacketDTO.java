package com.bestwu.mycache.common.dto;

import com.sun.istack.internal.NotNull;
import lombok.*;

/**
 * MyCacheServer 注册到 Register，Register 接收的 TCP 报文 <br>
 *
 * @author Best Wu
 * @date 2021/7/5 23:36 <br>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisterPacketDTO extends BasePacketDTO {
    private static final long serialVersionUID = -8401632341829216005L;
    /**
     * 数据组
     */
    private String dataGroup;
    /**
     * ip
     */
    private String ip;
    /**
     * wsPort
     */
    private String wsPort;
    /**
     * 这个特别注意一下 <br>
     * v 代表虚拟的，为了适应虚拟化服务器的浮动 IP 地址， <br>
     * 可以根据虚拟化的实例来生成一个随机数，但是要分布式唯一， <br>
     * 具体实现会在 MyCacheServer 端去写默认实现 <br>
     */
    private String vRandom;
    /**
     * 是否是心跳消息
     */
    private Boolean ping;


}
