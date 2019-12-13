package com.zero.pay.Form;

import com.zero.pay.Currency.Enum.PayTypeEnum;
import lombok.Data;

@Data
public class CreateOrderByShopV2Form
{
    private String shopAccountId;
    private String shopUserId;
    private String amountInString;
    private PayTypeEnum payChannel;
    private String sign;
    private String shopNo;
    private String shopCallbackUrl;
    private String returnUrl;
    
    public CreateOrderByShopV2Form() {
        this.shopAccountId = "";
        this.shopUserId = "";
        this.amountInString = "0";
        this.sign = "";
        this.shopNo = "";
        this.shopCallbackUrl = "";
        this.returnUrl = "";
    }

    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreateOrderByShopV2Form)) {
            return false;
        }
        final CreateOrderByShopV2Form other = (CreateOrderByShopV2Form)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$shopAccountId = this.getShopAccountId();
        final Object other$shopAccountId = other.getShopAccountId();
        Label_0065: {
            if (this$shopAccountId == null) {
                if (other$shopAccountId == null) {
                    break Label_0065;
                }
            }
            else if (this$shopAccountId.equals(other$shopAccountId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$shopUserId = this.getShopUserId();
        final Object other$shopUserId = other.getShopUserId();
        Label_0102: {
            if (this$shopUserId == null) {
                if (other$shopUserId == null) {
                    break Label_0102;
                }
            }
            else if (this$shopUserId.equals(other$shopUserId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$amountInString = this.getAmountInString();
        final Object other$amountInString = other.getAmountInString();
        Label_0139: {
            if (this$amountInString == null) {
                if (other$amountInString == null) {
                    break Label_0139;
                }
            }
            else if (this$amountInString.equals(other$amountInString)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$payChannel = this.getPayChannel();
        final Object other$payChannel = other.getPayChannel();
        Label_0176: {
            if (this$payChannel == null) {
                if (other$payChannel == null) {
                    break Label_0176;
                }
            }
            else if (this$payChannel.equals(other$payChannel)) {
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
        final Object this$shopNo = this.getShopNo();
        final Object other$shopNo = other.getShopNo();
        Label_0250: {
            if (this$shopNo == null) {
                if (other$shopNo == null) {
                    break Label_0250;
                }
            }
            else if (this$shopNo.equals(other$shopNo)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$shopCallbackUrl = this.getShopCallbackUrl();
        final Object other$shopCallbackUrl = other.getShopCallbackUrl();
        Label_0287: {
            if (this$shopCallbackUrl == null) {
                if (other$shopCallbackUrl == null) {
                    break Label_0287;
                }
            }
            else if (this$shopCallbackUrl.equals(other$shopCallbackUrl)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$returnUrl = this.getReturnUrl();
        final Object other$returnUrl = other.getReturnUrl();
        if (this$returnUrl == null) {
            if (other$returnUrl == null) {
                return true;
            }
        }
        else if (this$returnUrl.equals(other$returnUrl)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CreateOrderByShopV2Form;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $shopAccountId = this.getShopAccountId();
        result = result * 59 + (($shopAccountId == null) ? 43 : $shopAccountId.hashCode());
        final Object $shopUserId = this.getShopUserId();
        result = result * 59 + (($shopUserId == null) ? 43 : $shopUserId.hashCode());
        final Object $amountInString = this.getAmountInString();
        result = result * 59 + (($amountInString == null) ? 43 : $amountInString.hashCode());
        final Object $payChannel = this.getPayChannel();
        result = result * 59 + (($payChannel == null) ? 43 : $payChannel.hashCode());
        final Object $sign = this.getSign();
        result = result * 59 + (($sign == null) ? 43 : $sign.hashCode());
        final Object $shopNo = this.getShopNo();
        result = result * 59 + (($shopNo == null) ? 43 : $shopNo.hashCode());
        final Object $shopCallbackUrl = this.getShopCallbackUrl();
        result = result * 59 + (($shopCallbackUrl == null) ? 43 : $shopCallbackUrl.hashCode());
        final Object $returnUrl = this.getReturnUrl();
        result = result * 59 + (($returnUrl == null) ? 43 : $returnUrl.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CreateOrderByShopV2Form(shopAccountId=" + this.getShopAccountId() + ", shopUserId=" + this.getShopUserId() + ", amountInString=" + this.getAmountInString() + ", payChannel=" + this.getPayChannel() + ", sign=" + this.getSign() + ", shopNo=" + this.getShopNo() + ", shopCallbackUrl=" + this.getShopCallbackUrl() + ", returnUrl=" + this.getReturnUrl() + ")";
    }
}
