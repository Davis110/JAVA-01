package com.algorithm.algorithmshardingspheredata.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Order {

    private Long orderId;

    private String orderNo;

    private Long userId;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private String remark;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userId=").append(userId);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_0
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
               && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
               && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
               && (this.getOrderAmount() == null ? other.getOrderAmount() == null : this.getOrderAmount().equals(other.getOrderAmount()))
               && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
               && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_0
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOrderAmount() == null) ? 0 : getOrderAmount().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Order.Builder builder() {
        return new Order.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_order_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table t_order_0
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private Order obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_order_0
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new Order();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_order_0.order_id
         *
         * @param orderId the value for t_order_0.order_id
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder orderId(Long orderId) {
            obj.setOrderId(orderId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_order_0.order_no
         *
         * @param orderNo the value for t_order_0.order_no
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder orderNo(String orderNo) {
            obj.setOrderNo(orderNo);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_order_0.user_id
         *
         * @param userId the value for t_order_0.user_id
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_order_0.order_amount
         *
         * @param orderAmount the value for t_order_0.order_amount
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder orderAmount(BigDecimal orderAmount) {
            obj.setOrderAmount(orderAmount);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_order_0.order_status
         *
         * @param orderStatus the value for t_order_0.order_status
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder orderStatus(Integer orderStatus) {
            obj.setOrderStatus(orderStatus);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column t_order_0.remark
         *
         * @param remark the value for t_order_0.remark
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table t_order_0
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Order build() {
            return this.obj;
        }
    }
}