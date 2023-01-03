package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy;
    OrderServiceImpl orderService;

    @Test
    @DisplayName("VIP고객은 10프로 할인이 적용되어야 함")
    void vip_o() {
        //given
        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.orderService();
        Long memberId = 1L;
        int price = 10000;
        Member member = new Member(memberId, "member", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("일반고객은 할인 적용 x")
    void vip_x() {
        //given
        Long memberId = 1L;
        int price = 10000;
        Member member = new Member(memberId, "member", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }


}