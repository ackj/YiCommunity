package com.aglhz.yicommunity.entity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author: LiuJia on 2017/5/24 0024 09:59.
 * Email: liujia95me@126.com
 */

public class CarCardListBean extends BaseBean {


    /**
     * data : {"cardList":[{"annexFirstCarNo":"string","annexSecondCarNo":"string","approveDes":"string","approveState":0,"approveTime":"string","carNo":"string","cardName":"string","cardType":"string","createTime":"string","customerName":"string","endTime":"string","fid":"string","member":{"avator":"string","grade":"string","level":0,"memberFid":"string","memberNickName":"string"},"needToPayType":0,"parkPlace":{"address":"string","communityFid":"string","communityName":"string","fid":"string","lat":"string","lng":"string","name":"string","regionInfo":"string"},"phoneNo":"string","startTime":"string","surplusDays":0},{"approveState":0,"carNo":"粤","cardName":"车位用户","cardType":"免费卡","createTime":"2017-05-24 11:37:49","customerName":"张三","fid":"6040cb6b-0209-4c90-bb85-1c075cee146f","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170519220959715255.jpg","memberNickName":"刘嘉"},"parkPlace":{"address":"凯宾斯基负二层停车场","communityFid":"KBSJ-agl-00005","communityName":"惠州市 凯宾斯基","fid":"2c4361a2-450c-4cc7-a268-b2762d6501be","name":"凯宾斯基停车场","regionInfo":"惠城区广东省惠州市"},"phoneNo":"133333333"},{"approveState":0,"carNo":"粤S55555","cardName":"月租用户","cardType":"月租卡","createTime":"2017-05-24 10:34:28","customerName":"张三","fid":"3f37f389-acb5-4c16-9524-4408061460cf","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170519220959715255.jpg","memberNickName":"刘嘉"},"parkPlace":{"address":"凯宾斯基负二层停车场","communityFid":"KBSJ-agl-00005","communityName":"惠州市 凯宾斯基","fid":"2c4361a2-450c-4cc7-a268-b2762d6501be","name":"凯宾斯基停车场","regionInfo":"惠城区广东省惠州市"},"phoneNo":"133333333"},{"approveState":0,"carNo":"粤B88888","cardName":"月租用户","cardType":"月租卡","createTime":"2017-05-24 09:55:26","customerName":"张三","fid":"96a76a9d-9fd1-45e2-b0bf-e6538f756cef","member":{"avator":"http://aglhzmall.image.alimmdn.com/member/20170519220959715255.jpg","memberNickName":"刘嘉"},"parkPlace":{"address":"凯宾斯基负二层停车场","communityFid":"KBSJ-agl-00005","communityName":"惠州市 凯宾斯基","fid":"2c4361a2-450c-4cc7-a268-b2762d6501be","name":"凯宾斯基停车场","regionInfo":"惠城区广东省惠州市"},"phoneNo":"1333333333"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CardListBean> cardList;

        public List<CardListBean> getCardList() {
            return cardList;
        }

        public void setCardList(List<CardListBean> cardList) {
            this.cardList = cardList;
        }

        public static class CardListBean implements Serializable{
            /**
             * annexFirstCarNo : string
             * annexSecondCarNo : string
             * approveDes : string
             * approveState : 0
             * approveTime : string
             * carNo : string
             * cardName : string
             * cardType : string
             * createTime : string
             * customerName : string
             * endTime : string
             * fid : string
             * member : {"avator":"string","grade":"string","level":0,"memberFid":"string","memberNickName":"string"}
             * needToPayType : 0
             * parkPlace : {"address":"string","communityFid":"string","communityName":"string","fid":"string","lat":"string","lng":"string","name":"string","regionInfo":"string"}
             * phoneNo : string
             * startTime : string
             * surplusDays : 0
             */

