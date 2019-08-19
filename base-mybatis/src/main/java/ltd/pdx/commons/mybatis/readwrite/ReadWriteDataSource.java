package ltd.pdx.commons.mybatis.readwrite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 读写分离数据源注解
 *
 * @author pdx-team
 * @date 2019/05/05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReadWriteDataSource {

    /**
     * 默认为写数据源
     *
     * @return DynamicDataSourceType
     */
    DataSourceFrom value() default DataSourceFrom.WRITE;

}
