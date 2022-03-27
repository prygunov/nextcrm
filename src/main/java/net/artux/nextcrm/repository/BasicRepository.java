package net.artux.nextcrm.repository;

import net.artux.nextcrm.model.BaseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;

public class BasicRepository<T> {

    protected final JdbcTemplate template;
    protected final Class<T> clazz;
    private final Field[] fields;
    private final String additionForInsert;
    private final String additionForUpdate;
    protected final String tableName;

    protected BeanPropertyRowMapper<T> rowMapper;

    public BasicRepository(JdbcTemplate template, Class<T> clazz) {
        this.template = template;

        this.clazz = clazz;
        fields = clazz.getDeclaredFields();
        tableName = clazz.getAnnotation(Table.class).name();
        rowMapper = BeanPropertyRowMapper.newInstance(clazz);

        String prefix = "";

        StringBuilder insertBuilder = new StringBuilder();
        StringBuilder updateBuilder = new StringBuilder();
        insertBuilder.append("(");
        for (Field value : fields) {

            value.setAccessible(true);

            Column annotation = value.getAnnotation(Column.class);
            Id id = value.getAnnotation(Id.class);
            if (id==null) {
                insertBuilder.append(prefix);
                updateBuilder.append(prefix);
                prefix = ",";
                if (annotation != null) {
                    insertBuilder.append(annotation.name());
                    updateBuilder.append(annotation.name()).append("=?");
                } else {
                    if (value.getType().getSuperclass() == null || !value.getType().getSuperclass().equals(BaseEntity.class)) {
                        insertBuilder.append(value.getName());
                        updateBuilder.append(value.getName()).append("=?");
                    } else {
                        insertBuilder.append(value.getName() + "_id");
                        updateBuilder.append(value.getName() + "_id").append("=?");
                    }
                }
            }
        }

        insertBuilder.append(") VALUES(");
        prefix = "";
        for (int i = 0; i < fields.length; i++) {
            insertBuilder.append(prefix);
            prefix = ",";
            insertBuilder.append("?");
        }
        insertBuilder.append(")");
        additionForInsert = insertBuilder.toString();
        additionForUpdate = updateBuilder.toString();
    }

    public int save(T object) {
        return template.update("INSERT INTO " + tableName + additionForInsert,
                getValues(object));
    }

    public int update(T object) {
        List<Object> values = new ArrayList<>(Arrays.asList(getValues(object)));
        values.add(((BaseEntity)object).getId());
        return template.update("UPDATE " + tableName + " SET " + additionForUpdate + " WHERE id=?",
                values.toArray());
    }

    public int update(Long id, T object) {
        List<Object> values = new ArrayList<>(Arrays.asList(getValues(object)));
        values.add(id);
        return template.update("UPDATE " + tableName + " SET " + additionForUpdate + " WHERE id=?",
                values.toArray());
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    public T getById(Long id) {
        if (id == null)
            return null;
        T object = template.queryForObject("SELECT * FROM " + tableName + " WHERE id=?",
                rowMapper, id);

        return object;
    }

    public int deleteById(Long id) {
        return template.update("DELETE FROM " + tableName + " WHERE id=?", id);
    }

    public List<T> findAll() {
        return template.query("SELECT * from " + tableName, rowMapper);
    }

    public int deleteAll() {
        return template.update("DELETE from " + tableName);
    }

    protected Object[] getValues(T object) {
        Object[] values = new Object[fields.length];

        int i = 0;
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                if (f.getType().getSuperclass()!= null && f.getType().getSuperclass().equals(BaseEntity.class)) {
                    BaseEntity baseEntity = (BaseEntity) f.get(object);
                    if (baseEntity!=null)
                        values[i] = baseEntity.getId();
                    else
                        values[i] = null;
                }else
                    values[i] = f.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
        return values;
    }

}
