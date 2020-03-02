package org.chiefdata.set;

import org.chiefdata.tree.bst.BST;

/**
 * @author : Kevin
 * @Title : BSTSet
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/29 13:07
 * @Modifyed By :
 */
public class BSTSet<E> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        this.bst = new BST<E>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