            private String annexFirstCarNo;
            private String annexSecondCarNo;
            private String approveDes;
            private int approveState; //0：正在审核   1：审核通过  2：被拒绝
            private String approveTime;
            private String carNo;
            private String cardName;
            private String cardType;
            private String createTime;
            private String customerName;
            private String endTime;
            private String fid;
            private MemberBean member;
            private int needToPayType;//1：立即缴费  >1：充值
            private ParkPlaceBean parkPlace;
            private String phoneNo;
            private String startTime;
            private int surplusDays; //<=0 已过期

            public String getAnnexFirstCarNo() {
                return annexFirstCarNo;
            }

            public void setAnnexFirstCarNo(String annexFirstCarNo) {
                this.annexFirstCarNo = annexFirstCarNo;
            }

            public String getAnnexSecondCarNo() {
                return annexSecondCarNo;
            }

            public void setAnnexSecondCarNo(String annexSecondCarNo) {
                this.annexSecondCarNo = annexSecondCarNo;
            }

            public String getApproveDes() {
                return approveDes;
            }

            public void setApproveDes(String approveDes) {
                this.approveDes = approveDes;
            }

            public int getApproveState() {
                return approveState;
            }

            public void setApproveState(int approveState) {
                this.approveState = approveState;
            }

            public String getApproveTime() {
                return approveTime;
            }

            public void setApproveTime(String approveTime) {
                this.approveTime = approveTime;
            }

            public String getCarNo() {
                return carNo;
            }

            public void setCarNo(String carNo) {
                this.carNo = carNo;
            }

            public String getCardName() {
                return cardName;
            }

            public void setCardName(String cardName) {
                this.cardName = cardName;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
            }

            public int getNeedToPayType() {
                return needToPayType;
            }

            public void setNeedToPayType(int needToPayType) {
                this.needToPayType = needToPayType;
            }

            public ParkPlaceBean getParkPlace() {
                return parkPlace;
            }

            public void setParkPlace(ParkPlaceBean parkPlace) {
                this.parkPlace = parkPlace;
            }

            public String getPhoneNo() {
                return phoneNo;
            }

            public void setPhoneNo(String phoneNo) {
                this.phoneNo = phoneNo;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public int getSurplusDays() {
                return surplusDays;
            }

            public void setSurplusDays(int surplusDays) {
                this.surplusDays = surplusDays;
            }

            public static class MemberBean {
                /**
                 * avator : string
                 * grade : string
                 * level : 0
                 * memberFid : string
                 * memberNickName : string
                 */

                private String avator;
                private String grade;
                private int level;
                private String memberFid;
                private String memberNickName;

                public String getAvator() {
                    return avator;
                }

                public void setAvator(String avator) {
                    this.avator = avator;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getMemberFid() {
                    return memberFid;
                }

                public void setMemberFid(String memberFid) {
                    this.memberFid = memberFid;
                }

                public String getMemberNickName() {
                    return memberNickName;
                }

                public void setMemberNickName(String memberNickName) {
                    this.memberNickName = memberNickName;
                }
            }

            public static class ParkPlaceBean {
                /**
                 * address : string
                 * communityFid : string
                 * communityName : string
                 * fid : string
                 * lat : string
                 * lng : string
                 * name : string
                 * regionInfo : string
                 */

                private String address;
                private String communityFid;
                private String communityName;
                private String fid;
                private String lat;
                private String lng;
                private String name;
                private String regionInfo;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCommunityFid() {
                    return communityFid;
                }

                public void setCommunityFid(String communityFid) {
                    this.communityFid = communityFid;
                }

                public String getCommunityName() {
                    return communityName;
                }

                public void setCommunityName(String communityName) {
                    this.communityName = communityName;
                }

                public String getFid() {
                    return fid;
                }

                public void setFid(String fid) {
                    this.fid = fid;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getRegionInfo() {
                    return regionInfo;
                }

                public void setRegionInfo(String regionInfo) {
                    this.regionInfo = regionInfo;
                }
            }
        }
    }
}
