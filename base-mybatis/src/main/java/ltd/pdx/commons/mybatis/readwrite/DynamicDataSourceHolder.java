package ltd.pdx.commons.mybatis.readwrite;

/**
 * @author pdx-team
 * @date 2019/05/05
 */
public final class DynamicDataSourceHolder {
    private static final ThreadLocal<DataSourceFrom> DATASOURCE_THREAD_LOCAL = new ThreadLocal<>();

    private DynamicDataSourceHolder() {
    }

    public static void putDataSource(final DataSourceFrom dataSource) {
        DATASOURCE_THREAD_LOCAL.set(dataSource);
    }

    public static DataSourceFrom getDataSource() {
        return DATASOURCE_THREAD_LOCAL.get();
    }

    public static void clearDataSource() {
        DATASOURCE_THREAD_LOCAL.remove();
    }

}
