package org.chiefdata.util;

import java.util.Collection;

/**
 * @author : Kevin
 * @Title : CollectionUtils
 * @ProjectName data-structure
 * @Description : TODO
 * @Time : Created in 2019/5/23 16:47
 * @Modifyed By :
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection collection){
        if (null == collection || collection.isEmpty()){
            return true;
        }

        return false;
    }
}
