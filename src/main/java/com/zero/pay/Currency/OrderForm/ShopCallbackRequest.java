package com.zero.pay.Currency.OrderForm;

public class ShopCallbackRequest
{
    private String date;
    private String money;
    private String order_no;
    private String shop_no;
    private String sign;
    private int status;
    private String trade_no;
    private String type;
    private String user_id;
    
    public String getDate() {
        return this.date;
    }
    
    public String getMoney() {
        return this.money;
    }
    
    public String getOrder_no() {
        return this.order_no;
    }
    
    public String getShop_no() {
        return this.shop_no;
    }
    
    public String getSign() {
        return this.sign;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getTrade_no() {
        return this.trade_no;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getUser_id() {
        return this.user_id;
    }
    
    public void setDate(final String date) {
        this.date = date;
    }
    
    public void setMoney(final String money) {
        this.money = money;
    }
    
    public void setOrder_no(final String order_no) {
        this.order_no = order_no;
    }
    
    public void setShop_no(final String shop_no) {
        this.shop_no = shop_no;
    }
    
    public void setSign(final String sign) {
        this.sign = sign;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setTrade_no(final String trade_no) {
        this.trade_no = trade_no;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setUser_id(final String user_id) {
        this.user_id = user_id;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ShopCallbackRequest)) {
            return false;
        }
        final ShopCallbackRequest other = (ShopCallbackRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        Label_0065: {
            if (this$date == null) {
                if (other$date == null) {
                    break Label_0065;
                }
            }
            else if (this$date.equals(other$date)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$money = this.getMoney();
        final Object other$money = other.getMoney();
        Label_0102: {
            if (this$money == null) {
                if (other$money == null) {
                    break Label_0102;
                }
            }
            else if (this$money.equals(other$money)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$order_no = this.getOrder_no();
        final Object other$order_no = other.getOrder_no();
        Label_0139: {
            if (this$order_no == null) {
                if (other$order_no == null) {
                    break Label_0139;
                }
            }
            else if (this$order_no.equals(other$order_no)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$shop_no = this.getShop_no();
        final Object other$shop_no = other.getShop_no();
        Label_0176: {
            if (this$shop_no == null) {
                if (other$shop_no == null) {
                    break Label_0176;
                }
            }
            else if (this$shop_no.equals(other$shop_no)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$sign = this.getSign();
        final Object other$sign = other.getSign();
        Label_0213: {
            if (this$sign == null) {
                if (other$sign == null) {
                    break Label_0213;
                }
            }
            else if (this$sign.equals(other$sign)) {
                break Label_0213;
            }
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$trade_no = this.getTrade_no();
        final Object other$trade_no = other.getTrade_no();
        Label_0263: {
            if (this$trade_no == null) {
                if (other$trade_no == null) {
                    break Label_0263;
                }
            }
            else if (this$trade_no.equals(other$trade_no)) {
                break Label_0263;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0300: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0300;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0300;
            }
            return false;
        }
        final Object this$user_id = this.getUser_id();
        final Object other$user_id = other.getUser_id();
        if (this$user_id == null) {
            if (other$user_id == null) {
                return true;
            }
        }
        else if (this$user_id.equals(other$user_id)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ShopCallbackRequest;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $date = this.getDate();
        result = result * 59 + (($date == null) ? 43 : $date.hashCode());
        final Object $money = this.getMoney();
        result = result * 59 + (($money == null) ? 43 : $money.hashCode());
        final Object $order_no = this.getOrder_no();
        result = result * 59 + (($order_no == null) ? 43 : $order_no.hashCode());
        final Object $shop_no = this.getShop_no();
        result = result * 59 + (($shop_no == null) ? 43 : $shop_no.hashCode());
        final Object $sign = this.getSign();
        result = result * 59 + (($sign == null) ? 43 : $sign.hashCode());
        result = result * 59 + this.getStatus();
        final Object $trade_no = this.getTrade_no();
        result = result * 59 + (($trade_no == null) ? 43 : $trade_no.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $user_id = this.getUser_id();
        result = result * 59 + (($user_id == null) ? 43 : $user_id.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ShopCallbackRequest(date=" + this.getDate() + ", money=" + this.getMoney() + ", order_no=" + this.getOrder_no() + ", shop_no=" + this.getShop_no() + ", sign=" + this.getSign() + ", status=" + this.getStatus() + ", trade_no=" + this.getTrade_no() + ", type=" + this.getType() + ", user_id=" + this.getUser_id() + ")";
    }
}
