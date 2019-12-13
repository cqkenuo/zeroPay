// 
// Decompiled by Procyon v0.5.30
// 

package com.zero.pay.Form;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderLimit
{
    private BigDecimal min;
    private BigDecimal max;

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OrderLimit)) {
            return false;
        }
        final OrderLimit other = (OrderLimit)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$min = this.getMin();
        final Object other$min = other.getMin();
        Label_0065: {
            if (this$min == null) {
                if (other$min == null) {
                    break Label_0065;
                }
            }
            else if (this$min.equals(other$min)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$max = this.getMax();
        final Object other$max = other.getMax();
        if (this$max == null) {
            if (other$max == null) {
                return true;
            }
        }
        else if (this$max.equals(other$max)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof OrderLimit;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $min = this.getMin();
        result = result * 59 + (($min == null) ? 43 : $min.hashCode());
        final Object $max = this.getMax();
        result = result * 59 + (($max == null) ? 43 : $max.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "OrderLimit(min=" + this.getMin() + ", max=" + this.getMax() + ")";
    }
}
