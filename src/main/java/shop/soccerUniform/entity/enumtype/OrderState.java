package shop.soccerUniform.entity.enumtype;

public enum OrderState {
    //배송준비중
    READY,

    //주문취소
    CANCEL,

    //배송중
    DELIVERING,

    //배송완료
    ARRIVE,

    //주문 반품
    RETURN,

    //구매확정
    FINISH
}
