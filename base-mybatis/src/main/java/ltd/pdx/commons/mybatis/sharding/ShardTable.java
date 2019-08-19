package ltd.pdx.commons.mybatis.sharding;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pdx-team
 * @date 2019/05/05
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShardTable {
    /**
     * sharding 前缀
     */
    private String prefix;

    /**
     * sharding 表名称
     */
    private String name;

    /**
     * sharding 后缀
     */
    private String suffix;
}
