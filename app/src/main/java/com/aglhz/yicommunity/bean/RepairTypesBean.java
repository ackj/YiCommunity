package com.aglhz.yicommunity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/5/26 0026 10:52.
 * Email: liujia95me@126.com
 */

public class RepairTypesBean extends BaseBean{

    /**
     * data : {"types":[{"code":"hy","name":"花园"},{"code":"ggqy","name":"公共区域"},{"code":"DT","name":"电梯"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TypesBean> types;

        public List<TypesBean> getTypes() {
            return types;
        }

        public void setTypes(List<TypesBean> types) {
            this.types = types;
        }

        public static class TypesBean {
            /**
             * code : hy
             * name : 花园
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
