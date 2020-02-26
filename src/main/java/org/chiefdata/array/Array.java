package org.chiefdata.array;

import com.google.common.base.Joiner;

import java.util.function.Predicate;

/**
 * @author : Kevin
 * @Title : Array
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2020/2/25 14:41
 * @Modifyed By :
 */
public class Array<T> {
    private T[] data;
    private int size;

    private static int DEFAULT_CAPACITY = 16;

    public Array(){
        this(DEFAULT_CAPACITY);
    }

    public Array(int capacity){
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(T e){
        add(size, e);
    }

    public void add(int index, T e){
        if (size == getCapacity()){
            resize(2 * data.length);
        }

        if (index > size){
            throw new RuntimeException("当前数组容量为:" + getSize() + ", index 必须小于 容量");
        }

        for (int i = size; i > index; i--) {
            data[size] = data[size-1];
        }

        data[index] = e;

        size ++;
    }

    // 将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity){

        T[] newData = (T[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i ++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public T get(int index){
        return data[index];
    }

    public T set(int index, T e){
        if (index > size){
            throw new RuntimeException("当前数组容量为:" + getSize() + ", index 必须小于 容量");
        }

        T old = data[index];
        data[index] = e;
        return old;
    }

    public T remove(int index){
        if (index >= size){
            throw new RuntimeException("当前数组容量为:" + getSize() + ", index 必须小于 容量");
        }

        T old = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[index] = null;

        if(size == data.length / 4 && size /2 != 0){
            resize(data.length / 2);
        }

        size --;

        return old;
    }

    public T find(Predicate<T> equalsFunc){
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (equalsFunc.test(data[i])){
                index = i;
                break;
            }
        }

        if (-1 == index){
            return null;
        }

        return data[index];
    }

    @Override
    public String toString() {
        return Joiner.on(",").skipNulls().join(data);
    }
}
