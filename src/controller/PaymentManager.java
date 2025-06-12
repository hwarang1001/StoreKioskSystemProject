package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.PaymentVO;

public class PaymentManager {
	// 결제목록 출력
	public void list() throws Exception {
		PaymentDAO pd = new PaymentDAO();
		System.out.println("결제 전체 리스트");
		ArrayList<PaymentVO> paymentList = pd.selectAll();
		if (paymentList.size() <= 0) {
			System.out.println("결제리스트에 데이터가 없습니다.");
			return;
		} else if (paymentList == null) {
			System.out.println("결제리스트에 에러가 발생했습니다.");
			return;
		}
		for (PaymentVO data : paymentList) {
			System.out.println(data.toString());
		}
		System.out.println();
	}
}
