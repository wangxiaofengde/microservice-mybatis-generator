package ltd.pdx.commons.mybatis.sharding.service;

import ltd.pdx.commons.mybatis.pager.PageInfo;
import ltd.pdx.commons.mybatis.sharding.ShardTable;
import ltd.pdx.commons.mybatis.sharding.data.SelectRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 * @param <Example>
 * @param <Type>    Key字段数据类型(Integer,Long,String等)
 * @author pdx-team
 * @date 2019/05/05
 */
public abstract class AbstractGetService<Dao extends SelectRepository<Po, Example, Type>, Po, Example, Type>
        implements GetService<Po, Example, Type> {
    @Autowired
    protected Dao dao;

    @Override
    public boolean exists(final Example example, final ShardTable shardTable) {
        return this.dao.countByExample(example, shardTable) > 0;
    }

    @Override
    public Po getById(final Type id, final ShardTable shardTable) {
        return this.dao.selectById(id, shardTable);
    }

    @Override
    public List<Po> getByExample(final Example example, final ShardTable shardTable) {
        return this.dao.selectByExample(example, shardTable);
    }

    @Override
    public List<Po> getAll(final ShardTable shardTable) {
        return this.dao.selectByExample(null, shardTable);
    }

    @Override
    public Po getOneByExample(final Example example, final ShardTable shardTable) {
        return this.dao.selectOneByExample(example, shardTable);
    }

    @Override
    public List<Po> getIn(final List<Po> records, final ShardTable shardTable) {
        return this.dao.selectIn(records, shardTable);
    }

    @Override
    public List<Po> getByPage(final PageInfo pageInfo, final ShardTable shardTable) {
        return this.getByPage(pageInfo, "", "", shardTable);
    }

    @Override
    public List<Po> getByPage(final PageInfo pageInfo, final String fieldName, final String keyword,
                              final ShardTable shardTable) {
        if (StringUtils.isBlank(fieldName)) {
            return this.getByPage(pageInfo, null, shardTable);
        }
        return this.getByPage(pageInfo, this.getPageExample(fieldName, keyword), shardTable);
    }

    @Override
    public List<Po> getByPage(final PageInfo pageInfo, final Example example, final ShardTable shardTable) {
        pageInfo.setTotals(this.dao.countByPager(pageInfo, example, shardTable));
        return this.dao.selectByPager(pageInfo, example, shardTable);
    }

    protected abstract Example getPageExample(String fieldName, String keyword);
}
