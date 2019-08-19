package ltd.pdx.commons.mybatis.pager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页查询结果对象类
 *
 * @author pdx-team
 * @date 2018/05/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataGridPagerResult<T> {
    private Long total = 0L;
    private List<T> rows;
}