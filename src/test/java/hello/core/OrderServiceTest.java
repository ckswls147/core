package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {


    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @Test
    void order() {
        //given
        Member member = new Member(1L, "yun", Grade.VIP);
        memberService.join(member); //  VIP 멤버 조인시키고
        //when
        Order order = orderService.createOrder(1L, "itemA", 10000); // createOrder 해서
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // vip멤버의 discount가 1000이 맞는지 assertion
    }
}
