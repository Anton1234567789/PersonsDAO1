package org.sourceit.db.DAO;

import java.sql.SQLException;
import java.util.List;



/**
 * Интерфейс, который ответственнен за CRUD(https://ru.wikipedia.org/wiki/CRUD)
 * @param <T> какая именно сущность используется.
 */
public interface DAO<T> {

    /**
     * Создает сущность в базе данных.
     * @param entity параметры
     * @return создался или нет.
     */
    boolean create(T entity);

    /**
     * Обновляет данную сущность
     * @return обновилась или нет.
     */
    boolean update(T entity);

    /**
     * Список сущностей.
     * @return
     */
    List<T> list();

    /**
     * Удаляет данную сущность.
     * @param entity параметры по которым, можно удалить.
     * @return удалилось или нет.
     */
    boolean remove(T entity) throws SQLException;

}