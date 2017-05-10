package com.aglhz.yicommunity.bean;

import com.aglhz.abase.widget.selector.ISelectAble;

/**
 * Created by leguang on 2017/5/10 0010.
 * Email：langmanleguang@qq.com
 */

public class SelectorBean implements ISelectAble {
    public String name;
    public int id;
    public Object tag;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public SelectorBean(String name, int id, Object tag) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Object getArg() {
        return tag;
    }
}
