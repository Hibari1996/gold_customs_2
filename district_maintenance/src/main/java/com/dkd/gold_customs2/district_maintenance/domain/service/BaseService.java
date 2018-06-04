package com.dkd.gold_customs2.district_maintenance.domain.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description: 
 * @Author: zhengxudong
 * @Date: 2018/5/8  13:38
 */
public interface BaseService<T,ID> {

    /**
     * 保存
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S save(S entity);

    /**
     * 修改
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S update(S entity);


    /**
     * 逻辑删除
     * @param ids
     * @return
     */
    int delete(Iterable<ID> ids);

    /**
     * 删除
     * @param entity
     * @return
     */
    <S extends T> void remove(S entity);

    /**
     * 查询信息
     * @param id
     * @return
     */
    T getOne(ID id);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

    /**
     * 查询所有
     * @param entity
     * @return
     */
    <S extends T> List<S> findAll(S entity);

    /**
     * 查询所有
     * @param entity
     * @return
     */
    <S extends T> Iterable<S> findAll(S entity, Integer page, Integer size);

    /**
     * 查询所有
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 查询所有
     * @param entity
     * @param pageable
     * @return
     */
    Page<T> findAll(T entity, Pageable pageable);


    /**
     * 查询所有
     * @param example
     * @return
     */
    List<T> findAll(Example<T> example);

    /**
     * 查询所有
     * @param example
     * @param pageable
     * @return
     */
    Page<T> findAll(Example<T> example, Pageable pageable);

    /**
     * 查询所有
     * @param example
     * @param page
     * @param size
     * @return
     */
    Page<T> findAll(Example<T> example, Integer page, Integer size);
}
