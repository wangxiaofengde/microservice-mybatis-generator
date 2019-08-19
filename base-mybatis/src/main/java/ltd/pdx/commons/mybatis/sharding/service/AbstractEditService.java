package ltd.pdx.commons.mybatis.sharding.service;

import ltd.pdx.commons.mybatis.sharding.ShardTable;
import ltd.pdx.commons.mybatis.sharding.data.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 * @param <Example>
 * @author pdx-team
 * @date 2019/05/05
 */
public abstract class AbstractEditService<Dao extends UpdateRepository<Po, Example>, Po, Example>
        implements EditService<Po, Example> {
    @Autowired
    protected Dao dao;

    @Override
    public int editById(final Po record, final ShardTable shardTable) {
        return this.dao.updateById(record, shardTable);
    }

    @Override
    public int editByExample(final Po record, final Example example, final ShardTable shardTable) {
        return this.dao.updateByExample(record, example, shardTable);
    }

    @Override
    public int batchEdit(final List<Po> records, final ShardTable shardTable) {
        return this.dao.batchUpdate(records, shardTable);
    }
}
