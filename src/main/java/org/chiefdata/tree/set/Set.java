package org.chiefdata.tree.set;

/**
 * @author : Kevin
 * @Title : Set
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 13:04
 * @Modifyed By :
 */
public interface Set<E> {
    /**
     * 添加
     * @param e
     */
    public void add(E e);

    /**
     * 删除
     * @param e
     * @return
     */
    public E remove(E e);

    /**
     * 是否存在
     * @param e
     * @return
     */
    public boolean contains(E e);

    /**
     * 获取容量
     * @return
     */
    public int getSize();

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty();
}
