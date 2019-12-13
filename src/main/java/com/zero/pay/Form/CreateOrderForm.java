package com.zero.pay.Form;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CreateOrderForm
{

  @NotBlank(message="商家ID不能为空")
//  @JsonProperty("shop_id")
  private String shopAccountId = "";

//  @JsonProperty("user_id")
  private String shopUserId;

  @NotBlank(message="商订单金额不能为空")
//  @JsonProperty("money")
  private String amountInString = "";

  @NotBlank(message="支付类型不能为空")
//  @JsonProperty("type")
  private String payChannel = "";

  @NotBlank(message="sign不能为空")
//  @JsonProperty("sign")
  private String sign = "";

  @Length(max=40, message="商家订单号，长度不能超过40")
//  @JsonProperty("shop_no")
  private String shopNo = "";

//  @JsonProperty("notify_url")
  private String shopCallbackUrl = "";

//  @JsonProperty("return_url")
  private String returnUrl = "";


  public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof CreateOrderForm)) return false; CreateOrderForm other = (CreateOrderForm)o; if (!other.canEqual(this)) return false; Object this$shopAccountId = getShopAccountId(); Object other$shopAccountId = other.getShopAccountId(); if (this$shopAccountId == null ? other$shopAccountId != null : !this$shopAccountId.equals(other$shopAccountId)) return false; Object this$shopUserId = getShopUserId(); Object other$shopUserId = other.getShopUserId(); if (this$shopUserId == null ? other$shopUserId != null : !this$shopUserId.equals(other$shopUserId)) return false; Object this$amountInString = getAmountInString(); Object other$amountInString = other.getAmountInString(); if (this$amountInString == null ? other$amountInString != null : !this$amountInString.equals(other$amountInString)) return false; Object this$payChannel = getPayChannel(); Object other$payChannel = other.getPayChannel(); if (this$payChannel == null ? other$payChannel != null : !this$payChannel.equals(other$payChannel)) return false; Object this$sign = getSign(); Object other$sign = other.getSign(); if (this$sign == null ? other$sign != null : !this$sign.equals(other$sign)) return false; Object this$shopNo = getShopNo(); Object other$shopNo = other.getShopNo(); if (this$shopNo == null ? other$shopNo != null : !this$shopNo.equals(other$shopNo)) return false; Object this$shopCallbackUrl = getShopCallbackUrl(); Object other$shopCallbackUrl = other.getShopCallbackUrl(); if (this$shopCallbackUrl == null ? other$shopCallbackUrl != null : !this$shopCallbackUrl.equals(other$shopCallbackUrl)) return false; Object this$returnUrl = getReturnUrl(); Object other$returnUrl = other.getReturnUrl(); return this$returnUrl == null ? other$returnUrl == null : this$returnUrl.equals(other$returnUrl); } 
  protected boolean canEqual(Object other) { return other instanceof CreateOrderForm; } 
  public int hashCode() { int PRIME = 59; int result = 1; Object $shopAccountId = getShopAccountId(); result = result * 59 + ($shopAccountId == null ? 43 : $shopAccountId.hashCode()); Object $shopUserId = getShopUserId(); result = result * 59 + ($shopUserId == null ? 43 : $shopUserId.hashCode()); Object $amountInString = getAmountInString(); result = result * 59 + ($amountInString == null ? 43 : $amountInString.hashCode()); Object $payChannel = getPayChannel(); result = result * 59 + ($payChannel == null ? 43 : $payChannel.hashCode()); Object $sign = getSign(); result = result * 59 + ($sign == null ? 43 : $sign.hashCode()); Object $shopNo = getShopNo(); result = result * 59 + ($shopNo == null ? 43 : $shopNo.hashCode()); Object $shopCallbackUrl = getShopCallbackUrl(); result = result * 59 + ($shopCallbackUrl == null ? 43 : $shopCallbackUrl.hashCode()); Object $returnUrl = getReturnUrl(); result = result * 59 + ($returnUrl == null ? 43 : $returnUrl.hashCode()); return result; } 
  public String toString() { return "CreateOrderForm(shopAccountId=" + getShopAccountId() + ", shopUserId=" + getShopUserId() + ", amountInString=" + getAmountInString() + ", payChannel=" + getPayChannel() + ", sign=" + getSign() + ", shopNo=" + getShopNo() + ", shopCallbackUrl=" + getShopCallbackUrl() + ", returnUrl=" + getReturnUrl() + ")"; }

}