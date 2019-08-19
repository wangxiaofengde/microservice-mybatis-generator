package ltd.pdx.commons.mybatis.pager;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 分页查询对象类
 *
 * @author pdx-team
 * @date 2019/05/08
 */
@Data
@NoArgsConstructor
public class DataGridPager<T> {
    private Integer page = 1;
    private Integer rows = 50;
    private String sort = "id";
    private String order = "desc";
    /**
     * 查询参数对象
     */
    private T queryParameter;

    public PageInfo toPageInfo() {
        return this.toPageInfo("");
    }

    public PageInfo toPageInfo(final String tablePrefix) {
        final String prefix = StringUtils.defaultString(tablePrefix, "").trim();
        final String name = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.sort), '_');
        final String sortField = prefix + StringUtils.defaultString(name, "").toLowerCase();
        return new PageInfo((this.page - 1) * this.rows, this.rows, sortField, this.order);
    }
}
