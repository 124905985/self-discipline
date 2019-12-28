package cn.suvue.discipline.practice.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类实例
 *
 * @author suvue
 * @date 2019/12/28
 */
public class ObjectTool<T> {
    private T obj;

    /**
     * set操作
     */
    public void setObj(T obj) {
        this.obj = obj;
    }

    /**
     * get操作
     */
    public T getObj() {
        return obj;
    }

    /**
     * 泛型方法
     */
    public <T> void show(T t) {
        System.out.println(t);
    }

    /**
     * 泛型通配符
     */
    public void foreachList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * 通配符的上限
     */
    public void foreachNumber(List<? extends Number> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    /**
     * main方法测试
     */
    public static void main(String[] args) {
        ObjectTool<String> stringObjectTool = new ObjectTool<>();
        stringObjectTool.setObj("泛型是Java中很重要的知识点");
        System.out.println(stringObjectTool.getObj());
        //泛型方法不不受泛型类的约束，即优先级更高
        stringObjectTool.show(99999);

        ObjectTool<Integer> integerObjectTool = new ObjectTool<>();
        integerObjectTool.setObj(20191227);
        System.out.println(integerObjectTool.getObj());

        ObjectTool<List> listObjectTool = new ObjectTool<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(12);
        listObjectTool.setObj(arrayList);
        System.out.println(listObjectTool.getObj().toString());

    }
}
