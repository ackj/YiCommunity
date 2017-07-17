package com.aglhz.yicommunity.entity.bean;

import java.util.List;

/**
 * Author: LiuJia on 2017/6/30 0030 16:03.
 * Email: liujia95me@126.com
 * 服务分类
 */

public class ServicesTypesBean extends BaseBean {

    /**
     * data : {"classifyList":[{"code":"JTBJ","communityName":"凯宾斯基","desc":"您的健康,我们最大的使命","enable":true,"fid":"7175ff0d-bbf2-4daf-b35b-8222b2b7edea","name":"家庭保健"},{"code":"JTBJ","communityName":"凯宾斯基","desc":"健康最重要","enable":true,"fid":"c45efb88-321d-46ee-8504-c9e51423ba10","name":"家庭保健"},{"code":"JJWX","communityName":"凯宾斯基","desc":"专业品质服务,大可放心","enable":true,"fid":"010015ef-acb5-4328-97c1-535c24e5f45b","name":"家居维修"},{"code":"JZFW","communityName":"凯宾斯基","desc":"您家里的一切,让我来搞定","enable":true,"fid":"4d39f6fe-64d7-4162-bb68-72c4c117c8d2","name":"家政服务"},{"code":"SSSM","communityName":"凯宾斯基","desc":"社区周边送水上门服务","enable":true,"fid":"6a0dafe9-916a-4a87-9ad2-47516aa2db94","name":"送水上门"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ClassifyListBean> classifyList;

        public List<ClassifyListBean> getClassifyList() {
            return classifyList;
        }

        public void setClassifyList(List<ClassifyListBean> classifyList) {
            this.classifyList = classifyList;
        }

        public static class ClassifyListBean {
            /**
             * code : JTBJ
             * communityName : 凯宾斯基
             * desc : 您的健康,我们最大的使命
             * enable : true
             * fid : 7175ff0d-bbf2-4daf-b35b-8222b2b7edea
             * name : 家庭保健
             */
            private String classifyPhotoUrl;
            private String code;
            private String communityName;
            private String desc;
            private boolean enable;
            private String fid;
            private String name;

            public String getClassifyPhotoUrl() {
                return classifyPhotoUrl;
            }

            public void setClassifyPhotoUrl(String classifyPhotoUrl) {
                this.classifyPhotoUrl = classifyPhotoUrl;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCommunityName() {
                return communityName;
            }

            public void setCommunityName(String communityName) {
                this.communityName = communityName;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
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
