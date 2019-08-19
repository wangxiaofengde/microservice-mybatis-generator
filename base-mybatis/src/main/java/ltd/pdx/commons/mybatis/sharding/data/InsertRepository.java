package ltd.pdx.commons.mybatis.sharding.data;

import ltd.pdx.commons.mybatis.sharding.ShardTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @param <T>
 * @author pdx-team
 * @date 2019/05/05
 */
public interface InsertRepository<T> {
    /**
     * 插入一条数据，忽略record中的ID
     *
     * @param record     pojo对象
     * @param shardTable 分表对象
     * @return 影响的记录数
     */
    int insert(@Param("record") T record, @Param("shardTable") ShardTable shardTable);

    /**
     * @param records    pojo记录集
     * @param shardTable 分表对象
     * @return 影响的记录数
     */
    int batchInsert(@Param("records") List<T> records, @Param("shardTable") ShardTable shardTable);

    /**
     * 使用mysql on duplicate key 语句插入与修改
     *
     * @param records    pojo记录集
     * @param shardTable 分表对象
     * @return 影响的记录数
     */
    int batchInsertOnDuplicateKey(@Param("records") List<T> records, @Param("shardTable") ShardTable shardTable);

}
